package ua.ronaldo173.library.web.beans;

import java.io.Serializable;

/**
 * Created by Developer on 11.04.2016.
 */
public class Genre implements Serializable {
    private String name;
    private long id;

    public Genre(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public Genre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
