package View;


import Controller.EmployeeWorkingController;
import Model.EmployeeWorking;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * deal with view time page of employee
 */

public class TimePage extends JFrame {
    JPanel background, sidebar;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel message;

    ArrayList<JLabel> labels;

    JScrollPane scrollTable;
    String[] columns = {"id","date of working", "start time", "finish time"};
    TableAttributes tableAttributes;
    TableFunction tableFunction;
    JComboBox<String>startTimeField;
    JComboBox<String>finishTimeField;
    JLabel startTimeText,finishTimeText;
    JLabel addTime;
    //----------
    EmployeeWorking employeeWorking;
    User user;
    EmployeeWorkingController employeeWorkingController;

    public TimePage(User user) {
        this.user = user;
        employeeWorking=new EmployeeWorking();
        employeeWorkingController=new EmployeeWorkingController();
        background = new JPanel();
        size = new Size();
        position = new Position();
        component = new Component();
        sidebar = new JPanel();
        labels = new ArrayList<>();


        tableAttributes = new TableAttributes(columns);
        tableFunction = new TableFunction();
        message = new JLabel();

        startTimeField=new JComboBox<>(getTime());
        finishTimeField=new JComboBox<>(getTime());
        startTimeField.setSelectedIndex(0);
        finishTimeField.setSelectedIndex(0);
        startTimeText=new JLabel("start time");
        finishTimeText=new JLabel("finish time");
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
        setTable();
        setStartTimeField();
        setStartTimeText();
        setFinishTimeField();
        setfinishTimeText();
        setAddButton();
    }

    private void addComponentsBackground() {
        addComponentBackground(sidebar);
        addComponentBackground(scrollTable);
        addComponentBackground(startTimeField);
        addComponentBackground(startTimeText);
        addComponentBackground(finishTimeField);
        addComponentBackground(finishTimeText);
        addComponentBackground(addTime);
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

    private String[]getTime()
    {
        ArrayList<String>time=new ArrayList<>();
        for(int hour=1;hour<=24;hour++)
        {
            if(hour<9)
                time.add("0"+hour+":00:00");
            else
                time.add(hour+":00:00");

        }
        String []ans=new String[time.size()];
        time.toArray(ans);
        return ans;
    }
    private void setAddButton()
    {
        String defaultPath="../image/add_before.png";
        int x=size.sizeXPercent(10);
        int y=size.sizeYPercent(5);
        addTime = new JLabel(Helper.getImage(defaultPath,x,y));
        addTime.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(15),
                x,y
        );
        component.createButton(addTime,"../image/add_before.png","../image/add_after.png",x,y);
        addTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String startTime= (String) startTimeField.getSelectedItem();
                String finishTime= (String) finishTimeField.getSelectedItem();
                int userId=user.getId();
                employeeWorking.setUserId(userId);
                employeeWorking.setStartTime(startTime);
                if(!employeeWorking.setFinishTime(finishTime))
                {
                    message.setText("Please make sure start time is less than finish time");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    try {
                        if(employeeWorkingController.add(employeeWorking))
                        {
                            message.setText("You have entered your time successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                        }
                        else
                        {
                            message.setText("Please logout and try to login again");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        message.setText("You have already entered your time today");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }
        });
    }
    private void setStartTimeField()
    {
        startTimeField.setFont(new Font("",Font.PLAIN,16));
        startTimeField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        startTimeField.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(15),
                x, y
        );
    }
    private void setStartTimeText()
    {
        startTimeText.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        startTimeText.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(11),
                x, y
        );
    }
    private void setFinishTimeField()
    {
        finishTimeField.setFont(new Font("",Font.PLAIN,16));
        finishTimeField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        finishTimeField.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(15),
                x, y
        );
    }
    private void setfinishTimeText()
    {
        finishTimeText.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        finishTimeText.setBounds(
                position.moveXPercent(46),
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
                new Dimensions(75, 55, 25, 40));
        try {
            ArrayList<String>rows=employeeWorkingController.search(user.getId());

            for(int i=0;i<rows.size();i+=4)
            {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i),rows.get(i+1), rows.get(i+2), rows.get(i+3)});
            }
        } catch (SQLException e) {
            message.setText("Please restart the system");
            message.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
        }
        tableFunction.hideColumn(tableAttributes,0);
    }
    public void referesh_table()
    {
        try {
            tableFunction.clearTable(tableAttributes);
            ArrayList<String>rows=employeeWorkingController.search(user.getId());

            for(int i=0;i<rows.size();i+=4)
            {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i),rows.get(i+1), rows.get(i+2), rows.get(i+3)});
            }
        } catch (SQLException e) {
            message.setText("Please restart the system");
            message.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void setMessage() {
        message.setFont(new Font("", Font.PLAIN, 16));
    }
}
