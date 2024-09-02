package alanensina.mfm.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error to save an user.")
public class SaveUserException extends RuntimeException{

    public SaveUserException(String message) {
        super(message);
    }
}
