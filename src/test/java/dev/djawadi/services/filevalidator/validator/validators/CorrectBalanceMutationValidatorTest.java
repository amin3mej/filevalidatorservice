package dev.djawadi.services.filevalidator.validator.validators;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CorrectBalanceMutationValidatorTest {
    @Autowired
    private CorrectBalanceMutationValidator validator;

    @Test
    public void shouldValidateOneTransaction() {
        Transaction transaction = new Transaction();
        transaction.setStartBalance(BigDecimal.valueOf(1));
        transaction.setMutation(BigDecimal.valueOf(2));
        transaction.setEndBalance(BigDecimal.valueOf(3));

        assertThat(validator.isValid(transaction)).isTrue();
    }

    @Test
    public void shouldThrowExceptionWhenNotUniqueReferenceSeen() {
        Transaction transaction = new Transaction();
        transaction.setStartBalance(BigDecimal.valueOf(1));
        transaction.setMutation(BigDecimal.valueOf(2));
        transaction.setEndBalance(BigDecimal.valueOf(1));

        assertThat(validator.isValid(transaction)).isFalse();
    }

    @Test
    public void shouldReturnSemanticErrorMessage() {
        assertThat(validator.getErrorMessage()).containsIgnoringCase("mutation").containsIgnoringCase("balance");
    }
}