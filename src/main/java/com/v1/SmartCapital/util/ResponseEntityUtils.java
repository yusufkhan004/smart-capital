package com.v1.SmartCapital.util;

import com.v1.SmartCapital.dto.ResponseDTO;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor
public class ResponseEntityUtils {

    public static ResponseEntity<ResponseDTO> get(Object object, String message) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(object, message));
    }
}