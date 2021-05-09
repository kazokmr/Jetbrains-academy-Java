package phonebook;

public class Dictionary {
    private final Contact[] contacts;

    public Dictionary(Contact[] contacts) {
        this.contacts = contacts;
    }

    boolean linearSearch(String name) {
        for (Contact contact : contacts) {
            if (contact.isNameMatch(name)) {
                return true;
            }
        }
        return false;
    }

    boolean jumpSearch(String name) {
        if (contacts.length == 0) {
            return false;
        }
        if (contacts[0].isNameMatch(name)) {
            return true;
        }
        int jumpLength = (int) Math.sqrt(contacts.length);
        int currentRight = 0;
        int prevRight = 0;
        while (true) {
            currentRight = Math.min(currentRight + jumpLength, contacts.length - 1);
            if (contacts[currentRight].compareName(name) >= 0) {
                break;
            }
            prevRight = currentRight;
        }
        for (int index = currentRight; index > prevRight; index--) {
            if (contacts[index].isNameMatch(name)) {
                return true;
            }
        }
        return false;
    }

    boolean binarySearch(String name) {
        int left = 0;
        int right = contacts.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            Contact contact = contacts[mid];
            if (contact.isNameMatch(name)) {
                return true;
            }
            if (contact.compareName(name) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    void bubbleSort() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < contacts.length; i++) {
            for (int j = 0; j < contacts.length - i - 1; j++) {
                if (contacts[j].compareName(contacts[j + 1]) > 0) {
                    swap(contacts, j, j + 1);
                }
            }
            if (System.currentTimeMillis() - startTime > 45_000L) {
                System.out.printf("Stop Sorting %d / %d\n", i + 1, contacts.length);
                return;
            }
        }
    }

    void quickSort() {
        runQuickSort(0, contacts.length - 1);
    }

    private void runQuickSort(int left, int right) {
        if (left < right) {
            int pivotIndex = partition(left, right);
            runQuickSort(left, pivotIndex - 1);
            runQuickSort(pivotIndex + 1, right);
        }
    }

    private int partition(int left, int right) {
        Contact pivot = contacts[right];
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (contacts[i].compareName(pivot) <= 0) {
                swap(contacts, i, partitionIndex);
                partitionIndex++;
            }
        }
        swap(contacts, partitionIndex, right);
        return partitionIndex;
    }

    private void swap(Contact[] contacts, int j, int i) {
        Contact temp = contacts[i];
        contacts[i] = contacts[j];
        contacts[j] = temp;
    }
}
