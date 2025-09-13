package dev.mohnish1904.spring_ai_dev.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }


    //blocking call
    @GetMapping("/chat")
    public String chat(){
        return chatClient.prompt()
                .user("tell me an interesting fact about AI in 3 lines.")
                .call()
                .content();
    }

    @GetMapping("/chat-stream")
    public Flux<String> chatStream(){
         return chatClient.prompt()
                .user("i'm visiting tokyo soon. Give me 10 things i must try.")
                .stream()
                .content()
                 .log();
    }

    @GetMapping("/joke")
    public ChatResponse joke(){
        return chatClient.prompt()
                .user("tell me a programming joke.")
                .call()
                .chatResponse();
    }
}
