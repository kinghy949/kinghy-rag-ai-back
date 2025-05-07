package com.kinghy.rag.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class SearchUtils {
    // 搜索引擎
    private String baseUrl = "https://api.tavily.com/search";
    private String apiKey;
    @Value("${kinghy.websearch.api-key}")
    private void readApiKey(String key) {
        this.apiKey = key;
    }
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public SearchUtils() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public List<Map<String, String>> tavilySearch(String query) {
        List<Map<String, String>> results = new ArrayList<>();
        try {
            Map<String,String> requestBody = new HashMap<String, String>();
            requestBody.put("query", query);
            Request request = new Request.Builder()
                    .url(baseUrl)
                    .post(RequestBody.create(MediaType.parse("application/json"), objectMapper.writeValueAsString(requestBody)))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer" + apiKey)
                    .build();


            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("请求失败: " + response);

                JsonNode jsonNode = objectMapper.readTree(response.body().string()).get("results");

                if (!jsonNode.isEmpty()) {
                    jsonNode.forEach(data -> {
                        Map<String, String> processedResult = new HashMap<>();
                        processedResult.put("title", data.get("title").toString());
                        processedResult.put("url", data.get("url").toString());
                        processedResult.put("content", data.get("content").toString());
                        results.add(processedResult);
                    });
                }
            }
        } catch (Exception e) {
            System.err.println("搜索时发生错误: " + e.getMessage());
        }
        return results;
    }
}
