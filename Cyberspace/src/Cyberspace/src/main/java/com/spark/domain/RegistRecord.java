package com.spark.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.domain
 * @Description: TODO
 * @date Date : 2018-12-03  21:22
 * @version： V1.0
 */
@Setter
@Getter
@ToString(callSuper=true, includeFieldNames=true)

public class RegistRecord {

    private int id;
    private String name;
    private int status;
    private String mail;
    private String date;
    private String ip;

    public RegistRecord(){
        super();
    }
//    public RegistRecord(Map<String, Object> map) {
//    }
    public RegistRecord(Map<String, Object> map){
        this.id = (int)map.get("id");
        this.name = (String)map.get("name");
        this.status = (int)map.get("status");
        this.mail = (String) map.get("date");
        this.ip = (String)map.get("address");
    }

    public RegistRecord(int id, String name, int status, String mail, String date, String ip) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.mail = mail;
        this.date = date;
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
