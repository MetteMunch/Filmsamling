import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    //create instance of Controller class
    Controller samling1 = new Controller();
    Scanner input;

    int SENTINEL = 5;
    int inputKey = 0;
    int filmIndexNo = 9;


    //Constructor
    public UserInterface() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");//denne gør sådan, at jeg ikke får scanner bugs, men skal huske at bruge next og ikke nextLine
        startProgram();
    }

    public void startProgram() {
        //Jeg tilføjer lige nogle titler, så jeg har lidt at lege med...
        samling1.addMovie2("Burn After Reading", "Coen", 2008, true, 120, "Komedie");
        samling1.addMovie2("Godfather", "Coppola", 1972, true, 175, "Thriller");
        samling1.addMovie2("La vita e bella", "Benigni", 1997, true, 120, "Drama");

        userCommunication();//Her kalder jeg metoden, hvor user oplyses om valgmuligheder

        while (inputKey != SENTINEL) {

            if (inputKey == 4) {  //i denne printer den en liste med samtlige film
                samling1.findAllMovies();


            } else if (inputKey == 2) {  //denne søger efter en specifik titel
                findFilmMedNyArray();


            } else if (inputKey == 1) {
                opretFilm();


            } else if (inputKey == 3) {
                findAndEditMovie();


            } else if (inputKey == 6) {
                userCommunication();

            }
            repeatMenu();
        }
        System.out.println("Thank you and see you soon.");

    }

    public void userCommunication() {//usercase 2 og tilrettet i de følgende
        System.out.println("Welcome to the movie collection!\n" +
                "Type 1, if you want to add a movie.\n" +
                "Type 2, if you want to search for a title.\n" +
                "Type 3, if you want to edit or delete a movie in the collection.\n" +
                "Type 4, if you wat to see the full movie collection.\n" +
                "Type 5, if you want to exit the program.");
        inputKey = input.nextInt();
        //input.nextLine();

    }

    public void opretFilm() {
        System.out.println("Type the title of the movie you want to add:");
        String title = input.next();// skal bruge nextLine når der kan være flere ord ellers er next ok

        System.out.println("Type the director of the movie:");
        String director = input.next();

        System.out.println("Type genre of the movie, choose between action, thriller, drama, comedy, romance, crime, horror and sci-fi:");
        String genre = input.next();

        //indarbejdelse af try/catch
        int yearCreated = 0;
        boolean exceptionHandling = false;
        while (!exceptionHandling) {
            try {
                System.out.println("Year of premiere: ");
                yearCreated = input.nextInt();
                exceptionHandling = true;
            } catch (InputMismatchException ime) {
                System.out.println("Sorry you have to type a year, try again.");
                input.nextLine();
            }
        }


        System.out.println("Type true if the movie is in color or false if it is black/white.");
        boolean isInColor = input.nextBoolean();

        System.out.println("Type the duration of the movie in minutes.");
        int lengthInMinutes = input.nextInt();

        addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
    }

    public void repeatMenu() {
        System.out.println("\nIf you want to add another movie, type 1.\n" +
                "If you want to search for a title, type 2.\n" +
                "If you want to edit or delete a title, type 3.\n" +
                "If you want to see a full list of the movie collection, type 4.\n" +
                "If you want to exit the program, type 5.");
        inputKey = input.nextInt();
    }

    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        String result = samling1.addMovie2(title, director, yearCreated, isInColor, lengthInMinutes, genre);
        if (result.equals("true")) {
            System.out.println("You have added the movie: " + title);
            System.out.println("The collection now contains: " + samling1.getInstanceMovieCollection().getMovieListe().size() + " film.");
        }
    }

    public void findAndEditMovie() {//her vises kun første match
        //user case 5, hvis der skal søges og udskrives med toString
        System.out.println("Which movie do you like to edit?");
        String inputTitle = input.next();
        String result = samling1.findTitleMedToString(inputTitle);
        filmIndexNo = samling1.getIndexToBeChanged();
        //System.out.println("Dette er filmIndexNo: " +filmIndexNo + " Dette er filmtitel: " +result);
        if (result.equals("noMovie")) {
            System.out.println("Sorry it seems like we do not have a movie with that title in the collection.");
        } else {
            System.out.println("We have this movie in the collection: " + samling1.findTitleMedToString(inputTitle));
            System.out.println("Is it the correct movie, which you want to edit? yes/no");
            String answer = input.next();
            if (answer.toLowerCase().equals("yes")) {
                System.out.println("What would you like to change? Type number.\n" +
                        "1.Title\n" +
                        "2.Director\n" +
                        "3.Genre\n" +
                        "4.Premiere\n" +
                        "5.Color or black/white\n" +
                        "6.Duration in minutes\n" +
                        "7.Delete movie from collection\n");
                inputKey = input.nextInt();

                switch(inputKey) {
                    case 1 -> {
                        changeTitle();
                    }
                    case 2 -> {
                        //changeDirector(filmIndex);
                    }
                    case 3 -> {
                        changeGenre();
                    }
                    case 4 -> {
                        //changeYear(filmIndex);
                    }
                    case 5 -> {
                        changeColor();
                    }
                    case 6 -> {
                        changeDuration();
                    }
                    case 7 -> {
                        deleteMovie();
                    }
                }

            } else if (answer.toLowerCase().equals("no")) {
                System.out.println("Then try to search again with a bit more information");
                findAndEditMovie();
            }
        }
    }





    public void findFilmMedNyArray() {
        System.out.println("Which movie are you looking for?");
        int count = 1;
        String inputTitle = input.next();
        for (Movie movie : samling1.findTitle(inputTitle)) {
            if (!samling1.getInstanceMovieCollection().getSearchMatch().isEmpty()) {
                System.out.println("\n We have this movie in the collection: ");
                System.out.print(count + ". " + movie);
                count++;
            } else {
                System.out.println("I am sorry we do not have this movie in the collection.");
            }
        }
    }

    public void changeTitle() {
        System.out.println("What do you want to change the title to?");
        String inputNewTitle = input.next();
        samling1.setTitle(filmIndexNo, inputNewTitle);
        String result = samling1.setTitle(filmIndexNo, inputNewTitle);
        if(result.equals("titleChanged")) {
            System.out.println("You have now succesfully changed the information on the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        } else {
            System.out.println("Sorry some information where incorrect!");
        }
    }

    public void changeGenre() {
        System.out.println("What do you want to change the genre to?");
        String inputNewGenre = input.next();
        samling1.setGenre(filmIndexNo, inputNewGenre);
        String result = samling1.setGenre(filmIndexNo, inputNewGenre);
        if(result.equals("genreChanged")) {
            System.out.println("You have now succesfully changed the information on the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        } else {
            System.out.println("Sorry some information where incorrect!");
        }
    }

    public void changeDuration() {
        System.out.println("What would you like to change the duration to?");
        int newDuration = input.nextInt();
        samling1.setDuration(filmIndexNo, newDuration);
        String result = samling1.setDuration(filmIndexNo, newDuration);
        if(result.equals("durationChanged")) {
            System.out.println("You have now succesfully changed the duration of the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        }
    }

    public void changeColor() {
        System.out.println("Is this movie in color? Type yes/no.");
        String colorInput = input.next().toLowerCase();

        if(colorInput.equals("yes")) {
            boolean newColor = true;
            samling1.setIsInColor(filmIndexNo,newColor);
            System.out.println("You have succesfully changed the color aspect of the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        }else if(colorInput.equals("no")) {
            boolean newColor = false;
            samling1.setIsInColor(filmIndexNo,newColor);
            System.out.println("You have succesfully changed the color aspect of the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        }
        else {
            System.out.println("No changes were made.");
        }
    }

    public void deleteMovie(){
        System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        System.out.println("Are you sure you want to delete this movie from the collection, yes/no?");
        String answer = input.next();
        if (answer.toLowerCase().equals("yes")) {
            samling1.deleteMovie();
    } else {
            System.out.println("Okay then lets move on");
        }
    }
}










