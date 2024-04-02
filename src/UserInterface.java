import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    //create instance of Controller class
    Controller samling1 = new Controller();
    Scanner input;

    int SENTINEL = 5;
    int inputKey = 0;
    int filmIndex = 9;


    //Constructor
    public UserInterface() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");//denne gør sådan, at jeg ikke får scanner bugs, men skal huske at bruge next og ikke nextLine
    }

    public void startProgram() {
//Jeg tilføjer lige nogle titler, så jeg har lidt at lege med...
        samling1.addMovie2("Burn After Reading", "Coen", 2008, true, 120, "Komedie");
        samling1.addMovie2("Godfather", "Coppola", 1972, true, 175, "Thriller");
        samling1.addMovie2("La vita e bella", "Benigni", 1997, true, 120, "Drama");

        userCommunication();//Her kalder jeg metoden, hvor user oplyses om valgmuligheder

        while (inputKey != SENTINEL) {
            //input.nextLine();//nødvendig for at kunne indsætte titel (scanner bug)

            if (inputKey == 4) {//i denne printer den en liste med samtlige film
                samling1.findAllMovies();


            } else if (inputKey == 2) {
                //findFilm();
                findFilmMedNyArray();


            } else if (inputKey == 1) {
                opretFilm();


            } else if (inputKey == 3) {
                findMovieSingleResult();
                //editMovie();


            } else if (inputKey == 6) {
                userCommunication();

            }
            gentagMenu();
        }
        System.out.println("Tak for denne gang");

    }

    public void userCommunication() {//usercase 2 og tilrettet i de følgende
        System.out.println("Velkommen til min filmsamling\n" +
                "Tryk 1, hvis du vil oprette en film\n" +
                "Tryk 2, hvis du vil søge på en filmtitel\n" +
                "Tryk 3, hvis du vil redigere en filmtitel\n" +
                "Tryk 4 hvis du vil se en fuld liste\n" +
                "tryk 5 hvis du vil afslutte");
        inputKey = input.nextInt();
        //input.nextLine();

    }

    public void opretFilm() {
        System.out.println("Angiv titel på filmen, som du vil tilføje:");
        String title = input.next();// skal bruge nextLine når der kan være flere ord ellers er next ok

        System.out.println("Angiv director på filmen:");
        String director = input.next();

        System.out.println("Angiv genre på filmen, vælg mellem action, thriller, drama, komedie, gyser, romantik, krimi eller sci-fi:");
        String genre = input.next();

        //indarbejdelse af try/catch
        int yearCreated = 0;
        boolean exceptionHandling = false;
        while (!exceptionHandling) {
            try {
                System.out.println("Hvilket år havde den premiere");
                yearCreated = input.nextInt();
                exceptionHandling = true;
            } catch (InputMismatchException ime) {
                System.out.println("Den går ikke, du skal indtaste et årstal,\n" +
                        " prøv igen");
                input.nextLine();
            }
        }


        System.out.println("Skriv true, hvis filmen er i farver og false, hvis den s/h:");
        boolean isInColor = input.nextBoolean();

        System.out.println("Hvor mange minutter varer filmen?");
        int lengthInMinutes = input.nextInt();

        addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
    }

    public void gentagMenu() {
        System.out.println("\nVil du tilføje en film mere så tryk 1\n" +
                "Vil du søge på en filmtitel så tryk 2\n" +
                "Vil du redigere en filmtitel så tryk 3\n" +
                "Vil du se en fuld liste så tryk 4\n" +
                "Vil du slutte programmet så tryk 5");
        inputKey = input.nextInt();
    }

    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        String result = samling1.addMovie2(title, director, yearCreated, isInColor, lengthInMinutes, genre);
        if (result.equals("true")) {
            System.out.println("You have added the movie: " + title);
            System.out.println("The collection now contains: " + samling1.getInstanceMovieCollection().getMovieListe().size() + " film.");
        }
    }

    public void findMovieSingleResult() {//her vises kun første match
        //user case 5, hvis der skal søges og udskrives med toString
        System.out.println("Which movie do you like to edit?");
        String inputTitle = input.next();
        String result = samling1.findTitleMedToString(inputTitle);
        filmIndex = samling1.getInstanceMovieCollection().indexToBeChanged;
        System.out.println(result);
        if (result.equals("noMovie")) {
            System.out.println("Sorry it seems like we do not have a movie with that title in the collection.");
        } else {
            System.out.println("We have this movie in the collection: " + samling1.findTitleMedToString(inputTitle));
            System.out.println("Is it the correct movie, which you want to edit? yes/no");
            String answer = input.next();
            if (answer.toLowerCase().equals("yes")) {
                editMovie(filmIndex);
            } else if (answer.toLowerCase().equals("no")) {
                System.out.println("Then try to search again with a bit more information");
                findMovieSingleResult();
            }
        }
    }

    public void editMovie(int filmIndex) {

        System.out.println("Type the number of what you like to change\n" +
                "1.Title\n" +
                "2.Director\n" +
                "3.Genre\n" +
                "4.Premiere år\n" +
                "5.Farve el.S/H\n" +
                "6.Varighed\n" +
                "7.Slet film fra listen\n");
        inputKey = input.nextInt();

        switch(inputKey) {
            case 1 -> {
                //changeTitle(filmIndex);
            }
            case 2 -> {
                //changeDirector(filmIndex);
            }
            case 3 -> {
                //changeGenre(filmIndex);
            }
            case 4 -> {
                //changeYear(filmIndex);
            }
            case 5 -> {
                //changeColor(filmIndex);
            }
            case 6 -> {
                //changeDuration(filmIndex);
            }
            case 7 -> {
                deleteMovie(filmIndex);
            }
        }
    }

    public void findFilmMedNyArray() {
        System.out.println("Hvilken film søger du?");
        int count = 1;
        String inputTitle = input.next();
        for (Movie movie : samling1.findTitle(inputTitle)) {
            if (!samling1.getInstanceMovieCollection().getSearchMatch().isEmpty()) {
                System.out.println("\n Vi har denne film i samlingen: ");
                System.out.print(count + ". " + movie);
                count++;
            } else {
                System.out.println("Desværre vi har ikke filmen");
            }
        }
    }

    public void deleteMovie(int filmIndex){
        filmIndex = this.filmIndex;//det virker ikke lige nu, men at den tager korrekt index nummer med???
        System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndex));
        //String movieToDelete = samling1.getInstanceMovieCollection().getMovieListe().indexOf(filmIndex);
        System.out.println("Are you sudden that you will delete " +"movieToDelete" + "?");
    }
}










