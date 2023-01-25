import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class ConnectionFrame extends JFrame implements ActionListener {
    JButton button;
    JTextArea textField1;
    JTextArea textField2;
    public ConnectionFrame() {
        Font font = new Font("Arial", Font.PLAIN, 20);
        JPanel panel = new JPanel();
        button = new JButton();
        textField1 = new JTextArea(1,10);
        textField2 = new JTextArea(1,10);
        JTextArea infoText1 = new JTextArea();
        JTextArea infoText2 = new JTextArea();

        button.setBounds(125,200,150,50);
        button.addActionListener(this);
        button.setText("Connect");

        infoText1.setText("Enter host:");
        infoText1.setBounds(10,50,110,20);
        infoText1.setFont(font);
        infoText1.setVisible(true);
        infoText1.setBackground(new Color(150, 150, 150, 255));
        infoText1.setEditable(false);

        textField1.setBackground(new Color(94, 94, 94));
        textField1.setFont(font);
        textField1.setBounds(150,50,200,20);
        textField1.setForeground(new Color(255,255,255));
        textField1.setText("10.0.136.140");

        infoText2.setText("Enter name:");
        infoText2.setBounds(10,120,110,20);
        infoText2.setFont(font);
        infoText2.setVisible(true);
        infoText2.setBackground(new Color(150, 150, 150, 255));
        infoText2.setEditable(false);

        textField2.setBackground(new Color(94, 94, 94));
        textField2.setFont(font);
        textField2.setBounds(150,120,200,20);
        textField2.setForeground(new Color(255,255,255));
        textField2.setText("Test");

        panel.setLayout(null);
        panel.setBackground(new Color(80, 80, 80));
        panel.add(button);
        panel.add(textField1);
        panel.add(infoText1);
        panel.add(textField2);
        panel.add(infoText2);

        this.setSize(400,300);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            var client = new Client(textField1.getText(),textField2.getText(),this);
            System.out.println("New Client");
            this.setVisible(false);
        } catch (IOException ex) {
            System.out.println("IOConnectionError");
        }
    }
}
