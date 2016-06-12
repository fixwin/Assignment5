//import java.io.PrintWriter;

/* Class AVLTree */
class AVLTree {
    public AVLNode root;

    /* Constructor */
    public AVLTree() {
        //Complete Your Code Here
    }

    /* Function to check if tree is empty */
    public boolean isEmpty() {
        return (root.left == null && root.right == null);
    }

    /* Make the tree logically empty */
    public void makeEmpty() {
        root.left = null;
        root.right = null;
    }

    /* Function to insert data */
    public void insert(Point point) {
        root = insert(point, this.root);
    }

    /* Function to get height of node */
    private int height(AVLNode t) {
        if (t == null) return 0;
        if (t.right == null && t.left == null) return 1;
        if (t.right == null) return t.left.height + 1;
        if (t.left == null) return t.right.height + 1;
        return Math.max(t.left.height, t.right.height) + 1;
    }

    /* Function to max of left/right node */
//    private int max(int lhs, int rhs)
//    {
//        //Complete Your Code Here
//    }
    private boolean checkBalance(AVLNode t) {
        if (t == null) return true;
        if (t.height == 1) return true;
        if (t.left == null) {
            if (t.right.height >= 2) return false;
            return true;
        }
        if (t.right == null) {
            if (t.left.height >= 2) return false;
            return true;
        } else {
            if (Math.abs(t.left.height - t.right.height) > 1) return false;
        }
        return true;
    }

    /* Function to insert data recursively */
    private AVLNode insert(Point p, AVLNode t) {
        if (t == null)
            t = new AVLNode(p);
        else if (p.getX() < t.p.getX()) {
            t.left = insert(p, t.left);
            if (height(t.left) - height(t.right) == 2)
                if (p.getX() < t.left.p.getX()) t = rotateWithLeftChild(t);
                else t = doubleWithLeftChild(t);
        } else if (p.getX() > (t.p.getX())) {
            t.right = insert(p, t.right);
            if (height(t.right) - height(t.left) == 2)
                if (p.getX() > (t.right.p.getX()))
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);
        }
        t.height = height(t);
        if(t.left != null) t.numOfLeftChildren+=1;
        if(t.right != null) t.RightYsum +=1;
        return t;
    }

    /* Rotate binary tree node with left child */
    private AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = height(k2);
        k1.height = height(k1);
        return k1;
    }

    /* Rotate binary tree node with right child */
    private AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = height(k1);
        k2.height = height(k2);
        return k2;
    }
    public AVLNode remove(AVLNode t) {

    }
    private Point findMax(AVLNode nd) {
        AVLNode a = nd;
        while (a.right!=null) {
            a = a.right;
        }
        return a.p;
    }
    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child
     */
    private AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child
     */
    private AVLNode doubleWithRightChild(AVLNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    /* Functions to count number of nodes */
    public int countNodes() {
        return 0;
    }

    private int countNodes(AVLNode r) {
        return 0;
    }

    /* Functions to search for an element */
    public boolean search(Comparable val) {
        return false;
    }

    private AVLNode search(AVLNode t, int xVal) {
        while( t != null ) {
            if (xVal < t.p.getX()) t = t.left;
            else if (xVal > t.p.getX()) t = t.right;
            else return t;    // Match
        }

        return null;   // No match
    }

    /* Function for inorder traversal */
   /* public void inorder(PrintWriter out) {
        //Complete Your Code Here
    }

    private void inorder(AVLNode r, PrintWriter out) {
        //Complete Your Code Here
    }*/

    private void printTree(AVLNode t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.p.getX());
            printTree(t.right);
        }
    }

    public void printTree() {
        printTree(root);
    }

}