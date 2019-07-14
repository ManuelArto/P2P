package gui;

import javax.swing.*;

public class View extends JFrame {

    private static P2P p2p;
    private static JFrame frame;

    private JPanel Login;
    private JTextField username;
    private JButton Enter;

    //Chat
    private static JLabel chatArea;

    public static void main(String args[]) {
        p2p = new P2P();
        createFrame(new View().Login);
    }

    void addMessage(String message){
        JPanel panel = (JPanel)frame.getContentPane().getComponent(2);
        chatArea = (JLabel) panel.getComponent(0);
        chatArea.setText("CIAOOOOO");
    }

    public View() {
        Enter.addActionListener(actionEvent -> {
            try {
                p2p.username = username.getText() + ": ";
                p2p.start();
                frame.dispose();
                createFrame(new Chat().Chat);
                addMessage("CIAO");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    static void createFrame(JPanel content){
        frame = new JFrame("Chat P2P");
        frame.setContentPane(content);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }

}
