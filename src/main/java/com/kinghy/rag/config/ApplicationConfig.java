package com.kinghy.rag.config;

import com.alibaba.cloud.ai.advisor.RetrievalRerankAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aliang
 * @date 2025/2/8
 */

@Configuration
public class ApplicationConfig {

    /**
     * ETL中的DocumentTransformer的实现，将文本数据源转换为多个分割段落
     * @return
     */
    @Bean
    public TokenTextSplitter tokenTextSplitter() {
        return new TokenTextSplitter();
    }

    @Bean
    ChatClient chatclient(ChatClient.Builder builder){
        return builder.defaultSystem("你是一个乐于助人解决问题的AI机器人")
                .build();
    }


}
