package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.sound.sampled.*;

public class Welcome {
    public static void main(String[] args) {
        JFrame frame = new JFrame("UNITE");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);
        placeComponents(layeredPane, frame);
        frame.setVisible(true);

        // Putar musik latar belakang
        playBackgroundMusic("Aset/poke.wav");
    }

    private static void placeComponents(JLayeredPane layeredPane, JFrame frame) {
        ImageIcon imageIcon = new ImageIcon("Aset/poke.gif");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, -70, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(0));

        // Membuat label untuk tombol NEW GAME
        ImageIcon playIcon = new ImageIcon("Aset/play.png");
        Image image = playIcon.getImage();
        Image resizedImage = image.getScaledInstance(200, 60, Image.SCALE_SMOOTH);
        ImageIcon resizedPlayIcon = new ImageIcon(resizedImage);
        JLabel playLabel = new JLabel(resizedPlayIcon);
        playLabel.setBounds(600, 315, 200, 60);
        layeredPane.add(playLabel, Integer.valueOf(2));

        JButton playButton = new JButton("NEW GAME");
        playButton.setBounds(610, 330, 180, 30);
        playButton.setBackground(Color.GREEN);
        layeredPane.add(playButton, Integer.valueOf(2));

        // Membuat label untuk tombol LOAD GAME
        ImageIcon LoadIcon = new ImageIcon("Aset/load.png");
        Image imageLoad = LoadIcon.getImage();
        Image resizedImageLoad = imageLoad.getScaledInstance(200, 60, Image.SCALE_SMOOTH);
        ImageIcon resizedLoadIcon = new ImageIcon(resizedImageLoad);
        JLabel LoadLabel = new JLabel(resizedLoadIcon);
        LoadLabel.setBounds(600, 375, 200, 60);
        layeredPane.add(LoadLabel, Integer.valueOf(2));

        JButton loadButton = new JButton("LOAD GAME");
        loadButton.setBounds(610, 390, 180, 30);
        loadButton.setBackground(Color.GREEN);
        layeredPane.add(loadButton, Integer.valueOf(2));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                NewGame.main(null);
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputStream fontStream = null;
                Font retro = null;
                try {
                    fontStream = new FileInputStream("Aset/retro.ttf");
                    retro = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                    retro = retro.deriveFont(24f); // Mengatur ukuran font menjadi 24
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (FontFormatException | IOException e1) {
                    e1.printStackTrace();
                } finally {
                    if (fontStream != null) {
                        try {
                            fontStream.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        
                JFrame loadFrame = new JFrame();
                loadFrame.setSize(400, 500); // Mengubah ukuran frame menjadi 400x500
                loadFrame.setLocationRelativeTo(frame); // Menempatkan frame baru di tengah-tengah frame utama
        
                // Membuat panel untuk frame baru
                JPanel loadPanel = new JPanel();
                loadPanel.setLayout(null); // Menggunakan null layout untuk absolute positioning
        
                // Menambahkan gambar ke frame baru
                ImageIcon imageIcon1 = new ImageIcon("Aset/load_wood.png");
                JLabel image1Label = new JLabel(imageIcon1);
                image1Label.setBounds(0, 0, 390, 470); // Menyesuaikan ukuran label dengan ukuran gambar
                loadPanel.add(image1Label);
        
                // Membuat dan menambahkan JLabel dengan teks "Users"
                JLabel usersLabel = new JLabel("Users");
                usersLabel.setFont(retro); // Menggunakan font retro
                usersLabel.setForeground(Color.WHITE); // Menyesuaikan warna teks jika diperlukan
                usersLabel.setBounds(150, 10, 100, 30); // Menyesuaikan posisi dan ukuran label
                loadPanel.add(usersLabel);
        
                loadFrame.add(loadPanel);
                loadFrame.setVisible(true);
            }
        });
        
        

        layeredPane.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
    }

    // Method untuk memutar musik latar belakang
    private static void playBackgroundMusic(String musicPath) {
        try {
            File musicFile = new File(musicPath);
            if (musicFile.exists()) {
                System.out.println("Playing sound from: " + musicFile.getAbsolutePath());
                // Menggunakan Java Sound API untuk memutar musik
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the background music
            } else {
                System.out.println("File not found: " + musicFile.getAbsolutePath());
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio file: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
