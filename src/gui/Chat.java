package gui;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Chat {

    private ClientWriter clientWriter;
    private String username;

    JPanel Chat;
    private JLabel ChatArea;
    private JScrollPane ScrollPane;
    private JFormattedTextField Insert;

    Chat() {
        Insert.addActionListener(actionEvent -> {
            clientWriter.send(Insert.getText());
            write(username + Insert.getText());
            Insert.setText("");
        });
    }

    void setClientWriter(ClientWriter clientWriter){
        this.clientWriter = clientWriter;
    }

    void setUsername(String username){ this.username = username;}

    void write(String mes){
        ChatArea.setText("<html>" + ChatArea.getText().replaceAll("<html>", "").replaceAll("</html>", "")
                         + "<br/>" + mes + "<br/> </html>" );
        SwingUtilities.invokeLater(() ->
            ScrollPane.getVerticalScrollBar().setValue(ScrollPane.getVerticalScrollBar().getMaximum())
        );
    }

}
