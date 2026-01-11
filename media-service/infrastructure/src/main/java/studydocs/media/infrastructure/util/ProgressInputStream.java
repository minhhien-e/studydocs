package studydocs.media.infrastructure.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

public class ProgressInputStream extends FilterInputStream {
    private final long totalBytes;
    private final Consumer<Integer> progressCallback;
    private long bytesRead = 0;
    private int lastPercent = 0;

    public ProgressInputStream(InputStream in, long totalBytes, Consumer<Integer> progressCallback) {
        super(in);
        this.totalBytes = totalBytes;
        this.progressCallback = progressCallback;
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        if (b != -1) {
            updateProgress(1);
        }
        return b;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int n = super.read(b, off, len);
        if (n != -1) {
            updateProgress(n);
        }
        return n;
    }

    private void updateProgress(long n) {
        bytesRead += n;
        if (totalBytes > 0) {
            int percent = (int) ((bytesRead * 100) / totalBytes);
            if (percent > lastPercent) {
                lastPercent = percent;
                if (progressCallback != null) {
                    progressCallback.accept(percent);
                }
            }
        }
    }
}
