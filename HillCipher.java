import java.util.Scanner;

public class HillCipher {
    public static void generateKeyMatrix (String key, int[][] keyMatrix) {
        int idx = 0;
        key = key.toUpperCase();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyMatrix[i][j] = (key.charAt(idx) % 65);
                idx += 1;
            }
        }
    }

    public static String encrypt (int[][] keyMatrix, String msg) {
        int[][] cipherMatrix = new int[3][1];
        int[][] msgArray = new int[3][1];
        String cipherText = "";
        for (int i = 0; i < 3; i++) {
            msgArray[i][0] = msg.charAt(i) % 65;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                cipherMatrix[i][j] = 0;
                for (int x = 0; x < 3; x++) {
                    cipherMatrix[i][j] += (msgArray[x][j] * keyMatrix[i][x]);
                }
                cipherMatrix[i][j] = cipherMatrix[i][j] % 26;
                cipherText += (char) (cipherMatrix[i][j] + 65);
            }
        }
        return cipherText;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String plain = in.next();
        String key = in.next();
        int[][] keyMatrix = new int[3][3];
        generateKeyMatrix(key, keyMatrix);

        System.out.println ("---------------------------------------");
        System.out.println ("Simulating Hill Cipher");
        System.out.println ("Encrypted Cipher Text : " + encrypt(keyMatrix, plain));
        in.close();
    }
}

/**
ACT
GYBNQKURP
 */