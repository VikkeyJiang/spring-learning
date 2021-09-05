package learning.springboot.web.security;

public interface ISecureRandomService {

    byte[] secureRandom(int len);
}
