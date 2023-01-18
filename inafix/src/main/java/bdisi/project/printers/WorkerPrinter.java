package bdisi.project.printers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerPrinter extends DefaultPrinter{

    @Override
    public void welcomeTheUser() {
        System.out.println("Witaj pracowniku !\n");
    }

    @Override
    public void talkWithTheUser() {
        String msg = "Czego bys chial dokonac w naszym systemie ? Wybierz jedna z opcji ponizej wpisujac odpowiadajacy jej numer:\n\n" +
                "1. Wyswietl informacje na temat wybranego przeze mnie produktu.\n" +
                "2. Podaj wysokosc mojej pensji.\n" +
                "3. Podaj moj harmonogram w tym tygodniu.\n" +
                "4. Podaj ilosc wszystkich produktow na sklepie.\n" +
                "5. Podaj ilosc wszystkich produktow na magazynie.\n" +
                "6. Podaj na ktorym dziale znajduje sie produkt o podanej przeze mnie nazwie.\n" +
                "7. Przenies wskazany przeze mnie produkt z magazynu do sklepu.\n" +
                "8. Podaj ilosc wskazanego przez mnie produktu na sklepie.\n" +
                "9. Podaj ilosc wskazanego przez mnie produktu na magazynie.\n";

        super.printSystemMessage(msg);
    }

    /**
     * Simply asks user to pass a login
     */
    public void askForLogin() {
        System.out.println("Prosze podaj swoj login pracowniczy:");
    }

    /**
     * Simply asks user to pass a password
     */
    public void askForPassword() {
        System.out.println("\nProsze podaj swoje haslo pracownicze:");
    }

    /**
     * Method printFailedLogin - show a message that logging in was interrupted
     */
    public void printFailedLogin() {
        System.out.println("\nProces logowania zostal przerwany ...\n");
    }

    /**
     * Method printWrongLogin - simply shows that login or password were incorrect
     */
    public void printWrongLogin() {
        System.out.println("Proces logowania nie powiodl sie. Niepoprawny login lub haslo ...\n");
    }

    /**
     * Method printWorkerData1 - simply shows the result of worker1 statement query
     * @param resultSet
     */
    public void printWorkerData1(ResultSet resultSet) {
        System.out.println("\nWyswietlam informacje odnosnie wskazanego przez Ciebie produktu:");

        try {
            while(resultSet.next()){
                System.out.println("Id: " + resultSet.getInt(1) + "\n" +
                        "nazwa: " + resultSet.getString(2) + "\n" +
                        "opis: " + resultSet.getString(3) + "\n" +
                        "cena: " + resultSet.getDouble(4) + "\n" +
                        "producent: " + resultSet.getString(5) + "\n" +
                        "kod_kreskowy: " + resultSet.getInt(6));
            }
        } catch (SQLException e) {
            printErrorQuery();
        }

        System.out.println();
    }

    public void printWorkerData4(ResultSet resultSet) {
        System.out.println("\nWyswietlam ilosc kazdego z produktow na sklepie:");
        printWorkerData45(resultSet);
    }

    /**
     * Method printWorkerData5 - simply writes down the quantity of all products in the magazine
     * @param resultSet
     */
    public void printWorkerData5(ResultSet resultSet) {
        System.out.println("\nWyswietlam ilosc kazdego z produktow na magazynie:");
        printWorkerData45(resultSet);
    }

    /**
     * Method printWorkerData45 - is used as a shared method between worker4 and worker5
     * @param resultSet
     */
    private void printWorkerData45(ResultSet resultSet) {
        int counter = 0;

        try {
            while(resultSet.next()){
                System.out.println(++counter + ": " + resultSet.getString(1) + " - " + resultSet.getInt(2) + " szt.");
            }
        } catch (SQLException e) {
            printErrorQuery();
        }

        System.out.println();
    }

    /**
     * Method printWorkerData6 - is used to show in which district is the product located
     * @param resultSet
     */
    public void printWorkerData6(ResultSet resultSet) {
        int counter = 0;

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
     * Method printWorkerData6 - is used to show quantity of a given product name in the shop
     * @param resultSet
     */
    public void printWorkerData8(ResultSet resultSet) {
        try {
            while(resultSet.next()){
                System.out.println("Nazwa: " + resultSet.getString(1) + ", ilosc = " + resultSet.getInt(2));
            }
        } catch (SQLException e) {
            printErrorQuery();
        }

        System.out.println();
    }

    /**
     * Method printWorkerData6 - is used to show quantity of a given product name in the magazine
     * @param resultSet
     */
    public void printWorkerData9(ResultSet resultSet) {
        try {
            if(!resultSet.next()){
                System.out.println("\nBrak pasujacych danych ...");
            } else {
                while(resultSet.next()){
                    System.out.println("Nazwa: " + resultSet.getString(1) + ", ilosc = " + resultSet.getInt(2));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
    }
}
