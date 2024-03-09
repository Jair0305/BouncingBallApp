package org.example;

import java.awt.*;


public class Ball {
    private int x;// = 50;
    private int y;// = 50;
    private double xSpeed;// = 5;
    private double  ySpeed;// = 3;
    private int radius; //= 5;
    private int note;
    private Color color;

    public Ball(int x, int y, double xSpeed, int ySpeed, int radius, int note, Color color)
    {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.radius = radius;
        this.note = note;
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

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double  getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move()
    {
        x += xSpeed;
        y += ySpeed;

        if(x + radius > 600 )
        {
            xSpeed = -xSpeed;
        }
        if(x- radius < 0)
        {
            xSpeed = -xSpeed;
        }
        if(y + radius > 600)
        {
            ySpeed = -ySpeed;
        }
        if(y - radius < 0)
        {
            ySpeed = -ySpeed;
        }
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
