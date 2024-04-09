package domain_model;

import domain_model.Movie;

import java.util.ArrayList;

public class MovieCollection {

    //private ArrayList<Movie> movieListe = new ArrayList<>(); Denne er blevet flyttet til FileHandler
    private ArrayList<Movie> searchMatch;
    private int indexToBeChanged;
    private FileHandler fh;


    //constructor
    public MovieCollection() {
        searchMatch = new ArrayList<>();//ny ArrayList til at gemme søge resultater i
        fh = new FileHandler();
    }


    //metoder

    //usercase 1 - opret film
    public String addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        fh.getMovieListe().add(new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre));
        String result = "true";
        return result;
    }

    //metoder til editing og delete
    public void deleteMovie() {
        fh.getMovieListe().remove(indexToBeChanged);
    }

    public String setTitle(int indexToBeChanged, String newTitle) {
        String result = "noChange";
        if (indexToBeChanged >= 0 && indexToBeChanged < fh.getMovieListe().size()) {
            Movie movieToBeChanged = fh.getMovieListe().get(indexToBeChanged);
            movieToBeChanged.setTitle(newTitle);
            result = "titleChanged";
        }
        return result;

    }

    public String setDirector(int indexToBeChanged, String newDirector) {
        String result = "noChange";
        if (indexToBeChanged >= 0 && indexToBeChanged < fh.getMovieListe().size()) {
            Movie direcetorToBeChanged = fh.getMovieListe().get(indexToBeChanged);
            direcetorToBeChanged.setDirector(newDirector);
            result = "directorChanged";
        }
        return result;
    }

    public String setYear(int indexToBeChanged, int newYear) {
        String result = "noChange";
        if (indexToBeChanged >= 0 && indexToBeChanged < fh.getMovieListe().size()) {
            Movie movieToBeChanged = fh.getMovieListe().get(indexToBeChanged);
            movieToBeChanged.setYearCreated(newYear);
            result = "yearChanged";
        }
        return result;

    }

    public String setDuration(int indexToBeChanged, int newDuration) {
        String result = "noChange";
        if (indexToBeChanged >= 0 && indexToBeChanged < fh.getMovieListe().size()) {
            Movie movieToBeChanged = fh.getMovieListe().get(indexToBeChanged);
            movieToBeChanged.setLengthInMinutes(newDuration);
            result = "durationChanged";
        }
        return result;
    }

    public String setGenre(int indexToBeChanged, String newGenre) {
        String result = "noChange";
        if (indexToBeChanged >= 0 && indexToBeChanged < fh.getMovieListe().size()) {
            Movie movieToBeChanged = fh.getMovieListe().get(indexToBeChanged);
            movieToBeChanged.setGenre(newGenre);
            result = "genreChanged";
        }
        return result;
    }

    public String setIsInColor(int indexToBeChanged, boolean isInColor) {
        String result = "noChange";
        if (indexToBeChanged >= 0 && indexToBeChanged < fh.getMovieListe().size()) {
            Movie movieToBeChanged = fh.getMovieListe().get(indexToBeChanged);
            movieToBeChanged.setIsInColor(isInColor);
            result = "colorChanged";
        }
        return result;
    }

    public void printFullListe() {
        for (Movie film : fh.getMovieListe()) {
            System.out.println(film.toString());
        }
    }

    public String searchMovieMedToString(String title) { //user case 5
        String result = "noMovie";
        for (Movie films : fh.getMovieListe()) {
            indexToBeChanged = fh.getMovieListe().indexOf(films);
            if (films.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result = "\n" + films + "FilmNo. " + indexToBeChanged + "\n";
                break;
            }
        }
        return result;
    }

    public ArrayList<Movie> searchMovie(String title) {
        for (Movie films : fh.getMovieListe()) {
            if (films.getTitle().toLowerCase().contains(title.toLowerCase())) {
                searchMatch.add(films);
            }
        }
        return searchMatch;
    }

    public ArrayList<Movie> getMovieListe() { //getter for at få MovieListe af "typen" domain_model.Movie
        return fh.getMovieListe();
    }

    public ArrayList<Movie> getSearchMatch() {//getter for at kunne kalde searchMatch af typen domain_model.Movie.
        return searchMatch;
    }

    public int getIndexToBeChanged() {
        return indexToBeChanged;
    }

    public FileHandler getFh() {
        return fh;
    }
}
