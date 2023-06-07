package View;


import Controller.ProjectController;
import Controller.TaskController;
import Controller.UserController;
import Model.Project;
import Model.Task;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * deal with view task page of project manager
 */
public class TaskManager extends JFrame {
    JPanel background, sidebar;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel message;

    ArrayList<JLabel> labels;

    JScrollPane scrollTable;
    String[] columns = {"id", "project name", "task name", "task details", "task percentage", "Assign"};
    TableAttributes tableAttributes;
    TableFunction tableFunction;


    JComboBox<String> projectField;
    JTextField taskNameField;
    JTextArea taskDetailsField;
    JTextField taskPercentageField;


    JComboBox<String> projectField2;
    JTextField taskNameField2;
    JTextArea taskDetailsField2;
    JTextField taskPercentageField2;

    JLabel projectText, taskNameText, taskDetailsText, taskPercentageText, projectText2, taskNameText2, taskDetailsText2, taskPercentageText2;
    JComboBox<String> projectField3;
    JLabel projectText3;
    JLabel addTime, update, delete;
    JTextField idField;

    //----------
    User user;
    UserController userController;

    Task task;
    TaskController taskController;

    ProjectController projectController;
    Project project;


    public TaskManager(User user) {
        this.user = user;
        taskController = new TaskController();
        userController = new UserController();
        projectController = new ProjectController();
        project = new Project();
        task = new Task();
        background = new JPanel();
        size = new Size();
        position = new Position();
        component = new Component();
        sidebar = new JPanel();
        labels = new ArrayList<>();

        message = new JLabel();

        projectField = new JComboBox<>(getProjects2());
        projectText = new JLabel("Project name");

        projectField2 = new JComboBox<>(getProjects());
        projectText2 = new JLabel("Project name");

        taskNameField = new JTextField();
        taskNameField2 = new JTextField();

        taskNameText = new JLabel("Task name");
        taskNameText2 = new JLabel("TaskName");

        taskDetailsField = new JTextArea();
        taskDetailsText = new JLabel("task details");

        taskDetailsField2 = new JTextArea();
        taskDetailsText2 = new JLabel("task details");


        taskPercentageField = new JTextField();
        taskPercentageText = new JLabel("task percentage");

        taskPercentageField2 = new JTextField();
        taskPercentageText2 = new JLabel("task percentage");

        idField = new JTextField();

        projectField3 = new JComboBox<>(getProjects());
        projectText3 = new JLabel("project name");
        addTime = new JLabel();
        update = new JLabel();
        delete = new JLabel();
        tableAttributes = new TableAttributes(columns);
        tableFunction = new TableFunction();

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
        setAddButton();
        setProjectField();
        setProjectText();
        setTaskNameField();
        setTaskNameText();
        setTaskDetailsField();
        setTaskDetailsText();
        setTaskPercentageField();
        setTaskPercentageText();

        setProjectField2();
        setProjectText2();
        setTaskNameField2();
        setTaskNameText2();
        setTaskDetailsField2();
        setTaskDetailsText2();
        setTaskPercentageField2();
        setTaskPercentageText2();


        setProjectField3();
        setProjectText3();


        setIdField();
        setTable();
        setUpdateButton();
        setDeleteButton();
    }

    private void addComponentsBackground() {
        addComponentBackground(sidebar);
        addComponentBackground(addTime);
        addComponentBackground(update);
        addComponentBackground(delete);
        addComponentBackground(projectField);
        addComponentBackground(projectText);
        addComponentBackground(taskNameField);
        addComponentBackground(taskNameText);
        addComponentBackground(taskDetailsField);
        addComponentBackground(taskDetailsText);
        addComponentBackground(taskPercentageField);
        addComponentBackground(taskPercentageText);


        addComponentBackground(idField);

        addComponentBackground(projectField2);
        addComponentBackground(projectText2);
        addComponentBackground(taskNameField2);
        addComponentBackground(taskNameText2);
        addComponentBackground(taskDetailsField2);
        addComponentBackground(taskDetailsText2);
        addComponentBackground(taskPercentageField2);
        addComponentBackground(taskPercentageText2);

        addComponentBackground(projectField3);
        addComponentBackground(projectText3);

        addComponentBackground(scrollTable);
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

    private String[] getProjects() {
        String[] ans = {};
        try {
            ArrayList<String> emails = projectController.searchProjectName();
            ans = new String[emails.size()];
            emails.toArray(ans);
            return ans;
        } catch (SQLException e) {
            return ans;
        }
    }

    private String[] getProjects2() {
        String[] ans = {};
        try {
            ArrayList<String> emails = projectController.searchProjectNameFinished();
            ans = new String[emails.size()];
            emails.toArray(ans);
            return ans;
        } catch (SQLException e) {
            return ans;
        }
    }

    private void refreshSelection() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(getProjects2());
        projectField.setModel(model);
        model = new DefaultComboBoxModel<>(getProjects());
        projectField2.setModel(model);
        model = new DefaultComboBoxModel<>(getProjects());
        projectField3.setModel(model);

    }

    private void setAddButton() {
        String defaultPath = "../image/assign_before.png";
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(5);
        addTime = new JLabel(Helper.getImage(defaultPath, x, y));
        addTime.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(25),
                x, y
        );
        component.createButton(addTime, "../image/assign_before.png", "../image/assign_after.png", x, y);
        addTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String ProjectName = (String) projectField.getSelectedItem();
                String taskName = taskNameField.getText();
                String taskDetails = taskDetailsField.getText();
                String taskPercentage = taskPercentageField.getText();
                int projectId = 0;
                try {
                    projectId = projectController.getProjectId(ProjectName);
                } catch (SQLException ex) {
                    message.setText("please refresh the page this project might not exist");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                boolean percentageCheck = true;
                for (int i = 0; i < taskPercentage.length(); i++) {
                    if (!Character.isDigit(taskPercentage.charAt(i))) {
                        percentageCheck = false;
                        break;
                    }
                }
                if (taskName == null) {
                    message.setText("please fill task name");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskName.isEmpty()) {
                    message.setText("please fill task name");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskDetails == null) {
                    message.setText("please fill task details");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskDetails.isEmpty()) {
                    message.setText("please fill task details");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (!percentageCheck) {
                    message.setText("please Enter number in percentage");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskPercentage==null) {
                    message.setText("please fill the percentage");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskPercentage.isEmpty()) {
                    message.setText("please fill the percentage");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (projectId == 0) {
                    message.setText("please refresh the page this project might not exist");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    task.setProjectId(projectId);
                    task.setTaskName(taskName);
                    task.setTaskDetails(taskDetails);
                    task.setTaskPercentage(Integer.parseInt(taskPercentage));
                    try {
                        int percentage=projectController.getProjectPercentage(projectId);
                        System.out.println();
                        if(task.getTaskPercentage()>percentage)
                        {
                            message.setText("The remaining percentage of the project only:"+percentage+" %");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {

                            project.setId(projectId);
                            project.setProjectPercentage(-Integer.parseInt(taskPercentage));
                            if (taskController.add(task) &&projectController.update(project)) {
                                message.setText("Task added successfully");
                                message.setForeground(Color.black);
                                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                                referesh_table();
                                refreshSelection();
                            } else {
                                message.setText("please refresh the page");
                                message.setForeground(Color.black);
                                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        message.setText("Task name already used before");
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
        update = new JLabel(Helper.getImage(defaultPath, x, y));
        update.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(87),
                x, y
        );
        component.createButton(update, "../image/update_before.png", "../image/update_after.png", x, y);
        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String id = idField.getText();
                String ProjectName = (String) projectField2.getSelectedItem();
                String taskName = taskNameField2.getText();
                String taskDetails = taskDetailsField2.getText();
                String taskPercentage = taskPercentageField2.getText();
                int projectId = 0;
                int taskId = 0;
                boolean percentageCheck = true;
                for (int i = 0; i < taskPercentage.length(); i++) {
                    if (taskPercentage.charAt(i) < '0' && taskPercentage.charAt(i) > '9') {
                        percentageCheck = false;
                        break;
                    }
                }
                if(id==null)
                {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (id.isEmpty())
                {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (taskName == null) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskName.isEmpty()) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskDetails == null) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskDetails.isEmpty()) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (!percentageCheck) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }  else {


                    try {
                        projectId = projectController.getProjectId(ProjectName);
                        taskId = Integer.parseInt(id);
                    } catch (Exception ex) {
                        message.setText("please choose from the table");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }

                    task.setId(taskId);
                    task.setProjectId(projectId);
                    task.setTaskName(taskName);
                    task.setTaskDetails(taskDetails);
                    task.setTaskPercentage(Integer.parseInt(taskPercentage));

                    try {
                        project.setId(projectId);
                        project.setProjectPercentage(Integer.parseInt(taskPercentage));
                        if (taskController.update(task)) {
                            message.setText("Task updated successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                            refreshSelection();
                        } else {
                            message.setText("please refresh the page");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ;
                        message.setText("Task name already used before");
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
        delete = new JLabel(Helper.getImage(defaultPath, x, y));
        delete.setBounds(
                position.moveXPercent(56),
                position.moveYPercent(87),
                x, y
        );
        component.createButton(delete, "../image/delete_before.png", "../image/delete_after.png", x, y);
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String id = idField.getText();
                String ProjectName = (String) projectField2.getSelectedItem();
                String taskName = taskNameField2.getText();
                String taskDetails = taskDetailsField2.getText();
                String taskPercentage = taskPercentageField2.getText();
                int projectId = 0;
                int taskId = 0;
                boolean percentageCheck = true;
                for (int i = 0; i < taskPercentage.length(); i++) {
                    if (taskPercentage.charAt(i) < '0' && taskPercentage.charAt(i) > '9') {
                        percentageCheck = false;
                        break;
                    }
                }
                if(id==null)
                {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (id.isEmpty())
                {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (taskName == null) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskName.isEmpty()) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskDetails == null) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (taskDetails.isEmpty()) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                } else if (!percentageCheck) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    try {
                        projectId = projectController.getProjectId(ProjectName);
                        taskId = Integer.parseInt(id);
                    } catch (SQLException ex) {
                        message.setText("please choose from the table");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }

                    task.setProjectId(projectId);
                    task.setTaskName(taskName);
                    task.setTaskDetails(taskDetails);
                    task.setTaskPercentage(Integer.parseInt(taskPercentage));
                    task.setId(taskId);
                    project.setId(projectId);
                    project.setProjectPercentage(Integer.parseInt(taskPercentage));
                    message.setText("Are you sure you want to delete");
                    message.setForeground(Color.black);
                    int choice = JOptionPane.showConfirmDialog(null, message, "ERROR", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        try {
                            taskController.delete(taskId);
                            projectController.update(project);
                            message.setText("Task deleted successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                            refreshSelection();
                            idField.setText("");
                        } catch (SQLException ex) {
                            message.setText("please make sure you have choosed the vacation from table");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (choice == JOptionPane.NO_OPTION) {
                        message.setText("Your Task is safe");
                        message.setForeground(Color.BLACK);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }
        });
    }


    private void setProjectField() {
        projectField.setFont(new Font("", Font.PLAIN, 16));
        projectField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        projectField.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(15),
                x, y
        );
    }

    private void setProjectText() {
        projectText.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        projectText.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(11),
                x, y
        );
    }


    private void setTaskNameField() {
        taskNameField.setFont(new Font("", Font.PLAIN, 16));
        taskNameField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        taskNameField.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(15),
                x, y
        );
    }

    private void setTaskNameText() {
        taskNameText.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskNameText.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(11),
                x, y
        );
    }

    private void setTaskDetailsField() {
        taskDetailsField.setFont(new Font("", Font.PLAIN, 16));
        taskDetailsField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(15);
        taskDetailsField.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(15),
                x, y
        );
    }

    private void setTaskDetailsText() {
        taskDetailsText.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskDetailsText.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(11),
                x, y
        );
    }

    private void setTaskPercentageField() {
        taskPercentageField.setFont(new Font("", Font.PLAIN, 16));
        taskPercentageField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskPercentageField.setBounds(
                position.moveXPercent(86),
                position.moveYPercent(15),
                x, y
        );
    }

    private void setTaskPercentageText() {
        taskPercentageText.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskPercentageText.setBounds(
                position.moveXPercent(86),
                position.moveYPercent(11),
                x, y
        );
    }

    //-------------------------------
    private void setProjectField3() {
        projectField3.setFont(new Font("", Font.PLAIN, 16));
        projectField3.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        projectField3.setBounds(
                position.moveXPercent(85),
                position.moveYPercent(30),
                x, y
        );
        setActionSelection();
    }

    private void setProjectText3() {
        projectText3.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        projectText3.setBounds(
                position.moveXPercent(85),
                position.moveYPercent(26),
                x, y
        );
    }

    private void setActionSelection() {
        projectField3.addActionListener(event -> {
            String projectName = (String) projectField3.getSelectedItem();

            try {
                tableFunction.clearTable(tableAttributes);
                ArrayList<String> rows = taskController.searchLeader(projectName);
                for (int i = 0; i < rows.size(); i += 6) {
                    tableFunction.addRow(tableAttributes,
                            new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2), rows.get(i + 3),
                                    rows.get(i + 4), rows.get(i + 5)});
                }
            } catch (SQLException e) {
                message.setText("please restart the system");
                message.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


    //----------------------------------


    private void setIdField() {
        idField.setFont(new Font("", Font.PLAIN, 16));
        idField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        idField.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(15),
                x, y
        );
        idField.setVisible(false);
    }

    private void setProjectField2() {
        projectField2.setEnabled(false);
        projectField2.setFont(new Font("", Font.PLAIN, 16));
        projectField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        projectField2.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(80),
                x, y
        );
    }

    private void setProjectText2() {
        projectText2.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        projectText2.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(76),
                x, y
        );
    }


    private void setTaskNameField2() {
        taskNameField2.setFont(new Font("", Font.PLAIN, 16));
        taskNameField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        taskNameField2.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(80),
                x, y
        );
    }

    private void setTaskNameText2() {
        taskNameText2.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskNameText2.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(76),
                x, y
        );
    }

    private void setTaskDetailsField2() {
        taskDetailsField2.setFont(new Font("", Font.PLAIN, 16));
        taskDetailsField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(15);
        taskDetailsField2.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(80),
                x, y
        );
    }

    private void setTaskDetailsText2() {
        taskDetailsText2.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskDetailsText2.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(76),
                x, y
        );
    }

    private void setTaskPercentageField2() {
        taskPercentageField2.setEditable(false);
        taskPercentageField2.setFont(new Font("", Font.PLAIN, 16));
        taskPercentageField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskPercentageField2.setBounds(
                position.moveXPercent(86),
                position.moveYPercent(80),
                x, y
        );
    }

    private void setTaskPercentageText2() {
        taskPercentageText2.setFont(new Font("", Font.PLAIN, 18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskPercentageText2.setBounds(
                position.moveXPercent(86),
                position.moveYPercent(76),
                x, y
        );
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
        addLabels(new String[]{"Employee project", "Employee tasks", "Employee Reports", "logout"});
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

        int x = size.sizeXPercent(sidebar.getWidth(), 80);
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
                        new ProjectManager(user);
                        dispose();
                        break;
                    case 2:
                        new TaskManager(user);
                        dispose();
                        break;
                    case 3:
                        new ReportManager(user);
                        dispose();
                        break;
                    case 4:
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
            ArrayList<String> rows = taskController.searchLeader();
            for (int i = 0; i < rows.size(); i += 6) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2),
                                rows.get(i + 3), rows.get(i + 4), rows.get(i + 5)});
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
            ArrayList<String> rows = taskController.searchLeader();
            for (int i = 0; i < rows.size(); i += 6) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2),
                                rows.get(i + 3), rows.get(i + 4), rows.get(i + 5)});
            }
        } catch (SQLException e) {
            message.setText("Please restart the system");
            message.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void setTableAction() {
        tableAttributes.searchTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                int r = tableAttributes.searchTable.getSelectedRow();
                String id = (String) tableAttributes.searchTable.getValueAt(r, 0);
                String projectName = (String) tableAttributes.searchTable.getValueAt(r, 1);
                String taskName = (String) tableAttributes.searchTable.getValueAt(r, 2);
                String taskDetails = (String) tableAttributes.searchTable.getValueAt(r, 3);
                String taskPercentage = (String) tableAttributes.searchTable.getValueAt(r, 4);


                idField.setText(id);
                projectField2.setSelectedItem(projectName);
                taskNameField2.setText(taskName);
                taskDetailsField2.setText(taskDetails);
                taskPercentageField2.setText(taskPercentage);
            }
        });
    }

    private void setMessage() {
        message.setFont(new Font("", Font.PLAIN, 16));
    }
}
