package dev.djawadi.services.filevalidator.validator.validators;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import dev.djawadi.services.filevalidator.validator.models.ValidateTransactionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidatorFactoryTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void shouldReturnNewInstanceEachTime() {
        ValidatorFactory first = applicationContext.getBean(ValidatorFactory.class);
        ValidatorFactory second = applicationContext.getBean(ValidatorFactory.class);
        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldReturnCorrectValidators() {
        List<Validator> validators = applicationContext.getBean(ValidatorFactory.class).getValidators();
        assertThat(validators).hasSize(2)
                .flatExtracting(Validator::getClass)
                .containsExactly(CorrectBalanceMutationValidator.class, UniqueReferenceValidator.class);
    }
}