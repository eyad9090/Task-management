package View;

import Controller.UserController;
import Model.Password;
import Model.User;
import Model.Validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * deal with view manage user page of admin
 */

public class ManageUserPage extends JFrame {
    JPanel background, sidebar;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel message;
    JLabel email , settingEmail;
    JLabel role , settingRole;
    JLabel settingPassword;
    JTextField emailField,settingEmailField;
    JTextField settingPasswordField;

    JLabel updateButton;
    JLabel deleteButton;


    JScrollPane scrollTable;
    String[] columns = {"id", "Email","role"};
    int tableUserId;
    TableAttributes tableAttributes;
    TableFunction tableFunction;

    JComboBox roleList ,settingRoleList;
    String [] roles = { "employee" , "team-leader" , "project-manager" , "admin"};

    JLabel addUserButton;

    ArrayList<JLabel> labels;
    User user;

    UserController userController ;
    public ManageUserPage(User user) {
        this.user = user;
        background = new JPanel();
        size = new Size();
        position = new Position();
        component = new Component();
        sidebar = new JPanel();
        labels = new ArrayList<>();
        emailField = new JTextField();
        roleList = new JComboBox(roles);
        addUserButton = new JLabel();
        tableAttributes = new TableAttributes(columns);
        tableFunction = new TableFunction();
        userController = new UserController();
        settingEmail = new JLabel("Email");
        settingPassword = new JLabel("Password");
        settingRole = new JLabel("Role");
        settingEmailField = new JTextField();
        settingPasswordField =  new JTextField();
        settingRoleList = new JComboBox(roles);



        message = new JLabel();
        email = new JLabel("Email");
        role = new JLabel ("Role");


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
        setEmailLabel ();
        setRoleLabel ();
        setEmailField();
        setRoleField();
        setAddButton();
        setTable();
        setSettingEmailLabel();
        setSettingPasswordLabel();
        setSettingRoleLabel();
        setSettingEmailField();
        setSettingPasswordField();
        setSettingRoleField();
        setUpdateButton();
        setDeleteButton();
        setTableAction();
//        setAddButton();
    }

    private void addComponentsBackground() {
        addComponentBackground(sidebar);
        addComponentBackground(email);
        addComponentBackground(role);
        addComponentBackground(emailField);
        addComponentBackground(roleList);
        addComponentBackground(addUserButton);
        addComponentBackground(scrollTable);
        addComponentBackground(settingEmail);
        addComponentBackground(settingPassword);
        addComponentBackground(settingRole);
        addComponentBackground(settingEmailField);
        addComponentBackground(settingPasswordField);
        addComponentBackground(settingRoleList);
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
        int order = 1, space = 24, active = 1;
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


    private void setAddButton()
    {
        String defaultPath = "../image/adder_before.png";
        int x = size.sizeXPercent(8);
        int y = size.sizeYPercent(5);
        addUserButton = new JLabel(Helper.getImage(defaultPath, x, y));
        addUserButton.setBounds(
                position.moveXPercent(80),
                position.moveYPercent(9),
                x , y

        );
        component.createButton(addUserButton, "../image/adder_before.png", "../image/adder_after.png", x, y);
        addUserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                User addedUser = new User();
                Password pass = new Password();

                addedUser.setRole(roleList.getSelectedItem().toString());
                addedUser.setPassword(pass.createPassword());

                if(!addedUser.setEmail(emailField.getText()))
                {
                    message.setText("Please enter valid email");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    try {
                        if(userController.add(addedUser))
                        {
                            message.setText("You have add user successfully \n "+"User Password : " + addedUser.getPassword());
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                        }
                        else {
                            message.setText("Please logout and try to login again");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        message.setText("Email is already exist");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                }



            }
        });
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
                UserController userController = new UserController();
                User updatedUser = new User();
                updatedUser.setId(tableUserId);
                updatedUser.setRole(settingRoleList.getSelectedItem().toString());
                if (tableUserId==0) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(settingEmailField.getText()==null)
                {
                    message.setText("please fill the email");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if ( !Validation.isValidEmail(settingEmailField.getText().toString())){
                    message.setText("please enter valid email");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(settingPasswordField.getText()==null)
                {
                    message.setText("please fill the password");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (!Validation.isValidPassword(settingPasswordField.getText().toString())){
                    message.setText("please enter valid password");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                 else{
                    updatedUser.setEmail(settingEmailField.getText().toString());
                    updatedUser.setPassword(settingPasswordField.getText().toString());
                    try {
                        if (userController.update(updatedUser)){
                            message.setText("user updated successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                        }
                    } catch (SQLException ex) {
                        message.setText("updated email is already exist");
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
                if(tableUserId==0)
                {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    try {
                        if (userController.delete(tableUserId)){
                            message.setText("User Deleted successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                            settingEmailField.setText("");
                            settingPasswordField.setText("");
                            tableUserId=0;
                        }
                        else{
                            message.setText("User is not deleted please try again later");
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


    private void setEmailLabel (){
        email.setFont(new Font("", Font.PLAIN, 20));
        email.setBounds(
                position.moveXPercent(30),
                position.moveYPercent(background.getHeight(), 5),
                size.sizeXPercent(10),
                size.sizeYPercent(5)
        );

    }
    private void setRoleLabel (){
        role.setFont(new Font("", Font.PLAIN, 20));
        role.setBounds(
                position.moveXPercent(50),
                position.moveYPercent(background.getHeight(), 5),
                size.sizeXPercent(10),
                size.sizeYPercent(5)
        );

    }
    private void setSettingEmailLabel (){
        settingEmail.setFont(new Font("", Font.PLAIN, 20));
        settingEmail.setBounds(
                position.moveXPercent(30),
                position.moveYPercent(background.getHeight(), 70),
                size.sizeXPercent(10),
                size.sizeYPercent(5)
        );

    }
    private void setSettingPasswordLabel (){
        settingPassword.setFont(new Font("", Font.PLAIN, 20));
        settingPassword.setBounds(
                position.moveXPercent(50),
                position.moveYPercent(background.getHeight(), 70),
                size.sizeXPercent(10),
                size.sizeYPercent(5)
        );

    }
    private void setSettingRoleLabel (){
        settingRole.setFont(new Font("", Font.PLAIN, 20));
        settingRole.setBounds(
                position.moveXPercent(70),
                position.moveYPercent(background.getHeight(), 70),
                size.sizeXPercent(10),
                size.sizeYPercent(5)
        );

    }

    private void setEmailField() {
        emailField.setFont(new Font("", Font.PLAIN, 16));
        emailField.setBackground(Color.WHITE);
        emailField.setBounds(
                position.moveXPercent(30),
                position.moveYPercent(10),
                size.sizeXPercent(15),
                size.sizeYPercent(4)

        );

    }
    private void setRoleField() {
        roleList.setFont(new Font("", Font.PLAIN, 16));
        roleList.setBackground(Color.WHITE);
        roleList.setBounds(
                position.moveXPercent(50),
                position.moveYPercent(10),
                size.sizeXPercent(15),
                size.sizeYPercent(4)

        );

    }
    private void setSettingEmailField() {
        settingEmailField.setFont(new Font("", Font.PLAIN, 16));
        settingEmailField.setBackground(Color.WHITE);
        settingEmailField.setBounds(
                position.moveXPercent(30),
                position.moveYPercent(75),
                size.sizeXPercent(15),
                size.sizeYPercent(4)

        );

    }
    private void setSettingPasswordField() {
        settingPasswordField.setFont(new Font("", Font.PLAIN, 16));
        settingPasswordField.setBackground(Color.WHITE);
        settingPasswordField.setBounds(
                position.moveXPercent(50),
                position.moveYPercent(75),
                size.sizeXPercent(15),
                size.sizeYPercent(4)

        );

    }
    private void setSettingRoleField() {
        settingRoleList.setEnabled(false);
        settingRoleList.setFont(new Font("", Font.PLAIN, 16));
        settingRoleList.setBackground(Color.WHITE);
        settingRoleList.setBounds(
                position.moveXPercent(70),
                position.moveYPercent(75),
                size.sizeXPercent(15),
                size.sizeYPercent(4)
        );
    }



    private void setTable() {
        scrollTable = component.createTable(tableAttributes,
                new Dimensions(75, 40, 25, 25));
        try {
            ArrayList<String> rows = userController.all();
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
        tableFunction.hideColumn(tableAttributes, 0);
    }
    public void referesh_table(){
        try {
            tableFunction.clearTable(tableAttributes);
            ArrayList<String> rows = userController.all();
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
                tableUserId = Integer.parseInt(id);

                try {
                    searchigUser = search.search(tableUserId);
                    settingEmailField.setText(searchigUser.getEmail());
                    settingPasswordField.setText(searchigUser.getPassword());
                    settingRoleList.setSelectedItem(searchigUser.getRole());
                } catch (Exception ex) {
                    message.setText("unexpected error");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
    }



    private void setMessage() {
        message.setFont(new Font("", Font.PLAIN, 16));
    }
}
