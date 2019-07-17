package gui;

import javax.swing.*;

public class View {

    private static P2P p2p;
    private static Chat chat;
    private static JFrame frame;

    private JPanel Login;
    private JTextField username;
    private JButton Enter;


    public static void main(String args[]) {
        chat = new Chat();
        p2p = new P2P(chat);
        createFrame(new View().Login);
    }

    public View() {
        Enter.addActionListener(actionEvent -> {
            try {
                p2p.username = username.getText() + ": ";
                p2p.start();
                chat.setUsername(username.getText() + ": ");
                frame.dispose();
                createFrame(chat.Chat);
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
