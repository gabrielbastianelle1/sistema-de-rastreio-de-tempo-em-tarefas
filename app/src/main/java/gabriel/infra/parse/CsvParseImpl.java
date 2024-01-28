package gabriel.infra.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import gabriel.infra.reflection.ObjectFactory;

public class CsvParseImpl<T> implements CsvParse<T> {

    private Scanner scanner;
    private final ObjectFactory objectFactory;
    private Collection<T> collection;

    public CsvParseImpl(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
        this.collection = new HashSet<>();
    }

    @Override
    public Collection<T> parseFile(File file, String delimiter, Class<T> clazz) {

        try {
            scanner = new Scanner(file);
            String[] headers;

            String headerLine = scanner.nextLine();
            headers = headerLine.split(delimiter);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(delimiter);

                if (fields.length > 0) {
                    Map<String, String> map = new HashMap<>();

                    for (int i = 0; i < fields.length; i++) {
                        map.put(headers[i], fields[i]);
                    }

                    T object = objectFactory.execute(map, clazz);
                    collection.add(object);
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }

            return collection;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
    }

}
