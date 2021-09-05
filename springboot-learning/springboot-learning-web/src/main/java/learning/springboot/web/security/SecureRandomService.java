package learning.springboot.web.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SecureRandomService implements ISecureRandomService {

    public static final Logger logger = LoggerFactory.getLogger(SecureRandomService.class);
    private static SecureRandom random;

    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            logger.error("error when init secure random ", e);
        }
    }

    @Override
    public byte[] secureRandom(int len) {
        byte[] bytes = new byte[len];
        random.nextBytes(bytes);
        return bytes;
    }
}
