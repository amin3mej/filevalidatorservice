package dev.djawadi.services.filevalidator.loader.readers;


import com.opencsv.bean.CsvToBeanBuilder;
import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import dev.djawadi.services.filevalidator.shared.utilities.exceptions.FileNotReadableException;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class CsvReader implements Reader {
    @Override
    public ReaderSupportedExtension getSupportedExtension() {
        return ReaderSupportedExtension.CSV;
    }

    @Override
    public List<Transaction> read(InputStream content) throws FileNotReadableException {
        return new CsvToBeanBuilder<Transaction>(new InputStreamReader(content))
                .withType(Transaction.class)
                .build()
                .parse();
    }
}
