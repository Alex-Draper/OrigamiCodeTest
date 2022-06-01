import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Forgetting Map class.
 * @param <K> The type of the keys to be stored.
 * @param <V> The type of the values to be stored.
 */
public class ForgettingMap<K, V> {

    private final int maximumSize;
    private final int minimumSize = 0;
    private Hashtable<K, V> dataMap;
    private Hashtable<K, QueueObject> queueReferenceMap;
    private PriorityQueue<QueueObject> accessQueue;
    private AtomicInteger insertionValue;

    /**
     * Constructor of the Forgetting Map
     * @param maxSize The maximum number of items the map can store
     * @throws IllegalArgumentException MaxSize must be greater then the minimum size of zero.
     */
    public ForgettingMap(int maxSize) throws IllegalArgumentException {

        if (maxSize < minimumSize) {
            throw new IllegalArgumentException("The maximum size must be greater then 0");
        }
        maximumSize = maxSize;
        dataMap = new Hashtable<>();
        queueReferenceMap = new Hashtable<>();
        accessQueue = new PriorityQueue<>();
        insertionValue = new AtomicInteger(0);


    }

    /**
     * Adds an association into the forgetting map for a given key and value
     * @param key The key be used for the association
     * @param value The value to be associated with the key
     * @return The value that was added to the map
     */
    public synchronized V add(K key, V value) {
        if (dataMap.size() + 1 > maximumSize) {
            QueueObject leastAccessItem = accessQueue.poll();
            queueReferenceMap.remove(leastAccessItem.getKey());
            dataMap.remove(leastAccessItem.getKey());
        }
        insertionValue.getAndIncrement();
        dataMap.put(key, value);
        QueueObject newItem = new QueueObject(key, 0, insertionValue.get());
        queueReferenceMap.put(key, newItem);
        accessQueue.add(newItem);
        return value;
    }

    /**
     * Method to get a value associated with a supplied key
     * @param key The key to search with
     * @return The value associated with the key if present, else null
     */
    public synchronized V find(K key) {
        if(dataMap.containsKey(key)) {
            QueueObject foundItem = queueReferenceMap.get(key);
            QueueObject newAccessObject = new QueueObject<>(key, foundItem.getNoOfTimesAccessed() + 1, foundItem.getInsertionValue());
            accessQueue.remove(foundItem);
            accessQueue.add(newAccessObject);
            queueReferenceMap.put(key, newAccessObject);
            return dataMap.get(key);
        }
        else {
            return null;
        }
    }

}

