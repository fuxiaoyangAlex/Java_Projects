package com.niit.clouddemo.util;

import com.alibaba.fastjson.JSONObject;
import com.niit.clouddemo.service.IQuestionService;
import net.sf.jsqlparser.schema.Column;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：Wangzhuang2
 * @date ：Created in 2019/06/13 14:53
 * @description：
 * @modified By：
 * @version: 1.0
 * TODO: 字符串验证、获取唯一ID等常用函数工具类
 */
public class BaseUtil {


    /**
     * 判断字符串是否为空
     *
     * @param   str       需要判断的字符串
     * @return  {boolean} 空：true ； 非空 ：false
     */
    public static boolean isNullOrEmpty(String str){
        if(null == str || "".equals(str)){
            return  true;
        }

        return false;
    }

    /**
     * 验证是否为手机号
     *
     * @param   phone       手机号
     * @return  {boolean}   是：true ； 否：false
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        }

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 验证用户名格式是否正确
     * 用户名格式：2到10位数字、字母、汉字组成
     *
     * @param   username    用户名
     * @return  {boolean}   正确：true ；不正确：false
     */
    public static boolean validateUserName(String username) {
        String regex = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]{2,10}$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(username);
        return m.matches();
    }

    /**
     * 验证密码是否正确
     * 密码要求：6到20位非重复数字或者字母组成，不能纯数字或者字母
     *
     * @param   password    密码
     * @return  {boolean}   正确：true ；不正确：false
     */
    public static boolean validatePassword(String password) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUID() {
        IdWorker worker = new IdWorker(1,1,1);
        String UID = String.valueOf(worker.nextId()).substring(7,19);
        return UID;
    }

    /**
     * 获取properties配置中属性
     *
     * @return
     */
    public static String getPropertyValue(String propertiesName, Object property){
        Properties properties = new Properties();
        InputStream inputStream = BaseUtil.class.getClassLoader().getResourceAsStream(propertiesName);

        try {
            properties.load(inputStream);

            return (String) properties.get(property);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * TODO: 生成手机验证码🔓
     * */
    public static String getRandomAuthCode(){
        IdWorker worker = new IdWorker(1,1,1);
        String AuthCodeTmp = String.valueOf(worker.nextId());
        return  AuthCodeTmp.substring(AuthCodeTmp.length() - 6, AuthCodeTmp.length());

    }

//    public static void main(String[] args) throws IOException {
//        String myPhoneNumber = "13462341587";
//        String randomAuthCode = BaseUtil.getRandomAuthCode();
//        System.out.println(randomAuthCode);
//        SMSVerification(myPhoneNumber, randomAuthCode);
//    }

    /**
     * TODO: 发送手机验证码📱
     *
     * @return
     */
    public static void SMSVerification(String phoneNumber, String authCode) throws IOException{

        //发送内容
        String content = "您的手机号："+ phoneNumber + "，验证码："+ authCode + "，请及时完成验证，如不是本人操作请忽略。";
        // 创建StringBuffer对象用来操作字符串
        StringBuffer sb = new StringBuffer("https://api.chanyoo.net/sendsms?");

        // 向StringBuffer追加平台帐号
        sb.append("username=Sunshineisbright");

        // 向StringBuffer追加调用密码
        sb.append("&password=20191731");

        // 向StringBuffer追加手机号码
        sb.append("&mobile=" + phoneNumber);

        // 向StringBuffer追加短信内容转URL标准码
        sb.append("&content=").append(URLEncoder.encode(content, "UTF-8"));

        // 创建url对象
        URL url = new URL(sb.toString());
        System.out.println(url.toString());

        // 打开url连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置url请求方式GET或者POST
        connection.setRequestMethod("GET");

        // 发送请求
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

//         返回发送结果
        String inputline = in.readLine();

        // 输出返回结果
        System.out.println(inputline);
    }




}