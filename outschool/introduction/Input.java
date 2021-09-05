/* imports go at the top of the source file just below a package statement if
   there is a package statement. In these source examples
   we don't define a package so these classes are in the "default" package which is the
   current directory.
 */

import java.util.Scanner;

/**
 * There are a couple of ways to get input but I will
 * demonstrate use of the Java Scanner class in this example.
 */

public class Input {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);  // Output user input
    }


}
