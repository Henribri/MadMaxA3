package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class View extends JFrame implements ActionListener {
    Model model = new Model();

    final JFrame frame;
    JLabel l1, l2, l3;
    JTextField tf1;
    JButton btn1;
    JPasswordField p1;

    public View() {
        frame = new JFrame("Login Form");

        l1 = new JLabel("Login Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Username");
        l3 = new JLabel("Password");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Login");
        btn1.addActionListener(this);

        l1.setBounds(150, 30, 100, 30);
        l2.setBounds(10, 70, 100, 30);
        l3.setBounds(10, 110, 100, 30);
        tf1.setBounds(150, 70, 200, 30);
        p1.setBounds(150, 110, 200, 30);
        btn1.setBounds(150, 160, 100, 30);

        frame.add(l1);
        frame.add(l2);
        frame.add(tf1);
        frame.add(l3);
        frame.add(p1);
        frame.add(btn1);

        frame.setSize(400, 300);
        setParams(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String uname = tf1.getText();
        String pass = new String(p1.getPassword());

        System.out.println(uname);
        System.out.println(pass);

        Boolean exists = false;

        try {
            exists = model.checkIfUserExist(uname,pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (exists) {
            JFrame wel = new JFrame("Bravo");
            setParams(wel);
            wel.setSize(400, 200);

        } else {
            JOptionPane.showMessageDialog(this, "Incorrect login or password",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void setParams(JFrame f) {
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLayout(null);
        f.setVisible(true);
    }
}
