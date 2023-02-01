import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class ConnectionFrame extends JFrame implements ActionListener, KeyListener {
    JButton vsButton;
    JButton coopButton;
    JTextArea textField1;
    JTextArea textField2;
    public ConnectionFrame() {
        addKeyListener(this);
        Font font = new Font("Arial", Font.PLAIN, 20);
        JPanel panel = new JPanel();
        vsButton = new JButton();
        coopButton = new JButton();
        textField1 = new JTextArea(1,10);
        textField2 = new JTextArea(1,10);
        JTextArea infoText1 = new JTextArea();
        JTextArea infoText2 = new JTextArea();



        vsButton.setBounds(10,200,170,50);
        vsButton.addActionListener(this);
        vsButton.setText("Vs.-Connect");

        coopButton.setBounds(205,200,170,50);
        coopButton.addActionListener(this);
        coopButton.setText("Coop.-Connect");

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
        textField1.setText("::1");

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
        panel.add(vsButton);
        panel.add(coopButton);
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
        if (e.getSource() == coopButton){
            try {
                new Client(textField1.getText(),textField2.getText(),this,true);
                System.out.println("New Client");
                this.setVisible(false);
            } catch (IOException ex) {
                System.out.println("IOConnectionError");
            }
        }else {
            try {
                new Client(textField1.getText(),textField2.getText(),this,true);
                System.out.println("New Client");
                this.setVisible(false);
            } catch (IOException ex) {
                System.out.println("IOConnectionError");
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*System.out.println("Pressed");
        if (e.getKeyCode() == 10){
            e.consume();
            System.out.println("Enter");
            try {
                new Client(textField1.getText(),textField2.getText(),this);
                System.out.println("New Client");
                this.setVisible(false);
            } catch (IOException ex) {
                System.out.println("IOConnectionError");
            }
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
