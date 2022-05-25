package com.thellog.laravelapiandroid.Model;

public class Home_Model {
    private int imgeHome;
    private String nameHome;

    public Home_Model(int imgeHome, String nameHome) {
        this.imgeHome = imgeHome;
        this.nameHome = nameHome;
    }

    public int getImgeHome() {
        return imgeHome;
    }

    public void setImgeHome(int imgeHome) {
        this.imgeHome = imgeHome;
    }

    public String getNameHome() {
        return nameHome;
    }

    public void setNameHome(String nameHome) {
        this.nameHome = nameHome;
    }
}
