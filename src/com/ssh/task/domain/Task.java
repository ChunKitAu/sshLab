package com.ssh.task.domain;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_user")
    private Integer create_user;//创建用户的主键

    @Column(name = "type_id")
    private Integer type_id; //分类主键

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "integral")
    private Integer integral;

    @Column(name = "img")
    private String img;

    @Column(name = "status")
    private boolean status;

    @Column(name = "create_time")
    private Date create_time;

    @Column(name = "start_time")
    private Date start_time;

    @Column(name = "end_time")
    private Date end_time;

    @Column(name="number")
    private Integer number;

    @Column(name="phone")
    private Integer phone;

    public Task() {
    }

    public Task(Integer id, Integer create_user, Integer type_id, String title, String content, Integer integral, String img, boolean status, Date create_time, Date start_time, Date end_time, Integer number, Integer phone) {
        this.id = id;
        this.create_user = create_user;
        this.type_id = type_id;
        this.title = title;
        this.content = content;
        this.integral = integral;
        this.img = img;
        this.status = status;
        this.create_time = create_time;
        this.start_time = start_time;
        this.end_time = end_time;
        this.number = number;
        this.phone = phone;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Integer create_user) {
        this.create_user = create_user;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", create_user=" + create_user +
                ", type_id=" + type_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", integral=" + integral +
                ", img='" + img + '\'' +
                ", status=" + status +
                ", create_time=" + create_time +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", number=" + number +
                ", phone=" + phone +
                '}';
    }
}
