package pensumcreator.pensumfirebase.StorageDataModels;

import java.io.Serializable;

public class PensumModel implements Serializable {

    private String title;
    private String teacher;
    private String comment;
    private int pages;
    private int pagesToGo;

    public PensumModel() {

    }

    public PensumModel(String title, String teacher, String comment, int pages, int pagesToGo) {

        this.title = title;
        this.teacher = teacher;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
