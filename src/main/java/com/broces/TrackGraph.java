package com.broces;

import net.sf.json.JSONObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrackGraph extends BrocesGraph {
    JSONObject jsonObject;

    public TrackGraph(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void drawGraph(Graphics g) {
        int panelWidth = super.getWidth();
        Integer trackSayisi = (Integer) jsonObject.get("trackSayisi");
        String minHex = (String) jsonObject.get("min");
        String maxHex = (String) jsonObject.get("max");
        Integer min = null;
        Integer max = null;
        if(minHex != null && !minHex.equals("")){
            min = Integer.parseInt(minHex,16);
        }
        if(maxHex != null && !maxHex.equals("")){
            max = Integer.parseInt(maxHex,16);
        }
        int isaretlenecekSayisi = 0;
        if (min != null && max != null) {
            isaretlenecekSayisi = (max - min) + 1;
        }
        ArrayList randomList = new ArrayList<Integer>();
        if (min != null && max != null) {
            getRandomNumber(randomList, isaretlenecekSayisi, 1, trackSayisi);
        }

        Graphics2D g1 = (Graphics2D) g;

        double width = 50;
        double height = 50;
        double y = 10;
        int cizilenDaireSayisi = 1;

        int isaretlenenSayisi = 0;
        for (int i = 1; i <= trackSayisi; i++) {
            double x = (cizilenDaireSayisi * 10) + (width * (cizilenDaireSayisi - 1));
            if (x + width > panelWidth) {
                y = y + height + 5;
                cizilenDaireSayisi = 1;
                x = (cizilenDaireSayisi * 10) + (width * (cizilenDaireSayisi - 1));
            }
            Shape circle = new Ellipse2D.Double(x, y, width, height);

            if (randomList.size() > 0) {
                g1.setPaint(new Color(255, 64, 64));
            }

            if (randomList.size() > 0 && randomList.indexOf((Integer) i) > -1 && isaretlenenSayisi <= isaretlenecekSayisi) {
                g1.setPaint(new Color(132, 222, 2));
                String hexStr = Integer.toHexString(min + isaretlenenSayisi).toUpperCase();
                int StrWidth = g.getFontMetrics().stringWidth(hexStr);
                int strX = 0;
                if (isaretlenenSayisi == 0)
                    strX = ((Long) Math.round(x + (width / 2) - StrWidth)).intValue() + 22;
                else
                    strX = ((Long) Math.round(x + (width / 2) - StrWidth)).intValue() + 13;
                int strY = ((Long) Math.round(y + (height / 2))).intValue() + 5;
                g1.setFont(new Font("Arial", Font.PLAIN, 9));
                g1.drawString(hexStr, strX, strY);

                isaretlenenSayisi++;
            }

            g1.draw(circle);
            cizilenDaireSayisi++;
        }
    }

    private void getRandomNumber(List<Integer> randomList,int uretilecekMaxRandomSayi, int min, int max) {
        Random r = new Random();
        Integer randNum = r.nextInt((max - min) + 1) + min;
        if (randomList.indexOf(randNum) > -1) {
            getRandomNumber(randomList, uretilecekMaxRandomSayi, min, max);
        }

        if (randomList.size() < uretilecekMaxRandomSayi) {
            randomList.add(randNum);
            getRandomNumber(randomList, uretilecekMaxRandomSayi, min, max);
        }
    }
}
