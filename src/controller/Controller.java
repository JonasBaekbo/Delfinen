//@Jonas Bækbo, Johanne Riis-Weitling, Mikkel Sandell

package controller;

import ui.UserInterface;

public class Controller {
    private boolean isRunning = true;
    private final UserInterface ui = new UserInterface();
    private final TreasurerController treasurerController =new TreasurerController();
    private final CEOController ceoController =new CEOController();
    private final CoachController coachController =new CoachController();


    public void start() {
        ui.printMessage("Velkommen til Delfinen");
        ui.printMessage("-----------------------");
        mainMenu();
    }
    private void mainMenu() {
        while (isRunning) {
            ui.mainMenu();
            switch (ui.userInput()) {
                case "1" -> ceoController.ceoMenu(this);
                case "2" -> coachController.coachMenu(this);
                case "3" -> treasurerController.treasurerMenu(this);
                case "0" -> exit();
                default -> ui.printMessage("Du skal vælge et punkt fra menuen. Prøv venligst igen");
            }
        }
    }

    private void exit() {
        isRunning = false;
        ceoController.stop();
        treasurerController.stop();
        coachController.stop();
    }

    public void backToMainMenu() {
        mainMenu();
    }
}