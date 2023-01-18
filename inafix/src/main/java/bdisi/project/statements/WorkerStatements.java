package bdisi.project.statements;

/**
 * Class WorkerStatements - holds prepared statements to run in SQL
 */
public class WorkerStatements {

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String worker1 = "SELECT * FROM Produkty WHERE nazwa = ?";

    /**
     * Variable worker4 - is a prepared statement for SQL to get quantity of all products in the shop
     */
    public static final String worker4 = "SELECT p.nazwa, s.ilosc " +
            "FROM Produkty AS p" +
            "JOIN Sklep AS s" +
            "ON p.id = s.id_produktu";

    /**
     * Variable worker5 - is a prepared statement for SQL to get quantity of all products in the magazine
     */
    public static final String worker5 = "SELECT p.nazwa, m.ilosc " +
            "FROM Produkty AS p" +
            "JOIN Magazyn AS m" +
            "ON p.id = m.id_produktu";

    /**
     * Variable worker6 - is a prepared statement for SQL to get the department where the product can be found
     */
    public static final String worker6 = "SELECT d.nazwa_dzialu " +
            "FROM Dzial AS d" +
            "JOIN Sklep AS s" +
            "ON d.id = s.dzial" +
            "JOIN Produkty AS p" +
            "ON s.id_produktu = p.id" +
            "WHERE nazwa = ?";

    /**
     * Variable worker8 - is a prepared statement for SQL to get wanted product quantity on shop
     */
    public static final String worker8 = "SELECT p.nazwa, s.ilosc " +
            "FROM Produkty AS p" +
            "JOIN Sklep AS s" +
            "ON p.id = s.id_produktu" +
            "WHERE nazwa = ?";

    /**
     * Variable worker9 - is a prepared statement for SQL to get wanted products quantity in magazine
     */
    public static final String worker9 = "SELECT p.nazwa, m.ilosc " +
            "FROM Produkty AS p" +
            "JOIN Magazyn AS m" +
            "ON p.id = m.id_produktu" +
            "WHERE nazwa = ?";
}
