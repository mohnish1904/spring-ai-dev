package dev.mohnish1904.spring_ai_dev.evals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @Test
    void testPositiveSentiment(){
        String positiveSentiment = "I absolutely loved the hotel. It was amazing";
        Sentiment sentiment = reviewService.classifySentiment(positiveSentiment);

        assertEquals(Sentiment.POSITIVE, sentiment, "Sentiment is classified as positive.");
    }

    @Test
    void testNegativeSentiment(){
        String negativeSentiment = "I hate the hotel experience. Not recommended";
        Sentiment sentiment = reviewService.classifySentiment(negativeSentiment);

        assertEquals(Sentiment.NEGATIVE, sentiment, "Sentiment is classified as negative.");
    }

    @Test
    void testNeutralSentiment(){
        String neutralSentiment = "The hotel stay was okay, staff were good. Good for cheap stays, Nothing amazing";
        Sentiment sentiment = reviewService.classifySentiment(neutralSentiment);

        assertEquals(Sentiment.NEUTRAL, sentiment, "Sentiment is classified as neutral.");
    }

}