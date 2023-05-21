package kg.edu.alatoo.spring_web.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Slf4j
public class CoverPhotoUtil {

    private final Path UPLOAD_DIR;

    public CoverPhotoUtil(@Value("${upload.dir}") String uploadDir) throws IOException {
        log.debug("Upload directory: {}", uploadDir);
        this.UPLOAD_DIR = Path.of(uploadDir);
        if (!Files.exists(UPLOAD_DIR)) {
            Files.createDirectory(UPLOAD_DIR);
        }
        if (!Files.isDirectory(UPLOAD_DIR)) {
            throw new FileAlreadyExistsException("The path '" + UPLOAD_DIR.toAbsolutePath() + "' is not a directory");
        }
    }

    public void save(byte[] bytes, String fileName) throws IOException {
        Files.write(UPLOAD_DIR.resolve(fileName),bytes);
    }

    public byte[] get(String fileName) throws IOException {
        Path filePath = UPLOAD_DIR.resolve(fileName);
        Files.exists(filePath);
        return Files.readAllBytes(filePath);
    }
}
