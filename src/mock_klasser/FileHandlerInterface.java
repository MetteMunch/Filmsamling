package mock_klasser;

import domain_model.Movie;

import java.util.ArrayList;

public interface FileHandlerInterface {
    public ArrayList<Movie> loadSavedMovieList();
    public void saveListOfMovies(ArrayList<Movie> movieListe);
}
