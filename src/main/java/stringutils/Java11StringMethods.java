package stringutils;

import java.util.Objects;

public class Java11StringMethods {

	public static void main(String[] args) {
		
		String x = null;
        System.out.println(Objects.nonNull(x));            //Output : false
		
        System.out.println("".isBlank());                  //Output : true

        System.out.println("".isBlank());                  //Output : true
        System.out.println("   ".isBlank());               //Output : true
        System.out.println("\t \t".isBlank());             //Output : true
        System.out.println("\n \n".isBlank());             //Output : true
        System.out.println("STRING".isBlank());            //Output : false
        System.out.println("String \t \n".isBlank());      //Output : false

	}

}
