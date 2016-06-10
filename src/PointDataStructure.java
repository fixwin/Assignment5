
public class PointDataStructure implements PDT {
	Point median;
	MaxHeap maxHeap;
	MinHeap minHeap;
	Point[] sortedPointsArr;
	int medianIndex;
	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint) {
		this.median = initialYMedianPoint;
		sortedPointsArr = new Point[points.length +(int)(Math.log(points.length)/Math.log(2))]; //add log to the array size
		maxHeap = new MaxHeap();
		minHeap = new MinHeap();
		boolean isSorted = checkSorted(points);
		medianIndex = getMedianIndex(points,initialYMedianPoint);
		for (int j=0; j<points.length; j++) {
			if(points[j].equals(this.median)) continue;
			if(points[j].getY() < this.median.getY()) {
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
	}
	@Override
	public Point[] getPointsInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numOfPointsInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double averageHeightInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeMedianPoint() {
		//TODO
		if(maxHeap.n == minHeap.n) {
			this.median = maxHeap.getMax();
			maxHeap.delete();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point[] getAllPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO: add members, methods, etc.

}

