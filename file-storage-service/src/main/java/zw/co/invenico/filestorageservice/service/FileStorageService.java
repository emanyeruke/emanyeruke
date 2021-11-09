package zw.co.invenico.filestorageservice.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import zw.co.invenico.filestorageservice.dto.UploadFileResponse;
import zw.co.invenico.filestorageservice.exception.FileNotFoundException;
import zw.co.invenico.filestorageservice.exception.FileStorageException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

/**
 * @author Sheldon
 * @created 28/06/2020 - 2:13 PM
 */

@Slf4j
@Component
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.");
        }
    }

    public UploadFileResponse saveFile(MultipartFile file) {

        val fileName = storeFile(file);
        return new UploadFileResponse(fileName);
    }

    private String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            validateFile(fileName);
            fileName = generateUniqueFileName(fileName);
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            log.info("Target location {}", targetLocation);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    Set<String> storeMultiple(Set<MultipartFile> files) {
        return files.stream()
                .filter(file -> file != null && file.getSize() != 0)
                .map(this::storeFile)
                .collect(toSet());
    }

    private void validateFile(String fileName) {
        if (fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        log.info("### file name {}", fileName);
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

    private String generateUniqueFileName(String fileName) {
        String fileExtension = "";
        try {
            fileExtension = fileName.substring(fileName.lastIndexOf("."));
        } catch (Exception ignored) {

        }
        return UUID.randomUUID().toString() + fileExtension;
    }

    public UploadFileResponse updateFile(MultipartFile file, String fileName) {
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return new UploadFileResponse(fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

}
