package contacts;

public class Main {

    public static void main(String[] args) {
        String fileName = args.length == 0 ? "" : args[0];
        PhoneBookInvoker invoker = new PhoneBookInvoker(fileName);
        do {
            invoker.openMenu();
        } while (invoker.isActive());
    }
}










