package dev.djawadi.services.filevalidator.validator.validators;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import org.springframework.stereotype.Component;

@Component
public class CorrectBalanceMutationValidator implements Validator {
    @Override
    public boolean isValid(Transaction transaction) {
        return transaction.getMutation().equals(transaction.getEndBalance().subtract(transaction.getStartBalance()));
    }

    @Override
    public String getErrorMessage() {
        return "The mutation of the transaction does not match the startBalance and endBalance of the transaction.";
    }
}
