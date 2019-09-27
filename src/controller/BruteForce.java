package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class BruteForce {
    Controller controller;

    public BruteForce(Controller c) throws Exception {
        this.controller = c;
        ArrayList<String> possibleKeys = new ArrayList<String>(); // holds possible keys

        TreeSet<String> wordList = new TreeSet<String>(); // populate tree with wordlist
        File words = new File("C:\\Users\\LANSEL Dorian\\IdeaProjects\\Mad Max Project\\src\\words.txt");
        Scanner in = new Scanner(words);
        while (in.hasNextLine())
            wordList.add(in.nextLine());
        in.close();

        byte[] tempkey = new byte[4];
        //FileReader fr = new FileReader(file);
        //BufferedReader br = new BufferedReader(fr);
        for (int i = 97; i <= 122; i++) {
            for (int j = 97; j <= 122; j++) {
                for (int k = 97; k <= 122; k++) {
                    for (int l = 97; l <= 122; l++) {
                        tempkey[0] = (byte) i;
                        tempkey[1] = (byte) j;
                        tempkey[2] = (byte) k;
                        tempkey[3] = (byte) l;

                        ArrayList<String> tokens = new ArrayList<String>();
                        String decryptedStuff = controller.encryptDecrypt(new StringBuilder().append((char) tempkey[0]).append((char) tempkey[1]).append((char) tempkey[2]).append((char) tempkey[3]).toString());
                        String[] tokenize = decryptedStuff.split(" ");
                        for (String s : tokenize)  //tokenize decrypted ciphertext
                        {
                            tokens.add(s);
                        }

                        int confidence = 0;

                        for (int w = 0; w < tokens.size(); w++) {
                            //String s = tokens.get(w).toLowerCase();
                            String r = tokens.get(w);
                            String s = r.split("[^a-zA-Z]").length > 1 ? r.split("[^a-zA-Z]")[1] : r;

                            //s = s.replaceAll("[^a-zA-Z]", ""); // remove all non-alpha characters from current token?

                            if (s.length() >= 3 && wordList.contains(s)) //ignore words less than 3 characters to improve speed
                            {
                                System.out.println(" - Match found using token [" + s + "] and key " + (char) tempkey[0] + (char) tempkey[1] + (char) tempkey[2] + (char) tempkey[3]);
                                confidence++;
                            }
                        }
                        if (confidence >= 4) // add key to list of possible solutions as well as a confidence level
                            possibleKeys.add(confidence + " " + (char) tempkey[0] + (char) tempkey[1] + (char) tempkey[2] + (char) tempkey[3]);
                    }
                }
            }
        }

        Collections.sort(possibleKeys, Collections.reverseOrder()); // sort possible keys in order from highest confidence to least

        System.out.println("Now printing 5 best matches. The number at the start is the confidence level and it is followed by the four-digit key.");
        for (int r = 0; r < possibleKeys.size() && r < 5; r++) // print possible keys that were found
        {
            System.out.println(possibleKeys.get(r));
        }

        String temp = possibleKeys.get(0);
        temp = temp.substring(temp.length() - 4);
        System.out.println("The key used to encrypt was probably " + temp);
        controller.writeFile(temp);
    }
}
