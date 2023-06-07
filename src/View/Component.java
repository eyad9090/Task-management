package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * this class using in creating button and table
 */

public class Component {
    Table table;
    public Component(){
        table =new Table();
    }
    public void createButton(JLabel label,String before,String after,
                             int x,int y) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setIcon(Helper.getImage(after,x,y));
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setIcon(Helper.getImage(before,x,y));
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
    public JScrollPane createTable(TableAttributes tableAttributes,Dimensions dimensions)
    {
        return table.setTable(tableAttributes,dimensions);
    }
}
