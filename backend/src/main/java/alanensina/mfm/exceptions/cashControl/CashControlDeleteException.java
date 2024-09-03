package alanensina.mfm.exceptions.cashControl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error to delete a cash control.")
public class CashControlDeleteException extends RuntimeException{

    public CashControlDeleteException(String message) {
        super(message);
    }
}
