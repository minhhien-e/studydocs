package com.domain.command;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value(staticConstructor = "commandOf")
public class UpdateImage implements UserCommand{
    String userId;
    MultipartFile image;
}