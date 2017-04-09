/* These code are picked from the site : http://www.raditha.com/java/swing/uidefaults.php
 * 
 */
package com.raditha.ui;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.table.*;


public class UIProperties {
    JFrame jfr = new JFrame();
    JComboBox jcmb = new JComboBox();
    JScrollPane pane = new JScrollPane();
    UIManager.LookAndFeelInfo[] lf;
    LFComboBoxModel model;

    UIProperties()
    {



        makeTable();
        lf = UIManager.getInstalledLookAndFeels();
        model = new LFComboBoxModel(lf);
        jcmb.setModel(model);
        jcmb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    pane.getViewport().removeAll();
                    UIManager.setLookAndFeel(lf[jcmb.getSelectedIndex()].getClassName());
//                    SwingUtilities.updateComponentTreeUI(jfr);
                    makeTable();

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        jfr.getContentPane().add(pane, BorderLayout.CENTER);
        jfr.getContentPane().add(jcmb,BorderLayout.NORTH);
        jfr.setSize(100,500);
        jfr.setVisible(true);
    }

    public static void main(String[] args) {
        new UIProperties();

    }

    void makeTable()
    {
        UIDefaults def = UIManager.getLookAndFeelDefaults();

        def.put("OptionPane.warningDialog.titlePane.shadow",new ColorUIResource(200,0,0));
        Enumeration enum = def.keys();

        System.out.println("make ui");
        Object[][] tableData = new Object[def.size()][3];
        Object []headers = new String[] { "Key","Type","Value" };

        for(int i=0 ; enum.hasMoreElements() ; i++)
        {
            Object item = enum.nextElement();
            Object value = def.get(item);

            if(value == null)
            {
                i--;
                System.out.println(item +" " + def.get(item));
                continue;
            }
            tableData[i][0] = item;
            tableData[i][1] = value.getClass().getName();
            tableData[i][2] = value;
        }
        JTable table = new JTable(tableData,headers);
        table.setRowHeight(25);
        table.setDefaultRenderer(new Object().getClass(),new PropertyCellRenderer());
        pane.getViewport().add(table);
    }
}

