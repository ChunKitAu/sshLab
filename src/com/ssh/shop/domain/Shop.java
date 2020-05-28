package com.ssh.shop.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @auther ChunKitAu
 * @create 2020-05-18 18
 */
@Entity
@Table(name="shop")
public class Shop  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "img")
    private String img;

    @Column(name = "integral")
    private Integer integral;

    @Column(name = "description")
    private  String description;


    @Column(name = "name")
    private String name;


    public Shop() {
    }

    public Shop(String img, Integer integral, String description, String name) {
        this.img = img;
        this.integral = integral;
        this.description = description;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
