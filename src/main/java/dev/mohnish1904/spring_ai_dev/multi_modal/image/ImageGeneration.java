package dev.mohnish1904.spring_ai_dev.multi_modal.image;

import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ImageGeneration {

    private final OpenAiImageModel openAiImageModel;

    public ImageGeneration(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    @GetMapping("/generate-image")
    public ResponseEntity<Map<String, String>> generateImage(
            @RequestParam(defaultValue = "A beautiful lake in vikings setting") String prompt) {

        // check resources
        ImageOptions options = OpenAiImageOptions.builder()
                .model("dall-e-3")
                .width(1024)
                .height(1024)
                .style("vivid")
                .quality("hd")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(prompt, options);
        ImageResponse imageResponse = openAiImageModel.call(imagePrompt);

        String imageUrl = imageResponse.getResult().getOutput().getUrl();

        return ResponseEntity.ok(Map.of(
                 "prompt", prompt,
                "imageUrl", imageUrl
        ));
    }
}
