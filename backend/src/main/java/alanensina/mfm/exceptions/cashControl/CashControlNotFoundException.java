package alanensina.mfm.exceptions.cashControl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cash control not found.")
public class CashControlNotFoundException extends RuntimeException{

    public CashControlNotFoundException(String message) {
        super(message);
    }
}
