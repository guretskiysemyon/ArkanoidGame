/**
 * @author Semyon Guretskiy.
 * ID: 336249255
 */
package listeners;

/**
 * interface to Notifier. The object that notify the listener after hit.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - hit listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove the hit listener from the list.
     * @param hl - hit listener.
     */
    void removeHitListener(HitListener hl);
}