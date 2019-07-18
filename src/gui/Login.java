package gui;

import javax.swing.*;

public class Login {

    private static JFrame frame;

    JPanel Login;
    private JTextField username;
    private JButton Enter;

    Login(Runnable runnable, Chat chat) {
        Enter.addActionListener(actionEvent -> {
            try {
                P2P.username = username.getText() + ": ";
                new Thread(() ->
                    runnable.run()
                ).start();
                chat.setUsername(username.getText() + ": ");
                frame.setContentPane(chat.Chat);
                frame.revalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    static void createFrame(JPanel content){
        frame = new JFrame("Chat P2P");
        frame.setContentPane(content);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }

}
