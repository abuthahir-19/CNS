import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class DES {
    public static void main(String[] args) {
        try {
            System.out.println ("-----------------Message encryption using DES Algorithm--------------");
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keyGenerator.generateKey();
            Cipher desCipher;
            desCipher = Cipher.getInstance ("DES/ECB/PKCS5Padding");
            desCipher.init (Cipher.ENCRYPT_MODE, myDesKey);
            byte[] text = "Secret Information".getBytes();
            System.out.println ("Input Message in Byte format : " + text);
            System.out.println ("Input Message : " + new String (text));
            byte[] encryptedText = desCipher.doFinal(text);
            System.out.println ("Encrypted Message : " + new String (encryptedText));
            desCipher.init (Cipher.DECRYPT_MODE, myDesKey);
            byte[] decryptedText = desCipher.doFinal(encryptedText);
            System.out.println ("Decrypted Message : " + new String (decryptedText));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();;
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } 
    }
}
