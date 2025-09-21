package dev.mohnish1904.spring_ai_dev.evals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.evaluation.RelevancyEvaluator;
import org.springframework.ai.document.Document;
import org.springframework.ai.evaluation.EvaluationRequest;
import org.springframework.ai.evaluation.EvaluationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RelevancyEvaluatorTest {

    RelevancyEvaluator relevancyEvaluator;

    @BeforeEach
    void setup(@Autowired ChatClient.Builder chatClientBuilder){
        relevancyEvaluator = new RelevancyEvaluator(chatClientBuilder);
    }

    @Test
    void testRelevantResponse(){
        String userQuery = "What is the capital of France";

        List<String> context = List.of(
                "France is a country in Western Europe known for its culture and cuisine",
                "The capital city of France is Paris, which is famous for landmarks like the Eiffel Tower and the Louvre Museum",
                "French is the official language spoken in France"
        );

        String llmResponse = "The capital of France in Paris";
        EvaluationRequest request = new EvaluationRequest(userQuery, contextStringToDocument(context), llmResponse);
        EvaluationResponse response = relevancyEvaluator.evaluate(request);
        Assertions.assertTrue(response.isPass(), "Response should be relevant. Feedback : {}" + response.getFeedback());
    }

    private List<Document> contextStringToDocument(List<String> context) {
        return context.stream()
                .map(Document::new)
                .toList();
    }
}
