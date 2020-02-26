package org.wso2.cipher.encryption.decryption.tool;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;

public class Receive {

    public static void main(String args[]) {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("keyfile"));
            DESKeySpec ks = new DESKeySpec((byte[]) ois.readObject());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey key = skf.generateSecret(ks);

            Cipher c = Cipher.getInstance("DES/CFB8/NoPadding");
            c.init(Cipher.DECRYPT_MODE,key, new IvParameterSpec( (byte[]) ois.readObject()));

            CipherInputStream cis = new CipherInputStream(new FileInputStream("ciphertext"),c);
            BufferedReader br = new BufferedReader(new InputStreamReader(cis));
            System.out.println("Got message - " + br.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
