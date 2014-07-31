/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author kawibi
 */
public class EncoderHelper {

    public String encodeMessage(String data) throws UnsupportedEncodingException {

        String encodeMessage = new String(Base64.encodeBase64(data.getBytes()));
        System.out.println("encodedBytes " + encodeMessage);

        String encodedUrl = URLEncoder.encode(encodeMessage, "UTF-8");
        System.out.println("encodedURL " + encodedUrl);

        return encodedUrl;
    }

    public String decodeMessage(String data) throws UnsupportedEncodingException {
    
        String decodeData = null;

        String decodedUrl = URLDecoder.decode(data, "UTF-8");
        System.out.println("decodedURL " + decodedUrl);

        byte[] decodedBytes = Base64.decodeBase64(decodedUrl.getBytes());
        System.out.println("decodedBytes " + new String(decodedBytes));

        decodeData=new String(decodedBytes);
        
        return decodeData;

    }
}
