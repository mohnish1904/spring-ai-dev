package dev.mohnish1904.spring_ai_dev.rag;

import lombok.extern.log4j.Log4j2;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
@Log4j2
public class RagConfiguration {

    private final static String VECTOR_STORE_NAME = "vector-store.json";

    @Value("classpath:/data/models.json")
    private Resource models;

    @Bean
    SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel){
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        File vectorStoreFile = getVectorStoreFile();

        if (vectorStoreFile.exists()){
            log.info("Vector Store already exists at {}", vectorStoreFile.getAbsolutePath());
            simpleVectorStore.load(vectorStoreFile);
        } else {
            log.info("Creating vector store at {}", vectorStoreFile.getAbsolutePath());
            TextReader textReader = new TextReader(models);
            textReader.getCustomMetadata().put("filename", "models.txt");
            List<Document> documents = textReader.get();
            TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
            List<Document> splitDocs = tokenTextSplitter.apply(documents);

            simpleVectorStore.add(splitDocs);
            simpleVectorStore.save(vectorStoreFile);
        }
        return simpleVectorStore;
    }

    private File getVectorStoreFile(){
        Path path = Paths.get("src", "main","resources", "data");
        String absolutePath = path.toFile().getAbsolutePath() + "/" + VECTOR_STORE_NAME;
        return new File(absolutePath);
    }

}
