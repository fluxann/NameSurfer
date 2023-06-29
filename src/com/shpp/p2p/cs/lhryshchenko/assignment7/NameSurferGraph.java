package com.shpp.p2p.cs.lhryshchenko.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {
    /**
     * List which store name surfer entries
     */
    private ArrayList<NameSurferEntry> entriesList;

    /**
     * Lines and dates spacing
     */
    private double space;

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        entriesList = new ArrayList<NameSurferEntry>();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entriesList.clear();
        update();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        entriesList.add(entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        createGraph();

        for (int i = 0; i < entriesList.size(); i++) {
            createEntry(entriesList.get(i), i);
        }
    }

    private void createEntry(NameSurferEntry entry, int color) {
        space = getWidth() / NDECADES;

        //loop to create entry lines which connects two neighbour decades value of current name
        for (int i = 0; i < NDECADES - 1; i++) {
            GLine line = new GLine(space * i, valueHeight(entry.getRank(i)),
                    space * (i + 1), valueHeight(entry.getRank(i + 1)));

            // to repeat colours after each 4 lines
            if (color % 4 == 0) line.setColor(Color.BLUE);
            else if (color % 4 == 1) line.setColor(Color.RED);
            else if (color % 4 == 2) line.setColor(Color.MAGENTA);
            else if (color % 4 == 3) line.setColor(Color.BLACK);
            add(line);
        }

        //loop to create entry labels which represent current decade rank and current name.
        for (int i = 0; i < NDECADES; i++) {
            String entryLabel = "";

            // represent "*" instead of 0 rank
            if (entry.getRank(i) != 0) entryLabel = entry.getName() + " " + entry.getRank(i);
            else entryLabel = entry.getName() + " *";

            GLabel labelName = new GLabel(entryLabel, space * i, valueHeight(entry.getRank(i)));

            // to repeat colours after each 4 labels
            if (color % 4 == 0) labelName.setColor(Color.BLUE);
            else if (color % 4 == 1) labelName.setColor(Color.RED);
            else if (color % 4 == 2) labelName.setColor(Color.MAGENTA);
            else if (color % 4 == 3) labelName.setColor(Color.BLACK);
            add(labelName);
        }

    }

    /**
     * Method returns height representation depends of rank value
     */
    private double valueHeight(int rank) {
        double doubleRank = rank;
        if (rank != 0) {
            //rank coefficient
            doubleRank = doubleRank / MAX_RANK;

            // clear height of rank
            doubleRank = doubleRank * (getHeight() - 2 * GRAPH_MARGIN_SIZE);

            // keep space from top
            doubleRank = doubleRank + GRAPH_MARGIN_SIZE;
        } else {
            doubleRank = getHeight() - GRAPH_MARGIN_SIZE;
        }
        return doubleRank;
    }

    /**
     * Create graph basics
     */
    private void createGraph() {
        createHorLines();
        createVerLines();
        createDateLabels();
    }

    /**
     * Create date labels in bottom with spacing depend of window width
     */
    private void createDateLabels() {
        space = getWidth() / NDECADES;

        for (int i = 0; i < NDECADES; i++) {
            String date = Integer.toString(i * 10 + START_DECADE);
            GLabel dateLabel = new GLabel(date, i * space, getHeight());
            add(dateLabel);
        }
    }

    /**
     * Create vertical lines  with spacing depend of window width
     */
    private void createVerLines() {
        space = getWidth() / NDECADES;

        for (int i = 0; i < NDECADES; i++) {
            GLine verLine = new GLine((i + 1) * space, 0, (i + 1) * space, getHeight());
            add(verLine);
        }
    }

    /**
     * Create horizontal lines which present bottom and top.
     */
    private void createHorLines() {
        GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        add(topLine);

        GLine botLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        add(botLine);
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
