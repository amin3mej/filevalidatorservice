package dev.djawadi.services.filevalidator.validator.validators;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UniqueReferenceValidator implements Validator {
    private final Set<Long> seenReferences;

    public UniqueReferenceValidator() {
        this.seenReferences = new HashSet<>();
    }

    @Override
    public boolean isValid(Transaction transaction) {
        Long reference = transaction.getReference();
        if (seenReferences.contains(reference))
            return false;

        seenReferences.add(reference);
        return true;
    }

    @Override
    public String getErrorMessage() {
        return "The reference is duplicate!";
    }
}
