package com.broces.bus;

import com.broces.TrackGraph;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.xml.security.utils.Base64;

@Service
@Transactional(isolation = Isolation.DEFAULT, readOnly = true, propagation = Propagation.REQUIRED)
public class BrocesBUS implements IBrocesBUS {

    @Override
    @Transactional(readOnly = false)
    public JSONObject trackCiz(JSONObject data) {
        JSONObject jsonObject = new JSONObject();

        int panelWidth = 800;
        int panelHeight = 1200;

        TrackGraph trackGraph = new TrackGraph(data);
        BufferedImage bufferedImage = trackGraph.createImage();

        BufferedImage bi = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.getGraphics();
        g.drawImage(bufferedImage, 5, 5, null);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "png", baos);
        } catch (IOException e) {
        }
        byte[] image = baos.toByteArray();
        String encode = "data:image/jpeg;base64," + Base64.encode(image);

        jsonObject.put("success", true);
        jsonObject.put("imageData", encode);
        return jsonObject;

    }
}