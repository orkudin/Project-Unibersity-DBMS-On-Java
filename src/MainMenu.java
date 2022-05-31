import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenu extends Container {
    public static Long userLogin = null;
    public static int StudID = 0;
    public static int TeachID = 0;
    public static int ModID = 0;
    public static boolean checkStudentMode = false;

    public MainMenu(){
        setSize(500, 400);
        setLayout(null);

            JLabel loginLabel = new JLabel("LOGIN:");
            loginLabel.setBounds(100, 50, 100, 30);
            add(loginLabel);

            JTextField loginTextField = new JTextField();
            loginTextField.setBounds(230, 50, 135, 30);
            add(loginTextField);

            JLabel passwordLabel = new JLabel("PASSWORD:");
            passwordLabel.setBounds(100, 100, 100, 30);
            add(passwordLabel);

            JPasswordField passwordTextField = new JPasswordField();
            passwordTextField.setBounds(230, 100, 135, 30);
            add(passwordTextField);

            JButton loginInSystemButton = new JButton("LOGIN IN SYSTEM");
            loginInSystemButton.setBounds(100, 250, 265, 30);
            add(loginInSystemButton);

            JLabel errorLabel = new JLabel("");
            errorLabel.setBounds(230, 150, 300, 30);
            add(errorLabel);

            JButton exitButton = new JButton("EXIT");
            exitButton.setBounds(100, 300, 265, 30);
            add(exitButton);

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            loginInSystemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userLogin = Long.valueOf(loginTextField.getText());
                    Long userLogin = Long.valueOf(loginTextField.getText());
                    String userPassword = passwordTextField.getText();

                    for (int i = 0; i < Main.allUsers.size(); i++) {
                        if (userLogin.equals(Main.allUsers.get(i).getId())&&
                                userPassword.equals(Main.allUsers.get(i).getPassword())){
                            System.out.println(Main.allUsers.get(i));
                            if (Main.allUsers.get(i) instanceof Student){

                                StudID = Main.getStudentIndex(Main.students, MainMenu.userLogin);
                                Main.frame.mainMenu.setVisible(false);
                                Main.frame.studentMenu.setVisible(true);

                            }else if(Main.allUsers.get(i) instanceof Teacher){

                                TeachID = Main.getTeacherIndex(Main.teachers, MainMenu.userLogin);
                                Main.frame.mainMenu.setVisible(false);
                                Main.frame.teacherMenu.setVisible(true);

                            }else if(Main.allUsers.get(i) instanceof Moderator){

                                ModID = Main.getModeratorIndex(Main.moderators, MainMenu.userLogin);
                                Main.frame.mainMenu.setVisible(false);
                                Main.frame.moderatorMenu.setVisible(true);
                            }
                        }

                    }

                    errorLabel.setText("Incorrect data");
                    errorLabel.setForeground(Color.red);
                    loginTextField.setText("");
                    passwordTextField.setText("");

                }
            });
    }
}
