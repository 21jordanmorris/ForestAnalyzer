package forestanalyzer;

import java.util.function.Function;

/**
 * /**
 * Reports an exception in a binary search tree
 *
 * @author Duncan
 * @since 99-99-9999
 */
class BSTreeException extends Exception {
    /**
     * Creates a new instance of <code>BSTreeException</code> without detail
     * message.
     */
    public BSTreeException() {
    }

    /**
     * Constructs an instance of <code>BSTreeException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BSTreeException(String msg) {
        super(msg);
    }
}


/**
 * Describes a binary search tree<br>
 * Requires JDK 1.8 for Function
 *
 * @param <E> the data type
 * @author Duncan
 * @see BSTreeException
 * @since 99-99-9999
 */
public interface BSTreeAPI<E extends Comparable<E>> {
    /**
     * Determines whether the tree is empty.
     *
     * @return true if the tree is empty;  otherwise, false
     */
    boolean isEmpty();

    /**
     * Inserts an item into the tree.
     *
     * @param obj the value to be inserted.
     */
    void insert(E obj);

    /**
     * Determine whether an item is in the tree.
     *
     * @param item item with a specified search key.
     * @return true on success; false on failure.
     */
    boolean inTree(E item);

    /**
     * Delete an item from the tree.
     *
     * @param item item with a specified search key.
     */
    void remove(E item);

    /**
     * returns the item with the given search key.
     *
     * @param key the key of the item to be retrieved
     * @return the item with the specified key
     * @throws BSTreeException when no such element exists
     */
    E retrieve(E key) throws BSTreeException;

    /**
     * This function traverses the tree in in-order
     * and calls the function Visit once for each node.
     *
     * @param func the function to apply to the data in each node
     */
    void traverse(Function func);

    /**
     * This method returns the number of items stored in the tree.
     *
     * @return the size of the tree.
     */
    int size();
}