import java.util.Scanner;

public class CeaserCipher {
    public String encrypt (String msg, int offset) {
        String cipherText = "";
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        msg = msg.toUpperCase();
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            int indx = (alpha.indexOf(ch) + offset) % alpha.length();
            cipherText += alpha.charAt(indx);
        }
        return cipherText.toLowerCase();
    }

    public String decrypt (String msg, int offset) {
        String plainText = "";
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cipherText = encrypt(msg, offset).toUpperCase();
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            int idx = (alpha.indexOf(ch) - offset) % alpha.length();
            plainText += alpha.charAt(idx);
        }
        return plainText.toLowerCase();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        System.out.print ("Enter input message : ");
        String msg = in.next();
        System.out.print ("Enter the offset : ");
        int offset = in.nextInt();
        CeaserCipher ob = new CeaserCipher();
        System.out.println ();
        System.out.println ("-----------------------------------------------");
        System.out.println ("Simulating Ceaser Cipher");
        System.out.println ("Given input message : " + msg);
        System.out.println ("Encrypted Cipher Text : " + ob.encrypt(msg, offset));
        System.out.println ("Decrypted Plain Text : " + ob.decrypt(msg, offset));
        in.close();
    }
}