/**
 * Object to be inserted into Access Queue to maintain count of least access items and allow ordering
 * @param <K> The key of an item
 */
public class QueueObject<K> implements Comparable<QueueObject<K>>{

    private final Integer noOfTimesAccessed;
    private final K key;
    private final Integer insertionValue;

    /**
     * Constructor of QueueObject
     * @param key The key of the item being inserted into map
     * @param noOfTimesAccessed The number of times this item have been accessed with find
     * @param insertionValue Counter to use as tiebreaker that represents insertion order
     */
    public QueueObject(K key, Integer noOfTimesAccessed, Integer insertionValue) {
        this.noOfTimesAccessed = noOfTimesAccessed;
        this.key = key;
        this.insertionValue = insertionValue;
    }

    public int getNoOfTimesAccessed() {
        return this.noOfTimesAccessed;
    }

    public K getKey() {
        return this.key;
    }

    public Integer getInsertionValue() {
        return this.insertionValue;
    }

    /**
     * Method to handle sorting by number of times accessed and handle tiebreaks
     * @param o The object to compare against
     * @return int value depending on how the queue object compares to another queue object
     */
    @Override
    public int compareTo(QueueObject o) {
        int i = ((Integer) this.getNoOfTimesAccessed()).compareTo((Integer)o.getNoOfTimesAccessed());
        if (i != 0) return i;

        i = (this.getInsertionValue()).compareTo(o.getInsertionValue());
        return i;
    }
}

