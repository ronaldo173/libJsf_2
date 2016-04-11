package ua.ronaldo173.library.web.controllers;

import ua.ronaldo173.library.web.beans.Genre;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Developer on 11.04.2016.
 */
@ManagedBean
@ApplicationScoped
public class GenreController implements Serializable {
private List<Genre> genreList;

    public GenreController(){
        fillGenresAll();
    }

    private void fillGenresAll() {

    }

    public List<Genre> getGenreList() {
        return genreList;
    }
}
