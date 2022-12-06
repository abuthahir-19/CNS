import java.util.Scanner;

public class VigenereCipher {
    public static String encrypt (String text, String key) {
        String res = "";
        text = text.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            int t1 = text.charAt(i) % 65;
            int k1 = key.charAt(i) % 65;
            int c1 = (t1 + k1) % 26;
            res += (char) ((char) c1 + 65);
        }
        return res;
    }

    public static String decrypt (String text, String key) {
        String plain = "";
        text = text.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            int t1 = text.charAt(i) % 65;
            int k1 = key.charAt(i) % 65;
            int p1 = ((t1 - k1) + 26) % 26;
            plain += (char) ((char) p1 + 65);
        }
        return plain;
    }

    public static String removeSpaces (String msg) {
        String s = "";
        int start = 0;
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            if (c == ' ') {
                s += msg.substring(start, i);
                start = i+1;
            }
        }
        s += msg.substring(start);
        return s;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        System.out.println ("Enter the key : ");
        String key = in.nextLine();
        System.out.println ("Enter the Plain Text : ");
        String msg = in.nextLine();
        key = removeSpaces(key);
        msg = removeSpaces(msg);

        // making the key equal to the message
        while (key.length() < msg.length()) {
            key += key;
        }

        key = key.substring(0, msg.length());
        System.out.println ("Key : " + key);
        System.out.println ("Message : " + msg);
        System.out.println ("--------------Simulating Vigenere Cipher----------------");
        System.out.println ("Input Message : " + msg);
        String enc = encrypt(msg, key);
        System.out.println ("Encrypted Cipher Text : " + enc);
        System.out.println ("Decrypted Plain Text : " + decrypt(enc, key).toUpperCase());
        in.close();
    }
}

/**
deceptive
we are discovered save yourself
 */