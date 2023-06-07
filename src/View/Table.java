package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

//deal qith creating table

public class Table {
    DefaultTableModel searchModel;
    JTable searchTable;
    JScrollPane scrollTable;
    Position position;
    Size size;
    public Table()
    {
        position=new Position();
        size=new Size();
    }
    public JScrollPane setTable(TableAttributes tableAttributes,Dimensions dimensions) {
        searchModel = new DefaultTableModel();
        searchTable = new JTable(searchModel) {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        scrollTable = new JScrollPane(searchTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tableAttributes.searchModel=searchModel;
        tableAttributes.searchTable=searchTable;
        tableAttributes.scrollTable=scrollTable;

        String[] columnsName = tableAttributes.columns;
        setTableFeature(tableAttributes,dimensions);
        setTableColumns(tableAttributes,columnsName);
        return tableAttributes.scrollTable;
    }

    private void setTableFeature(TableAttributes tableAttributes,Dimensions dimensions) {
        tableAttributes.searchTable.setRowHeight(50);
        tableAttributes.searchTable.getTableHeader().setBackground(Color.decode("#2573B0"));
        tableAttributes.searchTable.getTableHeader().setForeground(Color.decode("#ffffff"));
        tableAttributes.searchTable.getTableHeader().setFont(new Font("", Font.BOLD, 20));
        tableAttributes.searchTable.setFont(new Font("", Font.BOLD, 18));
        tableAttributes.scrollTable.setBounds(
                position.moveXPercent(dimensions.positionX),
                position.moveYPercent(dimensions.positionY),
                size.sizeXPercent(dimensions.x),
                size.sizeYPercent(dimensions.y)
        );
    }



    private void setTableColumns(TableAttributes tableAttributes,String[] columnsName) {
        for (String name : columnsName) {
            tableAttributes.searchModel.addColumn(name);
        }
        TableColumn column;
        searchTable.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnsName.length; i++) {
            column = searchTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(size.sizeXPercent(20));
            searchTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

}

