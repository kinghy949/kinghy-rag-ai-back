package com.kinghy.rag.controller;

import com.kinghy.rag.common.ApplicationConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @Title: ChatController
 * @Author KingHY
 * @Package com.kinghy.rag.controller
 * @Date 2025/2/1 21:03
 * @description: 对话接口
 */

@Tag(name="AiRagController",description = "chat对话接口")
@Slf4j
@RestController
@RequestMapping(ApplicationConstant.API_VERSION + "/chat")
public class ChatController {

    @Autowired
    private  ChatClient chatClient;

    @Operation(summary = "stream",description = "流式对话接口")
    @GetMapping(value = "/stream")
    public Flux<String> streamRagChat(@RequestParam(value = "message", defaultValue = "你好" ) String message,
                                      @RequestParam(value = "prompt", defaultValue = "你是一名AI助手，致力于帮助人们解决问题.") String prompt){
        return chatClient.prompt()
                .system(prompt)
                .user(message)
                .stream()
                .content();
    }



}
