package forestanalyzer;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Performs insertions and searches, using the same data set,on a binary search
 * tree and an AVL tree to compare the performance of
 * these operations on the trees.
 *
 * @author Duncan, Jordan Morris
 * @SEE AVLTree, AVLTreeException, BSTree, BSTreeException,
 * @since 10-3-2018
 */
public class ForestAnalyzer {
    /**
     * @param args the command line arguments
     * @throws AVLTreeException
     * @throws BSTreeException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws AVLTreeException, BSTreeException, IOException {
        //Implement this method

        //Testing
        BSTree tree = new BSTree();
        tree.insert(70);
        tree.insert(40);
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(35);
        tree.insert(10);
        tree.insert(25);
        tree.insert(60);
        tree.insert(55);
        tree.insert(65);
        tree.insert(67);
        tree.insert(80);
        tree.insert(75);
        tree.insert(85);
        tree.insert(66);
        tree.insert(90);
        tree.insert(92);
        tree.insert(91);
        tree.insert(95);
        tree.insert(96);

        System.out.println(tree.isFull());
        System.out.println(tree.height());
        System.out.println(tree.diameter());

        BSTree newTree = new BSTree();
        System.out.println(newTree.isFull());
        System.out.println(newTree.height());
        System.out.println(newTree.diameter());


        int count = 56;
        double cLog = Math.ceil(Math.log(count) / Math.log(2));
        double fLog = Math.floor(Math.log(count) / Math.log(2));
        if(cLog == fLog)
            System.out.println(count + " is a power of two (2^" + cLog + ")");
        else
            System.out.println(count + " is not a power of two.");

        System.out.println();
        AVLTree avltest = new AVLTree();
        System.out.println(avltest.height());
        System.out.println(avltest.isFull());
        avltest.insert(25);
        avltest.insert(20);
        avltest.insert(36);
        avltest.insert(10);
        avltest.insert(22);
        avltest.insert(30);
        avltest.insert(40);
        avltest.insert(12);
        avltest.insert(28);
        avltest.insert(38);
        avltest.insert(48);
        System.out.println();
        System.out.println(avltest.height());
        System.out.println(avltest.isFull());
        System.out.println(avltest.depth(22));
        System.out.println(avltest.depth(25));
        System.out.println(avltest.depth(38));
        System.out.println(avltest.depth(91));
    }
}
