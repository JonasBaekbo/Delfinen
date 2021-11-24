// @JonasBaekbo

package ui;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    public void menu(){
        printMessage("""
                                        
                    Foretag et valg:
                    1) Registrer nyt medlem (Formand)
                    2) Tilføj tid til medlem (Træner)
                    3) Registrer bedste træningsresultat for svømmer (Træner)
                    4) Registrer resultat til stævne for svømmer (Træner)
                    5) Se top 5 svømmere indenfor hver svømmedisciplin (Træner)
                    6) Opret kontingent opkrævning (Kasser)
                    7) Register indbetaling (Kasser)
                    8) Se forventet indbetaling af kontingentet (Kasser)
                    9) Se oversigt over medlemmer i restance (Kasser)
                    0) Slut programmet""");
    }
    public String userInput(){

        return scanner.nextLine();
    }

    public void printMessage(String s) {
        System.out.println(s);
    }
}

