package bdisi.project.system;

/**
 * Inafix class - starts whole programme. Its finish means finish of whole programme course.
 */
public class Inafix {

    /**
     * Static main method - source of Inafix class course
     */
    public static void main(String[] args) {

        /**
         * Reference programme - holds pointer to a Programme object, which is whole application
         */
        Programme programme = new Programme();

        /**
         * Shows running message when programme is starting, which means that the programme was just started by someone
         */
        System.out.println("Inafix programme is being started ...");

        /**
         * Starts out programme
         */
        programme.start();

        /**
         * Makes main thread wait for programme thread to finish and then lets the main thread continue running
         */
        try{
            programme.join();

            /**
             * Shows closing message when programme thread is finished, which means the programme was closed
             */
            System.out.println("Inafix programme has been closed !");

        } catch (InterruptedException e) {
            System.out.println("Inafix Programme has been interrupted ...");
        }
    }
}
