package com.eepl.lab_back.controller;

import com.eepl.lab_back.service.implement.AwsS3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Tag(name = "File", description = "AWS S3 API")
public class AmazonS3Controller {


    private final AwsS3Service awsS3Service;

    @PostMapping
    @Operation(summary = "사진 업로드", description = "AWS S3에 사진을 업로드 하고 이미지 URL을 번환하는 API")
    public ResponseEntity<List<String>> uploadFile(List<MultipartFile> multipartFiles){
        return ResponseEntity.ok((awsS3Service.uploadFile(multipartFiles)));
    }
    @DeleteMapping
    @Operation(summary = "사진 삭제", description = "AWS S3에서 사진을 삭제하는 API")
    public ResponseEntity<String> deleteFile(@RequestParam String fileName){
        awsS3Service.deleteFile(fileName);
        return ResponseEntity.ok(fileName);
    }
}
