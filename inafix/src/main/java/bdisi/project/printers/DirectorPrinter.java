package bdisi.project.printers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectorPrinter extends WorkerPrinter{

    @Override
    public void welcomeTheUser() {
        System.out.println("Witamy w systemie dyrektorze !\n");
    }

    @Override
    public void talkWithTheUser() {
        String msg = "Czego bys chial dokonac w naszym systemie ? Wybierz jedna z opcji ponizej wpisujac odpowiadajacy jej numer:\n\n" +
                "1. Wyswietl informacje na temat moich pracownikow.\n" +
                "2. Zmien harmonogram danego pracownika.\n" +
                "3. Wyswietl faktury podanego przeze mnie klienta.\n" +
                "4. Wyswietl fakture o podanym przeze mnie numerze.\n" +
                "5. Wyswietl wszystkie numery faktur.\n" +
                "6. Wyswietl liczbe klientow ktorzy odwiedzili sklep.\n" +
                "7. Dodaj nowy dzial na sklepie.\n" +
                "8. Wylej wskazanego przeze mnie pracownika.\n" +
                "9. Zatrudnij pracownika.\n";

        super.printSystemMessage(msg);
    }

    public void printDirectorData1(ResultSet resultSet) {
        System.out.println("Wyswietlam informacje na temat pracownikow w Panskiej firmie:\n");

        try {
            while(resultSet.next()){
                System.out.println("Id: " + resultSet.getInt(1) + "\n" +
                        "id_uzytkownika: " + resultSet.getInt(2) + "\n" +
                        "imie: " + resultSet.getString(3) + "\n" +
                        "nazwisko: " + resultSet.getString(4) + "\n" +
                        "typ_pracownika: " + resultSet.getString(5) + "\n" +
                        "pensja: " + resultSet.getDouble(6) + "\n" +
                        "plec: " + resultSet.getString(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
    }

    public void printNameQuestion() {
        System.out.println("Podaj imie pracownika:");
    }

    public void printSurnameQuestion() {
        System.out.println("Podaj nazwisko pracownika:");
    }

    public void printScheduleChange() {
        System.out.println("Zmiana harmonogramu ...\n");
    }

    public void printStartingDataQuestion() {
        System.out.println("Podaj date rozpoczecia w formacie YYYY-MM-DD HH:MM:SS :");
    }

    public void printEndingDataQuestion() {
        System.out.println("Podaj date zakonczenia w formacie YYYY-MM-DD HH:MM:SS :");
    }

    public void printSuccessfulQuery() {
        System.out.println("\nPolecenie zostalo wykonane !\n");
    }

    public void printWorkerNumberQuestion() {
        System.out.println("Podaj id pracownika, ktoremu chcesz zmienic harmonogram:");
    }

    public void printDirectorData2(int result) {
        if(result == 0){
            System.out.println("\nTabela nie zostala zmieniona, sprobuj jeszcze raz ...\n");
        } else {
            printSuccessfulQuery();
        }
    }
}
