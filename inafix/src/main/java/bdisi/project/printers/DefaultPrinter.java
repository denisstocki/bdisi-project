package bdisi.project.printers;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class DefaultPrinter - is used to print default users output in a correct format
 */
public class DefaultPrinter {

    /**
     * Method successfulConnection - prints message about successful connection with database
     */
    public void printSuccessfulConnection(){
        String msg = "Successfully connected to Inafix database !\n";

        printSystemMessage(msg);
    }

    /**
     * Method welcomeTheUser - simply welcomes the user
     */
    public void welcomeTheUser() {
        String msg = "Witaj niezalogowany uzytkowniku !\n";

        printSystemMessage(msg);
    }

    /**
     * Method printSystemMessage - prints any message that was given as a parameter
     */
    void printSystemMessage(String msg){
        System.out.println(msg);
    }

    /**
     * Method askTheUser - simply asks the user for its wants
     */
    public void talkWithTheUser() {
        String msg = "Czego bys chial dokonac w naszym systemie ? Wybierz jedna z opcji ponizej wpisujac odpowiadajacy jej numer:\n\n" +
                "1. Wyswietl dostepne w sklepie produkty.\n" +
                "2. Wyswietl informacje na temat wybranego przeze mnie produktu.\n" +
                "3. Sprzedaj mi wybrany przeze mnie produkt.\n";

        printSystemMessage(msg);
    }

    /**
     * Method printErrorQuery - prints an error when query failed
     */
    public void printErrorQuery() {
        String msg = "Prepared query is wrongly prepared ! Needs to be fixed ...\n";

        printSystemMessage(msg);
    }

    /**
     * Method printNamesResultSet - prints an output given by SQL by running a query
     */
    public void printDefaultData1(ResultSet resultSet) {
        int counter = 0;

        System.out.println("\nWyswietlam dostepne w naszym sklepie produkty:");

        try {
            while(resultSet.next()){
                System.out.println(++counter + ": " + resultSet.getString(1));
            }
        } catch (SQLException e) {
            printErrorQuery();
        }

        System.out.println();
    }

    /**
     * Method printProductInfomation - outputs given product information
     * @param resultSet
     */
    public void printDefaultData2(ResultSet resultSet) throws SQLException {
        System.out.println("\nWyswietlam informacje o wybranym przez Ciebie produkcie:");

        while(resultSet.next()){
            System.out.println("Id: " + resultSet.getInt(1) + "\n" +
                    "nazwa: " + resultSet.getString(2) + "\n" +
                    "opis: " + resultSet.getString(3) + "\n" +
                    "cena: " + resultSet.getDouble(4) + "\n" +
                    "producent: " + resultSet.getString(5) + "\n" +
                    "kod_kreskowy: " + resultSet.getInt(6));
        }

        System.out.println();
    }

    /**
     * Method printDefaultError - outputs a text that tells that there was an error when executing query
     */
    public void printDefaultError() {
        System.out.println("System nie udostepnia takiej opcji !\n");
    }

    /**
     * Method printDefaultEnding - prints a message that the user finished work with the system
     */
    public void printDefaultEnding() {
        System.out.println("Uzytkownik postanowil zakonczyc prace z systemem ...\n");
    }

    /**
     * Method printProductNameAsk - ask for a product name to run a query
     */
    public void printProductNameAsk() {
        System.out.println("Podaj nazwe produktu, na ktorego temat mam wyswietlic informacje:");
    }
}
