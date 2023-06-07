package View;


import Controller.UserController;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

/**
 * deal with login view of users
 */

public class Login extends JFrame {

    private static Login loginPage;
    JPanel background, login;
    Component component;
    Position position;
    Size size;
    JLabel logo;
    JLabel loginTitle, loginButton;
    JTextField emailField;
    JLabel emailText;
    JPasswordField passwordField;
    JLabel passText;
    JLabel message;

    //----------
    UserController userController;
    User user;

    private Login() {
        background = new JPanel();
        size = new Size();
        position = new Position();
        login = new JPanel();
        loginTitle = new JLabel("Sign In");
        emailField = new JTextField("eyad4@gmail.com");
        emailText=new JLabel("Email");
        passwordField = new JPasswordField("eE0123456789");
        passText=new JLabel("Password");
        component=new Component();
        message=new JLabel();
        //---------
        userController=new UserController();
        user=new User();
        ShowLogin();
    }
    public static Login getLoginPage()
    {
        if(loginPage==null)
        {
            loginPage=new Login();
        }
        return loginPage;
    }
    public void ShowLogin() {
        setMessage();
        setComponentsBackground();
        addComponentsBackground();
        setComponentsLogin();
        addComponentsLogin();
        this.setVisible(true);
    }

    private void setComponentsBackground() {
        setJFrame();
        setBackground();
    }
    private void addComponentsBackground() {
        addComponentBackground(login);
    }
    private void addComponentBackground(Object component) {
        if (component instanceof JButton) {
            background.add((JButton) component);
        } else if (component instanceof JPanel) {
            background.add((JPanel) component);
        } else if (component instanceof JTextField) {
            background.add((JTextField) component);
        }
    }

    private void setComponentsLogin() {
        setLogin();
        setLogo();
        setLoginTitle();
        setName();
        setNameText();
        setPass();
        setPassText();
        setLoginButton();
    }

    private void addComponentsLogin() {
        addComponentLogin(loginTitle);
        addComponentLogin(logo);
        addComponentLogin(loginButton);
        addComponentLogin(emailField);
        addComponentLogin(emailText);
        addComponentLogin(passwordField);
        addComponentLogin(passText);
    }
    private void addComponentLogin(Object component) {
        if (component instanceof JButton) {
            login.add((JButton) component);
        } else if (component instanceof JPanel) {
            login.add((JPanel) component);
        } else if (component instanceof JTextField) {
            login.add((JTextField) component);
        } else if (component instanceof JLabel) {
            login.add((JLabel) component);
        }
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



    private void setLogin() {
        login.setBackground(Color.decode("#ffffff"));
        login.setSize(size.sizeXPercent(30), size.sizeYPercent(75));
        login.setLayout(null);
        login.setLocation(position.centerX(login.getWidth()), position.centerY(login.getHeight()));
    }
    private void setLogo() {
        String defaultPath="../image/logo.Png";
        int x=size.sizeXPercent(login.getWidth(), 18);
        int y=size.sizeYPercent(login.getHeight(), 16);
        logo = new JLabel(Helper.getImage(defaultPath,x,y));
        logo.setBounds(
                position.centerX(login.getWidth(), size.sizeXPercent(login.getWidth(), 18)),
                position.moveYPercent(login.getHeight(), 8),
                size.sizeXPercent(login.getWidth(), 18),
                size.sizeYPercent(login.getHeight(), 16)
        );
    }
    private void setLoginTitle() {
        Font font = new Font("", Font.BOLD, 42);
        loginTitle.setBounds(
                position.centerX(login.getWidth(), size.sizeXPercent(login.getWidth(), 35)),
                position.moveYPercent(login.getHeight(), 29),
                size.sizeXPercent(login.getWidth(), 35),
                size.sizeYPercent(login.getHeight(), 8)
        );
        loginTitle.setFont(font);
        loginTitle.setForeground(Color.decode("#2573B0"));
    }


    private void setName() {
        emailField.setBounds(
                position.centerX(login.getWidth(), size.sizeXPercent(login.getWidth(), 80)),
                position.moveYPercent(login.getHeight(), 45),
                size.sizeXPercent(login.getWidth(), 80),
                size.sizeYPercent(login.getHeight(), 5)
        );
        emailField.setBackground(Color.decode("#ffffff"));
        emailField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        emailField.setFont(new Font("", Font.PLAIN, 20));
        emailField.setForeground(Color.BLACK);
    }
    private void setNameText() {
        emailText.setBounds(
                position.centerX(login.getWidth(), size.sizeXPercent(login.getWidth(), 80)),
                position.moveYPercent(login.getHeight(), 39),
                size.sizeXPercent(login.getWidth(), 80),
                size.sizeYPercent(login.getHeight(), 5)
        );
        emailText.setFont(new Font("", Font.PLAIN, 20));
        emailText.setForeground(Color.LIGHT_GRAY);
    }


    private void setPass() {
        passwordField.setBounds(
                position.centerX(login.getWidth(), size.sizeXPercent(login.getWidth(), 80)),
                position.moveYPercent(login.getHeight(), 60),
                size.sizeXPercent(login.getWidth(), 80),
                size.sizeYPercent(login.getHeight(), 5)
        );
        passwordField.setBackground(Color.decode("#ffffff"));
        passwordField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        passwordField.setFont(new Font("", Font.PLAIN, 20));
        passwordField.setForeground(Color.BLACK);
    }
    private void setPassText() {
        passText.setBounds(
                position.centerX(login.getWidth(), size.sizeXPercent(login.getWidth(), 80)),
                position.moveYPercent(login.getHeight(), 54),
                size.sizeXPercent(login.getWidth(), 80),
                size.sizeYPercent(login.getHeight(), 5)
        );
        passText.setFont(new Font("", Font.PLAIN, 20));
        passText.setForeground(Color.LIGHT_GRAY);
    }

    private void setLoginButton() {
        String defaultPath="../image/login_before.png";
        int x=size.sizeXPercent(login.getWidth(),40);
        int y=size.sizeYPercent(login.getHeight(),8);
        loginButton = new JLabel(Helper.getImage(defaultPath,x,y));
        loginButton.setBounds(
                position.centerX(login.getWidth(), x),
                position.moveYPercent(login.getHeight(), 80),
                x,y
        );
        component.createButton(loginButton,"../image/login_before.png","../image/login_after.png",x,y);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String email=emailField.getText();
                String password=new String(passwordField.getPassword());
                if(email.isEmpty())
                {
                    message.setText("Please fill the email");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(password.isEmpty())
                {
                    message.setText("Please fill the password");
                    message.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    if(!user.setEmail(email))
                    {
                        message.setText("Please enter valid email:name@gmail.com");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(!user.setPassword(password))
                    {
                        message.setText("Please enter valid password one upper letter or one lower letter and at least 8 characters");
                        message.setForeground(Color.red);
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        try {
                            if(!userController.search(user))
                            {
                                message.setText("user not found");
                                message.setForeground(Color.red);
                                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                            {
                                message.setText("Login successfully");
                                message.setForeground(Color.black);
                                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                                switch (user.getRole())
                                {
                                    case "employee":
                                        new TimePage(user);
                                        break;
                                    case "team-leader":
                                        new LeaderTimePage(user);
                                        break;
                                    case "project-manager":
                                        new ProjectManager(user);
                                        break;
                                    case "admin":
                                        new ManageUserPage(user);
                                        break;
                                }
                                dispose();
                            }
                        } catch (SQLException ex) {
                            message.setText("This email already used before");
                            message.setForeground(Color.red);
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }


                }

            }
        });
    }
    private void setMessage()
    {
        message.setFont(new Font("",Font.PLAIN,16));
    }
}
