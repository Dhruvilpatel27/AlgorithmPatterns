import java.util.LinkedList;

class KeyValuePair<K, V> {
    K key;
    V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class HashTable<K, V> {
    private LinkedList<KeyValuePair<K, V>>[] table;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        table = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<KeyValuePair<K, V>> list = table[index];
        for (KeyValuePair<K, V> pair : list) {
            if (pair.key.equals(key)) {
                pair.value = value;
                return;
            }
        }
        list.add(new KeyValuePair<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<KeyValuePair<K, V>> list = table[index];
        for (KeyValuePair<K, V> pair : list) {
            if (pair.key.equals(key)) {
                return pair.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        LinkedList<KeyValuePair<K, V>> list = table[index];
        for (KeyValuePair<K, V> pair : list) {
            if (pair.key.equals(key)) {
                list.remove(pair);
                size--;
                return;
            }
        }
    }

    public int size() {
        return size;
    }
}

public class Hashing {
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>(10);
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        hashTable.put("four", 4);

        System.out.println("Size of hash table: " + hashTable.size());
        System.out.println("Value of 'two': " + hashTable.get("two"));
        System.out.println("Value of 'five': " + hashTable.get("five"));

        hashTable.remove("three");
        System.out.println("Size of hash table after removal: " + hashTable.size());
        System.out.println("Value of 'three' after removal: " + hashTable.get("three"));
    }
}
