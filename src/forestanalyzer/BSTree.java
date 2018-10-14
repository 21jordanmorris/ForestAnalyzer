package forestanalyzer;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * A binary search tree <br>
 * Requires JDK 1.8 for Function*
 *
 * @param <E> the tree data type
 * @author Duncan, Jordan Morris
 * @see BSTreeAPI, BSTreeException
 * @since 10-3-2018
 */
public class BSTree<E extends Comparable<E>> implements BSTreeAPI<E> {
    /**
     * the root of this tree
     */
    private Node root;
    /**
     * the number of nodes in this tree
     */
    private int count;

    /**
     * A node of a tree stores a data item and references
     * to the child nodes to the left and to the right.
     */
    private class Node {
        /**
         * the data in this node
         */
        public E data;
        /**
         * A reference to the left subtree rooted at this node.
         */
        public Node left;
        /**
         * A reference to the right subtree rooted at this node
         */
        public Node right;
    }

    /**
     * Constructs an empty tree
     */
    public BSTree() {
        root = null;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }


    public void insert(E item) {
        Node newNode = new Node();
        newNode.data = item;
        if (count == 0) {
            root = newNode;
            count++;
        } else {
            Node tmp = root;
            while (true) {
                int d = tmp.data.compareTo(item);
                if (d == 0) { /* Key already exists. (update) */
                    tmp.data = item;
                    return;
                } else if (d > 0) {
                    if (tmp.left == null) { /* If the key is less than tmp */
                        tmp.left = newNode;
                        count++;
                        return;
                    } else { /* continue searching for insertion pt. */
                        tmp = tmp.left;
                    }
                } else {
                    if (tmp.right == null) {/* If the key is greater than tmp */
                        tmp.right = newNode;
                        count++;
                        return;
                    } else { /* continue searching for insertion point*/
                        tmp = tmp.right;
                    }
                }
            }
        }
    }


    public boolean inTree(E item) {
        return search(item) != null;
    }


    public void remove(E item) {
        Node nodeptr = search(item);
        if (nodeptr != null) {
            remove(nodeptr);
            count--;
        }
    }

    public E retrieve(E key) throws BSTreeException {
        if (count == 0)
            throw new BSTreeException("Non-empty tree expected on retrieve().");
        Node nodeptr = search(key);
        if (nodeptr == null)
            throw new BSTreeException("Existent key expected on retrieve().");
        return nodeptr.data;
    }

    public void traverse(Function func) {
        traverse(root, func);
    }

    public int size() {
        return count;
    }
   
   
/*===> BEGIN: Augmented public Methods <===*/

    /**
     * Computes the depth  of the specified search key in this tree.
     *
     * @param item the search key
     * @return the depth of the specified search key if it is in the.
     * this tree. If it is not, -1-d, where d is the depth at which
     * it would have been found if inserted in the current tree.
     */
    public int depth(E item) {
        int levelCounter = 0;
        if(isEmpty())
            return -1;
        Node current = root;
        while (current != null) {
            int d = current.data.compareTo(item);
            if (d == 0)
                return levelCounter;
            else if (d > 0) {
                levelCounter++;
                current = current.left;
            }
            else {
                levelCounter++;
                current = current.right;
            }
        }
        return -1 - levelCounter;
    }

    /**
     * Give the heigh of this tree.
     *
     * @return the height of this tree
     */
    public int height() {
        return height(root);
    }


    /**
     * Computes the diameter, the number of nodes on the longest
     * path, in this tree
     *
     * @return the diameter of this tree
     */
    public int diameter() {
        return diameter(root);
    }

    /**
     * Determines whether or not this AVL tree is perfect.
     *
     * @return true if this tree is perfect; otherwise, false
     */
    public boolean isPerfect() {
        return (Math.ceil(Math.log(count + 1) / Math.log(2)) ==
                Math.floor(Math.log(count + 1) / Math.log(2)))
                && isPerfect(root, 0);
    }

    /**
     * Determines whether or not this tree is full
     *
     * @return true if this tree is full; otherwise, false
     */
    public boolean isFull() {
        return isFull(root);
    }
/* END: Augmented public Methods */

    /**
     * A recursive auxiliary method for the inorderTraver method that
     *
     * @param node a reference to a Node object
     * @param func a function that is applied to the data in each
     *             node as the tree is traversed in order.
     */
    private void traverse(Node node, Function func) {
        if (node != null) {
            traverse(node.left, func);
            func.apply(node.data);
            traverse(node.right, func);
        }
    }

    /**
     * An auxiliary method that supports the search method
     *
     * @param key a data key
     * @return a reference to the Node object whose data has the specified key.
     */
    private Node search(E key) {
        Node current = root;
        while (current != null) {
            int d = current.data.compareTo(key);
            if (d == 0)
                return current;
            else if (d > 0)
                current = current.left;
            else
                current = current.right;
        }
        return null;
    }

    /**
     * An auxiliary method that gives a Node reference to the parent node of
     * the specified node
     *
     * @param node a reference to a Node object
     * @return a reference to the parent node of the specified node
     */
    private Node findParent(Node node) {
        Node tmp = root;
        if (tmp == node)
            return null;
        while (true) {
            assert tmp.data.compareTo(node.data) != 0;
            if (tmp.data.compareTo(node.data) > 0) {
             /* this assert is not needed but just
                in case there is a bug         */
                assert tmp.left != null;
                if (tmp.left == node)
                    return tmp;
                else
                    tmp = tmp.left;
            } else {
                assert tmp.right != null;
                if (tmp.right == node)
                    return tmp;
                else
                    tmp = tmp.right;
            }
        }
    }


    /**
     * An auxiliary method that deletes the specified node from this tree
     *
     * @param node the node to be deleted
     */
    private void remove(Node node) {
        E theData;
        Node parent, replacement;
        parent = findParent(node);
        if (node.left != null) {
            if (node.right != null) {
                replacement = node.right;
                while (replacement.left != null)
                    replacement = replacement.left;
                theData = replacement.data;
                remove(replacement);
                node.data = theData;
                return;
            } else {
                replacement = node.left;
            }
        } else {
            if (node.right != null)
                replacement = node.right;
            else
                replacement = null;
        }
        if (parent == null)
            root = replacement;
        else if (parent.left == node)
            parent.left = replacement;
        else
            parent.right = replacement;
    }
   
/* BEGIN: Augmented Private Auxiliary Methods */

    /**
     * An auxiliary method that recursively determines the height
     * of a subtree rooted at the specified node.
     *
     * @param node a root of a subtree.
     */
    private int height(Node node) {
        if(node == null)
            return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * Recursively determines the diameter, the number of nodes on
     * the longest path, of the subtree rooted at the specified node
     *
     * @param node the root of the specified subtree
     * @return the diameter of the tree rooted at the specified node
     */
    private int diameter(Node node) {
        if(node == null)
            return 0;
        int heightLeft = height(node.left);
        int heightRight = height(node.right);

        int leftDiameter = diameter(node.left);
        int rightDiameter = diameter(node.right);

        return Math.max(heightLeft + heightRight + 3, Math.max(leftDiameter, rightDiameter));
    }

    /**
     * Recursively determines whether the subtree rooted at the
     * specified node is perfect.
     *
     * @param node  the root of a subtree
     * @param index the zero-based level-order index of this node
     * @return true if the tree root at the specified node is perfect;
     * otherwise, false
     */
    private boolean isPerfect(Node node, int index) {
        //TODO: FIND A WAY TO IMPLEMENT INDEX PORTION OF METHOD!!
        return node == null || count == Math.pow(2, height() + 1) - 1;
    }

    /**
     * Recursively determines whether the subtree rooted at the specified node
     * is full
     *
     * @param node the root of a subtree of this tree
     * @return true if the subtree rooted at the specified node is full; otherwise, false
     */
    private boolean isFull(Node node) {
        if(node == null)
            return true;
        //Leaf Node
        if(node.left == null && node.right == null)
            return true;
        //Full Node
        if(node.left != null && node.right != null) {
            return isFull(node.left) && isFull(node.right);
        }
        //Half Node
        return false;
    }
/* END: Augmented Private Auxiliary Methods */
}
