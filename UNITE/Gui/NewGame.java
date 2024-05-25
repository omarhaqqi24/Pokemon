package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGame {
    public static void main(String[] args) {
        // Membuat instance dari JFrame
        JFrame frame = new JFrame("UNITE");

        // Mengatur ukuran frame
        frame.setSize(1920, 1080);

        // Mengatur aksi default saat frame ditutup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menambahkan komponen ke frame
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);
        placeComponents(layeredPane, frame);

        // Menampilkan frame
        frame.setVisible(true);
    }

    private static void placeComponents(JLayeredPane layeredPane, JFrame frame) {
        // Membuat label gambar
        ImageIcon imageIcon = new ImageIcon("Aset/poke.gif"); // Ganti dengan path gambar Anda
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, -70, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(0));

        // Membuat label untuk username
        JLabel userLabel = new JLabel("ENTER USERNAME");
        userLabel.setBounds(600, 320, 135, 25);
        layeredPane.add(userLabel, Integer.valueOf(2));

        // Membuat text field untuk input username
        JTextField userText = new JTextField(20);
        userText.setBounds(600, 350, 120, 25);
        userText.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(userText, Integer.valueOf(2));

        // Membuat label untuk tombol SUBMIT
        ImageIcon submitIcon = new ImageIcon("Aset/submit.png");
        Image image = submitIcon.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage = image.getScaledInstance(200, 60, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedSubmitIcon = new ImageIcon(resizedImage); // Mengonversi kembali ke ImageIcon
        JLabel submitLabel = new JLabel(resizedSubmitIcon); // Membuat label dengan gambar yang sudah diresize
        submitLabel.setBounds(700, 330, 200, 60);
        layeredPane.add(submitLabel, Integer.valueOf(2));

        // Membuat tombol untuk submit username
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(730, 348, 130, 25);
        layeredPane.add(submitButton, Integer.valueOf(2));

        // Menambahkan event listener untuk tombol submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = userText.getText();
                Player player = new Player(playerName);
                frame.dispose();
                SwingUtilities.invokeLater(() -> {
                    new Home(player);
                });
            }
        });

        // Mengatur ukuran dan tata letak layeredPane
        layeredPane.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
    }
}
