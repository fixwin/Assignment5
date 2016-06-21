public class MaxHeap {
	public Point heap[];
	public int n = 0, child, parent, child1, child2;

	public void insert(Point value){
		heap[n] = value;

		parent = (n+1)/2 - 1;
		if(parent < 0) {n++; return;}
		child = n;

		while(true){

			// only need to compare newly inserted node and its parent
			if(heap[child].getY() > heap[parent].getY() || (heap[child].getY() == heap[parent].getY() && heap[child].getX() > heap[parent].getX())){

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
	MaxHeap(int size) {
		this.heap = new Point[size];
	}


	public Point delete(){

		Point ele = heap[0];
		heap[0] = heap[n-1];

		parent = 0;

		while(true){
			child1 = parent*2 + 1;
			child2 = parent*2 + 2;

			// condition to stop bubble down
			if(child1 > n-1 || child2 > n-1) break;


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
		return ele;
	}

	public Point getMax(){
		return heap[0];
	}
}
