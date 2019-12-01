package com.broces;

import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BrocesGraph extends JPanel {

    BrocesGraph() {
        setSize(800, 2000);
    }

    public void drawGraph(Graphics g) {

    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        drawGraph(g);
    }

    public BufferedImage createImage() {
        int w = this.getWidth();
        int h = this.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.setColor(Color.GREEN);
        this.paint(g);
        g.dispose();

        return bi;
    }
}
