package alanensina.mfm.exceptions.investment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error to save an investment.")
public class SaveInvestmentException extends RuntimeException{

    public SaveInvestmentException(String message) {
        super(message);
    }
}
