package View;

import Controller.ProjectController;
import Controller.UserController;
import Model.Password;
import Model.Project;
import Model.User;
import Model.Validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * deal with view project page of admin
 */

public class ProjectPage extends JFrame{
    JPanel background, sidebar;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel message;
    JLabel projectName;
    JTextField projectNameField;
    JLabel addProject;
    JLabel settingProjectName;
    JTextField settingProjectNameField;
    JLabel settingPercentage;
    JTextField settingPercentageField;
    JLabel updateButton;
    JLabel deleteButton;



    ArrayList<JLabel> labels;
    User user;

    JScrollPane scrollTable;
    String[] columns = {"id", "Project Name","Project Percentage"};
    int tableProjectId;
    TableAttributes tableAttributes;
    TableFunction tableFunction;

    ProjectController projectController ;





    public ProjectPage(User user) {
        this.user = user;
        projectController = new ProjectController();
        background = new JPanel();
        size = new Size();
        position = new Position();
        component = new Component();
        sidebar = new JPanel();
        labels = new ArrayList<>();
        message = new JLabel();
        tableAttributes = new TableAttributes(columns);
        tableFunction = new TableFunction();
        projectName = new JLabel("Name");
        projectNameField = new JTextField();
        settingProjectName = new JLabel("Name");
        settingPercentage = new JLabel("Percentage");
        settingProjectNameField = new JTextField();
        settingPercentageField = new JTextField();





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
        setProjectName ();
        setProjectNameField();
        setSettingProjectName();
        setSettingPercentage();
        setSettingPercentageField();
        setSettingProjectNameField();
        setAddButton();
        setUpdateButton();
        setDeleteButton();
        setTable();
        setTableAction();


    }

    private void addComponentsBackground() {
        addComponentBackground(sidebar);
        addComponentBackground(scrollTable);
        addComponentBackground(projectName);
        addComponentBackground(projectNameField);
        addComponentBackground(addProject);
        addComponentBackground(settingProjectName);
        addComponentBackground(settingPercentage);
        addComponentBackground(settingProjectNameField);
        addComponentBackground(settingPercentageField);
        addComponentBackground(updateButton);
        addComponentBackground(deleteButton);

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
        }else if (component instanceof JComboBox<?>) {
            background.add((JComboBox<?>) component);
        }
        else if (component instanceof JLabel) {
            background.add((JLabel) component);
        }
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
        addLabels(new String[]{"Manage user", "Projects", "logout"});
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
                        new ManageUserPage(user);
                        dispose();
                        break;
                    case 2:
                        new ProjectPage(user);
                        dispose();
                        break;
                    case 3:
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

    private void setProjectName (){
        projectName.setFont(new Font("", Font.PLAIN, 20));
        projectName.setBounds(
                position.moveXPercent(30),
                position.moveYPercent(background.getHeight(), 5),
                size.sizeXPercent(10),
                size.sizeYPercent(5)
        );

    }
    private void setProjectNameField() {
        projectNameField.setFont(new Font("", Font.PLAIN, 16));
        projectNameField.setBackground(Color.WHITE);
        projectNameField.setBounds(
                position.moveXPercent(30),
                position.moveYPercent(10),
                size.sizeXPercent(15),
                size.sizeYPercent(4)

        );

    }

    private void setSettingProjectNameField() {
        settingProjectNameField.setFont(new Font("", Font.PLAIN, 16));
        settingProjectNameField.setBackground(Color.WHITE);
        settingProjectNameField.setBounds(
                position.moveXPercent(30),
                position.moveYPercent(75),
                size.sizeXPercent(15),
                size.sizeYPercent(4)

        );

    }
    private void setSettingPercentageField() {
        settingPercentageField.setEditable(false);
        settingPercentageField.setFont(new Font("", Font.PLAIN, 16));
        settingPercentageField.setBackground(Color.WHITE);
        settingPercentageField.setBounds(
                position.moveXPercent(50),
                position.moveYPercent(75),
                size.sizeXPercent(15),
                size.sizeYPercent(4)

        );

    }
    private void setAddButton()
    {
        String defaultPath = "../image/adder_before.png";
        int x = size.sizeXPercent(8);
        int y = size.sizeYPercent(5);
        addProject = new JLabel(Helper.getImage(defaultPath, x, y));
        addProject.setBounds(
                position.moveXPercent(50),
                position.moveYPercent(9),
                x , y

        );
        component.createButton(addProject, "../image/adder_before.png", "../image/adder_after.png", x, y);
        addProject.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               Project project = new Project();
               project.setProjectName(projectNameField.getText());
                if(project.getProjectName()==null)
                {
                    message.setText("please fill project name");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(project.getProjectName().isEmpty())
                {
                    message.setText("please fill project name");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    try {
                        if ( projectController.add(project) ){
                            message.setText("project added successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                            projectNameField.setText("");
                        }
                        else{
                            message.setText("project is not added refresh the page");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        message.setText("project name is already exist");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                }





            }
        });
    }

    private void setSettingProjectName (){
        settingProjectName.setFont(new Font("", Font.PLAIN, 20));
        settingProjectName.setBounds(
                position.moveXPercent(30),
                position.moveYPercent(background.getHeight(), 70),
                size.sizeXPercent(10),
                size.sizeYPercent(5)
        );

    }
    private void setSettingPercentage (){

        settingPercentage.setFont(new Font("", Font.PLAIN, 20));
        settingPercentage.setBounds(
                position.moveXPercent(50),
                position.moveYPercent(background.getHeight(), 70),
                size.sizeXPercent(10),
                size.sizeYPercent(5)
        );

    }

    private void setUpdateButton()
    {
        String defaultPath = "../image/update_before.png";
        int x = size.sizeXPercent(8);
        int y = size.sizeYPercent(5);
        updateButton = new JLabel(Helper.getImage(defaultPath, x, y));
        updateButton.setBounds(
                position.moveXPercent(30),
                position.moveYPercent(83),
                x , y

        );
        component.createButton(updateButton, "../image/update_before.png", "../image/update_after.png", x, y);
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Project updatedProject = new Project();
                if(tableProjectId==0)
                {
                    message.setText("Please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(settingProjectNameField.getText()==null)
                {
                    message.setText("Please fill the project name");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(settingProjectNameField.getText().isEmpty())
                {
                    message.setText("Please fill the project name");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    try {
                        updatedProject.setId(tableProjectId);
                        updatedProject.setProjectName(settingProjectNameField.getText());
                        updatedProject.setProjectPercentage(Integer.parseInt(settingPercentageField.getText()));
                        if (projectController.update2(updatedProject)){
                            message.setText("project updated successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                        }
                    } catch (SQLException ex) {
                        message.setText("project Name is already exist");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }

                }



            }
        });
    }
    private void setDeleteButton()
    {
        String defaultPath = "../image/delete_before.png";
        int x = size.sizeXPercent(8);
        int y = size.sizeYPercent(5);
        deleteButton = new JLabel(Helper.getImage(defaultPath, x, y));
        deleteButton.setBounds(
                position.moveXPercent(40),
                position.moveYPercent(83),
                x , y

        );
        component.createButton(deleteButton, "../image/delete_before.png", "../image/delete_after.png", x, y);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                UserController userController = new UserController();
                if(tableProjectId==0)
                {
                    message.setText("Please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    try {
                        if (projectController.delete(tableProjectId)){
                            message.setText("project Deleted successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                            settingProjectNameField.setText("");
                            settingPercentageField.setText("");
                            tableProjectId=0;
                        }
                        else{
                            message.setText("project is not deleted please try again later");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException throwables) {
                        message.setText("Unexpected error please restart the system");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }
        });

    }

    private void setTable() {
        scrollTable = component.createTable(tableAttributes,
                new Dimensions(75, 50, 25, 20));
        try {
            ArrayList<String> rows = projectController.all();
            for (int i = 0; i < rows.size(); i += 3) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2)});
            }
        } catch (SQLException e) {
            //System.out.println(e);
            message.setText("Please restart the system");
            message.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
        }
        tableFunction.hideColumn(tableAttributes, 0);
    }

    public void referesh_table(){
        try {
            tableFunction.clearTable(tableAttributes);
            ArrayList<String> rows = projectController.all();
            for (int i = 0; i < rows.size(); i += 3) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2)});
            }
           // setTableAction();
        } catch (SQLException e) {
            //System.out.println(e);
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
                UserController search = new UserController();
                User searchigUser = new User();
                String id = (String) tableAttributes.searchTable.getValueAt(r, 0);
                tableProjectId = Integer.parseInt(id);
                String name = (String)tableAttributes.searchTable.getValueAt(r,1);
                String percentage = (String)tableAttributes.searchTable.getValueAt(r,2);
                settingProjectNameField.setText(name);
                settingPercentageField.setText(percentage);

            }
        });
    }



    private void setMessage() {
        message.setFont(new Font("", Font.PLAIN, 16));
    }


}
