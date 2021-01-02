package ru.job4j.urlshortcut.dto;

public class UrlDto {
    private String code;

    public UrlDto(String code) {
        this.code = code;
    }

    public UrlDto() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
