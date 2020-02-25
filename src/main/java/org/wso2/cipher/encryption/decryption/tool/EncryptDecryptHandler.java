package org.wso2.cipher.encryption.decryption.tool;

import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.*;

public class EncryptDecryptHandler {

    public static void main (String[] args) {

        try {
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
            Key key = kg.generateKey();

            c.init(Cipher.ENCRYPT_MODE, key);
            byte input[] = "need to encrypt this".getBytes();
            byte encrypted[] = c.doFinal(input);
            byte iv[] = c.getIV();
            System.out.println("Encrypted password is = " + Base64.getEncoder().encodeToString(encrypted));

            IvParameterSpec dsp = new IvParameterSpec(iv);
            c.init(Cipher.DECRYPT_MODE, key, dsp);
            byte output[] = c.doFinal(encrypted);
            System.out.println("Plain text value is = " + new String(output));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }


}

