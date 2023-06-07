

package View;


import Controller.PenaltyController;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * deal with penalty page of employee
 */

public class PenaltyPage extends JFrame {
    JPanel background, sidebar;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel message;

    ArrayList<JLabel> labels;

    JScrollPane scrollTable;
    String[] columns = {"id","penalty details"};
    TableAttributes tableAttributes;
    TableFunction tableFunction;
    //----------
    User user;
    PenaltyController penaltyController;
    public PenaltyPage(User user) {
        this.user = user;
        background = new JPanel();
        penaltyController=new PenaltyController();
        size = new Size();
        position = new Position();
        component = new Component();
        sidebar = new JPanel();
        labels = new ArrayList<>();
        tableAttributes = new TableAttributes(columns);
        tableFunction = new TableFunction();
        message = new JLabel();

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
    }

    private void addComponentsBackground() {
        addComponentBackground(sidebar);
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
        int order = 1, space = 24, active = 3;
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
                new Dimensions(75, 75, 25, 20));
        try {
            ArrayList<String>rows=penaltyController.search(user.getId());

            for(int i=0;i<rows.size();i+=2)
            {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i),rows.get(i+1)});
            }
        } catch (Exception e) {
            message.setText("Please restart the system");
            message.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
        }
        tableFunction.hideColumn(tableAttributes,0);
    }


    private void setMessage() {
        message.setFont(new Font("", Font.PLAIN, 16));
    }
}
