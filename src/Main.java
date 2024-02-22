import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //create instance of Controller class
        Controller samling1 = new Controller();

        Scanner input = new Scanner(System.in);

        int SENTINEL = 2;
        int inputKey = 0;

        System.out.println("Velkommen til min filmsamling. Tryk 1, hvis du vil oprette en film. Tryk 2, hvis du vil afslutte.");
        inputKey = input.nextInt();

        while (inputKey != SENTINEL) {  //ændre til at bruge SENTINEL og ikke break. variabel menuChoice

            input.nextLine();//nødvendig for at kunne indsætte titel (scanner bug)

            if (inputKey == 1) {
                System.out.println("Angiv titel på filmen, som du vil tilføje:");
                String title = input.nextLine();// skal bruge nextLine når der kan være flere ord ellers er next ok

                System.out.println("Angiv director på filmen:");
                String director = input.nextLine();

                System.out.println("Angiv genre på filmen, vælg mellem action, komedie, gyser, romantik, krimi eller sci-fi:");
                String genre = input.nextLine();

                System.out.println("Hvilket år havde den premiere");
                int yearCreated = input.nextInt();

                System.out.println("Skriv true, hvis filmen er i farver:");//tilføj ja eller nej med if sætning
                boolean isInColor = input.nextBoolean();

                System.out.println("Hvor mange minutter varer filmen?");
                int lengthInMinutes = input.nextInt();

                samling1.addMovie1(title, director, yearCreated, isInColor, lengthInMinutes, genre);


            }
            System.out.println("Vil du tilføje en film mere så tryk 1 og ellers tryk 2.");
            inputKey = input.nextInt();


        }
        System.out.println("Tak for denne gang");
        //System.out.println(samling1.getInstanceMovieCollection().getMovieListe().contains());
        //System.out.println(samling1.getInstanceMovieCollection().getMovieListe().toString()); //denne metode får kun udprintet referencer og ikke eksakt indhold
    }

}
