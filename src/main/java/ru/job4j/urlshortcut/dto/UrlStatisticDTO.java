package ru.job4j.urlshortcut.dto;

public class UrlStatisticDTO {
    private String url;
    private int total;

    public UrlStatisticDTO(String url, int count) {
        this.url = url;
        this.total = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
