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
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.table.*;



public class UIPropertiesAppletExt extends UIPropertiesApplet{

    protected void makeTable()
    {
        UIDefaults def = UIManager.getLookAndFeelDefaults();

        def.put("Button.background",new ColorUIResource(200,200,0));
        def.put("ScrollBar.foreground",new ColorUIResource(200,0,0));

        def.put("Table.focusCellBackground",new ColorUIResource(200,0,0));
        def.put("TableHeader.foreground",new ColorUIResource(255,255,200));
        def.put("TableHeader.background",new ColorUIResource(0,0,55));
        def.put("Table.background",new ColorUIResource(240,240,240));

        def.put("CheckBox.font",new FontUIResource("monospaced",Font.ITALIC,14));
        def.put("TableHeader.font",new FontUIResource("serif",Font.BOLD,14));
        super.makeTable();
    }
}

