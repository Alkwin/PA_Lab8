import DAO.MoviesDAO;
import db.Movie;

public class Main {
    public static void main(String[] args) {
        SQL.Database myDB = SQL.Database.getInstance();
        runExample();
        myDB.disconnect();
    }

    public static void runExample() {
        SQL.Database myDB = SQL.Database.getInstance();
        MoviesDAO dbMovies = new MoviesDAO();
        Movie exampleMovie = movieValue();
        dbMovies.insert(exampleMovie);

        myDB.commit();
        System.out.println("Movie table before deletion:");
        for (Movie m : myDB.getMovies()) {
            System.out.println(m);
        }
        dbMovies.delete(exampleMovie);
        myDB.commit();
        System.out.println("Movie table after deletion:");
        for (Movie m : myDB.getMovies()) {
            System.out.println(m);
        }
    }

    public static Movie movieValue() {
        Movie exampleMovie = new Movie();
        exampleMovie.setTitle("IT 2");
        exampleMovie.setScore(2);
        exampleMovie.setId(5);
        exampleMovie.setDuration(100);
        exampleMovie.setDate("2000-01-01");
        return exampleMovie;
    }
}
