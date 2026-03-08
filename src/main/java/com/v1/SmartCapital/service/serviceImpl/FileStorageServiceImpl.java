package com.v1.SmartCapital.service.serviceImpl;

import com.v1.SmartCapital.service.IFileStorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.v1.SmartCapital.constants.ErrorMsgConstants.FILE_UPLOAD_FAILED;

@Service
public class FileStorageServiceImpl implements IFileStorageService {

    private static final Logger logger = LogManager.getLogger(FileStorageServiceImpl.class);


    @Async
    @Override
    public void storeFileAsync(MultipartFile file, String filePath) {

        logger.info("FileStorageServiceImpl - Inside storeFileAsync method");

        try {
            Path uploadDir = Paths.get("uploads");

            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path fullPath = uploadDir.resolve(Paths.get(filePath).getFileName());
            Files.write(fullPath, file.getBytes());

        } catch (Exception e) {
            logger.error(FILE_UPLOAD_FAILED, e);
        }
    }
}
