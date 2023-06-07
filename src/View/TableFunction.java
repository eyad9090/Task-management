package View;

import javax.swing.table.TableColumn;

public class TableFunction {
    public void addRow(TableAttributes tableAttributes,String[] row) {
        tableAttributes.searchModel.addRow(row);
    }
    public void hideColumn(TableAttributes tableAttributes,int i)
    {
        TableColumn column=tableAttributes.searchTable.getColumnModel().getColumn(i);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setResizable(false);
    }
    public void clearTable(TableAttributes tableAttributes)
    {
        tableAttributes.searchModel.setRowCount(0);
    }

}
