/*==================================================================
프로젝트명 : 개인정보 동의서
작성지 : 신정호
작성일 : 2023년 12월 06일
용도 : 개인정보 동의서 컨트롤러
==================================================================*/

package com.personalPrivacyServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping(value = "/")
    public String XrayBoard(Model model){
        return "index";
    }

    @PostMapping(value = "/createPic")
    @ResponseBody
    public ResponseEntity<Object> createImage(@RequestParam(name = "mPhone", required = false) String mPhone,
                            @RequestParam(name = "mName", required = false) String mName,
                            @RequestParam(name = "sPhone", required = false) String sPhone,
                            @RequestParam(name = "sName", required = false) String sName) {

//    public ResponseEntity<Object> createImage(@RequestBody Map<String, Object> requestBody) {
//
//        String mPhone = (String) requestBody.get("mPhone");
//        String mName = (String) requestBody.get("mName");
//        String sPhone = (String) requestBody.get("sPhone");
//        String sName = (String) requestBody.get("sName");

        String imagePath;
        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if(sPhone.length()==0) imagePath = "C:/" +
                "z/normal_format.png";
        else imagePath = "C:/privacy_image/teenager_format.png";
        String outputPath = "C:/privacy_image/"+ dateFolder;
        File outputFolder = new File(outputPath);
        if (!outputFolder.exists()) {
            boolean folderCreated = outputFolder.mkdirs();
            if (!folderCreated) {
                throw new RuntimeException("Failed to create output folder: " + outputPath);
            }
        }

        try {
            processImage(imagePath, outputPath, mPhone, mName, sPhone, sName);
            // 성공적으로 처리된 경우 200 상태 코드로 응답
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // 처리 중 오류 발생 시 400 상태 코드로 응답
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
    }

    private void processImage(String imagePath, String outputPath, String mPhone, String mName, String sPhone, String sName) {
        SwingUtilities.invokeLater(() -> {
            Integer fontSize = 18;
            try {
                BufferedImage image = ImageIO.read(new File(imagePath));
                Graphics2D g2d = image.createGraphics();
                g2d.setFont(new Font("Arial", Font.PLAIN, 20));
                int x; int y;
                int paramCount;
                if(sPhone.length()==0) paramCount = 2;
                else paramCount = 4;

                for (int i = 0; i < paramCount; i++) {
                    String label;
                    if (i == 0) {label = mPhone;}
                    else if (i == 1) {label = mName;}
                    else if (i == 2) {label = sPhone;}
                    else {label = sName;}

                    // 각 라벨에 대한 좌표 및 스타일 정의
                    if(i==0){x=241; y=226;}
                    else if(i==1){x=241; y=303;}
                    else if(i==2){x=241; y=1208;}
                    else{x=241; y=1269;}
                    Color labelColor = new Color(0, 0, 0);
                    Font font = new Font("NanumGothic", Font.PLAIN, fontSize);

                    // 라벨 그리기
                    g2d.setColor(labelColor);
                    g2d.setStroke(new BasicStroke(6));
                    g2d.setFont(font);
                    g2d.drawString(label, x, y);
                }

                g2d.dispose(); // 그래픽 컨텍스트 해제
                String outputImagePath = outputPath + "/" + mPhone + "_" + mName + ".png";
                ImageIO.write(image, "png", new File(outputImagePath));



            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
