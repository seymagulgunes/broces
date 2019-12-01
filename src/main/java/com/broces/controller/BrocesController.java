package com.broces.controller;

import com.broces.bus.IBrocesBUS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import net.sf.json.JSONObject;

@Controller(value = "BrocesController")
@RequestMapping(value = "/broces")
public class BrocesController {

    @Autowired
    IBrocesBUS brocesBUS;

    @RequestMapping(value = "/trackCiz.ajax")
    public void trackCiz(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject sendJson = new JSONObject();
        sendJson.put("trackSayisi", Integer.parseInt(request.getParameter("trackSayisi").toString()));
        if (request.getParameter("min") != null)
            sendJson.put("min", request.getParameter("min"));

        if (request.getParameter("max") != null)
            sendJson.put("max", request.getParameter("max").toString());

        JSONObject jsonObject = brocesBUS.trackCiz(sendJson);

        response.getWriter().write(jsonObject.toString());
    }
}
