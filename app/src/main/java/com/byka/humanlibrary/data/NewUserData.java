package com.byka.humanlibrary.data;

public class NewUserData implements PostData {
    private String pass;
    private String nickname;

    public NewUserData() {

    }

    public NewUserData(String nickname, String pass) {
        this.nickname = nickname;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
