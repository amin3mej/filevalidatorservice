package dev.djawadi.services.filevalidator.loader;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;

import java.util.List;

public interface IFileLoaderService {
    List<Transaction> loadByFilename(String filename);
}
