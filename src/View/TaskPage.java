package View;


import Controller.EmployeeTaskController;
import Controller.ProjectController;
import Model.EmployeeTask;
import Model.Project;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * deal with view taskpage page of employee
 */
public class TaskPage extends JFrame {
    JPanel background, sidebar;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel message;

    ArrayList<JLabel> labels;

    JScrollPane scrollTable;
    String[] columns = {"employeeTaskId","projectId","percentage", "task name", "task details", "state"};
    TableAttributes tableAttributes;
    TableFunction tableFunction;

    JComboBox<String> stateField;
    JLabel stateText;

    JTextField taskNameField;
    JTextArea taskDetailsField;
    JComboBox<String> stateField2;

    JLabel taskNameText,taskDetailsText,stateText2;

    JLabel updateButton;
    JTextField employeeTaskIdField,projectIdField,percentageField;
    int selectedRow=-1;
    //----------
    User user;
    EmployeeTaskController employeeTaskController;
    EmployeeTask employeeTask;

    Project project;
    ProjectController projectController;
    public TaskPage(User user) {
        this.user = user;
        employeeTaskController=new EmployeeTaskController();
        employeeTask=new EmployeeTask();
        project =new Project();
        projectController =new ProjectController();
        background = new JPanel();
        size = new Size();
        position = new Position();
        component = new Component();
        sidebar = new JPanel();
        labels = new ArrayList<>();
        updateButton=new JLabel();



        tableAttributes = new TableAttributes(columns);
        tableFunction = new TableFunction();
        message = new JLabel();
        stateField = new JComboBox<>(new String[]{"finished", "not finished"});
        stateText = new JLabel("state");


        taskNameField=new JTextField();
        taskDetailsField=new JTextArea();
        stateField2=new JComboBox<>(new String[]{"finished","not finished"});

        taskNameText=new JLabel("task name");
        taskDetailsText=new JLabel("task details");
        stateText2=new JLabel("state");

        employeeTaskIdField = new JTextField();
        projectIdField=new JTextField();
        percentageField=new JTextField();
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
        setStateField();
        setStateText();
        setUpdateButton();
        setIdField();
        setTaskNameField();
        setTaskNameText();
        setTaskDetailsField();
        setTaskDetailsText();
        setStateField2();
        setStateText2();
        setProjectIdField();
        setPercentageField();
    }

    private void addComponentsBackground() {
        addComponentBackground(sidebar);
        addComponentBackground(scrollTable);
        addComponentBackground(stateField);
        addComponentBackground(stateText);
        addComponentBackground(employeeTaskIdField);
        addComponentBackground(updateButton);
        addComponentBackground(taskNameField);
        addComponentBackground(taskNameText);
        addComponentBackground(taskDetailsField);
        addComponentBackground(taskDetailsText);
        addComponentBackground(stateField2);
        addComponentBackground(stateText2);
        addComponentBackground(percentageField);
        addComponentBackground(projectIdField);

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
        }
        else if (component instanceof JTextArea) {
            background.add((JTextArea) component);
        }
    }



    private void setUpdateButton() {
        String defaultPath = "../image/update_before.png";
        int x = size.sizeXPercent(8);
        int y = size.sizeYPercent(5);
        updateButton = new JLabel(Helper.getImage(defaultPath, x, y));
        updateButton.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(87),
                x, y
        );
        component.createButton(updateButton, "../image/update_before.png", "../image/update_after.png", x, y);
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String employeeTaskId = employeeTaskIdField.getText();
                String projectId = projectIdField.getText();
                String percentage = percentageField.getText();


                String taskState=(String)stateField2.getSelectedItem();
                if(employeeTaskId.isEmpty()||projectId.isEmpty()||percentage.isEmpty())
                {
                    message.setText("Please select row from table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    String prevState=(String) tableAttributes.searchTable.getValueAt(selectedRow, 5);
                    employeeTask.setId(Integer.parseInt(employeeTaskId));
                    employeeTask.setTaskState(taskState);
                    project.setId(Integer.parseInt(projectId));
                    if(prevState.equals(taskState))
                    {
                        message.setText("Updated successfully");
                        message.setForeground(Color.black);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        if(taskState.equals("finished"))
                            project.setProjectPercentage(Integer.parseInt(percentage));
                        else
                            project.setProjectPercentage(-Integer.parseInt(percentage));
                        try {
                            if(employeeTaskController.update(employeeTask)&&
                                    projectController.update(project))
                            {
                                message.setText("Updated successfully");
                                message.setForeground(Color.black);
                                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                            {
                                message.setText("Please choose from the table");
                                message.setForeground(Color.red);
                                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            }
                            referesh_table();
                        } catch (SQLException ex) {
                            message.setText("Please restart the system");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

            }
        });
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



    private void setTaskNameField() {
        taskNameField.setEditable(false);
        taskNameField.setFont(new Font("", Font.PLAIN, 16));
        taskNameField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        taskNameField.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(80),
                x, y
        );
    }

    private void setTaskNameText() {
        taskNameText.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskNameText.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(76),
                x, y
        );
    }

    private void setTaskDetailsField() {
        taskDetailsField.setEditable(false);
        taskDetailsField.setFont(new Font("", Font.PLAIN, 16));
        taskDetailsField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(15);
        taskDetailsField.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(80),
                x, y
        );
    }

    private void setTaskDetailsText() {
        taskDetailsText.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskDetailsText.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(76),
                x, y
        );
    }

    private void setStateField2() {
        stateField2.setFont(new Font("", Font.PLAIN, 16));
        stateField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        stateField2.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(80),
                x, y
        );
    }

    private void setStateText2() {
        stateText2.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        stateText2.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(76),
                x, y
        );
    }

    private void setIdField() {
        employeeTaskIdField.setFont(new Font("", Font.PLAIN, 16));
        employeeTaskIdField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        employeeTaskIdField.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(80),
                x, y
        );
        employeeTaskIdField.setVisible(false);
    }

    private void setProjectIdField() {
        projectIdField.setFont(new Font("", Font.PLAIN, 16));
        projectIdField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        projectIdField.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(80),
                x, y
        );
        projectIdField.setVisible(false);
    }
    private void setPercentageField() {
        percentageField.setFont(new Font("", Font.PLAIN, 16));
        percentageField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        percentageField.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(80),
                x, y
        );
        percentageField.setVisible(false);
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
        int order = 1, space = 24, active = 4;
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
            ArrayList<String> rows = employeeTaskController.search(user.getId());
            for (int i = 0; i < rows.size(); i += 6) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2), rows.get(i + 3), rows.get(i + 4),rows.get(i + 5)});
            }
            setTableAction();
        } catch (SQLException e) {
            message.setText("Please restart the system");
            message.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
        }
        tableFunction.hideColumn(tableAttributes, 0);
        tableFunction.hideColumn(tableAttributes, 1);
        tableFunction.hideColumn(tableAttributes, 2);
    }

    public void referesh_table() {
        try {
            tableFunction.clearTable(tableAttributes);
            ArrayList<String> rows = employeeTaskController.search(user.getId());

            for (int i = 0; i < rows.size(); i += 6) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2), rows.get(i + 3), rows.get(i + 4),rows.get(i + 5)});
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
                ArrayList<String> rows = employeeTaskController.search(state);
                for (int i = 0; i < rows.size(); i += 6) {
                    tableFunction.addRow(tableAttributes,
                            new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2), rows.get(i + 3), rows.get(i + 4),rows.get(i + 5)});
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
                String employeeTaskId = (String) tableAttributes.searchTable.getValueAt(r, 0);
                String projectId = (String) tableAttributes.searchTable.getValueAt(r, 1);
                String percentage = (String) tableAttributes.searchTable.getValueAt(r, 2);
                String taskName = (String) tableAttributes.searchTable.getValueAt(r, 3);
                String taskDetails = (String) tableAttributes.searchTable.getValueAt(r, 4);
                String taskState=(String) tableAttributes.searchTable.getValueAt(r, 5);

                employeeTaskIdField.setText(employeeTaskId);
                projectIdField.setText(projectId);
                percentageField.setText(percentage);
                taskNameField.setText(taskName);
                taskDetailsField.setText(taskDetails);
                stateField2.setSelectedItem(taskState);
                selectedRow=r;
            }
        });
    }


    private void setMessage() {
        message.setFont(new Font("", Font.PLAIN, 16));
    }
}
