package top.zwx.crm.manage.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;

import java.io.File;
import java.net.URL;

/**
 * @program: crm-manage
 * @description:
 * @author: zwx
 * @create: 2021-12-17 11:37
 **/
public class Verify {
    public static void main(String[] args) {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        URL resource = Verify.class.getResource("");
        System.out.println(resource);
        //图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("E:/course/crm-manage/src/main/resources/top/zwx/crm/manage/other/1.png");
        //输出code
        System.out.println(lineCaptcha.getCode());
        //验证图形验证码的有效性，返回boolean值
        System.out.println(lineCaptcha.verify("1234"));

    }
}