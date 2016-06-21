import java.security.PublicKey;

public class PointDataStructure implements PDT {
	Point median;
	MaxHeap maxHeap;
	MinHeap minHeap;
	Point[] sortedPointsArr;

	int medianIndex;
	public AVLTree tree;
	//Point[] getPointsInRange;
	int getPointsIndex;
	int allPointsIndex;
	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint) {
		tree = new AVLTree();
		this.median = initialYMedianPoint;
		sortedPointsArr = new Point[points.length/*points.length +(int)(Math.log(points.length)/Math.log(2))*/]; //add log to the array size
		int n = points.length +(int) (10*Math.log(points.length)/Math.log(2)) + points.length/*add points for safety*/;
		maxHeap = new MaxHeap(n);
		minHeap = new MinHeap(n);
		boolean isSorted = checkSorted(points);
		medianIndex = getMedianIndex(points,initialYMedianPoint);
		for (int j=0; j<points.length; j++) {
			if(points[j].equals(this.median)) continue;
			if(points[j].getY() < this.median.getY() || (points[j].getY() == this.median.getY() && points[j].getX() < this.median.getX())) {
				maxHeap.insert(points[j]);
			}
			else {
				minHeap.insert(points[j]);
			}
		}
		if (isSorted) { //if sorted->insert into heaps
			for(int k=0; k<points.length; k++) {
				sortedPointsArr[k] = points[k];
			}
		}
		else { //if not sorted-> max value is n-1 and values are consecutive -> insert into heaps
			for (int j=0; j<points.length; j++) {
				sortedPointsArr[points[j].getX()] = points[j];
			}
		}
		tree.root = tree.sortedArrayToTree(sortedPointsArr,0,sortedPointsArr.length-1);
		tree.n = sortedPointsArr.length;
	}
	private boolean checkSorted(Point[] points) { //O(n)
		if (points.length <= 1) return true;
		for(int j=0; j<points.length-1; j++) {
			if(points[j].getX() > points[j+1].getX()) return false;
		}
		return true;
	}

	private int getMedianIndex(Point[] points, Point initialYMedianPoint) {
		for(int j=0; j<points.length; j++) {
			if(points[j].equals((Object) initialYMedianPoint)) return j;
		}
		return -1;
	}

	@Override
	public void addPoint(Point point) {
		if (point.getY() > this.median.getY()) { //if point should go into min heap
			if(minHeap.n<maxHeap.n) {
				minHeap.insert(point);
			}
			else if(minHeap.n >= maxHeap.n) {
				maxHeap.insert(this.median);
				minHeap.insert(point);
				this.median = minHeap.getMin();
				minHeap.delete();
			}
		}
		else { //if point should go into max heap
			if(maxHeap.n <= minHeap.n) {
				maxHeap.insert(point);
			}
			else {
				minHeap.insert(this.median);
				maxHeap.insert(point);
				this.median = maxHeap.getMax();
				maxHeap.delete();
			}
		}
		tree.insert(point);
	}
	@Override
	public Point[] getPointsInRange(int XLeft, int XRight) {
//		AVLNode pL = tree.search(tree.root,XLeft);
//		AVLNode pR = tree.search(tree.root,XLeft);
		//tree.printTree();
		Point[] pInR = new Point[numOfPointsInRange(XLeft,XRight)];
		getPointsIndex = 0;
		getPointsInRangeHandler(tree.root,XLeft,XRight,pInR);
		return pInR;
	}
	public void getPointsInRangeHandler(AVLNode node,int XLeft, int XRight,Point[] pInR) {
		if (node == null) {
			return;
		}

		if (XLeft < node.p.getX()) {
			getPointsInRangeHandler(node.left, XLeft, XRight,pInR);
		}
		if (XLeft <= node.p.getX() && XRight >= node.p.getX()) {
			pInR[getPointsIndex] = node.p;
			getPointsIndex++;
		}

		if (XRight > node.p.getX()) {
			getPointsInRangeHandler(node.right, XLeft, XRight,pInR);
		}
	}

	@Override
	public int numOfPointsInRange(int XLeft, int XRight) {
		int maxVal = tree.maximum(tree.root).p.getX();//O(log n)
		int minVal = tree.minimum(tree.root).p.getX();//O(log n)
		if(XLeft > maxVal) return 0; //if left is greater than maximum value, nothing to check
		int left = tree.getNumGreaterPoints(tree.root,XLeft);
		int right = tree.getNumGreaterPoints(tree.root,XRight);
		if(XRight>maxVal && XLeft < minVal) return left - right;
		else return left - right+1;
	}

	@Override
	public double averageHeightInRange(int XLeft, int XRight) {
		int num = numOfPointsInRange(XLeft,XRight);
		if(num==0) return 0.0;//if there are no points in range
		int left = tree.getNumGreaterEqualPointsY(tree.root,XLeft);
		int right = tree.getNumGreaterPointsY(tree.root,XRight);
		return (double) (left - right)/num;
	}

	@Override
	public void removeMedianPoint() {
		AVLNode n = tree.search(tree.root,median.getX());
		tree.root = tree.deleteRec(tree.root, median);

		if(maxHeap.n == minHeap.n) {
			this.median = minHeap.getMin();
			minHeap.delete();
		}
		else if(maxHeap.n> minHeap.n) {
			this.median = maxHeap.getMax();
			maxHeap.delete();
		}
		else {//if minheap has more than maxheap
			this.median = minHeap.getMin();
			minHeap.delete();
		}
	}

	@Override
	public Point[] getMedianPoints(int k) {
		int l = (k - 1) / 2;
		int h = (k - 1) / 2;
		if (k % 2 == 0) {
			l = k / 2;
		}
		int popped = 0;
		Point[] ret = new Point[k];
		MaxHeap max = new MaxHeap(k);
		max.insert(new xPoint(maxHeap.heap[0].getX(), maxHeap.heap[0].getY(), 0));
		for (int i = 0; i < l; i++) {
			popped = ((xPoint) max.getMax()).index;
			ret[i] = maxHeap.heap[popped];
			max.delete();
			if (popped * 2 + 1 < maxHeap.n)
				max.insert(new xPoint(maxHeap.heap[popped * 2 + 1].getX(), maxHeap.heap[popped * 2 + 1].getY(), popped * 2 + 1));
			if (popped * 2 + 2 < maxHeap.n)
				max.insert(new xPoint(maxHeap.heap[popped * 2 + 2].getX(), maxHeap.heap[popped * 2 + 2].getY(), popped * 2 + 2));
		}

		MinHeap min = new MinHeap(k);
		min.insert(new xPoint(minHeap.heap[0].getX(), minHeap.heap[0].getY(), 0));
		for (int i = 0; i < h; i++) {
			popped = ((xPoint) min.getMin()).index;
			ret[i + l] = minHeap.heap[popped];
			min.delete();
			if (popped * 2 + 1 < minHeap.n)
				min.insert(new xPoint(minHeap.heap[popped * 2 + 1].getX(), minHeap.heap[popped * 2 + 1].getY(), popped * 2 + 1));
			if (popped * 2 + 2 < minHeap.n)
				min.insert(new xPoint(minHeap.heap[popped * 2 + 2].getX(), minHeap.heap[popped * 2 + 2].getY(), popped * 2 + 2));
		}
		ret[k-1] = median;
		return ret;
	}

	@Override
	public Point[] getAllPoints() {
		Point[] pArr = new Point[maxHeap.n+minHeap.n + 1];
		int pArrIndex = 0;
		for (int j=0; j<maxHeap.n; j++) {
			pArr[pArrIndex] = maxHeap.heap[j];
			pArrIndex++;
		}
		for (int j=0; j<minHeap.n; j++) {
			pArr[pArrIndex] = minHeap.heap[j];
			pArrIndex++;
		}
		pArr[pArrIndex] = this.median;
		return pArr;
	}

	//TODO: add members, methods, etc.

	private class xPoint extends Point {

		int index;

		public xPoint(int x, int y, int index) {
			super(x, y);
			this.index = index;
		}
	}

}

