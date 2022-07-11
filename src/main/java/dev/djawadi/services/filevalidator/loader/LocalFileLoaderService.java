package dev.djawadi.services.filevalidator.loader;

import dev.djawadi.services.filevalidator.shared.entities.transaction.Transaction;
import dev.djawadi.services.filevalidator.loader.readers.ReaderFactory;
import dev.djawadi.services.filevalidator.loader.readers.ReaderSupportedExtension;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class LocalFileLoaderService implements IFileLoaderService {
    private static final Logger logger = LogManager.getLogger(LocalFileLoaderService.class);
    public static final Pattern VALID_RECORD_FILENAME_PATTERN = Pattern.compile("\\w+\\.\\w+");

    @Value("${application.recordsPath}")
    private String recordsPath;

    @Override
    public List<Transaction> loadByFilename(String filename) {
        InputStream content = findFile(filename);

        String extension = FilenameUtils.getExtension(filename);

        return readFile(extension, content);
    }

    private InputStream findFile(String filename) {
        ensureFilenameIsValid(filename);
        try {
            Resource resource = new ClassPathResource(recordsPath + "/" + filename);
            return resource.getInputStream();
        } catch (IOException e) {
            logger.debug("File not found: ", e);
            throw new IllegalArgumentException("File not found!");
        }
    }

    private void ensureFilenameIsValid(String filename) {
        java.util.regex.Matcher matcher = VALID_RECORD_FILENAME_PATTERN.matcher(filename);
        if (!matcher.matches())
            throw new IllegalArgumentException("The records' file name is invalid!");
    }


    private List<Transaction> readFile(String extensionRaw, InputStream file) {
        ReaderSupportedExtension ext;
        try {
            ext = EnumUtils.findEnumInsensitiveCase(ReaderSupportedExtension.class, extensionRaw);
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException(String.format("The extension .%s is not supported.", extensionRaw));
        }
        return ReaderFactory.getViewer(ext).read(file);
    }
}
