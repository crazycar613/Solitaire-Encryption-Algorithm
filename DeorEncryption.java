/*Name: Cheung Chun Fung
  Student ID:180468483
*/
public class DeorEncryption{
	public char [] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	public LinkedList getMsg(String msg){
		LinkedList  msgSave = new LinkedList();
		char[] chars = msg.toCharArray();
		for(int i=0; i<chars.length ;i++){
			if(Character.isLetter(chars[i])){
				msgSave.addToTail(Character.toUpperCase(chars[i]));
			}
		}
		return msgSave;
	}
	
	public int alphabetTranslale(char singleMsg){ //getting the num for the user of translation
		int num=0;
		for(int i=0; i<alphabet.length;i++){
			if(alphabet[i]==singleMsg)
				num=i;
		}
		return (num+1);
	}
	
	public void Encryption(LinkedList MSG,LinkedList keylist){ // doing the encryption
		int total = MSG.getCount();
		char [] encmsg = new char [total];
		int i=0;
		while(i<total){
			char singleMsg = (char)MSG.getItemAt(i);
			int num = alphabetTranslale(singleMsg);
			System.out.print(singleMsg +"	");
			System.out.print(num+"	");
			System.out.print(keylist.getItemAt(i)+"	");
			int sum = (int)keylist.getItemAt(i)+num;
			if(sum>26)
				sum=sum-26;
			System.out.print(sum+"	");
			System.out.println(alphabet[sum-1]);
			encmsg[i]=alphabet[sum-1];
			i++;
		}
		System.out.print("Encrypted message: ");
			for(int j=0;j<encmsg.length;j++)
			System.out.print(encmsg[j]);
	}
	
		public void Decryption(LinkedList MSG,LinkedList keylist){ // doing the decryption
		int total = MSG.getCount();
		char [] decmsg = new char [total];
		int i=0;
		while(i<total){
			char singleMsg = (char)MSG.getItemAt(i);
			int num = alphabetTranslale(singleMsg);
			System.out.print(singleMsg +"	");
			System.out.print(num+"	");
			System.out.print(keylist.getItemAt(i)+"	");
			int sum = num - (int)keylist.getItemAt(i);
			if(sum<1)
				sum=sum+26;
			System.out.print(sum+"	");
			System.out.println(alphabet[sum-1]);
			decmsg[i]=alphabet[sum-1];
			i++;
		}
		System.out.print("Encrypted message: ");
			for(int j=0;j<decmsg.length;j++)
			System.out.print(decmsg[j]);
	}
	
	
}