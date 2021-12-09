// @Jonas Bækbo, Mikkel Sandell, Johanne Riis-Weitling
package ui;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);

    public String userInput() {
        return scanner.nextLine();
    }

    public void printMessage(String string) {
        System.out.println(string);
    }

    public void mainMenu() {
        printMessage("""
                Foretag et valg:
                1) Formands menu
                2) Træner menu
                3) Kassér menu
                0) Slut programmet""");
    }

    public void menuCEO() {
        printMessage("""         
                Formand
                Foretag et valg:
                1) Registrer nyt medlem
                2) Skift status på medlem (Aktiv/Passiv)
                3) Registrer ny træner
                4) Udskriv liste med alle trænere
                0) Tilbage til hovedmenu""");
    }

    public void menuCoach() {
        printMessage("""                                    
                Træner
                Foretag et valg:
                1) Tilføj tid til svømmer
                2) Registrer resultat fra stævne til svømmer
                3) Se top 5 aktive svømmere indenfor hver svømmedisciplin
                4) Se alle tider for én svømmer
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

    public void printSeparator() {
        System.out.printf("%s%n", "-----------------------------------------------------------------------------------------------------------------------------");
    }

    public void printTableHeader() {
        printSeparator();
        System.out.printf("%-25s %15s %-15s %15s %-20s %15s %-20s %n", "Navn", "|", "Alder", "|", "Svømmedisciplin", "|", "Tid");
        printSeparator();
    }

    public void printTableContents(String tableContent) {
        System.out.format(tableContent);
        System.out.println();
    }
}