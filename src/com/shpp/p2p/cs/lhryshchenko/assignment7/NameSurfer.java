package com.shpp.p2p.cs.lhryshchenko.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    /**
     * The width of the Text Field
     */
    private NameSurferGraph graph;
    private JTextField nameField;
    private JButton graphButton;
    private JButton clearButton;
    private NameSurferDataBase database = new NameSurferDataBase(NAMES_DATA_FILE);

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        graph = new NameSurferGraph();
        add(graph);

        add(new JLabel("Name"), NORTH);

        nameField = new JTextField(NUM_FIELD);
        add(nameField, NORTH);

        graphButton = new JButton("Graph");
        add(graphButton, NORTH);

        clearButton = new JButton("Clear");
        add(clearButton, NORTH);

        nameField.setActionCommand("EnterPressed");
        addActionListeners();
    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */

    public void actionPerformed(ActionEvent e) {
        //String cmn = e.getActionCommand();

        if (e.getSource() == graphButton || e.getSource() == nameField) {
            String lineIn = nameField.getText();
            NameSurferEntry entry = database.findEntry(lineIn);
            println(lineIn);
            if (entry != null) {
                graph.addEntry(entry);
                graph.update();
            } else {
                println("This name doesn't exists");
                //add(new JLabel("This name doesn't exists"), CENTER);
            }

        } else if (e.getSource() == clearButton) {
            graph.clear();
            graph.update();


        }
    }


}
