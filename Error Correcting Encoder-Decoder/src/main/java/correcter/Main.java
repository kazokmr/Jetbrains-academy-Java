package correcter;

public class Main {
    public static void main(String[] args) {
        new FileEncoder().run();
        new FileSender().run();
        new FileDecoder().run();
    }
}
