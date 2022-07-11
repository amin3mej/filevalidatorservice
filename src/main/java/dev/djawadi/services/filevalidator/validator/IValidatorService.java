package dev.djawadi.services.filevalidator.validator;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import dev.djawadi.services.filevalidator.validator.models.ValidateTransactionResponse;

import java.util.List;

public interface IValidatorService {
    ValidateTransactionResponse validateTransactions(List<Transaction> transactions);
}
