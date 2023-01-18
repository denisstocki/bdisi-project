package bdisi.project.statements;

/**
 * Class DirectorStatements - holds prepared statements to run in SQL
 */
public class DirectorStatements {

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String director1 =
            "SELECT * " +
            "FROM Pracownicy";

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String director2 = "UPDATE Harmonogram " +
            "SET Harmonogram.rozpoczecie = ?, " +
            "Harmonogram.zakonczenie = ? " +
            "WHERE id_pracownika = ?";

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String director3_1 = "SELECT * " +
            "FROM Faktury " +
            "WHERE klient = ?";

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String director3_2 = "SELECT p.nazwa, p.cena" +
            "FROM Produkty AS p " +
            "JOIN Elementy_faktury AS ef " +
            "ON p.id = ef.id_towaru " +
            "WHERE ef.id_faktury = ?";

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String director4 = "SELECT SUM(p.cena) AS suma_faktury" +
            "FROM Produkty AS p " +
            "JOIN Elementy_faktury AS ef " +
            "ON p.id = ef.id_towaru " +
            "WHERE ef.id_faktury = ?";

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String director5 = "SELECT id " +
            "FROM Faktury";

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String director6 = "SELECT COUNT(id) AS liczba_klientow " +
            "FROM Klienci";

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String director7 = "INSERT INTO Dzial(nazwa, powierzchnia) " +
            "VALUES ('?', ?)";

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String director8 = "DELETE FROM Pracownicy " +
            "WHERE imie = ? AND nazwisko = ?";

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String director9 = "INSERT INTO Pracownicy(id_uzytkownika, imie, nazwisko, typ_pracownika, pensja, plec) " +
            "VALUES (?, '?', '?', '?', ?, '?')";
}
