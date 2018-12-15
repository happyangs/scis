package com.jcohy.scis.utils;

import com.jcohy.scis.model.SendEmailVo;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * Created by Byant on 2018-11-22.
 */
@Service
public class SimpleMailSender implements CommandLineRunner {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment environment;

    /**
     * 发送文本
     */
    public void sendText(SendEmailVo sendEmailVo) {
        String from = environment.getProperty("spring.mail.username");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(sendEmailVo.getToEmail());

        msg.setSubject(sendEmailVo.getSubject());
        msg.setText(sendEmailVo.getContent());

        this.mailSender.send(msg);
    }

    @Override
    public void run(String... strings) throws Exception {

    }

    /**
     * 发送页面模板-图片内嵌
     * @throws MessagingException
     */
//    public void sendTemplateMail() throws MessagingException {
//
//        String from = environment.getProperty("spring.mail.username");
//        String to = "ahuthj@163.com";
//
//        // 使用Mime消息体
//        MimeMessage message = mailSender.createMimeMessage();
//
//        MimeMessageHelper helper = null;
//        try {
//            helper = new MimeMessageHelper(message, true);
//
//            helper.setFrom(from);
//            helper.setTo(to);
//
//            helper.setSubject("first html report from yourself");
//
//            Context context = new Context();
//            String text = templateEngine.process("thymeleaf/mail/template", context);
//            helper.setText(text, true);
//            helper.addInline("soft", new FileSystemResource("D:/Desktop/515942841728020169.jpg"));
//            mailSender.send(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 发送附件
     * @throws MessagingException
     */
    public void sendAttachment() throws MessagingException {
        String from = environment.getProperty("spring.mail.username");
        String to = "ahuthj@163.com";

        // 使用Mime消息体
        MimeMessage message = mailSender.createMimeMessage();
        //指定系统文件
        File file = new File("D:/Desktop/20180319011002662.xls");
        // multipart参数为true，表示需要发送附件
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);

            helper.setSubject("20180319011002662.xls");
            helper.setText("check the file");


            FileSystemResource resource = new FileSystemResource(file);
            helper.addAttachment(file.getName(), resource);
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(message);


    }

}
