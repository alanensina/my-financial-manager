package alanensina.mfm.exceptions.investment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error to delete an investment.")
public class InvestmentDeleteException extends RuntimeException{

    public InvestmentDeleteException(String message) {
        super(message);
    }
}
