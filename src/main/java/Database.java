package SQL;

import db.Genre;
import db.Movie;

import java.sql.*;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private Connection con = null;
    private Statement stmt;
    private String sql;
    private ResultSet rs;
    private static Database single_instance = null;


    private Database() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentipa?useSSL=false", "root", "e78t9ijkl");
            con.setAutoCommit(false);
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (single_instance == null)
            single_instance = new Database();
        return single_instance;
    }

    public void disconnect() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new LinkedList<>();
        sql = " SELECT * FROM movies";
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Movie myMovie = new Movie();
                myMovie.setId(rs.getInt("ID"));
                myMovie.setTitle(rs.getString("title"));
                myMovie.setDate(rs.getString("release_date"));
                myMovie.setDuration(rs.getInt("duration"));
                myMovie.setScore(rs.getInt("score"));
                movies.add(myMovie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Genre> getGenres() {
        List<Genre> genres = new LinkedList<>();
        sql = " SELECT * FROM genres";
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Genre myGenre = new Genre();
                myGenre.setId(rs.getInt("ID"));
                myGenre.setName(rs.getString("name"));
                genres.add(myGenre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genres;
    }

    public void commit() {
        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeCommand(String command) {
        try {
            stmt.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
