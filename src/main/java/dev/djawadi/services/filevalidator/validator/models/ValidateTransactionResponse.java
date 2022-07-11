package dev.djawadi.services.filevalidator.validator.models;

import dev.djawadi.services.filevalidator.shared.entities.transaction.TransactionError;

import java.util.List;

public class ValidateTransactionResponse {
    private Integer processedTransactions;
    private List<TransactionError> errors;

    public Integer getProcessedTransactions() {
        return processedTransactions;
    }

    public void setProcessedTransactions(Integer processedTransactions) {
        this.processedTransactions = processedTransactions;
    }

    public List<TransactionError> getErrors() {
        return errors;
    }

    public void setErrors(List<TransactionError> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ValidateTransactionResponse{" +
                "processedTransactions=" + processedTransactions +
                ", errors=" + errors +
                '}';
    }
}
