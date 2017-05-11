package com.example;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Created by xiaosong on 2017/5/11.
 */
@XStreamAlias("xml")
public class NotifyBean {
    @XStreamAlias("return_code")
    private String return_code;
    @XStreamAlias("return_msg")
    private String return_msg;

    public NotifyBean() {
    }

    public NotifyBean(String return_code, String return_msg) {
        this.return_code = return_code;
        this.return_msg = return_msg;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}
