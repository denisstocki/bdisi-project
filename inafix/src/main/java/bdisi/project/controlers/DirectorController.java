package bdisi.project.controlers;

import bdisi.project.printers.DirectorPrinter;
import bdisi.project.printers.WorkerPrinter;
import bdisi.project.statements.DirectorStatements;
import bdisi.project.statements.WorkerStatements;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DirectorController extends WorkerController{

    /**
     * Variable printer - holds a printing object for a worker
     */
    DirectorPrinter printer;

    /**
     * Variable username - holds a username for current user
     */
    String username;

    /**
     * Variable password - holds a password for current user
     */
    String password;
    public DirectorController(Scanner scanner) {
        super(scanner);
        this.scanner = scanner;
        printer = new DirectorPrinter();
    }

    @Override
    public void welcomeTheUser() {
        printer.welcomeTheUser();
    }

    @Override
    public void talkWithTheUser() {
        printer.talkWithTheUser();
        action_loop: while (true){
            input = scanner.nextLine();
            switch (input){
                case "1":
                    printDirectorData1();
                    break;
                case "2":
                    printDirectorData2();
                    break;
                case "3":
                    printDirectorData3();
                    break;
                case "4":
                    printDirectorData4();
                    break;
                case "5":
                    printDirectorData5();
                    break;
                case "6":
                    printDirectorData6();
                    break;
                case "7":
                    printDirectorData7();
                    break;
                case "8":
                    printDirectorData8();
                    break;
                case "9":
                    printDirectorData9();
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

    private void printDirectorData1() {
        try {
            statement = connection.prepareStatement(DirectorStatements.director1);
            printer.printDirectorData1(statement.executeQuery());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }

    private void printDirectorData2() {
        try {
            printer.printScheduleChange();
            statement = connection.prepareStatement(DirectorStatements.director2);
            printer.printStartingDataQuestion();
            statement.setString(1, scanner.nextLine());
            printer.printEndingDataQuestion();
            statement.setString(2, scanner.nextLine());
            printer.printWorkerNumberQuestion();
            statement.setInt(3, scanner.nextInt());
            printer.printDirectorData2(statement.executeUpdate());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }

    private void printDirectorData3() {

    }

    private void printDirectorData4() {

    }

    private void printDirectorData5() {

    }

    private void printDirectorData6() {
        try {
            statement = connection.prepareStatement(DirectorStatements.director6);
            printer.printDirectorData6(statement.executeQuery());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }

    private void printDirectorData7() {

    }

    private void printDirectorData8() {

    }

    private void printDirectorData9() {

    }

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

    public void connectToDB(String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        printer.printSuccessfulConnection();
    }

    public void setUpDrivers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
