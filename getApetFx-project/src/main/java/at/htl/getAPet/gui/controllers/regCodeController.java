package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class regCodeController {


    public TextField codeField;
    public Button sendCodeButton;
    public Label errorCode;
    public Label messageEmail;

    @FXML
    private void initialize() {

        sendCodeButton.setOnAction(actionEvent -> checkCode());

        String userEmailAddress = App.getUser().getEmail();
        messageEmail.setText("we sent you a Email to: " + userEmailAddress);

        try {
            sendMail(userEmailAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void checkCode() {
        int code = 0;
        if (!codeField.getText().equals("")) {

            try{
                 code = Integer.parseInt(codeField.getText());
            }catch(Exception e){
                e.printStackTrace();
            }

            if (code == App.getCode()) {
                changeSceneToChangeProfile();
            } else {
                errorCode.setText("code is not correct");
                codeField.clear();
            }
        }
    }

    public static void sendMail(String recipient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "getapetteam@gmail.com";
        //Your gmail password
        String password = "getAPetTeam123";
        char[] passwordChar = password.toCharArray();
        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recipient);

        //Send mail
        assert message != null;
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("CODE - Get_A_Pet");
            String htmlCode = "<h1> Here is your code to edit your account </h1> <br/> <h2><b>" + App.getCode() + " </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println("in catch");
        }
        return null;
    }

    private void changeSceneToChangeProfile() {
        try {
            App.setScene("changeProfile");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }


}
