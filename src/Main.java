import javax.sound.midi.Soundbank;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //create instance of Controller class
        Controller samling1 = new Controller();

        Scanner input = new Scanner(System.in);

        int SENTINEL = 4;
        int inputKey = 0;

        //Her opretter jeg lige nogle film i MovieCollection
        samling1.addMovie1("Burn After Reading","Coen",2008,true,120,"Komedie");
        samling1.addMovie1("titel2","Coen",2008,true,120,"Komedie");
        samling1.addMovie1("titel3","Coen",2008,true,120,"Komedie");

        System.out.println("Velkommen til min filmsamling\n" +
                "Tryk 1, hvis du vil oprette en film\n" +
                "Tryk 2, hvis du vil søge på en filmtitel\n" +
                "Tryk 3 hvis du vil se en fuld liste\n" +
                "tryk 4 hvis du vil afslutte");
        inputKey = input.nextInt();

        while (inputKey != SENTINEL) {  //ændre til at bruge SENTINEL og ikke break. variabel menuChoice

            input.nextLine();//nødvendig for at kunne indsætte titel (scanner bug)

            if (inputKey == 3) {//i denne printer den en liste med samtlige film
                samling1.findAllMovies();


        } else if (inputKey == 2) {
                System.out.println("Hvilken film søger du?"); //koden kan køre, men virker ikke...hvorfor?
                String inputTitle = input.nextLine();
                samling1.findTitle(inputTitle);


        } else if (inputKey == 1) {
            System.out.println("Angiv titel på filmen, som du vil tilføje:");
            String title = input.nextLine();// skal bruge nextLine når der kan være flere ord ellers er next ok

            System.out.println("Angiv director på filmen:");
            String director = input.nextLine();

            System.out.println("Angiv genre på filmen, vælg mellem action, komedie, gyser, romantik, krimi eller sci-fi:");
            String genre = input.nextLine();

            System.out.println("Hvilket år havde den premiere");
            int yearCreated = input.nextInt();

            System.out.println("Skriv true, hvis filmen er i farver og false, hvis den s/h:");
            boolean isInColor = input.nextBoolean();

            System.out.println("Hvor mange minutter varer filmen?");
            int lengthInMinutes = input.nextInt();

            samling1.addMovie1(title, director, yearCreated, isInColor, lengthInMinutes, genre);

        }
        System.out.println("Vil du tilføje en film mere så tryk 1\n" +
                "Vil du søge på en filmtitel så tryk 2\n" +
                "Vil du se en fuld liste så tryk 3\n" +
                "Vil du slutte programmet så tryk 4");
        inputKey = input.nextInt();


    }
        System.out.println("Tak for denne gang");

}

}
