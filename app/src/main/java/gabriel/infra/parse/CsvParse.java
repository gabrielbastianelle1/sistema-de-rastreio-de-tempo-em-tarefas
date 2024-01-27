package gabriel.infra.parse;

import java.io.File;
import java.util.Collection;

public interface CsvParse<T> extends Parse {
    public abstract Collection<T> parseFile(File file, String delimiter);

    public abstract Collection<T> parseFile(String fileName, String delimiter);

    public abstract T parseLine(String csvLine, String delimiter);
}
