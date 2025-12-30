package com.domain.service;

import com.error.exception.HttpExeption;
import com.infrastructure.restemplate.RemoteApiCaller;
import com.interfaces.model.ApiResponse;
import com.interfaces.model.UpdateAvatarResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
@Component
@RequiredArgsConstructor
public class ImageServiceClient {

    private final RemoteApiCaller remoteApiCaller;

    @Value("${upload-file.service.url}")
    private String uploadFileUrl;  // Lấy URL từ YAML

    public String uploadImage(MultipartFile file) {
        try {
            Resource fileResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", fileResource);


            ApiResponse<UpdateAvatarResponse> response = remoteApiCaller.post(uploadFileUrl,body,MediaType.MULTIPART_FORM_DATA,new ParameterizedTypeReference<ApiResponse<UpdateAvatarResponse>>() {});
            if(response.errorCode()!=null){throw new HttpExeption(response.statusCode(),response.errorCode());
            }
            return response.data().getId().toString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage());
        }
    }
}
