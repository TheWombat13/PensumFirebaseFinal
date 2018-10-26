package com.example.jonathanlarsen.pensumfirebase.Litterature;

import java.io.Serializable;

public class LitteratureMetaModel implements Serializable {

    private int maxPages;

    public LitteratureMetaModel () {

    }

    public LitteratureMetaModel (int maxPages) {
        this.maxPages = maxPages;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }
}
