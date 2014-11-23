package textedu;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;


public class MessageHandler {

	private static Scanner scanInput;
	static String phonenum;
	static String txtmsg = "";
	static HashMap<String, User> userMap = new HashMap<String, User>();
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		scanInput = new Scanner(System.in);
		
		while(true) {
		System.out.print("--------------------\n#: ");
		phonenum = scanInput.nextLine();
		System.out.print("Message: ");
		txtmsg = scanInput.nextLine();
		System.out.println();
		System.out.println(getText(phonenum, txtmsg));
		}
	}
	
	public static String getText(String phonenum, String txtmsg) {
		String txtMsgLowercase = txtmsg.toLowerCase();
		User currentUser = new User("1");
		if(userMap.containsKey(phonenum)) {
			currentUser = userMap.get(phonenum);
		}
		if(newUser(phonenum)){
			userMap.put(phonenum, new User(phonenum));
			return("Intro. Pick a username");
		}
		//If the user has no name but exists, they must be responding with their desired name
		else if(userMap.get(phonenum).getName().equalsIgnoreCase("NewUser")) {
			if(uniqueName(txtmsg)) {
				userMap.get(phonenum).setName(txtmsg);
				return(txtmsg + ", welcome to textedu. Instructions.");
			}
			else {
				return(txtmsg + " is taken. Please choose another username.");
			}
		}
		else if(txtmsg.equalsIgnoreCase("?") || txtmsg.equalsIgnoreCase("help")) {
			return(getHelp());
		}
		//NEED TO EXPAND: points msg always respond with all points
		else if(txtMsgLowercase.contains("points")) {
			return currentUser.getPoints().toString();
		}
		
		return "No Response";
		}

	private static boolean newUser(String number) {
		if (userMap.containsKey(number)) {
			return false;
		}
		return true;
	}
	private static String getHelp() {
		return "Instructions.";
	}
	
	private static boolean uniqueName(String name) {
		//Iterate through all users, check if the name is taken
		java.util.Iterator<Entry<String, User>> iterator = userMap.entrySet().iterator() ;
        while(iterator.hasNext()){
           Entry<String, User> temp = iterator.next();
           User current = temp.getValue();
           if(current.getName().equalsIgnoreCase(name)) {
        	  return false;
           }
        }
        return true;
	}
}