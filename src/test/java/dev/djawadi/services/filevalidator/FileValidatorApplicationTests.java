package dev.djawadi.services.filevalidator;

import dev.djawadi.services.filevalidator.loader.IFileLoaderService;
import dev.djawadi.services.filevalidator.validator.IValidatorService;
import org.apache.logging.log4j.LogManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(OutputCaptureExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FileValidatorApplicationTests {
	@Autowired
	private IFileLoaderService localFileLoaderService;

	@Autowired
	private IValidatorService validatorService;

	@Autowired
	private FileValidatorApplication fileValidatorApplication;

	@Test
	void contextLoads() {
		assertThat(localFileLoaderService).isNotNull();
		assertThat(validatorService).isNotNull();
	}

	@Test
	public void testMain(CapturedOutput output) {
		fileValidatorApplication.run("onerecord.json");

		assertThat(output.getOut().contains("ValidateTransactionResponse: ")).isTrue();
	}
}
