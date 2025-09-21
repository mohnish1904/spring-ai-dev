package dev.mohnish1904.spring_ai_dev.tools.datetime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateTimeController {

    private final ChatClient chatClient;

    public DateTimeController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/tools")
    public String tools(){
        return chatClient.prompt()
                .user("what is tomorrow's date ?")
                .tools(new DateTimeTools())
                .call()
                .content();
    }
}

