public class MinHeap {
	public Point heap[] = new Point[10000];
	public int n, child, parent, child1, child2;

	public void insert(Point value){
		heap[n] = value; // insert the new value into the end of the array

		parent = (n+1)/2 - 1;
		if(parent < 0) {n++; return;} // if the heap contains only one node, then return immediately
		child = n;

		// bubble up
		while(true){
			if(heap[child].getY() < heap[parent].getY() || (heap[child].getY() == heap[parent].getY() && heap[child].getX() < heap[parent].getX())){
				Point temp = heap[child];
				heap[child] = heap[parent];
				heap[parent] = temp;
				if(parent == 0) break;
				child = parent;
				parent = (parent + 1)/2 - 1;
			}
			else break;
		}
		n++;
	}

	public Point delete(){

		// bring the last element of the array into the first position
		Point ele = heap[0];
		heap[0] = heap[n-1];

		parent = 0;

		// bubble down
		while(true){
			child1 = parent*2 + 1;
			child2 = parent*2 + 2;

			// condition for when to stop bubble down
			if(child1 > n-1 || child2 > n-1) break;

			// swap the parent with the least of its children in the case of min heap
			if((heap[parent].getY() > heap[child1].getY() || (heap[parent].getY() == heap[child1].getY() && heap[parent].getX() > heap[child1].getX()))
					|| (heap[parent].getY() > heap[child2].getY() || (heap[parent].getY() == heap[child2].getY() && heap[parent].getX() > heap[child2].getX()))){

				if(heap[child1].getY() < heap[child2].getY() || (heap[child1].getY() == heap[child2].getY() && heap[child1].getX() < heap[child2].getX())){
					Point temp = heap[child1];
					heap[child1] = heap[parent];
					heap[parent] = temp;
					parent = child1;
				}
				else{
					Point temp = heap[child2];
					heap[child2] = heap[parent];
					heap[parent] = temp;
					parent = child2;
				}
			}
			else break;
		}
		n--;
		return ele; // return the root(or min) element of the heap
	}

	// function to simply return the min element(or root) of the heap without deleting it
	public Point getMin(){
		return heap[0];
	}
}
