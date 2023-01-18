package bdisi.project.users;

import bdisi.project.controlers.DirectorController;
import bdisi.project.controlers.WorkerController;

import java.util.Scanner;

/**
 * Class DirectorUser - is thread that controls programme as a registered user
 */
public class DirectorUser implements Userable{

    /**
     * Variable controller - holds a controller object
     */
    DirectorController controller;
    public DirectorUser(Scanner scanner) {
        controller = new DirectorController(scanner);
    }

    @Override
    public void run() {
        /**
        * Sets up the drivers
        */
        controller.setUpDrivers();

        System.out.println();

        /**
         * Lets the user try to log into account
         */
        controller.logTheUser();
    }
}
