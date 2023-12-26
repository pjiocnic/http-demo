package pluralsight;

/*
 * Convert a Collection into an Array
 * src: https://learning.oreilly.com/scenarios/java-11-functional/9781492081333/
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class CollectionToArray {

	public static void main(String[] args) {
		jdk8Way();
		jdk11Way();
	}
	
	public static void jdk8Way() {
		List<String> monthCollection = new ArrayList<>();
		monthCollection.add("January");
		monthCollection.add("February");
		monthCollection.add("March");
		String[] monthArray = monthCollection.toArray(new String[monthCollection.size()]);
		System.out.println("Type (before): " + monthCollection.getClass());
		System.out.println("Size (before): " + monthCollection.size());
		System.out.println("Type (after): " + monthArray.getClass());
		System.out.println("Size (after): " + monthArray.length);
	}

	public static void jdk11Way() {
        List<String> monthCollection = new ArrayList<>();
        monthCollection.add("January");
        monthCollection.add("February");
        monthCollection.add("March");
        String[] monthArray = monthCollection.toArray(String[]::new);
        System.out.println("Type (before): " + monthCollection.getClass());
        System.out.println("Size (before): " + monthCollection.size());
        System.out.println("Type (after): " + monthArray.getClass());
        System.out.println("Size (after): " + monthArray.length);
	}
}
