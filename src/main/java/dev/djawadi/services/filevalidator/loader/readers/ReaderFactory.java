package dev.djawadi.services.filevalidator.loader.readers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ReaderFactory {
    private static final Logger logger = LogManager.getLogger(ReaderFactory.class);
    private static Map<ReaderSupportedExtension, Reader> readers;

    @Autowired
    private ReaderFactory(List<Reader> readers) {
        ReaderFactory.readers = readers.stream().collect(Collectors.toUnmodifiableMap(Reader::getSupportedExtension, Function.identity()));
        Arrays.stream(ReaderSupportedExtension.values()).forEach(extension -> {
            if (!ReaderFactory.readers.containsKey(extension))
                logger.warn("ReaderSupportedExtension {} does not have a reader.", extension);
        });
    }

    public static Reader getViewer(ReaderSupportedExtension readerSupportedExtension) {
        return Optional.ofNullable(readers.get(readerSupportedExtension)).orElseThrow(IllegalArgumentException::new);
    }
}
