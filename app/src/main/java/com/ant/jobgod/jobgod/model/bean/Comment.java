package com.ant.jobgod.jobgod.model.bean;

/**
 * Created by Mr.Jude on 2015/7/19.
 */
public class Comment {
    private int id;
    private String face;
    private String name;
    private String content;
    private long time;
    private int authorId;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public String getFace() {
        return face;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public long getTime() {
        return time;
    }
}
