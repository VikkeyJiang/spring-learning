package learning.springboot.web.security;

public interface IPasswordService {

    byte[] derivePassword(char[] password, byte[] salt);


    void cleanCredential(char[]... credential);

    void clearCredential(byte[]... credential);

}
