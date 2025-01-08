package boundry;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1118, 643);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Main");
        lblNewLabel.setBounds(515, 10, 74, 43);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        contentPane.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Wine Screen");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScreenWine s = new ScreenWine();
                s.setVisible(true);
                setVisible(false); // Hide the Main frame
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnNewButton.setBounds(225, 152, 182, 43);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Manufacturer Screen");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScreenManufacturer sc = new ScreenManufacturer();
                sc.setVisible(true);
                setVisible(false); // Hide the Main frame
            }
        });
        btnNewButton_1.setBounds(515, 154, 318, 39);
        contentPane.add(btnNewButton_1);
    }
}
