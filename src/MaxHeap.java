public class MaxHeap {
	public Point heap[] = new Point[10000];
	public int n = 0, child, parent, child1, child2;

	public void insert(Point value){
		heap[n] = value; // insert the new value in the last position of the array

		parent = (n+1)/2 - 1;
		if(parent < 0) {n++; return;} // if the only node present is the root, then return immediately
		child = n;

		// do bubble up if there are more than two nodes in the tree
		while(true){

			// only need to compare newly inserted node and its parent
			if(heap[child].getY() > heap[parent].getY() || (heap[child].getY() == heap[parent].getY() && heap[child].getX() > heap[parent].getX())){

				// swap the child and the parent if the child is greater than parent in case of max heap
				Point temp = heap[child];
				heap[child] = heap[parent];
				heap[parent] = temp;

				// stop bubble up when the root is reached
				if(parent == 0) break;

				// update the child and parent. The previous parent becomes the new child
				child = parent;
				parent = (parent + 1)/2 - 1;
			}
			else break;
		}
		n++;
	}

	// function which deletes the max element(or root) of the heap
	public Point delete(){

		// bring the last element to the first position of the array
		Point ele = heap[0];
		heap[0] = heap[n-1];

		parent = 0;

		while(true){
			child1 = parent*2 + 1;
			child2 = parent*2 + 2;

			// condition to stop bubble down
			if(child1 > n-1 || child2 > n-1) break;

			// swap the parent with the largest of its two children
			if((heap[parent].getY() < heap[child1].getY() || (heap[parent].getY() == heap[child1].getY() && heap[parent].getX() < heap[child1].getX()))
					|| (heap[parent].getY() < heap[child2].getY()|| (heap[parent].getY() == heap[child2].getY() && heap[parent].getX() < heap[child2].getX()))){

				if(heap[child1].getY() > heap[child2].getY() || (heap[child1].getY() == heap[child2].getY() && heap[child1].getX() > heap[child2].getX())){
					Point temp = heap[child1];
					heap[child1] = heap[parent];
					heap[parent] = temp;
					parent = child1; // update the new parent
				}
				else{
					Point temp = heap[child2];
					heap[child2] = heap[parent];
					heap[parent] = temp;
					parent = child2; // update the new parent
				}
			}
			else break;
		}
		n--;
		return ele; // return the max element that was deleted from the max heap(the root)
	}

	// function to simply return the max element of the heap(or root) without deleting it
	public Point getMax(){
		return heap[0];
	}
}
