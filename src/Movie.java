public class Movie {

    private String title;
    private String director;
    private int yearCreated;
    private boolean isInColor;
    private int lengthInMinutes;
    private String genre;

    //attributes på movie

    //constructor med setter af attributes
    public Movie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        this.title = title;
        this.director = director;
        this.yearCreated = yearCreated;
        this.isInColor = isInColor;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
    }


    // lav en metode toString @Override en slags stavekontrol  kaldes ved (Movies.toString)
    /*public String toString(){   bygger en string der sendes tilbage
    result += title + " " +genre;
    if(erifarve) {
    result = "";
    return result;
}   dette result sendes til en anden metode, der laver udskriften*/

    public String getTitle(){
        return title;
    }

    public String gerDirector(){
        return director;
    }

    public int getYearCreated (){
        return yearCreated;
    }

    public boolean getIsInColor() {
        return isInColor;
    }

    public int getLengthInMinutes(){
        return lengthInMinutes;
    }

    public String getGenre(){
        return genre;
    }







}
