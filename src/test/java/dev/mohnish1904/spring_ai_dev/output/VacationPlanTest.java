package dev.mohnish1904.spring_ai_dev.output;

import dev.mohnish1904.spring_ai_dev.config.ChatClientTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(ChatClientTestConfig.class)
class VacationPlanTest {

    @Autowired
    private ChatClient chatClient;

    @Test
    public void testGetItinerary() {
        String destination = "Udaipur, Rajasthan";
        var result = chatClient.prompt()
                .user(u-> {
                    u.text("What's a god vacation plan while i'm in  {destination} for 3 days");
                    u.param("destination", destination);
                })
                .call()
                .entity(Itinerary.class);

        System.out.println(result);

        assertNotNull(result, "The result should not be null.");
        assertNotNull(result.itinerary(), "Itinerary should not be null.");
        assertFalse(result.itinerary().isEmpty(), "Itinerary activities should not be empty.");
    }

}