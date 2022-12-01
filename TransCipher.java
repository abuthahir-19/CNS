import java.util.Scanner;

public class TransCipher {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println ("Enter the plain text :");
        String plainText = in.nextLine();
        in.close();;
        String s = "";
        int start = 0;
        //removing the spaces from plain text
        for (int i = 0; i < plainText.length(); i++) {
            if (plainText.charAt(i) == ' ') {
                s += (plainText.substring(start, i));
                start = i+1;
            }
        }
        s+= plainText.substring(start);
        System.out.println ("Plain text after removing spaces : " + s);

        int k = s.length();
        int l = 0;
        int col = 4;
        int row = s.length() / col;
        System.out.println (row + " " + col + " " + s.length());
        char ch[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (l < k) {
                    ch[i][j] = s.charAt(l);
                    l+=1;
                } else {
                    ch[i][j] = '#';
                }
            }
        }

        char trans[][] = new char[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                trans[j][i] = ch[i][j];
            }
        }

        System.out.println ("Encrypted cipher Text : ");
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.print (trans[i][j] + "");
            }
        }
        System.out.println ();
    }
}
