package alanensina.mfm.exceptions.investment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Investment not found")
public class InvestmentNotFoundException extends RuntimeException{

    public InvestmentNotFoundException(String message) {
        super(message);
    }
}
