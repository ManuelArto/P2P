package gui;

import javax.swing.*;

public class Chat extends JFrame{

    private ClientWriter clientWriter;

    private JFrame frame;
    private JPanel Chat;
    private JTextField Insert;
    private JLabel ChatArea;

    public Chat() {
        Insert.addActionListener(actionEvent -> {
            clientWriter.send(Insert.getText());
        });
    }

    void createFrame(){
        frame = new JFrame("Chat P2P");
        frame.setContentPane(Chat);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }

    void setClientWriter(ClientWriter clientWriter){
        this.clientWriter = clientWriter;
    }

    void write(String mes){
        ChatArea.setText(ChatArea.getText() + "\n" + mes + "\n");
    }

}
