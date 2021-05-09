package correcter;

import java.util.Random;

public class ByteErrorEmulator {
    private final byte[] bytes;

    public ByteErrorEmulator(String text) {
        this.bytes = text.getBytes();
    }

    public byte[] encodeError() {
        byte[] errors = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            errors[i] = modifyErrorBit(bytes[i]);
        }
        return errors;
    }

    private byte modifyErrorBit(byte value) {
        int numOfShift = new Random().nextInt(8);
        return (byte) (value ^ 1 << numOfShift);
    }
}
