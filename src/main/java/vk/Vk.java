package vk;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import students.Student;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


/**
 * Created by aleksejpluhin on 05.04.16.
 */
public class Vk {
    private static final String API_VERSION = "5.50";
    private static final String REPORTS_DIRECTORY = System.getProperty("user.home") + "/reports";
    private static final String AUTH_URL = "https://oauth.vk.com/authorize"
            + "?client_id={APP_ID}"
            + "&scope=offline,messages"
            + "&redirect_uri={REDIRECT_URI}"
            + "&display={DISPLAY}"
            + "&v={API_VERSION}"
            + "&response_type=token";

    private static final String API_REQUEST = "https://api.vk.com/method/{METHOD_NAME}"
            + "?{PARAMETERS}"
            + "&access_token={ACCESS_TOKEN}"
            + "&v=" + API_VERSION;

    public static String accessToken;


    public Vk() {
        createVk();
    }


    private Vk(String appId, String accessToken) throws IOException {
        this.accessToken = accessToken;
        if (accessToken == null || accessToken.isEmpty()) {
//            Thread thread = new Thread(new Runnable() {
//
//                @Override
//                public void run() {
//                 ViewWin.launch(ViewWin.class);
//                }
//            });
//            thread.setDaemon(true);
//            thread.start();
//            try {
//                Thread.sleep(20000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            while (thread.isAlive()) {
//                try {
//                    Thread.currentThread().sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
            Desktop.getDesktop().browse(URI.create(auth(appId)));
            System.out.println(auth(appId));
        }
    }
    private static String invokeApi(String requestUrl, Student student) throws Exception {
        final URL url = new URL(requestUrl);

        try {
            Document document = Jsoup.connect(String.valueOf(url)).ignoreContentType(true).post();
            student.setIsSendVk();
        } catch (Exception e) {
          throw new Exception();
        }
        return null;
    }

    private String auth(String appId) throws IOException {
        String reqUrl = AUTH_URL
                .replace("{APP_ID}", appId)
                .replace("{PERMISSIONS}", "photos,messages")
                .replace("{REDIRECT_URI}", "https://oauth.vk.com/blank.html")
                .replace("{DISPLAY}", "popup")
                .replace("{API_VERSION}", API_VERSION);
        return reqUrl;
    }

    public String sendMessage(Student student,Parametrs params) throws Exception {
        try {
            return invokeApi("messages.send", params, student);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private String invokeApi(String method, Parametrs params,Student student) throws Exception {
        final String parameters = (params == null) ? "" : params.build();
        String reqUrl = API_REQUEST
                .replace("{METHOD_NAME}", method)
                .replace("{ACCESS_TOKEN}", accessToken)
                .replace("{PARAMETERS}&", parameters);
        try {
            return invokeApi(reqUrl,student);
        } catch (Exception e) {
            throw new Exception();
        }

    }

    private static Vk createVk() {
        FileInputStream fileInputStream;
        File file = new File(REPORTS_DIRECTORY, "token.txt");
        Vk vk = null;
        try {

            if(!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(new File(REPORTS_DIRECTORY + "/token.txt"));
            String s = scanner.nextLine();
//Указать id
            vk = new Vk("yours_id", s);
            scanner.close();

        } catch (Exception e) {
            System.out.println("Пройдите по ссылке, добавть acessToken в файл ");
            try {
                vk = new Vk("yours_id", null);
                Desktop.getDesktop().open(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
     return vk;

    }

    public   void distributionMessage(List<Student> studentList) {
       Vk vk = createVk();
        for (Student student : studentList) {
            Parametrs parametrs = new Parametrs();
            parametrs.setParams("user_id", student.getVk_id());
            parametrs.setParams("message", student.getMessage());
            try {
                vk.sendMessage(student, parametrs);
                student.setIsSendVk();
            } catch (Exception e) {
                continue;
            }
        }
    }



//    public static void main(String[] args) throws IOException {
//        Vk vk = createVk();
//        Parametrs params = new Parametrs();
//        params.setParams("user_id", "222154225");
//        params.setParams("message", "собака");
//        vk.sendMessage(params);
//
//
//    }


}