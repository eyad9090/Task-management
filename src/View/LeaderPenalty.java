package View;


import Controller.EmployeeWorkingController;
import Controller.PenaltyController;
import Controller.UserController;
import Model.EmployeeWorking;
import Model.Penalty;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * deal with view penalty page of team leader
 */
public class LeaderPenalty extends JFrame {
    JPanel background, sidebar;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel message;

    ArrayList<JLabel> labels;

    JScrollPane scrollTable;
    String[] columns = {"id","employee","penalty"};
    TableAttributes tableAttributes;
    TableFunction tableFunction;


    JComboBox<String>emailField;
    JComboBox<String>emailField2;
    JTextArea penaltyField;
    JTextArea penaltyField2;
    JLabel emailText,emailText2,penaltyText,penaltyText2;
    JLabel addTime,update,delete;

    JTextField idField;
    //----------
    User user;
    UserController userController;

    Penalty penalty;
    PenaltyController penaltyController;

    public LeaderPenalty(User user) {
        this.user = user;
        userController=new UserController();
        penalty=new Penalty();
        penaltyController=new PenaltyController();
        background = new JPanel();
        size = new Size();
        position = new Position();
        component = new Component();
        sidebar = new JPanel();
        labels = new ArrayList<>();

        message = new JLabel();

        emailField=new JComboBox<>(getEmails());
        emailField.setSelectedIndex(0);
        emailText=new JLabel("Email");

        penaltyField=new JTextArea();
        penaltyText=new JLabel("Penalty");

        emailField2=new JComboBox<>(getEmails());
        emailText2=new JLabel("Email");

        penaltyField2=new JTextArea();
        penaltyText2=new JLabel("Penalty");

        idField=new JTextField();

        addTime=new JLabel();
        update=new JLabel();
        delete=new JLabel();
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
        setPenaltyField();
        setPenaltyText();
        setEmailField();
        setEmailText();
        setTable();
        setPenaltyField2();
        setPenaltyText2();
        setEmailField2();
        setEmailText2();
        setUpdateButton();
        setDeleteButton();
        setIdField();
    }

    private void addComponentsBackground() {
        addComponentBackground(sidebar);
        addComponentBackground(addTime);
        addComponentBackground(update);
        addComponentBackground(delete);
        addComponentBackground(penaltyField);
        addComponentBackground(penaltyText);
        addComponentBackground(emailField);
        addComponentBackground(emailText);
        addComponentBackground(penaltyField2);
        addComponentBackground(penaltyText2);
        addComponentBackground(emailField2);
        addComponentBackground(emailText2);
        addComponentBackground(idField);
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
        }else if (component instanceof JComboBox<?>) {
            background.add((JComboBox<?>) component);
        }
        else if (component instanceof JLabel) {
            background.add((JLabel) component);
        }
        else if (component instanceof JTextArea) {
            background.add((JTextArea) component);
        }
    }

    private String[] getEmails()  {
        String []ans={};
        try {
            ArrayList<String>emails=userController.searchEmployeeEmails();
            ans=new String[emails.size()];
            emails.toArray(ans);
            return ans;
        } catch (SQLException e) {
            return ans;
        }
    }
    private void setAddButton()
    {
        String defaultPath="../image/penalty_before.png";
        int x=size.sizeXPercent(10);
        int y=size.sizeYPercent(5);
        addTime = new JLabel(Helper.getImage(defaultPath,x,y));
        addTime.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(15),
                x,y
        );
        component.createButton(addTime,"../image/penalty_before.png","../image/penalty_after.png",x,y);
        addTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String email=(String) emailField.getSelectedItem();
                String details=penaltyField.getText();
                try {
                    int id=userController.getEmployeeId(email);
                    if(id==0)
                    {
                        message.setText("user not found please referesh the page");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(details.isEmpty())
                    {
                        message.setText("please enter the penalty");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        penalty.setUserId(id);
                        penalty.setDetails(details);
                        if(penaltyController.add(penalty))
                        {
                            message.setText("penalty added successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                        }
                        else
                        {
                            message.setText("Please refresh the page user is not found");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    message.setText("please restart the system");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
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
                position.moveXPercent(26),
                position.moveYPercent(87),
                x, y
        );
        component.createButton(update, "../image/update_before.png", "../image/update_after.png", x, y);
        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String id=idField.getText();
                String details=penaltyField2.getText();
                if(id.isEmpty())
                {
                    message.setText("please select from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(details.isEmpty())
                {
                    message.setText("please fill the details of penalty");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    penalty.setId(Integer.parseInt(id));
                    penalty.setDetails(details);
                    try {
                        if(penaltyController.update(penalty))
                        {
                            message.setText("updated successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                        }
                        else
                        {
                            message.setText("please select from the table");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        message.setText("please restart the system");
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
                position.moveXPercent(36),
                position.moveYPercent(87),
                x, y
        );
        component.createButton(delete, "../image/delete_before.png", "../image/delete_after.png", x, y);
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String id=idField.getText();
                if(id.isEmpty())
                {
                    message.setText("please select from the table");
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
                            penaltyController.delete(Integer.parseInt(id));
                            message.setText("Penalty deleted successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                            idField.setText("");
                            penaltyField2.setText("");
                        } catch (SQLException ex) {
                            message.setText("please make sure you have choosed the penalty from table");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (choice == JOptionPane.NO_OPTION) {
                        message.setText("Your penalty is safe");
                        message.setForeground(Color.BLACK);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    private void setIdField()
    {
        idField.setFont(new Font("",Font.PLAIN,16));
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
    private void setPenaltyField()
    {
        penaltyField.setFont(new Font("",Font.PLAIN,16));
        penaltyField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(15);
        penaltyField.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(15),
                x, y
        );
    }
    private void setPenaltyText()
    {
        penaltyText.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        penaltyText.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(11),
                x, y
        );
    }

    private void setEmailField()
    {
        emailField.setFont(new Font("",Font.PLAIN,16));
        emailField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        emailField.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(15),
                x, y
        );
    }
    private void setEmailText()
    {
        emailText.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        emailText.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(11),
                x, y
        );
    }

    private void setPenaltyField2()
    {
        penaltyField2.setFont(new Font("",Font.PLAIN,16));
        penaltyField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(15);
        penaltyField2.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(80),
                x, y
        );
    }
    private void setPenaltyText2()
    {
        penaltyText2.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        penaltyText2.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(76),
                x, y
        );
    }

    private void setEmailField2()
    {
        emailField2.setEnabled(false);
        emailField2.setFont(new Font("",Font.PLAIN,16));
        emailField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        emailField2.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(80),
                x, y
        );
    }
    private void setEmailText2()
    {
        emailText2.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        emailText2.setBounds(
                position.moveXPercent(26),
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
        addLabels(new String[]{"Employee hours", "Employee penalty", "Employee tasks", "Employee Vacation","Employee report", "logout"});
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
                        new LeaderTimePage(user);
                        dispose();
                        break;
                    case 2:
                        new LeaderPenalty(user);
                        dispose();
                        break;
                    case 3:
                        new LeaderTasks(user);
                        dispose();
                        break;
                    case 4:
                        new LeaderVacation(user);
                        dispose();
                        break;
                    case 5:
                        new LeaderReport(user);
                        dispose();
                        break;
                    case 6:
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
            ArrayList<String> rows = penaltyController.search();
            for (int i = 0; i < rows.size(); i += 3) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1),rows.get(i + 2)});
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
            ArrayList<String> rows = penaltyController.search();
            for (int i = 0; i < rows.size(); i += 3) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1),rows.get(i + 2)});
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
                String id=(String) tableAttributes.searchTable.getValueAt(r, 0);
                String email = (String) tableAttributes.searchTable.getValueAt(r, 1);
                String details = (String) tableAttributes.searchTable.getValueAt(r, 2);

                idField.setText(id);
                emailField2.setSelectedItem(email);
                penaltyField2.setText(details);
            }
        });
    }

    private void setMessage() {
        message.setFont(new Font("", Font.PLAIN, 16));
    }
}
