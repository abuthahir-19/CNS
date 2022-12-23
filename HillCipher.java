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

    public static int getMultiplicativeInverse (int value) {
        int i = 1;
        int prod = value * i;
        while (prod % 26 != 1) {
            i += 1;
            prod = value * i;
        }
        return i;
    }

    public static int getDeterminant (int[][] keyMatrix) {
        int sum = (keyMatrix[0][0] * ((keyMatrix[1][1] * keyMatrix[2][2]) - (keyMatrix[2][1] * keyMatrix[1][2]))) - (keyMatrix[0][1] * (keyMatrix[1][0]*keyMatrix[2][2] - keyMatrix[2][0] * keyMatrix[1][2])) + (keyMatrix[0][2] * (keyMatrix[1][0] * keyMatrix[2][1] - keyMatrix[2][0] *keyMatrix[1][1]));
        return (sum % 26) < 0 ? (sum % 26) + 26 : (sum % 26);
    }

    public static int[][] getKInverse (int[][] adj, int det) {
        int kInv[][] = new int[3][3];
        if (det > 1) {
            det = getMultiplicativeInverse(det);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                kInv[i][j] = (adj[i][j] * det) % 26;
            }
        }

        return kInv;
    }

    public static int[][] getAdjointMatrix (int[][] keyMatrix) {
        int[][] key = new int[5][5];
        int[][] adj = new int[3][3];
        int row = 0, c = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                key[i][j] = keyMatrix[i][j];
            }
        }

        // repeating first two columns
        for (int i = 0; i < 3; i++) {
            int col = 0;
            for (int j = 3; j < 5; j++) {
                key[i][j] = keyMatrix[i][col];
                col++;
            }
        }

        //repeating first two rows 
        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                key[i][j] = key[row][j]; 
            }
            row += 1;
        }

        for (int i = 0; i < 3; i++) {
            int r = 1;
            for (int j = 0; j < 3; j++) 
            {
                int val = (key[r][c]*key[r+1][c+1] - key[r+1][c]*key[r][c+1]) % 26;
                if (val < 0) val = val + 26;
                adj[i][j] = val;
                r += 1;
            }
            c+=1;
        }
        return adj;
    }


    public static String encrypt (int[][] keyMatrix, String msg) {
        int[][] cipherMatrix = new int[1][3];
        int[][] msgArray = new int[1][3];
        String cipherText = "";
        for (int i = 0; i < 3; i++) {
            msgArray[0][i] = msg.charAt(i) % 65;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                cipherMatrix[j][i] = 0;
                for (int x = 0; x < 3; x++) {
                    cipherMatrix[j][i] += (msgArray[j][x] * keyMatrix[x][i]);
                }
                cipherMatrix[j][i] = cipherMatrix[j][i] % 26;
                cipherText += (char) (cipherMatrix[j][i] + 65);
                
            }
        }
        return cipherText;
    }

    public static String decrypt (int[][] kInv, String cipher) {
        int[][] cipherMat = new int[1][3];
        int[][] plainMat = new int[1][3];
        String plain= "";
        for (int i = 0; i < 3; i++) {
            cipherMat[0][i] = cipher.charAt(i) % 65;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                plainMat[j][i] = 0;
                for (int x = 0; x < 3; x++) {
                    plainMat[j][i] += (cipherMat[j][x] * kInv[x][i]);
                }
                plainMat[j][i] = plainMat[j][i] % 26;
                plain += (char) (plainMat[j][i] + 65);
            }
        }

        return plain;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println ("Enter the plain text : ");
        String plain = in.next().toUpperCase();
        System.out.println ("Enter the key : ");
        String key = in.next().toUpperCase();
        String enc = "", dec = "";
        int end = 3;
        int[][] keyMatrix = new int[3][3];
        int[][] adj = new int[3][3];
        int[][] kInv = new int[3][3];
        generateKeyMatrix(key, keyMatrix);

        for (int i = 0; i < plain.length(); i+=3) {
            String sub = "";
            for (int j = i; j < end; j++) {
                sub += plain.charAt(j);
            }
            enc += encrypt(keyMatrix, sub);
            end += 3;
        }
        adj = getAdjointMatrix(keyMatrix);
        kInv = getKInverse(adj, getDeterminant(keyMatrix));

        end = 3;
        for (int i = 0; i < enc.length(); i+=3) {
            String sub = "";
            for (int j = i; j < end; j++) {
                sub += enc.charAt(j);
            }
            System.out.println (sub);
            dec += decrypt(kInv, sub);
            end += 3;
        }

        System.out.println ("---------------------------------------");
        System.out.println ("Simulating Hill Cipher");
        System.out.println ("Actual Input Message : " + plain);
        System.out.println ("Encrypted Cipher Text : " + enc);
        System.out.println ("Decrypted Plain Text : " + dec);
        in.close();
    }
}

/**
ACT
GYBNQKURP

PEN
ACTIVATED

PAYMOREMONEY
RRFVSVCCT
 */