package com.kinghy.rag.model.dto;


import com.kinghy.rag.common.ApplicationConstant;

public record AddApiDTO(String baseUrl, String apiKey, String describe) {
    /**
     *
     * @param baseUrl default value: https://api.openai.com
     * @param apiKey required
     * @param describe optional
     */
    public AddApiDTO(String baseUrl, String apiKey, String describe){
        this.baseUrl = baseUrl == null ? ApplicationConstant.DEFAULT_BASE_URL : baseUrl;
        this.apiKey = apiKey;
        this.describe = describe;
    }
}
