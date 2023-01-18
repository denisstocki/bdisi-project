package bdisi.project.printers;

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
        System.out.println("\nProsze podaj swoj login pracowniczy:");
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
        System.out.println("\nProces logowania nie powiodl sie. Niepoprawny login lub haslo ...\n");
    }
}
