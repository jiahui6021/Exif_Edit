package com.example.pic;

public class Msg {
    private String title;
    private int type;
    private String msg;

    public Msg(String title, int type, String msg) {
        this.title = title;
        this.type = type;
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
