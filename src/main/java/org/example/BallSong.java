package org.example;

import java.awt.*;

public class BallSong {
    private int x;// = 50;
    private int y;// = 50;
    private double xSpeed;// = 5;
    private double  ySpeed;// = 3;
    private int radius; //= 5;
    private int[] notes;
    private Color color;

    private int currentNoteIndex = 0;

    public BallSong(int x, int y, double xSpeed, double ySpeed, int radius, int[] notes, Color color)
    {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.radius = radius;
        this.notes = notes;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double  getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int[] getNote() {
        return notes;
    }

    public void setNote(int[] notes) {
        this.notes = notes;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        if(x + radius > 600) {
            xSpeed = -xSpeed;
            x = 600 - radius;
        }
        if(x - radius < 0) {
            xSpeed = -xSpeed;
            x = radius;
        }
        if(y + radius > 600) {
            ySpeed = -ySpeed;
            y = 600 - radius - 1;
        }
        if(y - radius < 0) {
            ySpeed = -ySpeed;
            y = radius + 1;
        }
    }

    public int getCurrentNoteIndex() {
        return currentNoteIndex;
    }

    public void incrementCurrentNoteIndex() {
        currentNoteIndex = (currentNoteIndex + 1) % notes.length;
    }


    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
