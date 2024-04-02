import java.util.ArrayList;

public class MovieCollection {

    private ArrayList<Movie> movieListe = new ArrayList<>();
    private ArrayList<Movie> searchMatch = new ArrayList<>();//ny ArrayList til at gemme søge resultater i
    int indexToBeChanged;


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

    //metode ifm usercase 8, som jeg arbejder på...
    public String deleteMovie() {
        String result = "movieDeleted";
        movieListe.remove(indexToBeChanged);
        return result;
    }

    /*public void editMovie(int movieToBeChanged) {
        int movieToBeChanged;
        movieListe.add(new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre));
        System.out.println("Du har ændret informationer på filmen: " + title);
        System.out.println("");


    }*/

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
                result = " "+ films.toString() + " FilmNo. " +indexToBeChanged;
            }
        }
        return result;
    }

    public ArrayList<Movie> searchMovie(String title) {
        for (Movie films : movieListe) {
            if (films.getTitle().toLowerCase().contains(title.toLowerCase())) {
                searchMatch.add(films);
                indexToBeChanged = searchMatch.indexOf(films);
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
}
