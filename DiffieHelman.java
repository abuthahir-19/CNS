import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DiffieHelman {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println ("Enter a prime number : ");
        int q = in.nextInt();
        int alpha = findPrimitiveRoot(q);
        int Xa = 3;
        
        in.close();
    }

    public static boolean CheckForRange (List <Integer> list) {
        for (int i = 0; i < list.size()-1; i++) {
            if (!(list.get(i) == list.get(i+1)-1)) return false;
        }
        return true;
    }

    public static int findPrimitiveRoot (int value) {
        List <Integer> rnge;
        int al = 1;
        for (int i = 2; i < value; i++) {
            al = i;
            rnge = new ArrayList<>();
            for (int j = 1; j < value; j++) {
                rnge.add (((int) Math.pow (al, j)) % value);
            }
            Collections.sort (rnge);
            if (CheckForRange(rnge)) return al;
        }
        return al;
    }
}