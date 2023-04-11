/**
 * @author Semyon Guretskiy.
 * ID: 336249255
 */
package instruments;

/**
 * Counter class. That count some objects.
 */
public class Counter {
    private int counter;

    /**
     * Constructor.
     * @param n - the start number.
     */
    public Counter(int n) {
        this.counter = n;
    }

    /**
     * Constructor without arguments.
     */
    public Counter() {
        this.counter = 0;
    }


    /**
     * Increase counter in some number.
     * @param number - the step in numbers.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Decrease counter in some number.
     * @param number - step in numbers.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Return value.
     * @return - the value of counter.
     */
    public int getValue() {
        return this.counter;
    }
}