/*Name: Cheung Chun Fung
  Student ID:180468483
*/
public class EmptyListException extends RuntimeException { // catch if its empty in the list
	public EmptyListException() {
		super("List is empty.");
	}
}
