package correcter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FileCorrecter {
    protected static final String SEND_FILE_NAME = "send.txt";
    protected static final String ENCODED_FILE_NAME = "encoded.txt";
    protected static final String RECEIVED_FILE_NAME = "received.txt";
    protected static final String DECODED_FILE_NAME = "decoded.txt";
    protected final FileAnalyzer inputFileAnalyzer;
    protected final List<Byte> dataList;
    private final String outputFileName;

    public FileCorrecter(String inputFileName, String outputFileName, String mode) {
        System.out.println("Write a mode: " + mode);
        this.inputFileAnalyzer = new FileAnalyzer(new File(inputFileName));
        this.dataList = new ArrayList<>();
        this.outputFileName = outputFileName;
    }

    abstract void run();

    protected void outputToFile() {
        System.out.println(outputFileName + ":");
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(outputFileName)))) {
            bos.write(getDataArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void outStringOfBinary(String title) {
        StringBuilder binaryString = new StringBuilder(title + ": ");
        for (byte data : dataList) {
            String binary = Integer.toBinaryString(Byte.toUnsignedInt(data));
            binaryString.append(String.format("%8s", binary).replaceAll("\\s", "0")).append(" ");
        }
        System.out.println(binaryString.toString().replaceAll("\\s$", ""));
    }

    protected void outStringOfHex() {
        StringBuilder hexString = new StringBuilder("hex view: ");
        for (byte data : dataList) {
            hexString.append(String.format("%02x", data)).append(" ");
        }
        System.out.println(hexString.toString().replaceAll("\\s$", ""));
    }

    protected byte[] getDataArray() {
        byte[] dataArray = new byte[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i] = dataList.get(i) == null ? 0 : dataList.get(i);
        }
        return dataArray;
    }
}
