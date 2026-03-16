package com.studydocs.media.core.detector;

import com.studydocs.media.core.model.enums.MediaType;

public interface MediaTypeDetector {
    MediaType detect(String contentType, String fileName);
}
