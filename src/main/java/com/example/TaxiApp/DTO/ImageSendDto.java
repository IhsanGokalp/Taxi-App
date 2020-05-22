package com.example.TaxiApp.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageSendDto {
    private MultipartFile photo;
}
