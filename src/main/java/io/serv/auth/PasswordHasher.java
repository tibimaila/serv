package io.serv.auth;

/** Defines the contract for securely hashing passwords 
 * and verifies plaintext passwords matches with generated hashes 
 */
public interface PasswordHasher {
    
    String hash(String rawPassword);
 
    boolean matches(String password, String hashedPassword);
}
