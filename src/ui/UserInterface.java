package ui;

import java.util.Scanner;

public class UserInterface {

    public UserInterface() throws Exception {
        start();
    }

    public void start() throws Exception {
        System.out.println("Velkommen hos Delfinen");
        System.out.println("---------------------------");

        mainMenu();
    }

    private void mainMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("""
                                        
                    Foretag et valg:
                    1) Registrer nyt medlem (Formand)
                    2) Se forventet indbetaling af kontingentet (Kasser)
                    3) Se oversigt over medlemmer i restance (Kasser)
                    4) Se top 5 svømmere indenfor hver svømmediciplin (Træner)
                    5) Registrer bedste træningsresultat for svømmer (Træner)
                    6) Registrer resultat til stævne for svømmer (Træner)
                    0) Exit application""");
            int selection = scanner.nextInt();
            switch (selection) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:


                    break;
                case 5:

                    break;
                case 0:
                    System.out.println("Tak for at vælge Marios Pizza");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg - Vælg et tal fra menuen.");
                    break;
            }
        }
    }
}

