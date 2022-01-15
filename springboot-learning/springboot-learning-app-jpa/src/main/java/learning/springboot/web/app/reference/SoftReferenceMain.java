package learning.springboot.web.app.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SoftReferenceMain {

    private static final Logger logger = LoggerFactory.getLogger(SoftReferenceMain.class);

    public static void main(String[] args) {
        final ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
        new Thread(() -> {
            while (true) {
                Reference<? extends byte[]> bytes = queue.poll();
                if (null != bytes) {
                    logger.info(bytes.toString());
                }
            }
        }).start();
        byte[] bytes = new byte[10];
        SoftReference<byte[]> reference = new VikkeySoftReference<>(bytes, queue, "ddd");
        bytes = null;
        try {
            byte[] bytes1 = new byte[1234000000];
        } catch (Exception e) {

        } finally {

        }
        System.out.printf("");

    }

    static class VikkeySoftReference<T> extends SoftReference<T> {

        private String refName;

        public VikkeySoftReference(T referent) {
            super(referent);
        }

        public VikkeySoftReference(T referent, ReferenceQueue q) {
            super(referent, q);
        }

        public VikkeySoftReference(T referent, ReferenceQueue q, String refName) {
            super(referent, q);
            this.refName = refName;
        }

        @Override
        public String toString() {
            return refName;
        }
    }
}
