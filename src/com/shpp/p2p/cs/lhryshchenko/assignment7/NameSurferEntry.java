package com.shpp.p2p.cs.lhryshchenko.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {
    /**
     * name part of String we get
     */
    private String name = "";

    /**
     * number part of String we get
     */
    private String numbers = "";
    /**
     * array which store ranks values in decades
     */
    private int[] decades = new int[NDECADES];


    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        name = line.substring(0, line.indexOf(" "));
        numbers = line.substring(line.indexOf(" ")).trim();

        for (int i = 0; i < NDECADES - 1; i++) {
            decades[i] = Integer.parseInt(numbers.substring(0, numbers.indexOf(" ")));
            numbers = numbers.substring(numbers.indexOf(" ") + 1);
        }
        // last rank value of string hasn't " " (we use .trim() ), so it out of for loop.
        decades[NDECADES - 1] = Integer.parseInt(numbers);
    }

    /* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {

        return name;
    }

    /* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return decades[decade];
    }

    /* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        String res = "";
        res = "\"" + name + "[";
        for (int i = 0; i < NDECADES - 1; i++) {
            res += decades[i] + " ";
        }
        res += decades[NDECADES - 1] + "]\"";
        return res;
    }
}

