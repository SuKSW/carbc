import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlockStatusUI {

    private JPanel proposalPane;
    private JButton sendProposalButton;
    private JLabel blockDetails;
    private JTextArea blockStatus;
    private JTextArea blockStatusText;

    public BlockStatusUI() {
        blockDetails.setText("working");
        sendProposalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button working");
            }
        });
    }

    public void runBlockStatusUI() {
        JFrame frame = new JFrame("BlockStatusUI");
        frame.setContentPane(new BlockStatusUI().proposalPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BlockStatusUI");
        frame.setContentPane(new BlockStatusUI().proposalPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
