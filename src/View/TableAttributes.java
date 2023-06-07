package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableAttributes {
    String []columns;

    DefaultTableModel searchModel;
    JTable searchTable;
    JScrollPane scrollTable;
    public TableAttributes(String[]columns)
    {
        this.columns=columns;
    }
}
