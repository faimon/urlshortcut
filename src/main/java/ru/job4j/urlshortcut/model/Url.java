package ru.job4j.urlshortcut.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    @JoinColumn(name = "url_short")
    private String urlShort;

    @JoinColumn(name = "count_calls")
    private int countCalls;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Url() {
    }

    public Url(String url, String urlShort, int countCalls, User user) {
        this.url = url;
        this.urlShort = urlShort;
        this.countCalls = countCalls;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlShort() {
        return urlShort;
    }

    public void setUrlShort(String urlShort) {
        this.urlShort = urlShort;
    }

    public int getCountCalls() {
        return countCalls;
    }

    public void setCountCalls(int countCalls) {
        this.countCalls = countCalls;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return id == url.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", urlShort='" + urlShort + '\'' +
                ", countCalls=" + countCalls +
                '}';
    }
}
