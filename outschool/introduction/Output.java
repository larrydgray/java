/**
 * This class will demonstrated some console output.
 * All programs do one thing (Input/Process/Output).
 * Or in this case simply (Process/Output).
 */
public class Output {
    public static void main(String args[]){
        /* In Java everything is an Object. So you can't simply say println("bla bla"); */
        /* All java.lang classes are imported implicitly(automatically). */
        /* So the line below says in java.lang.System.out call the println() method. */
        /* 'out' is a PrintStream to system console output stream. */
        /* With java you can not use color for either foreground or background without add on library
           called JCurses. The reason being that Java was meant to be system independent.
         */
        System.out.println("This is "+'a'+" Java output line. "+5);
        /*  In Java you will use "" for Strings and '' for single characters. */
        /*  + concatenates text with variables or character literals or number constants. */
        int a=5;
        char c='a';
        System.out.println("This is "+c+" Java output line. "+a);
        /* The above shows how to use local variables in output */
        String front="This is "+c; // This is how you can define a String object.
        String end=" Java Output line. "+a;
        System.out.println(front+end); // Java takes the front and end and makes a single String object
        /* It then calls the toString() method of the temporary String object. */
    }
}
/* There is more advanced output options with Java that you can research.
*  System.out.printf("My name is: %s%n", "joe");  C style
*  String output = String.format("%s = %d", "joe", 35); C style
*  Search google for "formatting numbers or dates in Java"
*  */
