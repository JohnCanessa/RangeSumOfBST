import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * Definition for a binary tree node.
 */
 class TreeNode {

    // **** members ****
    int val;
    TreeNode left;
    TreeNode right;

    // **** constructor ****
    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


/**
 * 938. Range Sum of BST
 * https://leetcode.com/problems/range-sum-of-bst/
 */
public class Solution {

    
    /**
     * This method populates a BT in level order as specified by the array.
     */
    static TreeNode populateTree(String[] arr) {
    
        // **** root for the BT ****
        TreeNode root = null;
    
        // **** ****
        Queue<TreeNode> q = new LinkedList<TreeNode>();
    
        // **** traverse the array of values inserting the nodes into the BT ****
        for (String strVal : arr)
            root = insertValue(root, strVal, q);
    
        // **** clear the queue ****
        q = null;
    
        // **** return the root of the BT ****
        return root;
    }


    /**
     * Enumerate which child in the node at the head of the queue 
     * should be updated.
     */
    enum Child {
        LEFT,
        RIGHT
    }


    // **** child turn to insert on node at head of queue ****
    static Child  insertChild = Child.LEFT;
    

    /**
     * This method inserts the next value into the specified BT.
     * This method is called repeatedly from the populateTree method.
     */
    static TreeNode insertValue(TreeNode root, String strVal, Queue<TreeNode> q) {
    
        // **** node to add to the BT in this pass ****
        TreeNode node = null;
    
        // **** create a node (if needed) ****
        if (!strVal.equals("null"))
            node = new TreeNode(Integer.parseInt(strVal));
    
        // **** BT is empty (this is the root node) ****
        if (root == null)
            root = node;
    
        // **** add node to left child (if possible) ****
        else if (insertChild == Child.LEFT) {
        
            // **** add this node as the left child ****
            if (node != null)
                q.peek().left = node; 
            
            // **** for next pass ****
            insertChild = Child.RIGHT;
        }
    
        // **** add node to right child (if possible) ****
        else if (insertChild == Child.RIGHT) {
        
            // **** add this node as a right child ****
            if (node != null)
                q.peek().right = node;
    
            // **** remove node from queue ****
            q.remove();
    
            // **** for next pass ****
            insertChild = Child.LEFT;
        }
    
        // **** add this node to the queue (if needed) ****
        if (node != null)
            q.add(node);
        
        // **** return the root of the BT ****
        return root;
    }


    /**
     * Traverse the specified BT displaying the values in order.
     * This method is used to verify that the BT was properly populated.
     */
    static void inOrder(TreeNode root) {
    
        // **** end condition ****
        if (root == null)
            return;
    
        // **** visit the left sub tree ****
        inOrder(root.left);
    
        // **** display the value of this node ****
        System.out.print(root.val + " ");
    
        // **** visit the right sub tree ****
        inOrder(root.right);
    }


    /**
     * Given the root node of a binary search tree, 
     * return the sum of values of all nodes with value 
     * between L and R (inclusive).
     */
    static int rangeSumBST(TreeNode root, int L, int R) {
        
        // **** initialize sum ****
        int sum = 0;

        // **** base case ****
        if (root == null)
            return 0;

        // **** traverse left tree ****
        if (root.left != null)
            sum += rangeSumBST(root.left, L, R);

        // **** update sum ****
        if ((root.val >= L) && (root.val <= R))
            sum += root.val;

        // **** traverse right tree ****
        if (root.right != null)
            sum += rangeSumBST(root.right, L, R);

        // **** ****
        return sum;
    }


    /**
     * Test scaffolding.
     */
    public static void main(String[] args) {

        // **** root for BST ***
        TreeNode root = null;

        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read the data for the BST ****
        String[] arr = sc.nextLine().split(",");

         // **** populate the BT in level order (supports 'null' values) ****
        root = populateTree(arr);

        // ???? inorder traversal (check the BT) ????
        System.out.print("main <<< inOrder: ");
        inOrder(root);
        System.out.println();
 
        // **** read the L & R values ****
        String[] lAndr = sc.nextLine().split(",");
        int L = Integer.parseInt(lAndr[0]);
        int R = Integer.parseInt(lAndr[1]);

        // ???? ????
        System.out.println("main <<< L: " + L + " R: " + R);

        // **** close scanner ****
        sc.close();

        // **** generate and display the sum ****
        System.out.println("main <<< sum: " + rangeSumBST(root, L, R));
    }

}