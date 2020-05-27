package com.ssh.auth.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther ChunKitAu
 * @create 2020-05-16 16
 */

@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键

    @Column(name = "user_name")
    private String userName; //用户名

    @Column(name = "account_name")
    private String accountName; //账号名

    @Column(name = "password")
    private String password;//密码


    //todo: 传入参数为11为数字映射错误？？？？
    @Column(name = "telphone")
    private Integer telphone; //手机

    @Column(name = "email")
    private String email; //邮箱

    @Column(name = "avatar")
    private String avatar; //头像

    @Column(name = "address")
    private String address;//地址

    @Column(name = "integral")
    private Integer integral; //积分

    @Column(name = "roleId")
    private Integer roleId ;//角色主键

    @Column(name = "login_time")
    private Date loginTime;


    public User() {
    }

    public User(Integer id, String userName, String accountName, String password, int telphone, String email, String avatar, String address, int integral, int roleId) {
        this.id = id;
        this.userName = userName;
        this.accountName = accountName;
        this.password = password;
        this.telphone = telphone;
        this.email = email;
        this.avatar = avatar;
        this.address = address;
        this.integral = integral;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTelphone() {
        return telphone;
    }

    public void setTelphone(Integer telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", telphone=" + telphone +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", address='" + address + '\'' +
                ", integral=" + integral +
                ", roleId=" + roleId +
                '}';
    }
}
