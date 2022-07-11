package dev.djawadi.services.filevalidator.validator.validators;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UniqueReferenceValidatorTest {
    @Autowired
    private UniqueReferenceValidator uniqueReferenceValidator;

    @Test
    public void shouldValidateOneTransaction() {
        Transaction transaction = new Transaction();
        transaction.setReference(123456L);
        assertThat(uniqueReferenceValidator.isValid(transaction)).isTrue();
    }

    @Test
    public void shouldThrowExceptionWhenNotUniqueReferenceSeen() {
        Transaction transaction = new Transaction();
        transaction.setReference(123456L);

        assertThat(uniqueReferenceValidator.isValid(transaction)).isTrue();
        assertThat(uniqueReferenceValidator.isValid(transaction)).isFalse();
    }

    @Test
    public void shouldReturnSemanticErrorMessage() {
        assertThat(uniqueReferenceValidator.getErrorMessage()).containsIgnoringCase("duplicate");
    }

}