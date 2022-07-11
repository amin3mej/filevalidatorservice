package dev.djawadi.services.filevalidator.loader.readers;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import dev.djawadi.services.filevalidator.shared.utilities.exceptions.FileNotReadableException;

import java.io.InputStream;
import java.util.List;

public interface Reader {
    ReaderSupportedExtension getSupportedExtension();
    List<Transaction> read(InputStream content) throws FileNotReadableException;
}
