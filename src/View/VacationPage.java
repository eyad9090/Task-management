package View;


import Controller.EmployeeVacationController;
import Model.EmployeeVacation;
import Model.User;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * deal with view vacation page of employee
 */
public class VacationPage extends JFrame {
    JPanel background, sidebar;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel message;

    ArrayList<JLabel> labels;

    JScrollPane scrollTable;
    String[] columns = {"id", "start date", "finish date", "reason", "state"};
    TableAttributes tableAttributes;
    TableFunction tableFunction;
    JTextArea reasonField;
    JLabel startDateText, finishDateText, reasonText;

    JComboBox<String> stateField;
    JLabel stateText;

    DatePicker startDateField;
    DatePicker finishDateField;
    DatePicker startDateField2;
    DatePicker finishDateField2;
    JTextArea reasonField2;
    JLabel startDateText2, finishDateText2, reasonText2;
    JLabel requestVacation, updateVaction, deleteVacation;
    JTextField idField;
    DatePickerSettings dateSetting, dateSetting2, dateSetting3, dateSetting4;
    //----------
    User user;

    EmployeeVacation employeeVacation;
    EmployeeVacationController employeeVacationController;

    public VacationPage(User user) {
        this.user = user;
        employeeVacation = new EmployeeVacation();
        employeeVacationController = new EmployeeVacationController();
        background = new JPanel();
        size = new Size();
        position = new Position();
        component = new Component();
        sidebar = new JPanel();
        labels = new ArrayList<>();

        dateSetting = new DatePickerSettings();
        setDateSetting(dateSetting);
        startDateField = new DatePicker(dateSetting);

        dateSetting2 = new DatePickerSettings();
        setDateSetting(dateSetting2);
        finishDateField = new DatePicker(dateSetting2);

        tableAttributes = new TableAttributes(columns);
        tableFunction = new TableFunction();
        message = new JLabel();
        startDateText = new JLabel("start Date");
        finishDateText = new JLabel("finish Date");
        reasonField = new JTextArea();
        reasonText = new JLabel("Reason");
        requestVacation = new JLabel();
        updateVaction = new JLabel();
        deleteVacation = new JLabel();
        stateField = new JComboBox<>(new String[]{"accepted", "rejected", "attempted"});
        stateText = new JLabel("state");

        dateSetting3 = new DatePickerSettings();
        setDateSetting(dateSetting3);
        startDateField2 = new DatePicker(dateSetting3);

        dateSetting4 = new DatePickerSettings();
        setDateSetting(dateSetting4);
        finishDateField2 = new DatePicker(dateSetting4);

        startDateText2 = new JLabel("start Date");
        finishDateText2 = new JLabel("finish Date");
        reasonField2 = new JTextArea();
        reasonText2 = new JLabel("Reason");
        idField = new JTextField();
        //---------

        ShowLogin();
    }

    private void ShowLogin() {
        setMessage();
        setComponentsBackground();
        addComponentsBackground();
        setComponentsSideBar();
        this.setVisible(true);
    }

    private void setComponentsBackground() {
        setJFrame();
        setBackground();
        setSidebar();
        setTable();
        setStartDateField();
        setStartDateText();
        setFinishDateField();
        setfinishDateText();
        setAddButton();
        setReasonField();
        setReasonText();
        setStateField();
        setStateText();
        setStartDateField2();
        setFinishDateField2();
        setStartDateText2();
        setfinishDateText2();
        setReasonField2();
        setReasonText2();
        setUpdateButton();
        setDeleteButton();
        setIdField();
    }

    private void addComponentsBackground() {
        addComponentBackground(sidebar);
        addComponentBackground(scrollTable);
        addComponentBackground(startDateField);
        addComponentBackground(startDateText);
        addComponentBackground(finishDateField);
        addComponentBackground(finishDateText);
        addComponentBackground(requestVacation);
        addComponentBackground(reasonField);
        addComponentBackground(reasonText);
        addComponentBackground(stateField);
        addComponentBackground(stateText);
        addComponentBackground(startDateField2);
        addComponentBackground(finishDateField2);
        addComponentBackground(reasonField2);
        addComponentBackground(startDateText2);
        addComponentBackground(finishDateText2);
        addComponentBackground(reasonText2);
        addComponentBackground(updateVaction);
        addComponentBackground(deleteVacation);
        addComponentBackground(idField);
    }

    private void addComponentBackground(Object component) {
        if (component instanceof JButton) {
            background.add((JButton) component);
        } else if (component instanceof JPanel) {
            background.add((JPanel) component);
        } else if (component instanceof JTextField) {
            background.add((JTextField) component);
        } else if (component instanceof JScrollPane) {
            background.add((JScrollPane) component);
        } else if (component instanceof JComboBox<?>) {
            background.add((JComboBox<?>) component);
        } else if (component instanceof JLabel) {
            background.add((JLabel) component);
        } else if (component instanceof JTextArea) {
            background.add((JTextArea) component);
        }
    }

    private void setAddButton() {
        String defaultPath = "../image/vacation_before.png";
        int x = size.sizeXPercent(13);
        int y = size.sizeYPercent(5);
        requestVacation = new JLabel(Helper.getImage(defaultPath, x, y));
        requestVacation.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(25),
                x, y
        );
        component.createButton(requestVacation, "../image/vacation_before.png", "../image/vacation_after.png", x, y);
        requestVacation.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LocalDate localDate = startDateField.getDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String startDate = localDate.format(formatter);

                localDate = finishDateField.getDate();
                String finishDate = localDate.format(formatter);
                String reason = reasonField.getText();
                int userId = user.getId();
                employeeVacation.setUserId(userId);
                employeeVacation.setStartDate(startDate);
                if (!employeeVacation.setFinishDate(finishDate)) {
                    message.setText("Please make end date later than the start date");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (!employeeVacation.setReason(reason)) {
                    message.setText("Please write your reason");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        employeeVacationController.add(employeeVacation);
                        message.setText("Vacation request send successfully");
                        message.setForeground(Color.black);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        referesh_table();
                    } catch (SQLException ex) {
                        message.setText("Please try to logout and login again");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    private void setUpdateButton() {
        String defaultPath = "../image/update_before.png";
        int x = size.sizeXPercent(8);
        int y = size.sizeYPercent(5);
        updateVaction = new JLabel(Helper.getImage(defaultPath, x, y));
        updateVaction.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(87),
                x, y
        );
        component.createButton(updateVaction, "../image/update_before.png", "../image/update_after.png", x, y);
        updateVaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LocalDate localDate = startDateField2.getDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String startDate = localDate.format(formatter);
                employeeVacation.setStartDate(startDate);
                localDate = finishDateField2.getDate();
                String finishDate = localDate.format(formatter);


                String reason = reasonField2.getText();
                int userId = user.getId();
                int id;
                employeeVacation.setUserId(userId);

                if(!idField.getText().isEmpty())
                {
                    id=Integer.parseInt(idField.getText());
                    employeeVacation.setId(id);
                }
                if(idField.getText().isEmpty())
                {
                    message.setText("Please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (!employeeVacation.setFinishDate(finishDate)) {
                    message.setText("Please make end date later than the start date");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (!employeeVacation.setReason(reason)) {
                    message.setText("Please write your reason");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        employeeVacationController.update(employeeVacation);
                        message.setText("Vacation updated successfully");
                        message.setForeground(Color.black);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        referesh_table();
                    } catch (SQLException ex) {
                        message.setText("please make sure you have choosed the vacation from table");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    private void setDeleteButton() {
        String defaultPath = "../image/delete_before.png";
        int x = size.sizeXPercent(8);
        int y = size.sizeYPercent(5);
        deleteVacation = new JLabel(Helper.getImage(defaultPath, x, y));
        deleteVacation.setBounds(
                position.moveXPercent(36),
                position.moveYPercent(87),
                x, y
        );
        component.createButton(deleteVacation, "../image/delete_before.png", "../image/delete_after.png", x, y);
        deleteVacation.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int userId = user.getId();
                int id=0;
                employeeVacation.setUserId(userId);
                if(!idField.getText().isEmpty())
                {
                    id=Integer.parseInt(idField.getText());
                    employeeVacation.setId(id);
                }
                if(idField.getText().isEmpty())
                {
                    message.setText("Please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    message.setText("Are you sure you want to delete");
                    message.setForeground(Color.black);
                    int choice = JOptionPane.showConfirmDialog(null, message, "ERROR", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        try {
                            employeeVacationController.delete(id);
                            message.setText("Vacation deleted successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                            idField.setText("");
                            reasonField2.setText("");
                            startDateField2.setDate(LocalDate.now());
                            finishDateField2.setDate(LocalDate.now());
                        } catch (SQLException ex) {
                            message.setText("please make sure you have choosed the vacation from table");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (choice == JOptionPane.NO_OPTION) {
                        message.setText("Your vacation request is safe");
                        message.setForeground(Color.BLACK);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    private void setStartDateField() {
        startDateField.setFont(new Font("", Font.PLAIN, 16));
        startDateField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        startDateField.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(15),
                x, y
        );
        LocalDate localDate = LocalDate.now();
        startDateField.setDate(localDate);

    }

    private void setStartDateText() {
        startDateText.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        startDateText.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(11),
                x, y
        );
    }

    private void setFinishDateField() {
        finishDateField.setFont(new Font("", Font.PLAIN, 16));
        finishDateField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        finishDateField.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(15),
                x, y
        );
    }

    private void setfinishDateText() {
        finishDateText.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        finishDateText.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(11),
                x, y
        );
    }

    private void setReasonField() {
        reasonField.setFont(new Font("", Font.PLAIN, 16));
        reasonField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(15);
        reasonField.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(15),
                x, y
        );
    }

    private void setReasonText() {
        reasonText.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        reasonText.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(11),
                x, y
        );
    }

    private void setStateField() {
        stateField.setFont(new Font("", Font.PLAIN, 16));
        stateField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        stateField.setBounds(
                position.moveXPercent(85),
                position.moveYPercent(30),
                x, y
        );
        setActionSelection();
    }

    private void setStateText() {
        stateText.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        stateText.setBounds(
                position.moveXPercent(85),
                position.moveYPercent(26),
                x, y
        );
    }

    private void setStartDateField2() {
        startDateField2.setFont(new Font("", Font.PLAIN, 16));
        startDateField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        startDateField2.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(80),
                x, y
        );
    }

    private void setStartDateText2() {
        startDateText2.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        startDateText2.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(76),
                x, y
        );
    }

    private void setFinishDateField2() {
        finishDateField2.setFont(new Font("", Font.PLAIN, 16));
        finishDateField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        finishDateField2.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(80),
                x, y
        );
    }

    private void setfinishDateText2() {
        finishDateText2.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        finishDateText2.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(76),
                x, y
        );
    }

    private void setReasonField2() {
        reasonField2.setFont(new Font("", Font.PLAIN, 16));
        reasonField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(15);
        reasonField2.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(80),
                x, y
        );
    }

    private void setReasonText2() {
        reasonText2.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        reasonText2.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(76),
                x, y
        );
    }

    private void setIdField() {
        idField.setFont(new Font("", Font.PLAIN, 16));
        idField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        idField.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(80),
                x, y
        );
        idField.setVisible(false);
    }

    private void setSidebar() {
        sidebar.setBackground(Color.decode("#ffffff"));
        sidebar.setSize(size.sizeXPercent(25), size.sizeYPercent(100));
        sidebar.setLayout(null);
        sidebar.setLocation(position.moveXPercent(0), position.moveYPercent(0));
    }

    private void setComponentsSideBar() {
        setLogo();
        sidebar.add(logo);
        addLabels(new String[]{"time page", "vacation", "penalty", "tasks", "logout"});
    }


    private void setJFrame() {
        this.setTitle("Login form");
        this.setSize(size.sizeXPercent(101), size.sizeYPercent(101));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(position.moveXPercent(0), position.moveYPercent(0));
        this.setResizable(false);
        this.setLayout(null);
    }

    private void setBackground() {
        background.setBackground(Color.decode("#F4F4F4"));
        background.setSize(size.sizeXPercent(100), size.sizeYPercent(100));
        background.setLayout(null);
        this.add(background);
    }


    private void setLogo() {
        String defaultPath = "../image/logo.Png";
        int x = size.sizeXPercent(sidebar.getWidth(), 18);
        int y = size.sizeYPercent(sidebar.getHeight(), 16);
        logo = new JLabel(Helper.getImage(defaultPath, x, y));
        logo.setBounds(
                position.centerX(sidebar.getWidth(), x),
                position.moveYPercent(sidebar.getHeight(), 5),
                x, y
        );
    }


    private void addLabels(String[] labelsName) {
        JLabel label;
        int order = 1, space = 24, active = 2;
        for (String labelName : labelsName) {
            label = new JLabel(labelName);
            sidebar.add(label);
            setLabel(label, space, order);
            if (active == order) {
                setActiveSideBar(label);
            }
            space += 12;
            order++;
        }
    }

    private void setLabel(JLabel label, int space, int order) {

        int x = size.sizeXPercent(sidebar.getWidth(), 40);
        int y = size.sizeYPercent(sidebar.getHeight(), 10);
        label.setBounds(
                position.centerX(sidebar.getWidth(), x),
                position.moveYPercent(sidebar.getHeight(), space),
                x, y
        );
        label.setFont(new Font("", Font.PLAIN, 24));
        label.setForeground(Color.decode("#2573B0"));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                label.setForeground(Color.decode("#e61919"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                label.setForeground(Color.decode("#2573B0"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                switch (order) {
                    case 1:
                        new TimePage(user);
                        dispose();
                        break;
                    case 2:
                        new VacationPage(user);
                        dispose();
                        break;
                    case 3:
                        new PenaltyPage(user);
                        dispose();
                        break;
                    case 4:
                        new TaskPage(user);
                        dispose();
                        break;
                    case 5:
                        Login.getLoginPage().ShowLogin();
                        dispose();
                        break;
                }
            }
        });
    }

    private void setActiveSideBar(JLabel label) {
        label.setForeground(Color.decode("#e61919"));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                label.setForeground(Color.decode("#e61919"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                label.setForeground(Color.decode("#e61919"));
            }
        });
    }


    private void setTable() {
        scrollTable = component.createTable(tableAttributes,
                new Dimensions(75, 40, 25, 35));
        try {
            ArrayList<String> rows = employeeVacationController.search(user.getId());
            for (int i = 0; i < rows.size(); i += 5) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2), rows.get(i + 3), rows.get(i + 4)});
            }
            setTableAction();
        } catch (SQLException e) {
            message.setText("Please restart the system");
            message.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
        }
        tableFunction.hideColumn(tableAttributes, 0);
    }

    public void referesh_table() {
        try {
            tableFunction.clearTable(tableAttributes);
            ArrayList<String> rows = employeeVacationController.search(user.getId());

            for (int i = 0; i < rows.size(); i += 5) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2), rows.get(i + 3), rows.get(i + 4)});
            }
        } catch (SQLException e) {
            message.setText("Please restart the system");
            message.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void setActionSelection() {
        stateField.addActionListener(event -> {
            String state = (String) stateField.getSelectedItem();

            try {
                tableFunction.clearTable(tableAttributes);
                ArrayList<String> rows = employeeVacationController.search(state);
                for (int i = 0; i < rows.size(); i += 5) {
                    tableFunction.addRow(tableAttributes,
                            new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2), rows.get(i + 3), rows.get(i + 4)});
                }
            } catch (SQLException e) {
                message.setText("No vacation exist with selected state");
                message.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void setTableAction() {
        tableAttributes.searchTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                int r = tableAttributes.searchTable.getSelectedRow();
                String id = (String) tableAttributes.searchTable.getValueAt(r, 0);
                String startDate = (String) tableAttributes.searchTable.getValueAt(r, 1);
                String finishDate = (String) tableAttributes.searchTable.getValueAt(r, 2);
                String reason = (String) tableAttributes.searchTable.getValueAt(r, 3);
                idField.setText(id);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(startDate, formatter);
                startDateField2.setDate(localDate);
                localDate = LocalDate.parse(finishDate, formatter);
                finishDateField2.setDate(localDate);
                reasonField2.setText(reason);
            }
        });
    }

    private void setDateSetting(DatePickerSettings dateSetting) {
        dateSetting.setFontValidDate(new Font("", Font.PLAIN, 16));
        dateSetting.setAllowKeyboardEditing(false);
        dateSetting.setAllowEmptyDates(false);
        dateSetting.setAllowKeyboardEditing(false);
        dateSetting.setFontCalendarDateLabels(new Font("dialog", Font.PLAIN, 16));
        dateSetting.setFontClearLabel(new Font("dialog", Font.PLAIN, 16));
        dateSetting.setFontInvalidDate(new Font("dialog", Font.PLAIN, 16));
        dateSetting.setFontCalendarWeekdayLabels(new Font("dialog", Font.PLAIN, 16));
        dateSetting.setFontMonthAndYearMenuLabels(new Font("dialog", Font.PLAIN, 16));
        dateSetting.setFontMonthAndYearNavigationButtons(new Font("dialog", Font.PLAIN, 16));
        dateSetting.setFontTodayLabel(new Font("dialog", Font.PLAIN, 16));
    }

    private void setMessage() {
        message.setFont(new Font("", Font.PLAIN, 16));
    }
}
