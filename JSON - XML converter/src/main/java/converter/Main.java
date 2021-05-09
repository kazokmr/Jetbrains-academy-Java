package converter;

import converter.impl.JsonToXmlConverter;
import converter.impl.XmlToJsonConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String document = readFile();
        convert(document);
    }

    private static void convert(String document) {
        Converter converter;
        if (Converter.isInputDataXml(document)) {
            converter = new XmlToJsonConverter(document);
        } else {
            converter = new JsonToXmlConverter(document);
        }
        System.out.println(converter.output());
    }

    private static String readFile() {
        File file = new File("test.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder inputData = new StringBuilder();
            String inputLine = reader.readLine();
            while (inputLine != null) {
                inputData.append(inputLine.trim());
                inputLine = reader.readLine();
            }
            return inputData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}