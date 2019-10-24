package com.asuna.textutils.entity;

/**
 * 账户实体类
 */
public class Account {
    private int id;
    private String account;
    private boolean gender;
    private String mail;
    private String phone;
    private String password;
    private double balance;
    private int level;
    private int exp;

    public Account() {
    }

    public Account(int id, String account, boolean gender, String mail, String phone, String password, double balance, int level, int exp) {
        this.id = id;
        this.account = account;
        this.gender = gender;
        this.mail = mail;
        this.phone = phone;
        this.password = password;
        this.balance = balance;
        this.level = level;
        this.exp = exp;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void levelUp(int exp){
        level++;
        this.exp = exp;
    }

    public void levelUp(int level, int exp){
        this.level += level;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", gender=" + gender +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", level=" + level +
                ", exp=" + exp +
                '}';
    }
}
