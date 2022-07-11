package dev.djawadi.services.filevalidator.loader;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import dev.djawadi.services.filevalidator.shared.utilities.exceptions.FileNotReadableException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LocalFileLoaderServiceTest {
    @Autowired
    private LocalFileLoaderService localFileLoaderService;

    @Test
    public void shouldReturnEmptyListOfTransactions() {
        List<Transaction> transactions = localFileLoaderService.loadByFilename("empty.csv");
        assertThat(transactions.size()).isEqualTo(0);

        transactions = localFileLoaderService.loadByFilename("empty.json");
        assertThat(transactions.size()).isEqualTo(0);
    }

    @Test
    public void shouldThrowExceptionWhenFilenameIsNotSafe() {
        assertThatThrownBy(() -> localFileLoaderService.loadByFilename("../another/path/file.csv"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The records' file name is invalid!");
    }

    @Test
    public void shouldThrowExceptionWhenFileExtensionIsNotSupported() {
        assertThatThrownBy(() -> localFileLoaderService.loadByFilename("unsupportedextension.txt"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The extension .txt is not supported.");
    }

    @Test
    public void shouldThrowExceptionWhenFileIsDamaged() {
        assertThrows(FileNotReadableException.class, () -> localFileLoaderService.loadByFilename("badsyntax.json"));
    }

    @Test
    public void shouldFindOneRecordInJson() {
        String filename = "onerecord.json";
        shouldFindOneRecord(filename);
    }

    @Test
    public void shouldFindOneRecordInCsv() {
        String filename = "onerecord.csv";
        shouldFindOneRecord(filename);
    }

    private void shouldFindOneRecord(String filename) {
        List<Transaction> transactions = localFileLoaderService.loadByFilename(filename);
        assertThat(transactions.size()).isEqualTo(1);

        assertThat(transactions.get(0))
                .extracting("reference", "accountNumber", "description", "startBalance", "mutation", "endBalance")
                .doesNotContainNull()
                .containsExactly(130498L, "NL69ABNA0433647324", "Book Jan Theuß", BigDecimal.valueOf(26.9), BigDecimal.valueOf(-18.78), BigDecimal.valueOf(8.12));
    }
}