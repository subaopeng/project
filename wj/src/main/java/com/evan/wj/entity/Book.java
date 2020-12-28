package com.evan.wj.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "book")
/**
 * @JsonIgnoreProperties 就是标注哪个属性不用转化为json格式
 */
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Book {
    @Id
    /**
     * JPA提供的四种标准用法为TABLE，SEQUENCE，IDENTITY，AUTO。
     * a，TABLE：使用一个特定的数据库表格来保存主键。
     * b，SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * c，IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * d，AUTO：主键由程序控制。
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBJ_ID")
    private int id;
    private String cover;
    private String title;
    private String author;
    private String date;
    private String press;
    private String abs;
    @ManyToOne
    @JoinColumn(name="cid")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

}
