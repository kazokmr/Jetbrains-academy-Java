package correcter;

public class FileEncoder extends FileCorrecter {

    public FileEncoder() {
        super(SEND_FILE_NAME, ENCODED_FILE_NAME, "encode");
        inputFileAnalyzer.viewText();
        inputFileAnalyzer.viewHex();
        inputFileAnalyzer.viewBin();
        System.out.println();
    }

    @Override
    public void run() {

        String binaryString = inputFileAnalyzer.byteArrayToBin().replaceAll("\\s", "");
        StringBuilder expandView = new StringBuilder("expand: ");
        for (int i = 0; i < binaryString.length(); i += 4) {
            int bit1 = Character.getNumericValue(binaryString.charAt(i));
            int bit2 = Character.getNumericValue(binaryString.charAt(i + 1));
            int bit3 = Character.getNumericValue(binaryString.charAt(i + 2));
            int bit4 = Character.getNumericValue(binaryString.charAt(i + 3));
            expandView
                    .append(".")
                    .append(".")
                    .append(bit1)
                    .append(".")
                    .append(bit2)
                    .append(bit3)
                    .append(bit4)
                    .append(" ");
            byte encodedByte = addParityBitByHammingCode(bit1, bit2, bit3, bit4);
            dataList.add(encodedByte);
        }
        outputToFile();
        System.out.println(expandView.toString().replaceAll("\\s$", ""));
        outStringOfBinary("parity");
        outStringOfHex();
        System.out.println();
    }

    private byte addParityBitByHammingCode(int bit1, int bit2, int bit3, int bit4) {
        int parityBit1 = bit1 ^ bit2 ^ bit4;
        int parityBit2 = bit1 ^ bit3 ^ bit4;
        int parityBit3 = bit2 ^ bit3 ^ bit4;
        return (byte) (parityBit1 << 7 | parityBit2 << 6 | bit1 << 5 | parityBit3 << 4 | bit2 << 3 | bit3 << 2 | bit4 << 1);
    }
}
