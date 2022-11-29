import java.util.Scanner;

public class PlayFairCipher {
    public void generateKeyTable (char[] key, char[][] keyT) {
        int dict[] = new int[26];
        int row = 0, col = 0;
        for (int i = 0; i < key.length; i++) {
            if (key[i] == 'j') keyT[row][col++] = 'i';
            if (key[i] != 'j')
            keyT[row][col++] = key[i];
            if (row >= 5) break;
            if (col >= 5) {
                col = 0;
                row += 1;
            }
            dict[key[i] - 97] = 1;
        }

        for (int i = 0; i < 26; i++) {
            char ch = (char) ((char) 97 + i);
            if (dict[i] == 0 && ch != 'j') {
                keyT[row][col++] = (char) ((char) 97 + i);
            }
            if (col >= 5) {
                col = 0;
                row += 1;
            }
            if (row >= 5) break;
        }
    }

    public char[] search (char a, char b, char[][] keyT) {
        int r1, c1, r2, c2;
        r1 = c1 = r2 = c2 = 0;
        char[] ret = new char[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyT[i][j] == a) {
                    r1 = i; c1 = j;
                }

                if (keyT[i][j] == b) {
                    r2 = i; c2 = j;
                }
            }
        }

        //same column
        if (c1 == c2) {
            r1 = (r1 + 1) % 5;
            r2 = (r2 + 1) % 5;
            ret[0] = keyT[r1][c1];
            ret[1] = keyT[r2][c2];
        }

        //same row
        if (r1 == r2) {
            c1 = (c1 + 1) % 5;
            c2 = (c2 + 1) % 5;
            ret[0] = keyT[r1][c1];
            ret[1] = keyT[r2][c2];
        }
        else {
            ret[0] = keyT[r1][c2];
            ret[1] = keyT[r2][c1];
        }
        return ret;
    }
    public String encrypt (char[] pt, char[][] keyT) {
        String cipherText = "";
        for (int i = 0; i < pt.length; i+=2) {
            char f = pt[i];
            char s = pt[i+1];
            char[] ret = search(f, s, keyT);
            cipherText += (ret[0] + "" + ret[1]);
        }
        return cipherText;
    }

    public String prepareText (String plain) {
        String newText = "";
        for (int i = 0; i < plain.length(); i++) {
            char ch = plain.charAt(i);
            if (newText.length() != 0 && newText.charAt(newText.length()-1) == ch) {
                newText += 'x';
                newText += ch;
            } else if (newText.length() != 0 && newText.charAt(newText.length()-1) != ch) {
                newText += ch;
            } else {
                newText += ch;
            }
        }
        int len = newText.length();
        if (len % 2 != 0) {
            newText += 'z';
        }
        return newText;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        String plain = in.next();
        char[] key = in.next().toCharArray();
        char[][] keyT = new char[5][5];
        
        PlayFairCipher ob = new PlayFairCipher();
        ob.generateKeyTable(key, keyT);
        char[] pt = ob.prepareText(plain).toCharArray();

        System.out.print (ob.encrypt(pt, keyT));
        in.close();
    }
}
/**
instruments
monarchy
 */