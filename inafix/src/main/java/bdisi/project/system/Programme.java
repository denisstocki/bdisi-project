package bdisi.project.system;

import bdisi.project.printers.ProgrammePrinter;
import bdisi.project.users.*;

import java.util.Scanner;

/**
 * Progarmme class - this class is a thread inheritor, which is a base class running Inafix programme
 */
public class Programme extends Thread{

    /**
     * Variable printer - allows this object to print some messages
     */
    ProgrammePrinter printer = new ProgrammePrinter();
    /**
     * Variable programmeScanner - scans users input
     */
    Scanner programmeScanner = new Scanner(System.in);
    /**
     * Variable userThread - is a chosen user thread
     */
    Thread userThread;
    /**
     * Variable user - is a reference for a chosen type of user
     */
    Userable user;
    /**
     * Variable userInput - variable that holds users input
     */
    String userInput;

    /**
     * Method that makes the thread runnable
     */
    @Override
    public void run() {

        /**
         * Prints out system message about programme being started
         */
        printer.showStartingMessage();

        /**
         * Shows welcome message
         */
        printer.showWelcomeMessage();

        /**
         * Displays some logging options to the user
         */
        printer.showLoggingMessage();

        /**
         * Waits for correct int input
         */
        getLoggingOption();

        /**
         * Prints out system message about programme being closed
         */
        printer.showEndingMessage();
    }

    /**
     * Method getLoggingOption - is supposed to get an int from a user to choose logging option.
     */
    private void getLoggingOption() {
        logging_loop: while (true){
            userInput = programmeScanner.nextLine();
            switch (userInput){
                case "1":
                    user = new DefaultUser(programmeScanner);
                    runUser();
                    printer.showLoggingMessage();
                    break;
                case "2":
                    user = new WorkerUser(programmeScanner);
                    runUser();
                    printer.showLoggingMessage();
                    break;
                case "3":
                    user = new DirectorUser(programmeScanner);
                    runUser();
                    printer.showLoggingMessage();
                    break;
                case "koniec":
                    printer.showExitMessage();
                    break logging_loop;
                default:
                    System.out.println("Podaj właściwy numer logowania !\n");
                    printer.showLoggingMessage();
                    break;
            }
        }
    }

    /**
     * Method runUser - lets a user be run
     */
    private void runUser() {
        /**
         * Creates chosen users thread
         */
        userThread = new Thread(user);

        /**
         * Starts chosen users thread
         */
        userThread.start();

        /**
         * Waits for userThread to finish
         */
        try {
            userThread.join();
        } catch (InterruptedException e) {
            System.out.println("Inafix Programme has been interrupted ...");
        }
    }
}
