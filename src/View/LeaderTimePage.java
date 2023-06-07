package View;


import Controller.EmployeeWorkingController;
import Controller.UserController;
import Model.EmployeeWorking;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * deal with show employees time work view of the team leader
 */

public class LeaderTimePage extends JFrame {
    JPanel background, sidebar;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel message;

    ArrayList<JLabel> labels;



    JComboBox<String>emailField;
    JComboBox<String>monthField;
    JComboBox<String>yearField;
    JTextField hoursField;
    JLabel monthText,yearText,emailText,hoursText;
    JLabel addTime;
    //----------
    User user;
    UserController userController;
    EmployeeWorkingController employeeWorkingController;
    EmployeeWorking employeeWorking;


    public LeaderTimePage(User user) {
        this.user = user;
        userController=new UserController();
        employeeWorking=new EmployeeWorking();
        employeeWorkingController=new EmployeeWorkingController();
        background = new JPanel();
        size = new Size();
        position = new Position();
        component = new Component();
        sidebar = new JPanel();
        labels = new ArrayList<>();

        message = new JLabel();

        monthField=new JComboBox<>(getMonth());
        yearField=new JComboBox<>(getYear());
        emailField=new JComboBox<>(getEmails());
        monthField.setSelectedIndex(0);
        yearField.setSelectedIndex(0);
        emailField.setSelectedIndex(0);
        monthText=new JLabel("Month");
        yearText=new JLabel("Year");
        emailText=new JLabel("Email");

        hoursField=new JTextField();
        hoursText=new JLabel("Hours");
        addTime=new JLabel();
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
        setMonthField();
        setMonthText();
        setYearField();
        setYearText();
        setAddButton();
        setHoursField();
        setHoursText();
        setEmailField();
        setEmailText();
    }

    private void addComponentsBackground() {
        addComponentBackground(sidebar);
        addComponentBackground(monthField);
        addComponentBackground(monthText);
        addComponentBackground(yearField);
        addComponentBackground(yearText);
        addComponentBackground(addTime);
        addComponentBackground(hoursField);
        addComponentBackground(hoursText);
        addComponentBackground(emailField);
        addComponentBackground(emailText);
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

    private String[]getMonth()
    {
        ArrayList<String>months=new ArrayList<>();
        for(int month=1;month<=12;month++)
        {
            months.add(month+"");
        }
        String []ans=new String[months.size()];
        months.toArray(ans);
        return ans;
    }
    private String[]getYear()
    {
        ArrayList<String>years=new ArrayList<>();
        for(int year=2022;year>=2000;year--)
        {
            years.add(year+"");
        }
        String []ans=new String[years.size()];
        years.toArray(ans);
        return ans;
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
        String defaultPath="../image/hours_before.png";
        int x=size.sizeXPercent(10);
        int y=size.sizeYPercent(5);
        addTime = new JLabel(Helper.getImage(defaultPath,x,y));
        addTime.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(25),
                x,y
        );
        component.createButton(addTime,"../image/hours_before.png","../image/hours_after.png",x,y);
        addTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String month= (String) monthField.getSelectedItem();
                String year= (String) yearField.getSelectedItem();
                String email=(String) emailField.getSelectedItem();
                int userId=user.getId();
                employeeWorking.setUserId(userId);
                employeeWorking.setMonth(month);
                employeeWorking.setYear(year);
                try {
                   int hours=employeeWorkingController.calculateHours(employeeWorking,email);
                   hoursField.setText(hours+"");
                } catch (SQLException ex) {
                    message.setText("please restart the system");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });
    }

    private void setHoursField()
    {
        hoursField.setEditable(false);
        hoursField.setFont(new Font("",Font.PLAIN,16));
        hoursField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        hoursField.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(25),
                x, y
        );
    }
    private void setHoursText()
    {
        hoursText.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        hoursText.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(21),
                x, y
        );
    }

    private void setEmailField()
    {
        emailField.setFont(new Font("",Font.PLAIN,16));
        emailField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
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
    private void setMonthField()
    {
        monthField.setFont(new Font("",Font.PLAIN,16));
        monthField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        monthField.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(15),
                x, y
        );
    }
    private void setMonthText()
    {
        monthText.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        monthText.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(11),
                x, y
        );
    }
    private void setYearField()
    {
        yearField.setFont(new Font("",Font.PLAIN,16));
        yearField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        yearField.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(15),
                x, y
        );
    }
    private void setYearText()
    {
        yearText.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        yearText.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(11),
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





    private void setMessage() {
        message.setFont(new Font("", Font.PLAIN, 16));
    }
}
