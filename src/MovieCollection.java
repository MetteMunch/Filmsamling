public class MovieCollection {


    //eventuelle attributes
    private Movie[] movieListe = new Movie[5];//ArrayList <Movie> movieListe = new ArrayList<Movie>;
    int count = 0; //tæller til at løbe igennem poster og tilføje, når der tilføjes ny film

    //constructor
    public MovieCollection() {
    }


    //metoder
    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieListe[count++] = new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
        System.out.println("Du har tilføjet filmen: " + title);
        System.out.println("Filmsamlingen indeholder nu samlet: " +count + " film.");
    }

    public Movie[] getMovieListe() {
        return movieListe;
    }
}
