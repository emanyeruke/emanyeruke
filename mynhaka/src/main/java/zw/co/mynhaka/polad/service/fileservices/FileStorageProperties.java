package zw.co.mynhaka.polad.service.fileservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class FileStorageProperties {

    @Value("upload-dir")
    private String uploadDir;

    @Value("templates-dir")
    private String templatesDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public String getTemplatesDir() {
        return templatesDir;
    }
}