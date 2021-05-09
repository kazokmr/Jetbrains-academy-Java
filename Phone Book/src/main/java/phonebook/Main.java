package phonebook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String FILE_PATH = "/Users/kazokmr/Downloads/";
    private final Contact[] contacts;
    private final String[] findList;

    public Main() {
        this.contacts = createDictionary();
        this.findList = createFindList();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startSearchDictionary();
    }

    private static String searchResult(long countOfSearched, long countOfEntries, long mills) {
        return String.format("Found %d / %d entries. Time taken: %s", countOfSearched, countOfEntries, millsToString(mills));
    }

    private static String millsToString(long mills) {
        long min = TimeUnit.MILLISECONDS.toMinutes(mills);
        long sec = TimeUnit.MILLISECONDS.toSeconds(mills - min * 60000L);
        long ms = mills - min * 60000L - sec * 1000L;
        return String.format("%d min. %02d sec. %d ms.", min, sec, ms);
    }

    private static Contact[] createDictionary() {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH + "directory.txt"))) {
            String contact = reader.readLine();
            while (contact != null) {
                int indexSeparate = contact.indexOf(" ");
                String number = contact.substring(0, indexSeparate);
                String name = contact.substring(indexSeparate + 1);
                contacts.add(new Contact(number, name));
                contact = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts.toArray(Contact[]::new);
    }

    private static String[] createFindList() {
        List<String> findList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH + "find.txt"))) {
            String nameForSearch = reader.readLine();
            while (nameForSearch != null) {
                findList.add(nameForSearch);
                nameForSearch = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return findList.toArray(String[]::new);
    }

    private void startSearchDictionary() {
        long startMills;
        int countOfSearched;
        long endMills;
        long searchingMills;
        Dictionary dictionary = new Dictionary(contacts);
        System.out.println("Start searching (linear search)...");
        startMills = System.currentTimeMillis();
        countOfSearched = linearSearch(dictionary);
        endMills = System.currentTimeMillis();
        searchingMills = endMills - startMills;
        System.out.println(searchResult(countOfSearched, findList.length, searchingMills));
        System.out.println();

        dictionary = new Dictionary(Arrays.copyOf(contacts, contacts.length));
        System.out.println("Start searching (bubble sort + jump search)...");
        startMills = System.currentTimeMillis();
//        dictionary.bubbleSort();
        dictionary.quickSort();
        long stopSortMills = System.currentTimeMillis();
        long sortingTime = stopSortMills - startMills;
        String changeSearchMsg = "";
        if (sortingTime >= searchingMills * 10) {
            countOfSearched = linearSearch(dictionary);
            changeSearchMsg = " - STOPPED, moved to linear search";
        } else {
            countOfSearched = jumpSearch(dictionary);
        }
        endMills = System.currentTimeMillis();
        searchingMills = endMills - startMills;
        System.out.println(searchResult(countOfSearched, findList.length, searchingMills));
        System.out.println("Sorting time :" + millsToString(sortingTime) + changeSearchMsg);
        System.out.println("Searching time :" + millsToString(endMills - stopSortMills));
        System.out.println();

        dictionary = new Dictionary(Arrays.copyOf(contacts, contacts.length));
        System.out.println("Start searching (quick sort + binary search)...");
        startMills = System.currentTimeMillis();
        dictionary.quickSort();
        stopSortMills = System.currentTimeMillis();
        sortingTime = stopSortMills - startMills;
        countOfSearched = binarySearch(dictionary);
        endMills = System.currentTimeMillis();
        searchingMills = endMills - startMills;
        System.out.println(searchResult(countOfSearched, findList.length, searchingMills));
        System.out.println("Sorting time :" + millsToString(sortingTime));
        System.out.println("Searching time :" + millsToString(endMills - stopSortMills));
        System.out.println();

        System.out.println("Start searching (hash table)...");
        startMills = System.currentTimeMillis();
        HashTable<String, Contact> table = new HashTable<>(contacts.length);
//        Hashtable<String, Contact> table = new Hashtable<>(contacts.length);
        Contact[] copy = Arrays.copyOf(contacts, contacts.length);
        for (Contact contact : copy) {
            table.put(contact.getName(), contact);
        }
        long createTableTime = System.currentTimeMillis() - startMills;
        countOfSearched = hashSearch(table);
        endMills = System.currentTimeMillis();
        searchingMills = endMills - startMills;
        System.out.println(searchResult(countOfSearched, findList.length, searchingMills));
        System.out.println("Creating time :" + millsToString(createTableTime));
        System.out.println("Searching time :" + millsToString(searchingMills - createTableTime));
    }

    private int linearSearch(Dictionary dictionary) {
        int countOfSearched = 0;
        for (String name : findList) {
            if (dictionary.linearSearch(name)) {
                countOfSearched++;
            }
        }
        return countOfSearched;
    }

    private int jumpSearch(Dictionary dictionary) {
        int countOfSearched = 0;
        for (String name : findList) {
            if (dictionary.jumpSearch(name)) {
                countOfSearched++;
            }
        }
        return countOfSearched;
    }

    private int binarySearch(Dictionary dictionary) {
        int countOfSearched = 0;
        for (String name : findList) {
            if (dictionary.binarySearch(name)) {
                countOfSearched++;
            }
        }
        return countOfSearched;
    }

    private int hashSearch(HashTable<String, Contact> table) {
        int countOfSearched = 0;
        for (String name : findList) {
            if (table.containsKey(name)) {
                countOfSearched++;
            }
        }
        return countOfSearched;
    }
}
