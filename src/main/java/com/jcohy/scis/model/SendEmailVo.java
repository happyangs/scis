package com.jcohy.scis.model;

import lombok.Data;

/**
 * 发送邮件
 * Created by Byant on 2018-12-14.
 */
@Data
public class SendEmailVo {

    /**
     * 收件邮箱
     */
    private String toEmail;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;
}
