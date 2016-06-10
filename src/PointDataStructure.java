
public class PointDataStructure implements PDT {
	Point median;

	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint) {
		this.median = initialYMedianPoint;
		boolean isSorted = checkSorted(points);
		int medianIndex = getMedianIndex(points,initialYMedianPoint);
		if (isSorted) { //if sorted->insert into heaps
			for (int j=0; j<medianIndex; j++) {
				//insert into max heap
			}
			for (int j=medianIndex+1; j<points.length; j++) {
				//insert into min heap
			}
		}
		else { //if not sorted-> max value is n-1 and values are consecutive -> insert into heaps
			for (int j=0; j<points.length; j++) {
				if(points[j].getX() < this.median.getX()) {
					//insert into max heap
				}
				else {
					// insert into min heap
				}
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
		if (point.getX() > this.median.getX()) { //if point should go into min heap
			//max heap <-median <- extract min, insert point into minHeap
		}
		else { //if point should go into max heap

		}
	}

	@Override
	public Point[] getPointsInRange(int XLeft, int XRight) {
		//
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point[] getMedianPoints(int k) {
		// TODO Auto