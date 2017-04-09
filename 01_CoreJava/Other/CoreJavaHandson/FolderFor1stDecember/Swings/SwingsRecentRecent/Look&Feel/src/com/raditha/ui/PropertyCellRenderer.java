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

import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.table.*;

public class PropertyCellRenderer extends DefaultTableCellRenderer
{

    /**
     * This method is never called directly by our classes but by the UI manager
     * of the JVM.
     *
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return
     */
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column)
    {
        JLabel lb = new JLabel();
        lb.setHorizontalAlignment(lb.CENTER);
        Component cmp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(column == 2)
        {
            if (value instanceof ColorUIResource)
            {
                Color col = (Color) value;
                if(col.getRed() < 64 && col.getGreen() < 64 && col.getBlue()<64)
                {
                    lb.setForeground(Color.white);
                }
                lb.setText(col.getRed() +","+ col.getGreen() +","+ col.getBlue());
                if(isSelected)
                {
                    lb.setPreferredSize(new Dimension(16,16));
                }
                lb.setOpaque(true);
                lb.setBackground(col);
                return lb;
            }
            else if(value instanceof IconUIResource)
            {
                Icon ico = (Icon) value;
                lb.setIcon(ico);
                lb.setMinimumSize(new Dimension(ico.getIconWidth(),ico.getIconHeight()));
                try {
                    ico.paintIcon(lb,lb.getGraphics(),1,1);
                }
                catch (ClassCastException ex) {
                    return cmp;
                }
                catch (NullPointerException ex)
                {
                }
                return lb;
            }
            else if (value instanceof Border && value.getClass().getName().indexOf("javax.swing.plaf.metal.MetalBorders$") == -1) {
                Border b = (Border) value;

                lb.setText(" Border ");
                lb.setBorder((Border) value);
                try {
                    b.paintBorder(lb,lb.getGraphics(),1,1,1,1);

                    b.getBorderInsets(lb);
                    lb.getInsets();
                    return lb;
                }
                catch (ClassCastException ex) {
                    return cmp;
                }
                catch (NullPointerException ex)
                {
                    System.err.println("NPE " + lb.getGraphics());
                  //  return cmp;
                }

                return lb;
            }
            else if(value instanceof FontUIResource)
            {
                FontUIResource font = (FontUIResource) value;
                lb.setText(font.getName());
                lb.setFont(font);
                return lb;
            }
            else if(value instanceof ComponentUI)
            {
                ComponentUI cmpUi = (ComponentUI) value;
                cmpUi.createUI(lb);

                System.out.println(value + " is a member of " + value.getClass());
                //return lb;
            }
        }
        return cmp;
    }
}
