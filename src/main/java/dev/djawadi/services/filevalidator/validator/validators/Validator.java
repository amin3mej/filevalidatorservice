package dev.djawadi.services.filevalidator.validator.validators;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;

public interface Validator {
    boolean isValid(Transaction transaction);

    String getErrorMessage();
}
