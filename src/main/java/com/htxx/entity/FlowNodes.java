package com.htxx.entity;

/**
 * @Author: jason ji
 * @Date: 2018/11/16 15:45
 */
public class FlowNodes {

    private Long id;

    private String name;

    private Long left;

    private Long top;

    private String type;

    private Long width;

    private Long height;

    private String alt;

    private String from;

    private String to;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLeft() {
        return left;
    }

    public void setLeft(Long left) {
        this.left = left;
    }

    public Long getTop() {
        return top;
    }

    public void setTop(Long top) {
        this.top = top;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
