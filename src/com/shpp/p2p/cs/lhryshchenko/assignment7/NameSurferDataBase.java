package com.shpp.p2p.cs.lhryshchenko.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.util.ErrorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {

    private HashMap<String, NameSurferEntry> dataBase = new HashMap<String, NameSurferEntry>();
    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        BufferedReader br = null;

        try {
            /* Open the file for reading. */
            br = new BufferedReader(new FileReader(filename));

            /* Process the file by adding one word */
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                NameSurferEntry entry = new NameSurferEntry(line);

                //add element to database.

                dataBase.put(entry.getName(), entry);
                System.out.println( dataBase.put(entry.getName(), entry));
            }

            br.close();

        } catch (IOException e) {
            throw new ErrorException(e);
        }
    }

    /* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        name = cleanLine(name);

        return dataBase.get(name);
    }

    private String cleanLine(String name) {
        String res = "";
        char curChar = name.charAt(0);
        res += Character.toUpperCase(curChar);
        for (int i = 1; i < name.length(); i++) {
             curChar = name.charAt(i);
            if (Character.isLetter(curChar)) {
                res += Character.toLowerCase(curChar);
            }
        }
        return res;
    }
}

