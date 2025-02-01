package com.kinghy.rag.controller;

import cn.hutool.core.util.StrUtil;
import com.kinghy.rag.common.ApplicationConstant;
import com.kinghy.rag.common.ChatType;
import com.kinghy.rag.common.ErrorCode;
import com.kinghy.rag.exception.BusinessException;
import com.kinghy.rag.model.dto.ChatDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

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

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/stream")
    public Flux<String> streamRagChat(@RequestParam(value = "message", defaultValue = "你好" ) String message,
                                      @RequestParam(value = "prompt", defaultValue = "你是一名AI助手，致力于帮助人们解决问题.") String prompt){
        return chatClient.prompt()
                .system(prompt)
                .user(message)
                .stream()
                .content();
    }


}
