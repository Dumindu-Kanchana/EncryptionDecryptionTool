package org.wso2.cipher.encryption.decryption.tool;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Send {

    public static void main (String[] args) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            kg.init(new SecureRandom());
            SecretKey key = kg.generateKey();
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            Class spec = Class.forName("javax.crypto.spec.DESKeySpec");
            DESKeySpec ks = (DESKeySpec) skf.getKeySpec(key, spec);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("keyfile"));
            oos.writeObject(ks.getKey());

            Cipher c = Cipher.getInstance("DES/CFB8/NoPadding");
            c.init(Cipher.ENCRYPT_MODE, key);
            CipherOutputStream cos = new CipherOutputStream(new FileOutputStream("ciphertext"),c);
            PrintWriter pw = new PrintWriter(cos);
            pw.println("Stand and unfold yourself");
            pw.close();
            oos.writeObject(c.getIV());
            oos.close();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

