package zw.co.invenico.filestorageservice.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zw.co.invenico.filestorageservice.dto.UploadFileResponse;
import zw.co.invenico.filestorageservice.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Sheldon
 * @created 28/06/2020 - 10:44 PM
 */


@Slf4j
@RestController
@RequestMapping(value = "/files", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileStorageRestController {

    private final FileStorageService fileStorageService;

    public FileStorageRestController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        log.info("### file name {}", fileName);
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = getContentType(request, resource);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/upload")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return fileStorageService.saveFile(file);
    }

    @PostMapping("/update")
    public UploadFileResponse updateFile(@RequestParam("file") MultipartFile file, @RequestParam String fileName) {
        return fileStorageService.updateFile(file, fileName);
    }


    private String getContentType(HttpServletRequest request, Resource resource) {
        String contentType = null;
        try {
            contentType = Optional.ofNullable(request.getServletContext().getMimeType(resource.getFile().getAbsolutePath()))
                    .orElse("application/octet-stream");
        } catch (IOException ex) {
            log.info("### Could not determine file type.");
        }
        return contentType;
    }
}
