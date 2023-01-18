package bdisi.project.statements;

/**
 * Class DefaultStatements - is supposed to keep all the prepared statements for a default user
 */
public class DefaultStatements {

    /**
     * Variable productCheck - is a prepared statement for SQL to get all data of a wanted product
     */
    public static final String default2 = "SELECT * FROM Produkty WHERE nazwa = ?";

    /**
     * Variable nameCheck - is a prepared statement for SQL to get name of all products
     */
    public static final String default1 = "SELECT nazwa FROM Produkty";
}
