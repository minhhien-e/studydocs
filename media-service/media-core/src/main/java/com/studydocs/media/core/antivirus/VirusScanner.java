package com.studydocs.media.core.antivirus;

import java.io.InputStream;

public interface VirusScanner {
    ScanResult scan(InputStream inputStream);
}
