package correcter;

public class FileDecoder extends FileCorrecter {

    public FileDecoder() {
        super(RECEIVED_FILE_NAME, DECODED_FILE_NAME, "decode");
        inputFileAnalyzer.viewHex();
        inputFileAnalyzer.viewBin();
        System.out.println();
    }

    @Override
    void run() {
        String[] inputData = inputFileAnalyzer.byteArrayToBin().split("\\s+");
        StringBuilder correctData = new StringBuilder();

        StringBuilder correctedByteString = new StringBuilder("correct: ");
        for (String data : inputData) {
            String correctStr = correct(data);
            correctedByteString.append(correctStr).append(" ");
            correctData
                    .append(correctStr.charAt(2))
                    .append(correctStr.charAt(4))
                    .append(correctStr.charAt(5))
                    .append(correctStr.charAt(6));
        }

        for (int i = 0; i < correctData.length(); i += 8) {
            String binary = correctData.substring(i, i + 8);
            dataList.add(binaryToByte(binary));
        }

        outputToFile();
        System.out.println(correctedByteString.toString().replaceAll("\\s$", ""));
        outStringOfBinary("decode");
        outStringOfHex();
        System.out.println("text view: " + new String(getDataArray()));
    }

    private byte binaryToByte(String binary) {
        byte data = 0;
        for (int i = 0; i < 8; i++) {
            int bit = Integer.parseInt(binary.substring(i, i + 1));
            data += (byte) (bit << (7 - i));
        }
        return data;
    }

    private String correct(String data) {

        int parityBit1 = Character.getNumericValue(data.charAt(0));
        int parityBit2 = Character.getNumericValue(data.charAt(1));
        int bit3 = Character.getNumericValue(data.charAt(2));
        int parityBit4 = Character.getNumericValue(data.charAt(3));
        int bit5 = Character.getNumericValue(data.charAt(4));
        int bit6 = Character.getNumericValue(data.charAt(5));
        int bit7 = Character.getNumericValue(data.charAt(6));

        int chkParity1 = bit3 ^ bit5 ^ bit7;
        int chkParity2 = bit3 ^ bit6 ^ bit7;
        int chkParity4 = bit5 ^ bit6 ^ bit7;

        int numOfError = 0;
        if (parityBit1 != chkParity1) {
            numOfError += 1;
        }
        if (parityBit2 != chkParity2) {
            numOfError += 2;
        }
        if (parityBit4 != chkParity4) {
            numOfError += 4;
        }

        switch (numOfError) {
            case 3:
                bit3 = bit3 ^ 1;
                break;
            case 5:
                bit5 = bit5 ^ 1;
                break;
            case 6:
                bit6 = bit6 ^ 1;
                break;
            case 7:
                bit7 = bit7 ^ 1;
                break;
            default:
                break;
        }

        int correctData = parityBit1 << 7 | parityBit2 << 6 | bit3 << 5 | parityBit4 << 4 | bit5 << 3 | bit6 << 2 | bit7 << 1;
        return String.format("%8s", Integer.toBinaryString(correctData)).replaceAll("\\s", "0");
    }
}
