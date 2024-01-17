package com.realEstate.realEstate.service.digitalSign;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;


public class DigitalSign {
    private static final String algorithm = "SHA256withRSA";


    byte [] sign(String dataFilename, PrivateKey privateKey)
            throws InvalidKeyException {
//		���� �б�
        byte[] content = readFile(dataFilename);
        byte [] signature = null;

        Signature sig;
        try {
            sig = Signature.getInstance(algorithm);
            sig.initSign(privateKey);
            sig.update(content);
            signature = sig.sign();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Arrays.fill(content, (byte) ' ');
        }

        return signature;
    }

    boolean verify(String dataFilename, PublicKey publicKey, String sigFilename)
            throws InvalidKeyException, FileNotFoundException {
        byte [] originContent = readFile(dataFilename);
        byte [] signedContent = readFile(sigFilename);
        boolean rslt = false;

        try {
            Signature sig = Signature.getInstance(algorithm);

            sig.initVerify(publicKey);
            sig.update(originContent);

            rslt = sig.verify(signedContent);

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Arrays.fill(originContent, (byte) ' ');
            Arrays.fill(signedContent, (byte) ' ');
        }

        return rslt;
    }

    byte [] readFile(String filename) {
        byte [] readBuffer = null;

        try (FileInputStream fileStream = new FileInputStream( filename )) {

            //���� ������ ���� ���� ����
            readBuffer = new byte[ fileStream.available( ) ];
            while (fileStream.read( readBuffer ) != -1) {}

//	       System.out.println(new String(readBuffer));
        } catch ( IOException e ) {
            System.out.println( "파일 입출력 에러!!" + e );
        }

        return readBuffer;
    }

    void writeFile(String filename, byte [] content) throws FileNotFoundException {

        try (FileOutputStream outputStream = new FileOutputStream(filename)){
            outputStream.write(content);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}