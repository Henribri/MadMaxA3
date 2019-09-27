package view;

import controller.Controller;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class View extends JFrame implements ActionListener {
    Model model = new Model();
    Controller controller = new Controller();

    JLabel l1, l2, l3;
    JTextField tf1;
    JButton btn1;
    JPasswordField p1;

    public View() {
        super("Login Form");

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

        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(p1);
        add(btn1);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String uname = tf1.getText();
        String pass = new String(p1.getPassword());

        Boolean exists = false;
        String pathToDecrypt = null;
        String pathDestination = null;

        try {
            exists = model.checkIfUserExist(uname, pass);

            if (exists) {
                this.setVisible(false);

                JFileChooser chooser = new JFileChooser();
                chooser.setApproveButtonText("Choix du fichier à décrypter");
                if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                    pathToDecrypt = chooser.getSelectedFile().getAbsolutePath();
                else
                    System.exit(-1);

                JFileChooser chooser2 = new JFileChooser();
                chooser2.setApproveButtonText("Fichier de destination");
                if (chooser2.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                    pathDestination = chooser2.getSelectedFile().getAbsolutePath();
                else
                    System.exit(-1);

                controller.launchProcedure(pathToDecrypt, pathDestination);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect login or password",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException | SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    public void frameFinal() {
        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("Terminé !", SwingConstants.CENTER));
        window.setBounds(900, 150, 50, 10);
        window.setVisible(true);
    }
}
