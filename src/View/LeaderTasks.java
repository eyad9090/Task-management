package View;


import Controller.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * deal with view task page of team leader
 */
public class LeaderTasks extends JFrame {
    JPanel background, sidebar;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel message;

    ArrayList<JLabel> labels;

    JScrollPane scrollTable;
    String[] columns = {"id","email","project name","task name","task details","task state"};
    TableAttributes tableAttributes;
    TableFunction tableFunction;


    JComboBox<String>emailField;
    JComboBox<String>emailField2;
    JComboBox<String>taskField;
    JComboBox<String>taskField2;
    JComboBox<String>taskField3;
    JTextArea detailsField;
    JTextArea detailsField2;
    JTextField projectField;
    JTextField projectField2;
    JLabel emailText,emailText2,taskText,taskText2,taskText3,detailsText,detailsText2,projectText,projectText2;

    JComboBox<String>stateField;
    JLabel stateText;
    JLabel addTime,update,delete;
    JTextField idField;

    //----------
    User user;
    UserController userController;

    Task task;
    TaskController taskController;

    EmployeeTask employeeTask;
    EmployeeTaskController employeeTaskController;


    public LeaderTasks(User user) {
        this.user = user;
        taskController=new TaskController();
        userController=new UserController();
        employeeTask=new EmployeeTask();
        employeeTaskController=new EmployeeTaskController();
        task=new Task();
        background = new JPanel();
        size = new Size();
        position = new Position();
        component = new Component();
        sidebar = new JPanel();
        labels = new ArrayList<>();

        message = new JLabel();

        emailField=new JComboBox<>(getEmails());
        emailText=new JLabel("Email");

        emailField2=new JComboBox<>(getEmails());
        emailText2=new JLabel("Email");

        taskField=new JComboBox<>(getTasks());
        taskField2=new JComboBox<>(getTasks2());
        taskField3=new JComboBox<>(getTasks());

        taskText=new JLabel("Task name");
        taskText2=new JLabel("Assigned Task");
        taskText3=new JLabel("New Task");

        detailsField=new JTextArea();
        detailsText =new JLabel("task details");

        detailsField2=new JTextArea();
        detailsText2=new JLabel("new task details");

        projectField=new JTextField();
        projectText=new JLabel("project name");

        projectField2=new JTextField();
        projectText2=new JLabel("new task project name");


        idField=new JTextField();

        stateField=new JComboBox<>(new String[]{"finished","not finished"});
        stateText=new JLabel("state");
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
        setEmailField();
        setEmailText();
        setTaskField();
        setTaskText();
        setDetailsField();
        setDetailsText();
        setProjectField();
        setProjectText();

        setIdField();
        setEmailField2();
        setEmailText2();
        setTaskField2();
        setTaskText2();
        setTaskField3();
        setTaskText3();
        setDetailsField2();
        setDetailsText2();
        setProjectField2();
        setProjectText2();

        setStateField();
        setStateText();


        setTable();
        setUpdateButton();
        setDeleteButton();
    }

    private void addComponentsBackground() {
        addComponentBackground(sidebar);
        addComponentBackground(addTime);
        addComponentBackground(update);
        addComponentBackground(delete);
        addComponentBackground(emailField);
        addComponentBackground(emailText);
        addComponentBackground(taskField);
        addComponentBackground(taskText);
        addComponentBackground(detailsField);
        addComponentBackground(detailsText);
        addComponentBackground(projectField);
        addComponentBackground(projectText);




        addComponentBackground(idField);

        addComponentBackground(emailField2);
        addComponentBackground(emailText2);
        addComponentBackground(taskField2);
        addComponentBackground(taskText2);
        addComponentBackground(taskField3);
        addComponentBackground(taskText3);
        addComponentBackground(detailsField2);
        addComponentBackground(detailsText2);
        addComponentBackground(projectField2);
        addComponentBackground(projectText2);

        addComponentBackground(stateField);
        addComponentBackground(stateText);

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
    private String[] getTasks()  {
        String []ans;
        try {
            ArrayList<String>emails=taskController.searchTasksNameNotAssigned();
            ans=new String[emails.size()];
            emails.toArray(ans);
            return ans;
        } catch (SQLException e) {
            return new String[]{"empty"};
        }
    }

    private String[] getTasks2()  {
        String []ans;
        try {
            ArrayList<String>emails=taskController.searchTasksNameAssigned();
            ans=new String[emails.size()];
            emails.toArray(ans);
            return ans;
        } catch (SQLException e) {
            return new String[]{"empty"};
        }
    }
    private void refreshSelection()
    {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( getTasks() );
        taskField.setModel(model);
        model=new DefaultComboBoxModel<>( getTasks2() );
        taskField2.setModel(model);
        model=new DefaultComboBoxModel<>( getTasks());
        taskField3.setModel(model);

    }
    private void setAddButton()
    {
        String defaultPath="../image/assign_before.png";
        int x=size.sizeXPercent(10);
        int y=size.sizeYPercent(5);
        addTime = new JLabel(Helper.getImage(defaultPath,x,y));
        addTime.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(25),
                x,y
        );
        component.createButton(addTime,"../image/assign_before.png","../image/assign_after.png",x,y);
        addTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String email=(String)emailField.getSelectedItem();
                String taskName=(String)taskField.getSelectedItem();
                if(taskName==null)
                {
                    message.setText("please choose the task name");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    int userId=0;
                    int taskId=0;
                    try {
                        userId=userController.getEmployeeId(email);
                        taskId=taskController.getTaskId(taskName);
                    } catch (SQLException ex) {
                        message.setText("please refresh the page the task or email might not exist");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(userId==0||taskId==0)
                    {
                        message.setText("please refresh the page the email might not exist or the task might be assigned");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        employeeTask.setUserId(userId);
                        employeeTask.setTaskId(taskId);
                        task.setId(taskId);
                        task.setAssign("assigned");
                        try {
                            if(employeeTaskController.add(employeeTask)&&
                                    taskController.updateAssign(task))
                            {
                                message.setText("Task assigned successfully");
                                message.setForeground(Color.black);
                                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                                referesh_table();
                                refreshSelection();
                            }
                            else
                            {
                                message.setText("Please referesh the page");
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
                String id=idField.getText();
                String taskName=(String)taskField2.getSelectedItem();
                String newTaskName=(String)taskField3.getSelectedItem();
                if(id==null)
                {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(id.isEmpty())
                {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    if(newTaskName==null)
                    {
                        message.setText("please choose the new task to assign");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        int taskId=0;
                        int prevTaskId=0;
                        try {
                            prevTaskId=taskController.getTaskId(taskName);
                            taskId=taskController.getTaskId(newTaskName);
                        } catch (SQLException ex) {
                            message.setText("please refresh the page the task might not exist");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                        if(taskId==0||prevTaskId==0)
                        {
                            message.setText("please refresh the page the task might be assigned");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {

                            employeeTask.setId(Integer.parseInt(id));
                            employeeTask.setTaskId(taskId);

                            task.setId(taskId);
                            task.setAssign("assigned");

                            Task task2=new Task();
                            task2.setId(prevTaskId);
                            task2.setAssign("not assigned");
                            try {
                                if(employeeTaskController.updateTask(employeeTask)&&
                                        taskController.updateAssign(task)&&
                                        taskController.updateAssign(task2))
                                {
                                    message.setText("Task updated successfully");
                                    message.setForeground(Color.black);
                                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                                    referesh_table();
                                    refreshSelection();
                                }
                                else
                                {
                                    message.setText("Please choose from the table");
                                    message.setForeground(Color.red);
                                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } catch (SQLException ex) {
                                message.setText("please refresh the page the email might not exist or the task might be assigned");
                                message.setForeground(Color.red);
                                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
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
                String id=idField.getText();
                String taskName=(String)taskField2.getSelectedItem();
                if(id==null)
                {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (id.isEmpty()) {
                    message.setText("please choose from the table");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    int taskId=0;
                    try {
                        taskId=taskController.getTaskId(taskName);
                    } catch (SQLException ex) {
                        message.setText("please refresh the page the task might not exist");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(taskId==0)
                    {
                        message.setText("please refresh the page the task might be assigned before");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                    try {
                        task.setId(taskId);
                        task.setAssign("not assigned");
                        if(employeeTaskController.delete(Integer.parseInt(id))&&
                                taskController.updateAssign(task))
                        {
                            message.setText("Task deleted successfully");
                            message.setForeground(Color.black);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            referesh_table();
                            refreshSelection();
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


    private void setTaskField()
    {
        taskField.setFont(new Font("",Font.PLAIN,16));
        taskField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        taskField.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(15),
                x, y
        );
        setTaskFieldAction();
    }
    private void setTaskFieldAction() {
        taskField.addActionListener(event -> {
            String taskName = (String) taskField.getSelectedItem();

            try {
                int taskId=taskController.getTaskId(taskName);
                ArrayList<String>ans=taskController.getTask(taskId);
                if(ans.isEmpty())
                {
                    message.setText("No task exist with selected state");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    detailsField.setText(ans.get(0));
                    projectField.setText(ans.get(1));
                }
            } catch (SQLException e) {
                message.setText("please restart the system");
                message.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    private void setTaskText()
    {
        taskText.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskText.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(11),
                x, y
        );
    }

    private void setDetailsField()
    {
        detailsField.setEditable(false);
        detailsField.setFont(new Font("",Font.PLAIN,16));
        detailsField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(15);
        detailsField.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(15),
                x, y
        );
    }
    private void setDetailsText()
    {
        detailsText.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        detailsText.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(11),
                x, y
        );
    }
    private void setProjectField()
    {
        projectField.setEditable(false);
        projectField.setFont(new Font("",Font.PLAIN,16));
        projectField.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        projectField.setBounds(
                position.moveXPercent(86),
                position.moveYPercent(15),
                x, y
        );
    }
    private void setProjectText()
    {
        projectText.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        projectText.setBounds(
                position.moveXPercent(86),
                position.moveYPercent(11),
                x, y
        );
    }
    //-------------------------------
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

    private void setActionSelection() {
        stateField.addActionListener(event -> {
            String state = (String) stateField.getSelectedItem();

            try {
                tableFunction.clearTable(tableAttributes);
                ArrayList<String> rows = taskController.search(state);
                for (int i = 0; i < rows.size(); i += 6) {
                    tableFunction.addRow(tableAttributes,
                            new String[]{rows.get(i), rows.get(i + 1), rows.get(i + 2), rows.get(i + 3),
                                    rows.get(i + 4),rows.get(i + 5)});
                }
            } catch (SQLException e) {
                message.setText("please restart the system");
                message.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }




    //----------------------------------



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


    private void setTaskField2()
    {
        taskField2.setEnabled(false);
        taskField2.setFont(new Font("",Font.PLAIN,16));
        taskField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        taskField2.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(80),
                x, y
        );
    }
    private void setTaskText2()
    {
        taskText2.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskText2.setBounds(
                position.moveXPercent(46),
                position.moveYPercent(76),
                x, y
        );
    }

    private void setTaskField3()
    {
        taskField3.setFont(new Font("",Font.PLAIN,16));
        taskField3.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        taskField3.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(80),
                x, y
        );
        setTaskFieldAction3();
    }
    private void setTaskText3()
    {
        taskText3.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        taskText3.setBounds(
                position.moveXPercent(66),
                position.moveYPercent(76),
                x, y
        );
    }


    private void setTaskFieldAction3() {
        taskField3.addActionListener(event -> {
            String taskName = (String) taskField3.getSelectedItem();

            try {
                int taskId=taskController.getTaskId(taskName);
                ArrayList<String>ans=taskController.getTask(taskId);
                if(ans.isEmpty())
                {
                    message.setText("No task exist with selected state");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    detailsField2.setText(ans.get(0));
                    projectField2.setText(ans.get(1));
                }
            } catch (SQLException e) {
                message.setText("please restart the system");
                message.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void setDetailsField2()
    {
        detailsField2.setEditable(false);
        detailsField2.setFont(new Font("",Font.PLAIN,16));
        detailsField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(15);
        detailsField2.setBounds(
                position.moveXPercent(86),
                position.moveYPercent(80),
                x, y
        );
    }
    private void setDetailsText2()
    {
        detailsText2.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        detailsText2.setBounds(
                position.moveXPercent(86),
                position.moveYPercent(76),
                x, y
        );
    }
    private void setProjectField2()
    {
        projectField2.setEditable(false);
        projectField2.setFont(new Font("",Font.PLAIN,16));
        projectField2.setBackground(Color.WHITE);
        int x = size.sizeXPercent(10);
        int y = size.sizeYPercent(4);
        projectField2.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(87),
                x, y
        );
    }
    private void setProjectText2()
    {
        projectText2.setFont(new Font("",Font.PLAIN,18));
        int x = size.sizeXPercent(15);
        int y = size.sizeYPercent(4);
        projectText2.setBounds(
                position.moveXPercent(26),
                position.moveYPercent(83),
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
            ArrayList<String> rows = taskController.search();
            for (int i = 0; i < rows.size(); i += 6) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1),rows.get(i + 2),
                                rows.get(i + 3), rows.get(i + 4),rows.get(i + 5)});
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
            ArrayList<String> rows = taskController.search();
            for (int i = 0; i < rows.size(); i += 6) {
                tableFunction.addRow(tableAttributes,
                        new String[]{rows.get(i), rows.get(i + 1),rows.get(i + 2),
                                rows.get(i + 3), rows.get(i + 4),rows.get(i + 5)});
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
                String taskName = (String) tableAttributes.searchTable.getValueAt(r, 3);

                idField.setText(id);
                emailField2.setSelectedItem(email);
                taskField2.setSelectedItem(taskName);
            }
        });
    }

    private void setMessage() {
        message.setFont(new Font("", Font.PLAIN, 16));
    }
}
