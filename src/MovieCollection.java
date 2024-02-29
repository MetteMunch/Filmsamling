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
    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieListe.add(new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre));
        System.out.println("Du har tilføjet filmen: " + title);
        System.out.println("Filmsamlingen indeholder nu samlet: " + movieListe.size() + " film.");
    }

    //metode ifm usercase 8, som jeg arbejder på...
    public void deleteMovie() {
        movieListe.remove(indexToBeChanged);
        System.out.println("Filmen er blevet slettet");
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
        for (Movie films : movieListe) {
            indexToBeChanged = movieListe.indexOf(films);
            if (films.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return "Ja vi har filmen: " + films.toString();
            }
        }
        return "Desværre vi har ikke filmen i samlingen.";
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
