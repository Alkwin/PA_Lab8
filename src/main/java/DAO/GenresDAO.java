package DAO;

import db.Genre;

import javax.xml.crypto.Data;
import java.util.LinkedList;
import java.util.List;

public class GenresDAO implements DAO<Genre> {
    List<Genre> genres;
    private final SQL.Database db;

    public GenresDAO() {
        db = SQL.Database.getInstance();
        genres = db.getGenres();
    }

    @Override
    public List<Genre> findById(int id) {
        List<Genre> pickedMovies = new LinkedList<>();
        for (Genre g : genres) {
            if (g.getId() == id)
                pickedMovies.add(g);
        }
        return pickedMovies;
    }

    @Override
    public List<Genre> findByName(String name) {
        List<Genre> pickedGenres = new LinkedList<>();
        for (Genre g : genres) {
            if (g.getName().equals(name))
                pickedGenres.add(g);
        }
        return pickedGenres;
    }

    @Override
    public List<Genre> getAll() {
        return genres;
    }

    @Override
    public void insert(Genre genre) {
        genres.add(genre);
        String command = " INSERT INTO `studentipa`.`genres` (`ID`, 'name') VALUES ( "
                + "'" + genre.getId() + "',"
                + "'" + genre.getName() + "')";

        db.executeCommand(command);
    }

    @Override
    public void delete(Genre genre) {
        genres.remove(genre);
        String command = " DELETE FROM genres WHERE id='" + genre.getId() + "'";
        db.executeCommand(command);
    }
}
