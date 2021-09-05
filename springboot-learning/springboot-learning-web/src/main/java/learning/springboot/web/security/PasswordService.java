package learning.springboot.web.security;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PasswordService implements IPasswordService {

    public static final Logger logger = LoggerFactory.getLogger(PasswordService.class);


    @Override
    public byte[] derivePassword(char[] password, byte[] salt) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WITHHMACSHA256", "BC");
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, 10000, 256);
            SecretKey secretKey = keyFactory.generateSecret(keySpec);
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
            logger.error("error when derive password", e);
        }
        return null;
    }

    @Override
    public void cleanCredential(char[]... credential) {
        Arrays.stream(credential).forEach(input -> Arrays.fill(input, (char) 0x0));
    }

    @Override
    public void clearCredential(byte[]... credential) {
        Arrays.stream(credential).forEach(input -> Arrays.fill(input, (byte) 0x0));
    }


}
