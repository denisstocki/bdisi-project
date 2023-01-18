package bdisi.project.users;

import bdisi.project.controlers.DefaultController;

import java.util.Scanner;

/**
 * Class DefaultUser - is thread that controls programme as a registered user
 */
public class DefaultUser implements Userable{

    /**
     * Variable controller - controls data flow betweem system default output and database
     */
    DefaultController controller;

    /**
     * DefaultUser class constructor
     * @param scanner
     */
    public DefaultUser(Scanner scanner) {
        /**
         * Passing an object to controller reference
         */
        controller = new DefaultController(scanner);
    }

    /**
     * Method run - it is being run when some class starts a thread holding this object
     */
    @Override
    public void run() {

        /**
         * Sets up the drivers
         */
        controller.setUpDrivers();

        /**
         * Connects to DB
         */
        controller.connectToDB();

        /**
         * Simply welcomes the user
         */
        controller.welcomeTheUser();

        /**
         * Asks the user what does he want to do
         */
        controller.talkWithTheUser();
    }
}
