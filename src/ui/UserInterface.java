// @Jonas Bækbo

package ui;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);

    public String userInput() {

        return scanner.nextLine();
    }

    public void printMessage(String s) {
        System.out.println(s);
    }

    public void MaineMenu() {
        printMessage("""
                Foretag et valg:
                1) Formands menu
                2) Træner menu
                3) Kassér menu
                0) Slut programmet
                """);

    }

    public void menuCEO() {
        printMessage("""
                                    
                Formand
                Foretag et valg:
                1) Registrer nyt medlem
                2) Skift status på medlem (Aktiv/Passiv)
                3) Registrer ny træner
                0) Tilbage til hovedmenu""");
    }

    public void menuCoach() {
        printMessage("""
                                    
                Træner
                Foretag et valg:
                1) Tilføj tid til medlem
                2) Registrer resultat til stævne for svømmer
                3) Se top 5 svømmere indenfor hver svømmedisciplin
                0) Tilbage til hovedmenu""");
    }

    public void menuTreasurer() {
        printMessage(""" 
                  
                Kassér
                Foretag et valg:
                1) Opret kontingent opkrævning
                2) Register indbetaling
                3) Se forventet indbetaling af kontingentet
                4) Se oversigt over medlemmer i restance
                0) Tilbage til hovedmenu""");
    }


    public void printTableHeader() {
        System.out.printf("%s%n", "-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %15s %-15s %20s %-20s %15s %-20s %15s %-20s %n", "Navn", "|", "Alder", "|", "Aktiv", "|", "Svømmedisciplin", "|", "Tid");
        System.out.printf("%s%n", "-------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void printTableContents(String tableContent) {
        System.out.format(tableContent);
        System.out.println();
    }
}

