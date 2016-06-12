/* Class AVLNode */
class AVLNode
{
    AVLNode left, right;
    int height;
    AVLNode parent;
    Point p;
    int numOfLeftChildren, numOfChildren, RightYsum, leftYsum, totalYsum;

    /* Constructor */
    public AVLNode(Point p)
    {
        this.p = p;
        left = null;
        right = null;
        height = 1;
        numOfLeftChildren = 0;
        RightYsum = 0;
        totalYsum = 0;
        leftYsum = 0;
    }

    /* Constructor */
//    public AVLNode(Comparable n)
//    {
//        left = null;
//        right = null;
//        data = n;
//        height = 1;
//    }
}
 