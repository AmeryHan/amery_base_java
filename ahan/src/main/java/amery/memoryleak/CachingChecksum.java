package amery.memoryleak;

import java.lang.ref.SoftReference;

public class CachingChecksum {

    private SoftReference<byte[]> bufferRef;

    public synchronized int getFileChecksum(String fileName) {
        /*int len = getFileSize(fileName);
        byte[] byteArray = bufferRef.get();
        if (byteArray == null || byteArray.length < len) {
            byteArray = new byte[len];
            bufferRef.set(byteArray);
        }
        readFileContents(fileName, byteArray);
        // calculate checksum and return it
*/
        return 0;
    }
}
