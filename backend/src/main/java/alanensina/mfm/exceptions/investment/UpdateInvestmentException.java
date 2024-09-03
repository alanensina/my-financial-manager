package alanensina.mfm.exceptions.investment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error to update an investment.")
public class UpdateInvestmentException extends RuntimeException{

    public UpdateInvestmentException(String message) {
        super(message);
    }
}
