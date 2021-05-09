package correcter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileAnalyzer {

    private final byte[] bytes;

    public FileAnalyzer(File file) {
        System.out.printf("%s:\n", file.getName());
        byte[] fileData1;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            fileData1 = bis.readAllBytes();
        } catch (IOException e) {
            fileData1 = null;
            e.printStackTrace();
        }
        bytes = fileData1;
    }

    public void viewText() {
        System.out.printf("text view: %s\n", new String(bytes));
    }

    public void viewHex() {
        System.out.println("hex view: " + byteArrayToHex());
    }

    public void viewBin() {
        System.out.println("bin view: " + byteArrayToBin());
    }

    public int getByteLength() {
        return bytes.length;
    }

    public byte getByteByIndex(int index) {
        return bytes[index];
    }

    public String byteArrayToBin() {
        StringBuilder binString = new StringBuilder();
        for (byte aByte : bytes) {
            String binary = Integer.toBinaryString(Byte.toUnsignedInt(aByte));
            String formattedBinary = String.format("%8S", binary).replaceAll("\\s", "0");
            binString.append(formattedBinary).append(" ");
        }
        return binString.toString().replaceAll("\\s$", "");
    }

    private String byteArrayToHex() {
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = String.format("%02x", aByte);
            hexString.append(hex).append(" ");
        }
        return hexString.toString().replaceAll("\\s$", "");
    }
}
