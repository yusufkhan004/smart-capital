package com.v1.SmartCapital.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UploadScreenResponse {
    List<TemplateDTO> templateDTOList = new ArrayList<>();

    List<BankDTO> bankDTOList = new ArrayList<>();
}
