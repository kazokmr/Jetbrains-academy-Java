package correcter;

import java.util.Random;

public class FileSender extends FileCorrecter {

    public FileSender() {
        super(ENCODED_FILE_NAME, RECEIVED_FILE_NAME, "send");
        inputFileAnalyzer.viewHex();
        inputFileAnalyzer.viewBin();
        System.out.println();
    }

    @Override
    void run() {
        for (int i = 0; i < inputFileAnalyzer.getByteLength(); i++) {
            dataList.add(modifyErrorBit(inputFileAnalyzer.getByteByIndex(i)));
        }
        outputToFile();
        outStringOfBinary("bin view");
        outStringOfHex();
        System.out.println();
    }

    private byte modifyErrorBit(byte byteData) {
        int numOfShift = new Random().nextInt(8);
        return (byte) (byteData ^ 1 << numOfShift);
    }
}
