/*
Most Important Message in History!!!

THIS IS NOT AN AVL TREE AS THE TITLE SUGGESTS, BUT A REGULAR BST BUILT UPON THE ASHES OF THE AVL FROM ASSIGNMENT 3!

 */
class AVLTree {
    int n;
    public AVLNode root;

    /* Constructor */
    public AVLTree() {
        this.n = 0;
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
        if (t == null) {
            t = new AVLNode(p);
            this.n++;
            return t;
        }
        else if (p.getX() < t.p.getX()) {
            t.left = insert(p, t.left);
            /*if (height(t.left) - height(t.right) == 2)
                if (p.getX() < t.left.p.getX()) t = rotateWithLeftChild(t);
                else t = doubleWithLeftChild(t);*/
        } else if (p.getX() > (t.p.getX())) {
            t.right = insert(p, t.right);
            /*if (height(t.right) - height(t.left) == 2)
                if (p.getX() > (t.right.p.getX()))
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);*/
        }
        updateParams(t);
        return t;
    }

    void updateParams(AVLNode t) {
        if(t.left!= null && t.right != null) {
            t.numOfChildren = t.left.numOfChildren + t.right.numOfChildren + 2;
            t.numOfLeftChildren = t.left.numOfChildren + 1;
            t.totalYsum = t.left.totalYsum + t.right.totalYsum + t.left.p.getY() + t.right.p.getY();
            t.RightYsum = t.right.totalYsum + t.right.p.getY();
        }
        else if(t.left==null && t.right!=null) { //if only right exists
            t.numOfChildren = t.right.numOfChildren + 1;
            t.numOfLeftChildren = 0;

            t.totalYsum = t.right.totalYsum + t.right.p.getY();
            t.RightYsum = t.right.totalYsum + t.right.p.getY();
        }
        else if(t.left!=null && t.right==null) { //if only left exists
            t.numOfChildren = t.left.numOfChildren +1;
            t.numOfLeftChildren = t.left.numOfChildren+1;

            t.totalYsum = t.left.totalYsum+t.left.p.getY();
            t.RightYsum = 0;
        }
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
    /*public AVLNode successor(AVLNode t) {
        AVLNode retVal;
        if(t.right !=null) {
            return minimum(retVal);
        }
        if(t.right == null && t.left == null) retVal = t;//if
        while (true)
    }*/
    public AVLNode minimum(AVLNode t) {
        AVLNode retVal = t;
        while (retVal.left != null) {
            retVal = retVal.left;
        }
        return retVal;
    }
    public AVLNode maximum(AVLNode t) {
        AVLNode retVal = t;
        while (retVal.right != null) {
            retVal = retVal.right;
        }
        return retVal;
    }
    AVLNode deleteRec(AVLNode root, Point p)
    {
        /* Base Case: If the tree is empty */
        if (root == null)  return root;

        /* Otherwise, recur down the tree */
        if (p.getX() < root.p.getX())
            root.left = deleteRec(root.left, p);
        else if (p.getX() > root.p.getX())
            root.right = deleteRec(root.right, p);

            // if key is same as root's key, then This is the node
            // to be deleted
        else
        {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.p = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.p);
        }
        updateParams(root);
        return root;
    }
    Point minValue(AVLNode root)
    {
        Point minv = root.p;
        while (root.left != null)
        {
            minv = root.left.p;
            root = root.left;
        }
        return minv;
    }
    public AVLNode remove(Point p, AVLNode t) {
        AVLNode retVal = null;
        if(t.p.equals(p)) { //if found node
            if(t.left != null && t.right != null) {
                retVal = minimum(t.right);
            }
            else if(t.left != null && t.right==null) {
                retVal = t.left;
            }
            else {
                retVal = t.right;
            }
        }
        else if (p.getX() < t.p.getX()) {
            t.left = remove(p, t.left);
            /*if (height(t.left) - height(t.right) == 2)
                if (p.getX() < t.left.p.getX()) t = rotateWithLeftChild(t);
                else t = doubleWithLeftChild(t);*/
        } else if (p.getX() > (t.p.getX())) {
            t.right = remove(p, t.right);
            /*if (height(t.right) - height(t.left) == 2)
                if (p.getX() > (t.right.p.getX()))
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);*/
        }
        updateParams(t);
        return retVal;
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
    public int getNumGreaterPoints(AVLNode t, int xVal) {
        int cnt=0;
        while( t != null ) {
            if (xVal < t.p.getX()) {
                if(t.right!= null) cnt += t.right.numOfChildren+1;
                t = t.left;
                cnt+=1;
            }
            else if (xVal > t.p.getX()){
                t = t.right;
            }
            else { //if found
                if (t.right!=null) cnt += t.right.numOfChildren +1;
                break;
            }
        }
        return cnt;
    }
    public int getNumGreaterPointsY(AVLNode t, int xVal) {
        int cnt=0;
        while( t != null ) {
            if (xVal < t.p.getX()) {
                if(t.right!= null) cnt += t.right.totalYsum + t.right.p.getY();
                cnt += t.p.getY();
                t = t.left;
            }
            else if (xVal > t.p.getX()){
                t = t.right;
            }
            else {
                //cnt +=t.p.getY();
                if (t.right!=null) cnt += t.right.totalYsum+t.right.p.getY();
                break;
            }
        }
        return cnt;
    }
    public int getNumGreaterEqualPointsY(AVLNode t, int xVal) {
        int cnt=0;
        while( t != null ) {
            if (xVal < t.p.getX()) {
                if(t.right!= null) cnt += t.right.totalYsum + t.right.p.getY();
                cnt += t.p.getY();
                t = t.left;
            }
            else if (xVal > t.p.getX()){
                t = t.right;
            }
            else {
                cnt +=t.p.getY();
                if (t.right!=null) cnt += t.right.totalYsum+t.right.p.getY();
                break;
            }
        }
        return cnt;
    }
    public AVLNode search(AVLNode t, int xVal) {
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
            System.out.println(t.p.getX() + " Yr: "+t.RightYsum +". Ytot: " + t.totalYsum +". totChld " + t.numOfChildren);
            printTree(t.right);
        }
    }

    public void printTree() {
        printTree(root);
    }
    AVLNode sortedArrayToTree(Point arr[], int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end-start)/ 2;
        AVLNode node = new AVLNode(arr[mid]);
        node.left = sortedArrayToTree(arr, start, mid - 1);
        node.right = sortedArrayToTree(arr, mid + 1, end);
        updateParams(node);
        return node;
    }

}