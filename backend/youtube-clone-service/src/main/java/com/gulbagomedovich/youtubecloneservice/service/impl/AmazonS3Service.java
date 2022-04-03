package com.gulbagomedovich.youtubecloneservice.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.gulbagomedovich.youtubecloneservice.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmazonS3Service implements FileService {
    private final AmazonS3Client amazonS3Client;

    @Setter(onMethod_ = @Value("${youtube-clone-service.bucket-name}"))
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3Client.putObject(bucketName, key, file.getInputStream(), metadata);
        } catch (IOException e) {
            //TODO Выбросить собственное исключение
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Не удалось загрузить файл");
        }

        amazonS3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);

        return amazonS3Client.getResourceUrl(bucketName, key);
    }
}
