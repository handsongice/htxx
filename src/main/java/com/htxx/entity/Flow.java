package com.htxx.entity;

import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/16 15:51
 */
public class Flow {

    private String title;

    private Map<String, Object> nodes;

    private Map<String, Object> lines;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Object> getNodes() {
        return nodes;
    }

    public void setNodes(Map<String, Object> nodes) {
        this.nodes = nodes;
    }

    public Map<String, Object> getLines() {
        return lines;
    }

    public void setLines(Map<String, Object> lines) {
        this.lines = lines;
    }
}
