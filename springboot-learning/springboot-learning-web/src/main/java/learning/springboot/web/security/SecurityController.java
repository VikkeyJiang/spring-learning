package learning.springboot.web.security;

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/security")
public class SecurityController {

    private IPasswordService passwordService;

    private ISecureRandomService secureRandomService;

    @GetMapping(path = "/provider")
    public List<Provider> printSecurityProvider() {
        return Arrays.asList(Security.getProviders());
    }

    @PostMapping(path = "/password/derive")
    public String derivePassword(char[] password) {
        byte[] salt = secureRandomService.secureRandom(32);
        byte[] derivedPassword = passwordService.derivePassword(password, salt);
        String ret = Hex.toHexString(salt) + Hex.toHexString(derivedPassword);
        passwordService.cleanCredential(password);
        passwordService.clearCredential(salt, derivedPassword);
        return ret;
    }

    @Autowired
    public void setPasswordService(IPasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Autowired
    public void setSecureRandomService(ISecureRandomService secureRandomService) {
        this.secureRandomService = secureRandomService;
    }
}
