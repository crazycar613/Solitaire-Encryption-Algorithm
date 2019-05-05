/*Name: Cheung Chun Fung
  Student ID:180468483
*/
import java.util.*;
import java.io.*;
public class SolitaireEncryption {
	public static LinkedList s= new LinkedList();
	public static LinkedList keylist = new LinkedList();
	public static DeorEncryption deOren = new DeorEncryption();
	
	public static void main(String [ ] args)throws FileNotFoundException{
		try{  // try to catch if there is an exception that occurs 
	
			String filename = args[1];
			processFile(filename);
			if(args[0].equals("keygen")){
				printStep(args[2]);
				System.out.println("Keystream values : "+ keylist );
			}else if(args[0].equals("en")){
				findKey(args[2]);
				LinkedList MSG = deOren.getMsg(args[2]);
				deOren.Encryption(MSG , keylist);
			}else if(args[0].equals("de")){
				findKey(args[2]);
				LinkedList MSG = deOren.getMsg(args[2]);
				deOren.Decryption(MSG , keylist);
			}else{
				System.out.println("Please enter correct option");
			}
		}
		catch (FileNotFoundException e) { //catch the FileNotFoundException 
			System.out.println("Please enter the correct text file.");
		}
		catch (ArrayIndexOutOfBoundsException e) { //catch the ArrayIndexOutOfBoundsException
			System.out.println("Please enter the correct format ! [option] [deck_file] [message_string]");
		}
		

	}
	public static void findKey(String msg){//find the key
		int count = 0;
		int key;
		while(count<msg.length()){
			s.findJokerA();
			s.findJokerB();
			s.tripleCut();
			s.countCut();
			key=s.generateKey();
			if(key==27||key==28){
				continue;
			}
			keylist.addToTail(key);
			count++;
		}
	}
	
	
	public static void printStep(String msg){ // print the step
		int count = 0;
		int key;
		while(count<msg.length()){		
			s.findJokerA();
			System.out.println("S1 : "+s);
			s.findJokerB();
			System.out.println("S2 : "+s);
			s.tripleCut();
			System.out.println("S3 : "+s);
			s.countCut();
			System.out.println("S4 : "+s);
			key=s.generateKey();
			if(key==27||key==28){
				System.out.println("Joker : Key Skipped");
				continue;
			}
			keylist.addToTail(key);
			System.out.println("Key"+(1+count)+" : " +key);
			count++;
		}
	}
	public static void processFile(String filename) throws FileNotFoundException { // read file
		Scanner fin = new Scanner(new File(filename));
		int num;

		while (fin.hasNextInt()) {
			num = fin.nextInt();
			s.addToTail(num);
		}
	}
}