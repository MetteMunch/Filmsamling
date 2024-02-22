import java.util.ArrayList;

public class MovieCollection {

    ArrayList<Movie> movieListe = new ArrayList<>();


    //constructor
    public MovieCollection() {
    }


    //metoder
    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieListe.add(new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre));
        System.out.println("Du har tilføjet filmen: " + title);
        System.out.println("Filmsamlingen indeholder nu samlet: " + movieListe.size() + " film.");
    }

    public void printFullListe() {
        for (Movie film : movieListe) {
            System.out.println(film.toString());
        }
    }

    public void searchMovie(String title) {
        for (Movie films : getMovieListe()) {
            if (films.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Ja vi har den ønskede film");
            }
            System.out.println("Nej desværre har vi ikke den film");
        }
    }


    public ArrayList<Movie> getMovieListe() { //getter for at få MovieListe af "typen" Movie
        return movieListe;
    }


}
