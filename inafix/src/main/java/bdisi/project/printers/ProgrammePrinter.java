package bdisi.project.printers;

/**
 * Class ProgrammePrinter - is used to print stuff from Programme Class
 */
public class ProgrammePrinter {

    /**
     * Method showSystemMessage - shows any message passed to it
     * @param msg
     */
    private static void showSystemMessage(String msg, boolean nextline) {
        if (nextline) System.out.println(msg + '\n');
        else System.out.println(msg);
    }

    /**
     * Method showLoggingMessage
     */
    public void showLoggingMessage() {

        String msg = "Aby rozpoczac prace z systemem, prosimy Cię o wybranie trybu obsługi systemu poniżej poprzez wpisanie odpowiadającemu trybowi numerka:\n" +
                "1. Klient\n" +
                "2. Pracownik\n" +
                "3. Dyrektor\n\n" +
                "Twoj wybor: ";

        showSystemMessage(msg, false);
    }

    /**
     * Method showWelcomeMessage - shows information about welcoming the user to the programme
     */
    public void showWelcomeMessage(){
        String msg = "Witamy w systemie firmy Inafix !\n";

        showSystemMessage(msg, false);
    }

    /**
     * Method showStartingMessage - shows information that the programme has been started
     */
    public void showStartingMessage() {
        String msg = "Inafix programme has been started !";

        showSystemMessage(msg, true);
    }

    /**
     * Method showEndingMessage - shows information that the programme has been ended
     */
    public void showEndingMessage() {
        String msg = "Inafix programme is being finished ...";

        showSystemMessage(msg, false);
    }

    /**
     * Shows information about not choosing a user
     */
    public void showExitMessage() {
        System.out.println("\nUzytkownik nie zostal wybrany ...\n");
    }
}
