package com.raditha.ui;

import javax.swing.*;
import javax.swing.plaf.*;

class LFComboBoxModel extends DefaultComboBoxModel
{

    public LFComboBoxModel(Object[] items)
    {
        super(items);
    }

    public Object getElementAt(int i)
    {
        UIManager.LookAndFeelInfo lfInfo = (UIManager.LookAndFeelInfo) super.getElementAt(i);
        return lfInfo.getName();
    }

}
