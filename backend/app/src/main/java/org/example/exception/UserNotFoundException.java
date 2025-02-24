package org.example.exception;

public class UserNotFoundException extends RuntimeException { // 🔥 Héritage de RuntimeException
    public UserNotFoundException() {
        super("Utilisateur non trouvé");
    }
}