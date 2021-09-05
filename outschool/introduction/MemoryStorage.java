import java.util.ArrayList;

public class MemoryStorage {
    public static void main(String args[]) {
        // commonly used primitives.
        // Since the addition of autoboxing primitives can act like Objects.
        int i = 13; // 4 bytes
        char c = 'Z'; // 2 bytes for unicode characters unsigned
        float f = 3.234f; // f means float, 4 bytes
        // String is a special object (not a primitive) but doesn't require new String("The quick brown fox."); syntax.
        String s = "The quick brown fox.";

        // less commonly used primitives.
        byte b = 127; // signed byte
        short sh = 32000; // two bytes
        long l = 928928743; // 8 bytes
        double d = 89823.232353e-200d;  // d means double, 8 bytes

        // arrays which are indexed list of primitives or objects.
        int a[] = {3, 2, 8, 83, 1, 93}; // a[0] is 3; a[2] is 8; Arrays begin at 0 not 1
        String st[] = {"Mon", "Tue", "Jane", "Adam", "December"}; // st[4] is December

        // Collection List Data Structure stores objects, primitives are autoboxed(converted to an object).

        ArrayList mArrayList = new ArrayList(); // This stores any object types
        mArrayList.add("Hello");
        mArrayList.add(5);

        ArrayList sArrayList = new <String>ArrayList(); // stores only Strings.

    } // main method
} // MemoryStorage class
