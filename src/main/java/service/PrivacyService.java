package service;

import com.jcraft.jsch.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.nio.file.*;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

@Service
@RequiredArgsConstructor
public class PrivacyService {

    public void createImage(String folderPath, String outputPath, String mPhone, String mName, String sPhone, String sName) {

    }

}
