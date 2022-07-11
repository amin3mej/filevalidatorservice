package dev.djawadi.services.filevalidator.loader.readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import dev.djawadi.services.filevalidator.shared.utilities.exceptions.FileNotReadableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class JsonReader implements Reader {
    private final ObjectMapper objectMapper;

    public JsonReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ReaderSupportedExtension getSupportedExtension() {
        return ReaderSupportedExtension.JSON;
    }

    @Override
    public List<Transaction> read(InputStream content) throws FileNotReadableException {
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Transaction.class);

        try {
            return objectMapper.readValue(content, collectionType);
        } catch (IOException e) {
            throw new FileNotReadableException(e);
        }
    }
}
