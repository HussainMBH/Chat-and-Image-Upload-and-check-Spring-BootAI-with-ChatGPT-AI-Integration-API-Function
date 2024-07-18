package com.springbootAI.ChatGPTIntegration.controller;

import com.springbootAI.ChatGPTIntegration.dto.ChatMessagePrompt;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatGPTController {
    @GetMapping("/getChat/{prompt}")
    public String getPrompt(@PathVariable String prompt){
        // /v1/completion -> text

        OpenAiService service = new OpenAiService("ChatGPT Key");
        CompletionRequest completionRequest = CompletionRequest.builder().prompt(prompt).model("gpt-3.5-turbo").echo(true).build();
        return service.createCompletion(completionRequest).getChoices().get(0).getText();
    }

    @PostMapping("/chat")
    public String getChatMessages(@RequestBody ChatMessagePrompt prompt){
        // /v1/chat/completion -> text
        OpenAiService service = new OpenAiService("ChatGPT Key");
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder().messages(prompt.getChatMessage()).model("gpt-3.5-turbo").build();
        return service.createChatCompletion(completionRequest).getChoices().get(0).getMessage().getContent();
    }
}
