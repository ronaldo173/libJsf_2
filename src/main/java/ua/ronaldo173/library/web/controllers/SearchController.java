package ua.ronaldo173.library.web.controllers;

import ua.ronaldo173.library.web.beans.Book;
import ua.ronaldo173.library.web.db.Database;
import ua.ronaldo173.library.web.enums.SearchType;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Developer on 11.04.2016.
 */
@ManagedBean(eager = true)
@SessionScoped
public class SearchController implements Serializable {

    private static Map<String, SearchType> searchList = new HashMap<>();
    private SearchType searchType;
    private List<Book> currentBookList;

    public SearchController() {
        fillBooksAll();

//        ResourceBundle bundle = ResourceBundle.getBundle("ua.ronaldo173.library.web.nls.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
//        searchList.put(bundle.getString("author_name"), SearchType.AUTHOR);
//        searchList.put(bundle.getString("book_name"), SearchType.TITLE);
    }

    public static void main(String[] args) {

        Character[] letters = new SearchController().getLetters();
        System.out.println(Arrays.toString(letters));
    }

    private void fillBooksAll() {
        String query = "select g.*, b.name, b.isbn, b.page_count, b.publish_year, a.fio, p.publisher from (select genre.id, genre.name as genre from genre) g\n" +
                "join book b on b.genre_id=g.id\n" +
                "join author a on a.id=b.author_id \n" +
                "join (select publisher.id, publisher.name as publisher from publisher) p on p.id=b.publisher_id" +
                " order by name";

        fillBooksBySql(query);

    }

    public void fillBooksByGenre() {
        Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer genre_id = Integer.valueOf(parameterMap.get("genre_id"));
        String query = "select g.*, b.name, b.isbn, b.page_count, b.publish_year, a.fio, p.publisher from (select genre.id, genre.name as genre from genre) g\n" +
                "join book b on b.genre_id=g.id\n" +
                "join author a on a.id=b.author_id \n" +
                "join (select publisher.id, publisher.name as publisher from publisher) p on p.id=b.publisher_id where g.id = "
                + genre_id + " order by name";

        fillBooksBySql(query);
    }

    private void fillBooksBySql(String query) {

        ResultSet resultSet = null;
        try {
            resultSet = Database.getResultByQuery(query);
            currentBookList = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setGenre(resultSet.getString("genre"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setAuthor(resultSet.getString("fio"));
                book.setPageCount(resultSet.getInt("page_count"));
                book.setPublishDate(resultSet.getInt("publish_year"));
                book.setPublisher(resultSet.getString("publisher"));
//                book.setDescr(resultSet.getString("descr"));

                currentBookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.getStatement().getConnection().close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getImage(int id) {

        ResultSet resultSet = null;
        byte[] image = null;
        String query = "select image from book where id = " + id;
        try {
            resultSet = Database.getResultByQuery(query);
            while (resultSet.next()) {
                image = resultSet.getBytes("image");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.getStatement().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    public Character[] getLetters() {
        Character[] letters = new Character[32];
        char a = 'А';
        for (int i = 0; i < 32; i++) {
            letters[i] = a++;
        }
        return letters;
    }
}
