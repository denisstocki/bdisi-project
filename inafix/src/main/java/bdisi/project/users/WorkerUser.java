package bdisi.project.users;

import bdisi.project.controlers.WorkerController;

import java.util.Scanner;

/**
 * Class AdvisorUser - is thread that controls programme as a registered user
 */
public class WorkerUser implements Userable{

    /**
     * Variable controller - holds a controller object
     */
    WorkerController controller;

    /**
     * Class WorkerUser constructor
     * @param scanner
     */
    public WorkerUser(Scanner scanner) {
        controller = new WorkerController(scanner);
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
