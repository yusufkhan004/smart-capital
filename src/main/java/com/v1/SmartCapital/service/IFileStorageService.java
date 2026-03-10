package com.v1.SmartCapital.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
    void storeFileAsync(MultipartFile file, String filePath);
}
