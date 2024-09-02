package alanensina.mfm.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error to update an user.")
public class UpdateUserException extends RuntimeException{

    public UpdateUserException(String message) {
        super(message);
    }
}
