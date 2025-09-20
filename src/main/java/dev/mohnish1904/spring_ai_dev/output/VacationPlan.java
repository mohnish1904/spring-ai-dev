package dev.mohnish1904.spring_ai_dev.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPlan {

    private  final ChatClient chatClient;

    public VacationPlan(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/vacation/unstructured")
    public String unstructuredResp() {
        return chatClient.prompt()
                .user("i want to plan a trip to paris for 5 days. Give me a list to do things?")
                .call()
                .content();
    }

    @GetMapping("/vacation/structured")
    public Itinerary structuredResp() {
        return chatClient.prompt()
                .user("i want to plan a trip to Kyoto for 2 days. Give me a list to do things?")
                .call()
                .entity(Itinerary.class);
    }
}
