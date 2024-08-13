package GameInfo;

/**
 * The type Counter.
 */
public class Counter {
    private int count;


    /**
     * Instantiates a new Counter.
     *
     * @param count the count
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Increases the count by the given number.
     *
     * @param number the number to increase the count by
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Decreases the count by the given number.
     *
     * @param number the number to decrease the count by
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return count;
    }
}
