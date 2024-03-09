package org.example;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BouncingSong extends JFrame {
    private BallSong ball;
    private Clip bounceSound;

    int[] notes = {64,63,64,63,64,59,62,60,57};

    public BouncingSong() throws MidiUnavailableException {

        ball = new BallSong(50, 50, 8, 3, 5, notes, Color.RED);
        int prevX = ball.getX();
        int prevY = ball.getY();
        Synthesizer synthesizer = MidiSystem.getSynthesizer();

        synthesizer.open();
        MidiChannel channel = synthesizer.getChannels()[0];
        channel.programChange(0);


        int width = 600;
        int height = 600;
        setTitle("Bouncing Ball App");
        setSize(600, 600 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BallPanel ballPanel = new BallPanel();
        add(ballPanel);

        setVisible(true);

        while (true) {
            ball.move();
            checkCollision(ball, channel, ball.getNote(), width, height);
            ballPanel.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkCollision(BallSong ball, MidiChannel channel, int[] notes, int width, int height) {
        int noteIndex = ball.getCurrentNoteIndex();
        boolean collisionDetected = false;

        if (ball.getX() - ball.getRadius() + ball.getxSpeed() < 0 || ball.getX() + ball.getRadius() + ball.getxSpeed() > width) {
            collisionDetected = true;
            ball.setxSpeed(-ball.getxSpeed());
        }

        if (ball.getY() - ball.getRadius() + ball.getySpeed() < 0 || ball.getY() + ball.getRadius() + ball.getySpeed() > height) {
            collisionDetected = true;
            ball.setySpeed(-ball.getySpeed());
        }

        if (collisionDetected) {
            playNote(channel, notes[noteIndex]);
            ball.incrementCurrentNoteIndex();
        }
    }



    private class BallPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            ball.draw(g);
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
        new BouncingSong();
    }
}