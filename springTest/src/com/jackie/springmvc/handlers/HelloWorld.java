package com.jackie.springmvc.handlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

    @RequestMapping("/helloworld")
    public String hello()throws Exception{
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://www.baidu.com");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
           result.append(line);
        }
        rd.close();
        System.out.println(result.toString());
        //return result.toString();
        return "success";
    }
}