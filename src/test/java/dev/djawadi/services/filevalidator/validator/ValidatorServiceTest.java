package dev.djawadi.services.filevalidator.validator;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import dev.djawadi.services.filevalidator.validator.models.ValidateTransactionResponse;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidatorServiceTest {
    @Autowired
    private ValidatorService validatorService;

    @Test
    public void shouldReturnValidateTransactionResponse() {
        List<Transaction> transactions = Collections.emptyList();
        assertThat(validatorService.validateTransactions(transactions)).isInstanceOf(ValidateTransactionResponse.class);
    }

    @Test
    public void shouldReturnEmptyResponseWhenValidatingEmptyList() {
        List<Transaction> transactions = Collections.emptyList();
        ValidateTransactionResponse actual = validatorService.validateTransactions(transactions);
        assertThat(actual.getProcessedTransactions()).isEqualTo(0);
        assertThat(actual.getErrors()).isEmpty();
    }


    @Test
    public void shouldReturnListOfValidators() {
        List<Transaction> transactions = Collections.emptyList();
        ValidateTransactionResponse actual = validatorService.validateTransactions(transactions);
        assertThat(actual.getProcessedTransactions()).isEqualTo(0);
        assertThat(actual.getErrors()).isEmpty();
    }

    @Test
    public void shouldReturnSuccessResponse() {
        List<Transaction> transactions = List.of(generateTransaction(null), generateTransaction(null));
        ValidateTransactionResponse actual = validatorService.validateTransactions(transactions);
        assertThat(actual.getProcessedTransactions()).isEqualTo(2);
        assertThat(actual.getErrors()).isEmpty();
    }

    @Test
    public void shouldReturnErrorResponse() {
        long reference = 123456L;
        Transaction transaction = generateTransaction(reference);
        transaction.setEndBalance(BigDecimal.valueOf(0));

        List<Transaction> transactions = List.of(generateTransaction(reference), transaction);

        ValidateTransactionResponse actual = validatorService.validateTransactions(transactions);

        assertThat(actual.getProcessedTransactions()).isEqualTo(2);
        assertThat(actual.getErrors()).extracting("reference", "description")
                .containsExactlyInAnyOrder(
                        new Tuple(reference, "The mutation of the transaction does not match the startBalance and endBalance of the transaction."),
                        new Tuple(reference, "The reference is duplicate!"));
    }

    private Transaction generateTransaction(Long defaultReference) {
        Transaction transaction = new Transaction();
        transaction.setReference(Optional.ofNullable(defaultReference).orElseGet(() -> ThreadLocalRandom.current().nextLong(100000,999999)));
        transaction.setStartBalance(BigDecimal.valueOf(1));
        transaction.setMutation(BigDecimal.valueOf(2));
        transaction.setEndBalance(BigDecimal.valueOf(3));

        return transaction;
    }
}