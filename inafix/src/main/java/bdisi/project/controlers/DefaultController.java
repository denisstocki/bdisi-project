package bdisi.project.controlers;

import bdisi.project.printers.DefaultPrinter;
import bdisi.project.statements.DefaultStatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class DefaultController - is used to process DefaultUsers data
 */
public class DefaultController {

    /**
     * Variable url - holds an url to a database Inafix
     */
    final String url = "jdbc:mysql://localhost:3306/Inafix";

    /**
     * Variable statement - holds an object of prepared statement
     */
    PreparedStatement statement;

    /**
     * Variable scanner - earns the input from user
     */
    Scanner scanner;

    /**
     * Variable input - holds users input
     */
    String input;

    /**
     * Passing an object to printer reference
     */
    DefaultPrinter printer;

    /**
     * Variable connection - holds connection to the database
     */
    Connection connection;

    /**
     * DefaultController class constructor
     */
    public DefaultController(Scanner scanner){
        this.scanner = scanner;
        this.printer = new DefaultPrinter();
    }

    /**
     * Method setUpDrivers - tries to set up the drivers for connection
     */
    public void setUpDrivers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method connectToDB - tries to connect with Inafix database
     */
    public void connectToDB(){
        try {
            /**
             * Variable username - holds mysql username for default user
             */
            String username = "root";
            /**
             * Variable password - holds mysql password for default user
             */
            String password = "sined";
            connection = DriverManager.getConnection(url, username, password);
            printer.printSuccessfulConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method welcomeTheUser - simply welcomes the user
     */
    public void welcomeTheUser() {
        printer.welcomeTheUser();
    }

    /**
     * Method askTheUser - simply asks the user for his wants
     */
    public void talkWithTheUser() {
        printer.talkWithTheUser();
        action_loop: while (true){
            input = scanner.nextLine();
            switch (input){
                case "1":
                    printDefaultData1();
                    break;
                case "2":
                    printDefaultData2();
                    break;
                case "3":
                    printDefaultData3();
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

    private void printDefaultData3() {

    }

    /**
     * Method giveProductCheckStatement - outputs information about a given product from user
     */
    private void printDefaultData2() {
        try {
            printer.printProductNameAsk();
            statement = connection.prepareStatement(DefaultStatements.default2);
            statement.setString(1, scanner.nextLine());
            printer.printDefaultData2(statement.executeQuery());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }

    /**
     * Method giveNameCheckStatement - output names of available products in Inafix shop
     */
    private void printDefaultData1(){
        try {
            statement = connection.prepareStatement(DefaultStatements.default1);
            printer.printDefaultData1(statement.executeQuery());
        } catch (SQLException e) {
            printer.printErrorQuery();
        }
    }
}
