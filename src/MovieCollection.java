import java.util.ArrayList;

public class MovieCollection {

    private ArrayList<Movie> movieListe = new ArrayList<>();
    private ArrayList<Movie> searchMatch = new ArrayList<>();//ny ArrayList til at gemme søge resultater i
    private int indexToBeChanged;


    //constructor
    public MovieCollection() {
    }


    //metoder

    //usercase 1 - opret film
    public String addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieListe.add(new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre));
        String result = "true";
        return result;
    }

    //metoder til editing og delete
    public void deleteMovie() {
        movieListe.remove(indexToBeChanged);
    }

    public String setTitle(int indexToBeChanged, String newTitle) {
        String result = "noChange";
        if(indexToBeChanged >= 0 && indexToBeChanged < movieListe.size()){
            Movie movieToBeChanged = movieListe.get(indexToBeChanged);
            movieToBeChanged.setTitle(newTitle);
            result = "titleChanged";
        }
        return result;

    }
    public String setYear(int indexToBeChanged, int newYear) {
        String result = "noChange";
        if(indexToBeChanged >= 0 && indexToBeChanged < movieListe.size()){
            Movie movieToBeChanged = movieListe.get(indexToBeChanged);
            movieToBeChanged.setYearCreated(newYear);
            result = "yearChanged";
        }
        return result;

    }

    public void printFullListe() {
        for (Movie film : movieListe) {
            System.out.println(film.toString());
        }
    }

    public String searchMovieMedToString(String title) { //user case 5
        String result = "noMovie";
        for (Movie films : movieListe) {
            indexToBeChanged = movieListe.indexOf(films);
            if (films.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result = " "+ films + " FilmNo. " +indexToBeChanged;
                break;
            }
        }
        return result;
    }

    public ArrayList<Movie> searchMovie(String title) {
        for (Movie films : movieListe) {
            if (films.getTitle().toLowerCase().contains(title.toLowerCase())) {
                searchMatch.add(films);
                //indexToBeChanged = searchMatch.indexOf(films);
                /*int indexOfMovie= searchMatch.indexOf(films);
                Movie target= searchMatch.get(0);
                target.number =*/
            }
        }
        return searchMatch;
    }


    public ArrayList<Movie> getMovieListe() { //getter for at få MovieListe af "typen" Movie
        return movieListe;
    }

    public ArrayList<Movie> getSearchMatch() {//getter for at kunne kalde searchMatch af typen Movie.
        return searchMatch;
    }

    public int getIndexToBeChanged() {
        return indexToBeChanged;
    }
}
