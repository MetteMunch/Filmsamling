import java.util.ArrayList;

public class Controller {

    //oprettelse af instance af MovieCollection
    private MovieCollection instanceMovieCollection = new MovieCollection();

    //constructor
    public Controller() {
    }

    public void addMovie1(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        instanceMovieCollection.addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
    }

    public MovieCollection getInstanceMovieCollection() {
        return instanceMovieCollection;//getter til instanceMovieCollection og typen er MovieCollection
    }

    public void findAllMovies() {
        instanceMovieCollection.printFullListe();
    }

    public void findTitle(String title){
        instanceMovieCollection.searchMovie(title);
    }
}
