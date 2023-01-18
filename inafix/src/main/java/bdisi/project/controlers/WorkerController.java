package bdisi.project.controlers;

import bdisi.project.printers.WorkerPrinter;
import bdisi.project.statements.WorkerStatements;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class WorkerController
 */
public class WorkerController extends DefaultController{

    /**
     * Variable printer - holds a printing object for a worker
     */
    WorkerPrinter printer;

    /**
     * Variable username - holds a username for current user
     */
    String username;

    /**
     * Variable password - holds a password for current user
     */
    String password;

    /**
     * DefaultController class constructor
     * @param scanner
     */
    public WorkerController(Scanner scanner) {
        super(scanner);
        this.scanner = scanner;
        printer = new WorkerPrinter();
    }

    @Override
    public void welcomeTheUser() {
        printer.welcomeTheUser();
    }

    public void connectToDB(String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        printer.printSuccessfulConnection();
    }

    @Override
    public void talkWithTheUser() {
        printer.talkWithTheUser();
        action_loop: while (true){
            input = scanner.nextLine();
            switch (input){
                case "1":
                    printWorkerData1();
                    break;
                case "2":
                    printWorkerData2();
                    break;
                case "3":
                    printWorkerData3();
                    break;
                case "4":
                    printWorkerData4();
                    break;
                case "5":
                    printWorkerData5();
                    break;
                case "6":
                    printWorkerData6();
                    break;
                case "7":
                    printWorkerData7();
                    break;
                case "8":
                    printWorkerData8();
                    break;
                case "9":
                    printWorkerData9();
                    break;
                case "koniec":
                    printer.printDefaultEnding();
                    break action_loop;
                default:
                    printer.printDefaultError();
                    break;
            }
            printer.talkWithTheUser();
        }
    }

    private void printWorkerData2() {

    }

    private void printWorkerData3() {

    }

    /**
     * Method printWorkerData4 - shows quantity of all products in the shop
     */
    private void printWorkerData4() {
        try {
            statement = connection.prepareStatement(WorkerStatements.worker4);
            printer.printWorkerData4(statement.executeQuery());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }

    /**
     * Method printWorkerData5 - shows quantity of all products in the magazine
     */
    private void printWorkerData5() {
        try {
            statement = connection.prepareStatement(WorkerStatements.worker5);
            printer.printWorkerData5(statement.executeQuery());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }

    /**
     * Method printWorkerData6 - shows district location of a given product
     */
    private void printWorkerData6() {
        try {
            statement = connection.prepareStatement(WorkerStatements.worker6);
            printer.printProductNameAsk();
            statement.setString(1, scanner.nextLine());
            printer.printWorkerData6(statement.executeQuery());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }

    private void printWorkerData7() {

    }

    private void printWorkerData8() {
        try {
            statement = connection.prepareStatement(WorkerStatements.worker8);
            printer.printProductNameAsk();
            statement.setString(1, scanner.nextLine());
            printer.printWorkerData8(statement.executeQuery());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }

    private void printWorkerData9() {
        try {
            statement = connection.prepareStatement(WorkerStatements.worker9);
            printer.printProductNameAsk();
            statement.setString(1, scanner.nextLine());
            printer.printWorkerData9(statement.executeQuery());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }

    /**
     * Method printWorkerData1 - calls SQL statement that gives information about given product by user
     */
    private void printWorkerData1() {
        try {
            printer.printProductNameAsk();
            statement = connection.prepareStatement(WorkerStatements.worker1);
            statement.setString(1, scanner.nextLine());
            printer.printWorkerData1(statement.executeQuery());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }

    /**
     * Method logTheUser - waits for user to pass correct login and password to log into a system
     */
    public void logTheUser() {
        while (true){
            printer.askForLogin();
            username = scanner.nextLine();
            if("koniec".equals(username)){
                printer.printFailedLogin();
                break;
            }
            printer.askForPassword();
            password = scanner.nextLine();
            try {
                System.out.println();
                connectToDB(username, password);
                welcomeTheUser();
                /**
                 * Asks the user what does he want to do
                 */
                talkWithTheUser();
                break;
            } catch (SQLException e) {
                printer.printWrongLogin();
            }
        }
    }
}
