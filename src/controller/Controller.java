package controller;

import com.sun.tools.javac.Main;
import view.View;

import java.io.*;

public class Controller {
    String original = "awqpmndf";
    String PATH_DESTINATION;
    String inputString;

    public void launchProcedure(String path,String destination) throws Exception {
        PATH_DESTINATION = destination;
        InputStream stream;
        stream = new FileInputStream(path);
        StringBuilder reponse;

        reponse = new StringBuilder();

        for (int a = stream.read(); a != -1; a = stream.read()) {
            reponse.append((char) a);
        }

        stream.close();

        inputString = reponse.toString();

        BruteForce b = new BruteForce(this);

        //encryptDecrypt("awqpmndfnbvc",reponse.toString());
        // generateKeyAndED(reponse.toString());
    }

    public String encryptDecrypt(String key) throws IOException {

        String nKey = original + key;

        StringBuilder sb1;
        char c1;
        char c2;
        char c3;
        int i;
        int ii;

        ii = 0;
        sb1 = new StringBuilder();

        for (i = 0; i < inputString.length(); i++) {
            c1 = inputString.charAt(i);
            c2 = nKey.charAt(ii);

            c3 = (char) (c1 ^ c2);
            sb1.append(c3);

            ii++;
            if (ii == nKey.length()) {
                ii = 0;
            }
        }
        return sb1.toString();
        //writeFile(sb1.toString());
    }

    public void writeFile(String goodKey) throws IOException {;
        OutputStream stream;
        String text = encryptDecrypt(goodKey);
        System.out.println(text);
        stream = new FileOutputStream(PATH_DESTINATION);
        byte[] buffer = text.getBytes();
        stream.write(buffer);
        stream.close();
        System.out.println("Terminé !");
        System.exit(-1);
    }

    private void generateKeyAndED(String inputString) {
        String letters[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String intitial = "awqpmndf";

        for (int i = 0; i < 26; i++) {
            String L1 = intitial + letters[i];

            for (int j = 0; j < 26; j++) {
                String L2 = (L1 + letters[j]);

                for (int k = 0; k < 26; k++) {
                    String L3 = (L2 + letters[k]);

                    for (int l = 0; l < 26; l++) {
                        String L4 = (L3 + letters[l]);

                        for (int m = 0; m < 26; m++) {
                            String L5 = (L4 + letters[m]);

                            for (int n = 0; n < 26; n++) {
                                String L6 = (L5 + letters[n]);

                                for (int o = 0; o < 26; o++) {
                                    String L7 = (L6 + letters[o]);

                                    for (int p = 0; p < 26; p++) {
                                        String L8 = (L7 + letters[p]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
