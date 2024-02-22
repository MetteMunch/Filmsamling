import java.util.ArrayList;

public class MovieCollection {

    private ArrayList<Movie> movieListe = new ArrayList<>();


    //constructor
    public MovieCollection() {
    }


    //metoder
    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieListe.add(new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre));
        System.out.println("Du har tilføjet filmen: " + title);
        System.out.println("Filmsamlingen indeholder nu samlet: " + movieListe.size() + " film.");
    }

    public ArrayList<Movie> getMovieListe() { //getter for at få MovieListe af "typen" Movie
        return movieListe;
    }
    /*loop igennem med for each og kald movie.toString
        }*/


}
