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

        Scanner inFile = new Scanner(new FileReader(args[0]));

        BSTree bsTree = new BSTree();
        AVLTree avlTree = new AVLTree();

        while (inFile.hasNext()) {
            String currentLine = inFile.nextLine();
            String[] splitCommand = currentLine.split(" ");

            String command = splitCommand[0];
            if (command.equals("insert") || command.equals("remove")) {
                if(command.equals("insert")) {
                    avlTree.insert(splitCommand[1]);
                    System.out.println("inserted: " + splitCommand[1].toUpperCase() + " in the AVL");
                    bsTree.insert(splitCommand[1]);
                    System.out.println("inserted: " + splitCommand[1].toUpperCase() + " in the BST");
                }
                else if(command.equals("remove")) {
                    int depthAVL = avlTree.depth(splitCommand[1]);
                    avlTree.remove(splitCommand[1]);
                    System.out.println("removed: " + splitCommand[1].toUpperCase() + " from a depth of " + depthAVL + " in the AVL");
                    int depthBST = bsTree.depth(splitCommand[1]);
                    bsTree.remove(splitCommand[1]);
                    System.out.println("removed: " + splitCommand[1].toUpperCase() + " from a depth of " + depthBST + " in the BST");
                }

                System.out.printf("%-5s %-9s %-9s %-9s %-9s %-9s%n", "Type:", "size", "height", "diameter", "full?", "perfect?");
                System.out.printf("%-5s %-9s %-9s %-9s %-9s %-9s%n", "AVL:", avlTree.size(), avlTree.height(), avlTree.diameter(), avlTree.isFull(), avlTree.isPerfect());
                System.out.printf("%-5s %-9s %-9s %-9s %-9s %-9s%n%n", "BST:", bsTree.size(), bsTree.height(), bsTree.diameter(), bsTree.isFull(), bsTree.isPerfect());
            } else if (command.equals("traverse")) {
                System.out.println("AVL (in-order):");
                avlTree.traverse(currentWord -> System.out.printf("%S%n", currentWord));
                System.out.printf("-----------------------------------%nword count: %s%n%n", avlTree.size());

                System.out.println("BST (in-order):");
                bsTree.traverse(currentWord -> System.out.printf("%S%n", currentWord));
                System.out.printf("-----------------------------------%nword count: %s%n%n%n", bsTree.size());
            } else
                System.out.println("Exception: Invalid Command");
        }
        inFile.close();
    }
}
