package gui;

import javax.swing.*;

public class View extends JFrame{

    private JPanel Chat;
    private JPanel Login;
    private JTextField username;
    private JButton Enter;

    public View() {
        setTitle("Chat P2P");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);
        add(Login);

        Enter.addActionListener(actionEvent -> {
            try {
                P2P.username = username.getText() + ": ";
                P2P.start();
                //add(Chat);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
