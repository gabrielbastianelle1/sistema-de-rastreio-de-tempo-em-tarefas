package gabriel.infra.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

public class CsvParseImpl<T> implements CsvParse<T> {

    private Scanner scanner;

    @Override
    public Collection<T> parseFile(File file, String delimiter) {
        try {
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String object = scanner.nextLine();
            }

            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
    }

    @Override
    public Collection<T> parseFile(String fileName, String delimiter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseFile'");
    }

    @Override
    public T parseLine(String csvLine, String delimiter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseLine'");
    }

}
