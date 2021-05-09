package phonebook;

public class HashTable<K, V> {
    private final int size;
    private final TableEntry<K, V>[] table;

    public HashTable(int size) {
        this.size = size;
        this.table = new TableEntry[size];
    }

    public void put(K key, V value) {
        int hash = findIndex(key);
        if (hash == -1) {
            return;
        }
        table[hash] = new TableEntry<>(key, value);
    }

    public boolean containsKey(K key) {
        int hash = findIndex(key);
        if (hash == -1 || table[hash] == null) {
            return false;
        }
        return true;
    }

    private int findIndex(K key) {
        int hash = key.hashCode();
        int index = Integer.remainderUnsigned(hash, size);
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + 1) % size;
            if (index == hash % size) {
                return -1;
            }
        }
        return index;
    }

    class TableEntry<K, V> {
        private final K key;
        private final V value;

        TableEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
