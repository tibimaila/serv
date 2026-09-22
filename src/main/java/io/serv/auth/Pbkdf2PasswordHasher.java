package io.serv.auth;

import io.serv.config.ServProperties;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class Pbkdf2PasswordHasher implements PasswordHasher {

    private static final int SALT_LENGTH_BYTES = 16;
    private static final int HASH_WIDTH_BITS = 256;

    private final Pbkdf2PasswordEncoder encoder;

    public Pbkdf2PasswordHasher(ServProperties props) {
        this.encoder = new Pbkdf2PasswordEncoder(
                props.jwt().secret(),
                SALT_LENGTH_BYTES,
                props.security().pbkdf2Iterations(),
                HASH_WIDTH_BITS
        );

        this.encoder.setEncodeHashAsBase64(true);
    }

    @Override
    public String hash(String password) { return encoder.encode(password); }

    @Override
    public boolean matches(String password, String hashedPassword) {
        return encoder.matches(password, hashedPassword);
    }
}
