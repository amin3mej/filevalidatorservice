package dev.djawadi.services.filevalidator.validator;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import dev.djawadi.services.filevalidator.shared.entities.transaction.TransactionError;
import dev.djawadi.services.filevalidator.validator.models.ValidateTransactionResponse;
import dev.djawadi.services.filevalidator.validator.validators.Validator;
import dev.djawadi.services.filevalidator.validator.validators.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ValidatorService implements IValidatorService {
    private final ApplicationContext applicationContext;

    @Autowired
    public ValidatorService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public ValidateTransactionResponse validateTransactions(List<Transaction> transactions) {
        List<Validator> validators = getValidators();
        List<TransactionError> errors = validateTransactions(transactions, validators);
        return generateResponse(transactions.size(), errors);
    }

    private List<Validator> getValidators() {
        return applicationContext.getBean(ValidatorFactory.class).getValidators();
    }

    private List<TransactionError> validateTransactions(List<Transaction> transactions, List<Validator> validators) {
        List<TransactionError> errors = new LinkedList<>();

        for (Transaction transaction : transactions) {
            validators.parallelStream().forEach(validator -> {
                if (!validator.isValid(transaction)) {
                    TransactionError error = new TransactionError();
                    error.setReference(transaction.getReference());
                    error.setDescription(validator.getErrorMessage());

                    errors.add(error);
                }
            });
        }

        return errors;
    }

    private ValidateTransactionResponse generateResponse(int processedTransactions, List<TransactionError> errors) {
        ValidateTransactionResponse response = new ValidateTransactionResponse();
        response.setProcessedTransactions(processedTransactions);
        response.setErrors(errors);
        return response;
    }
}
