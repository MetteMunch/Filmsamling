public class MovieCollection {


    //eventuelle attributes
    private Movie[] movieListe = new Movie[5];
    int count = 0; //tæller til at løbe igennem poster og tilføje, når der tilføjes ny film

    //constructor
    public MovieCollection() {
    }


    //metoder bl.a. addMovie..
    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieListe[count++] = new Movie(title,director,yearCreated,isInColor,lengthInMinutes,genre);
        System.out.println("Du har tilføjet filmen: " +title);
    }

    public Movie[] getMovieListe() {
        return movieListe;
    }
}
