package crawler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;

public class Util {
	public static String[] splitString(String input) {
		String[] parts = input.split("|");
		return parts;
	}
	
	public static ArrayList<Integer> getIntegerArray(List<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
               //System.out.println("Could not parse " + nfe);
//                Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
            } 
        }       
        return result;
    }
}
