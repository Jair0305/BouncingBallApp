package org.example;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BouncingBallApp extends JFrame {
    private List<Ball> balls;
    private Clip bounceSound;

    public BouncingBallApp() throws MidiUnavailableException {
        balls = new ArrayList<>();

        Color[] colors = {
                new Color(255, 0, 0), // Red
                new Color(255, 127, 0), // Orange
                new Color(255, 255, 0), // Yellow
                new Color(127, 255, 0), // Chartreuse Green
                new Color(0, 255, 0), // Green
                new Color(0, 255, 127), // Spring Green
                new Color(0, 255, 255), // Cyan
                new Color(0, 127, 255), // Azure
                new Color(0, 0, 255), // Blue
                new Color(127, 0, 255), // Violet
                new Color(255, 0, 255), // Magenta
                new Color(255, 0, 127), // Rose
                Color.LIGHT_GRAY, // Light Gray
                Color.GRAY // Gray
        };
        for (int i = 0; i < 14; i++) {
            balls.add(new Ball(50, 50+i*10, i+1, 0, 5, 64 + i, colors[i]));
        }

        Synthesizer synthesizer = MidiSystem.getSynthesizer();

        synthesizer.open();
        MidiChannel channel = synthesizer.getChannels()[0];
        channel.programChange(0);

        int width = 600;
        int height = 600;
        setTitle("Bouncing Ball App");
        setSize(1500, 700 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BallPanel ballPanel = new BallPanel();
        add(ballPanel);

        setVisible(true);

        while (true) {
            for (Ball ball : balls) {
                ball.move();
                checkCollision(ball, channel, ball.getNote(), width, height);
            }
            ballPanel.repaint();

            try {
                Thread.sleep(10);  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkCollision(Ball ball, MidiChannel channel, int note, int width, int height) {
        if (ball.getX() - ball.getRadius() <= 0) {
            playNote(channel, note);
        } else if (ball.getX() + ball.getRadius() >= width) {
            playNote(channel, note);
        }

        if (ball.getY() - ball.getRadius() <= 0) {
            playNote(channel, note);
        } else if (ball.getY() + ball.getRadius() >= height) {
            playNote(channel, note);
        }
    }

    private class BallPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for(Ball ball : balls) {
                ball.draw(g);
            }
        }
    }

    private static void playNote(MidiChannel channel, int note) {
        int velocity = 100;
        int duration = 200;

        new Thread(() -> {
            channel.noteOn(note, velocity);
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channel.noteOff(note);
        }).start();
    }

    public static void main(String[] args) throws MidiUnavailableException {
        new BouncingBallApp();
    }
}