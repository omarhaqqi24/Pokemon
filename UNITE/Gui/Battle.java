package Gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

public class Battle {
    public static void main(String[] args) {
        JFrame frame = new JFrame("UNITE");
        frame.setSize(1200, 675);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);
        placeComponents(layeredPane, frame);
        frame.setVisible(true);

        // Putar musik latar belakang
        playBackgroundMusic("Aset/naruto.wav");
    }

    private static void placeComponents(JLayeredPane layeredPane, JFrame frame) {
        ImageIcon originalIcon = new ImageIcon("Aset/battle.jpg");
        Image originalImage = originalIcon.getImage();
        // Mengubah ukuran gambar sesuai dengan ukuran frame
        Image scaledImage = originalImage.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        layeredPane.add(imageLabel, Integer.valueOf(0));

        layeredPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        ImageIcon atkIcon = new ImageIcon("Aset/atk.png");
        Image image = atkIcon.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage = image.getScaledInstance(600, 300, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon = new ImageIcon(resizedImage); // Mengonversi kembali ke ImageIcon
        JLabel atkLabel = new JLabel(resizedatkIcon); // Membuat label dengan gambar yang sudah diresize
        atkLabel.setBounds(0, 0, 400, 600);
        layeredPane.add(atkLabel, Integer.valueOf(2));
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
