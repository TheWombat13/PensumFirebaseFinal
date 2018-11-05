package com.example.jonathanlarsen.pensumfirebase.Storage_DataModels;

import java.io.Serializable;

public class PensumModel implements Serializable {

    private String title;
    private String teacher;
    private int pages;
    private int pagesToGo;

    public PensumModel() {

    }

    public PensumModel(String title, String teacher, int pages, int pagesToGo) {

        this.title = title;
        this.teacher = teacher;
        this.pages = pages;
        this.pagesToGo = pagesToGo;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPagesToGo() {
        return pagesToGo;
    }

    public void setPagesToGo(int pagesToGo) {
        this.pagesToGo = pagesToGo;
    }

}
