package dev.djawadi.services.filevalidator.validator.validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ValidatorFactory {
    private final List<Validator> validators;

    @Autowired
    private ValidatorFactory(List<Validator> validators) {
        this.validators = validators;
    }

    public List<Validator> getValidators() {
        return validators;
    }
}
