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


    @Override //en slags stavekontrol  kaldes ved at kalde metoden Movies.toString()
    public String toString() {
        String result = " ";
        result += "Filmtitel: " + title + " , Instruktør: " + director + " , Genre: " + genre + " , Filmlængde i min.:" + lengthInMinutes;
        if(isInColor){
            result += " , Filmen er i farver";
        } else {
            result += " , Filmen er i sort/hvid";
        }
        return result;
        /*if(erifarve) {
    result = "";*/

    }   //dette result sendes til en anden metode, der laver udskriften

    public String getTitle() {
        return title;
    }

    public String gerDirector() {
        return director;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public boolean getIsInColor() {
        return isInColor;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public String getGenre() {
        return genre;
    }


}
