package bdisi.project.controlers;

import bdisi.project.printers.WorkerPrinter;
import bdisi.project.statements.DefaultStatements;
import bdisi.project.statements.WorkerStatements;

import java.sql.DriverManager;
import java.sql.SQLException;
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

    private void printWorkerData4() {

    }

    private void printWorkerData5() {

    }

    private void printWorkerData6() {

    }

    private void printWorkerData7() {

    }

    private void printWorkerData8() {

    }

    private void printWorkerData9() {

    }

    /**
     * Method printWorkerData1 - calls SQL statement that
     */
    private void printWorkerData1() {
        try {
            printer.printProductNameAsk();
            statement = connection.prepareStatement(WorkerStatements.worker1);
            statement.setString(1, scanner.nextLine());
            printer.printDefaultData1(statement.executeQuery());
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
                connectToDB(username, password);
            } catch (SQLException e) {
                printer.printWrongLogin();
            }
            break;
        }
    }
}
