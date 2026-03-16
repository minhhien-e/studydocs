package com.studydocs.media.core.exception;

import com.studydocs.media.core.exception.enums.ErrorCode;

import java.util.Map;

public class VirusDetectedException extends BaseException {
    public VirusDetectedException(String filename) {
        super(ErrorCode.VIRUS_DETECTED, "Virus detected in file: " + filename, Map.of("filename", filename));
    }
}
