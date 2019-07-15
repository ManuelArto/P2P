package gui;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Chat extends JFrame{

    private ClientWriter clientWriter;
    private String username;

    private JFrame frame;
    private JPanel Chat;
    private JTextField Insert;
    private JLabel ChatArea;
    private JScrollPane ScrollPane;

    public Chat() {
        Insert.addActionListener(actionEvent -> {
            clientWriter.send(Insert.getText());
            write(username + Insert.getText());
            Insert.setText("");
        });
        ScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
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

    void setUsername(String username){ this.username = username;}

    void write(String mes){
        ChatArea.setText("<html>" + ChatArea.getText().replaceAll("<html>", "").replaceAll("</html>", "")
                         + "<br/>" + mes + "<br/> </html>" );
    }

}
