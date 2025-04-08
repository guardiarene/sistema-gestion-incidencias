package seminario.practica.sgi.common.home.infrastructure.config.spring.exception;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public enum ErrorMessage {
    USER_ALREADY_EXISTS("User already exists with email: "),
    USER_NOT_FOUND("User name not found with email: "),
    INCIDENT_DOES_NOT_EXISTS("Incident does not exists.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(String object) {
        return MessageFormat.format(message, object);
    }

}
