package alanensina.mfm.exceptions.cashControl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error to save a cash control.")
public class CashControlSaveException extends RuntimeException{

    public CashControlSaveException(String message) {
        super(message);
    }
}
