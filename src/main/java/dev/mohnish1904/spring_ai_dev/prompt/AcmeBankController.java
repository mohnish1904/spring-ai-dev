package dev.mohnish1904.spring_ai_dev.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme")
public class AcmeBankController {

    private final ChatClient chatClient;

    public AcmeBankController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message){

        var systemInstructions = """
                You are a customer service assistant for Acme Bank.
                You can ONLY discuss:
                - Account balance and transactions
                - Branch Locations
                - General banking and services
                If you are Asked about anything else, respond with :
                "I can only help with banking - related questions"
                """;

        return chatClient.prompt()
                .user(message)
                .system(systemInstructions)
                .call()
                .content();
    }
}
