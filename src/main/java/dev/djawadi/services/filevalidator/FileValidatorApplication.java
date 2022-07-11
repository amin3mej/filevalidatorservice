package dev.djawadi.services.filevalidator;

import dev.djawadi.services.filevalidator.loader.IFileLoaderService;
import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import dev.djawadi.services.filevalidator.validator.IValidatorService;
import dev.djawadi.services.filevalidator.validator.ValidatorService;
import dev.djawadi.services.filevalidator.validator.models.ValidateTransactionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

@SpringBootApplication
public class FileValidatorApplication implements CommandLineRunner {
	private static final Logger logger = LogManager.getLogger(FileValidatorApplication.class);
	private final IFileLoaderService fileLoaderService;
	private final IValidatorService validatorService;

	@Autowired
	public FileValidatorApplication(IFileLoaderService fileLoaderService, IValidatorService validatorService) {
		this.fileLoaderService = fileLoaderService;
		this.validatorService = validatorService;
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(FileValidatorApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}

	@Override
	public void run(String... args) {
		if (args.length == 0) {
			logger.error("No filename found. exiting...");
			return;
		}

		List<Transaction> transactions = fileLoaderService.loadByFilename(args[0]);
		ValidateTransactionResponse response = validatorService.validateTransactions(transactions);

		logger.info("ValidateTransactionResponse: {}", response);
	}
}
