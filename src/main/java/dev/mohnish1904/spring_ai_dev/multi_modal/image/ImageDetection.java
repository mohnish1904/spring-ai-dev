package dev.mohnish1904.spring_ai_dev.multi_modal.image;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageDetection {

    @Value("classpath:/static/pexels-souvenirpixels-414612.jpg")
    Resource sampleImage;

    public final ChatClient chatClient;

    public ImageDetection(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/image-to-text")
    public String imageToText(){
        return chatClient.prompt()
                .user(u -> {
                    u.text("Can you describe what you see in the following image in 15 words.");
                    u.media(MimeTypeUtils.IMAGE_JPEG, sampleImage);
                })
                .call()
                .content();
    }
}
