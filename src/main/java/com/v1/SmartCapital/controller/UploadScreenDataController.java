package com.v1.SmartCapital.controller;

import com.v1.SmartCapital.dto.UploadScreenResponse;
import com.v1.SmartCapital.service.IUploadScreenDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/upload-data")
@RequiredArgsConstructor
public class UploadScreenDataController {

    private final IUploadScreenDataService uploadScreenDataService;

    @GetMapping
    public UploadScreenResponse uploadScreen() {
        return uploadScreenDataService.prepareUploadScreenData();
    }

}
