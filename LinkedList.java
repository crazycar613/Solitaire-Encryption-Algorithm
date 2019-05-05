/*Name: Cheung Chun Fung
  Student ID:180468483
*/
public class LinkedList {
	private ListNode head;
	private ListNode tail;
	private int count;

	public LinkedList() {
		head = null;
		tail = null;
		count = 0;
	}

	public boolean isEmpty() {
		return (head==null);
	}

	public int getCount() {
		return count;
	}

	public void addToHead(Object item) {
		count++;
		if (isEmpty()) {
			head = tail = new ListNode(item);
		} else {
			head = new ListNode(item, head);
		}
	}

	public void addToTail(Object item) {
		count++;
		if (isEmpty()) {
			head = tail = new ListNode(item);
		} else {
			tail.next = new ListNode(item);
			tail =  tail.next;
		}
	}

	public Object removeFromHead() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		} 
		count--;
		Object item = head.data;
		if (head == tail) // there's only one single node
			head = tail = null;
		else
			head = head.next;
		return item;
	}

	public Object removeFromTail() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		} 
		count--;
		Object item = tail.data;
		if (head == tail) {   // there is only one node
			head = tail = null;
			return item;
		}
		// search for the second last node 
		ListNode current = head;
		while (current.next != tail)
			current = current.next;
		// set second last node as new tail
		tail = current;
		tail.next = null;
		return item;
	}

	public Object getItemAt(int n) {
		if (n < 0 || n >= count)
			throw new IndexOutOfBoundsException();
		int currentPos=0;
		ListNode current=head;
		while (currentPos < n) {
			current=current.next;
			currentPos++;
		}
		return current.data;
	}

	public Object removeItemAt(int n) {
		if (n < 0 || n >= count)
			throw new IndexOutOfBoundsException();
		if (n==0) {
			return (removeFromHead());
		}
		
		int currentPos=0;
		ListNode current=head;
		while (currentPos < (n-1)) { // locate the node before the one to be removed
			current=current.next;
			currentPos++;
		}
		Object item = current.next.data;
		current.next = current.next.next;
		count--;
		return item;
	}


	public void addItemAt(Object item, int n) {
		if (isEmpty() || n==0) {
			addToHead(item);
			return;
		}
		if (n >= count) {
			addToTail(item);
			return;
		}

		int currentPos=0;
		ListNode current=head;
		while (currentPos < (n-1)) {  // locate the node before the insertion point
			current=current.next;
			currentPos++;
		}
		ListNode newNode = new ListNode(item);
		newNode.next = current.next;
		current.next = newNode;
		count++;
	}



	public String toString() {
		String s = "[ ";

		// traverse the list from head towards tail
		ListNode current = head;
		while (current != null) {
			s += current.data + " ";
			current = current.next;
		}
		return s + "]";
	}
	public void findJokerA() { //find joker A
		ListNode current = head;
		while (current.next != null) {
			if ((int) head.data == 27) {
				int nextToHead = (int)head.next.data;
				removeFromHead();
				removeFromHead();
				addToHead(27);
				addToHead(nextToHead);
			}else if ((int) tail.data == 27) {
				addToHead(27);
				removeFromTail();
				break;
			}else if ((int) current.next.data == 27 && current.next.next != null) {
				current.next.data = current.next.next.data;
				current.next.next.data = 27;
				break;
			} 
			current = current.next;
		}
	}
	
	public void findJokerB(){ //find joker B
		ListNode current = head; 
		while (current.next != null) {
			if ((int) head.data == 28){
				int nextToHead = (int)head.next.data;
				int nextToHead2 = (int)head.next.next.data;
				removeFromHead();
				removeFromHead();
				removeFromHead();
				addToHead(28);
				addToHead(nextToHead2);
				addToHead(nextToHead);
			}else if((int) tail.data == 28){
				int headNumber = (int)head.data;
				removeFromTail();
				removeFromHead();
				addToHead(28);
				addToHead(headNumber);
				break;
			}else if ((int) current.next.data == 28 && current.next.next.next != null) {
				current.next.data = current.next.next.data;
				current.next.next.data = current.next.next.next.data;
				current.next.next.next.data = 28;
				break;
			}else if ((int) current.next.data == 28 && current.next.next != null) {
				int tailNumber = (int)tail.data;
				removeFromTail();
				removeFromTail();
				addToHead(28);
				addToTail(tailNumber);
				break;
			}
			current = current.next;
		}
	
	}
	
	public int findPositon(int num){ //find the position of the card
		ListNode current = head;
		int position = 0;
		while((int)current.data!=num){
			current=current.next;
			position++;
		}
		return position;
	}
	
	public void tripleCut(){ //triple cut
		int JokerAposi=findPositon(27);
		int JokerBposi=findPositon(28);
		if(JokerAposi>JokerBposi){
			int count=27-JokerAposi;
			for(int i=JokerAposi+1;i<28;i++){
				int j=0;
				int item = (int) removeFromTail();
				addToHead(item);
			}
			int NewJokerB= findPositon(28);
			for(int i=count;i<NewJokerB;i++){
				int item =(int) removeItemAt(count);
				addToTail(item);
			}
		}
		else if(JokerBposi>JokerAposi){
			int count=27-JokerBposi;
			for(int i=JokerBposi+1;i<28;i++){
				int j=0;
				int item = (int) removeFromTail();
				addToHead(item);
			}
			int NewJokerA= findPositon(27);
			for(int i=count;i<NewJokerA;i++){
				int item =(int) removeItemAt(count);
				addToTail(item);
			}
		}
	}
	
	public void countCut(){ // remove the amout cards that match the last number and put in front of the number.
		int lastNumber = (int)tail.data;
		int i=0;
		if(lastNumber<27) {
    		int number = (int)removeFromTail();
    		for(ListNode current=head; current.next!=null&&i<lastNumber; current = current.next) {
    			int swap = (int)removeFromHead();
    			addToTail(swap);
    			i++;
    		}
    		addToTail(number);
    	}
	}
	
	public int generateKey(){ // generate the key
		int firstKeyNumber = (int)head.data;
		if(firstKeyNumber == 28){
			return (int)tail.data;
		}else
			return (int)getItemAt(firstKeyNumber);
	}
}





