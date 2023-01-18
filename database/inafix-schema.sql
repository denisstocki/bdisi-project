DROP SCHEMA IF EXISTS Inafix;

CREATE SCHEMA Inafix;
USE Inafix;

DROP TABLE IF EXISTS Produkty;

CREATE TABLE Produkty(
	id INT UNSIGNED AUTO_INCREMENT,
	nazwa VARCHAR(30) NOT NULL,
	opis VARCHAR(500),
	cena DECIMAL(7,2) NOT NULL,
	producent VARCHAR(30) NOT NULL,
	kod_kreskowy INT UNSIGNED NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT niedodatnia_cena_towaru CHECK(cena > 0)
);

DROP TABLE IF EXISTS Dzial;

CREATE TABLE Dzial(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	nazwa VARCHAR(30) NOT NULL,
	powierzchnia DECIMAL(7,2) NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT ujemna_powierzchnia_dzialu CHECK(powierzchnia > 0)
);

DROP TABLE IF EXISTS Klienci;

CREATE TABLE Klienci(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	imie VARCHAR(30) NOT NULL,
	nazwisko VARCHAR(30) NOT NULL,
	poziom_lojalnosci ENUM('brazowy', 'srebrny', 'zloty') NOT NULL,
	plec ENUM('K', 'M') NOT NULL,
	kod_pocztowy VARCHAR(6),
	PRIMARY KEY(id)
);

DROP TABLE IF EXISTS Faktury;

CREATE TABLE Faktury(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	numer VARCHAR(30) NOT NULL,
	data DATE NOT NULL,
	id_klienta INT UNSIGNED NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_klienta) REFERENCES Klienci(id)
);

DROP TABLE IF EXISTS Elementy_faktur;

CREATE TABLE Elementy_faktur(
	id_faktury INT UNSIGNED NOT NULL,
	id_towaru INT UNSIGNED NOT NULL,
	FOREIGN KEY(id_faktury) REFERENCES Faktury(id),
	FOREIGN KEY(id_towaru) REFERENCES Produkty(id)
);

DROP TABLE IF EXISTS Uzytkownicy;

CREATE TABLE Uzytkownicy(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	login VARCHAR(30),
	haslo VARCHAR(30),
	PRIMARY KEY(id)
);

DROP TABLE IF EXISTS Pracownicy;

CREATE TABLE Pracownicy(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_uzytkownika INT UNSIGNED NOT NULL,
	imie VARCHAR(30) NOT NULL,
	nazwisko VARCHAR(30) NOT NULL,
	typ_pracownika SET('dyrektor', 'doradca') NOT NULL,
	pensja DECIMAL(7,2) NOT NULL,
	plec ENUM('K', 'M'),
	PRIMARY KEY(id),
	FOREIGN KEY(id_uzytkownika) REFERENCES Uzytkownicy(id),
	CONSTRAINT ujemna_pensja_pracownika CHECK(pensja > 0)
);

DROP TABLE IF EXISTS Magazyn;

CREATE TABLE Magazyn(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_produktu INT UNSIGNED NOT NULL,
	ilosc INT UNSIGNED NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_produktu) REFERENCES Produkty(id),
	CONSTRAINT ujemna_liczba_towaru CHECK(ilosc >= 0)
);

DROP TABLE IF EXISTS Sklep;

CREATE TABLE Sklep(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_produktu INT UNSIGNED NOT NULL,
	ilosc INT UNSIGNED NOT NULL,
	id_dzialu INT UNSIGNED NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_produktu) REFERENCES Produkty(id),
	FOREIGN KEY(id_dzialu) REFERENCES Dzial(id),
	CONSTRAINT ujemna_liczba_towaru CHECK(ilosc >= 0)
);

DROP TABLE IF EXISTS Harmonogram;

CREATE TABLE Harmonogram(
	id_pracownika INT UNSIGNED NOT NULL,
	rozpoczecie TIMESTAMP NOT NULL,
	zakonczenie TIMESTAMP NOT NULL,
	id_dzialu INT UNSIGNED NOT NULL,
	FOREIGN KEY(id_dzialu) REFERENCES Dzial(id),
	FOREIGN KEY(id_pracownika) REFERENCES Pracownicy(id),
	CONSTRAINT niespojne_godziny_pracy CHECK(zakonczenie > rozpoczecie)
);

DELIMITER $$
DROP TRIGGER IF EXISTS stockWareHouse $$
CREATE TRIGGER stockWarehouse
AFTER UPDATE ON Magazyn
FOR EACH ROW
BEGIN
    IF (
        NEW.ilosc = 0
    )
    THEN
    	UPDATE Magazyn
        SET NEW.ilosc = 100
        WHERE Magazyn.id = NEW.id;
    END IF;
END$$
DELIMITER ;

DELIMITER $$
DROP TRIGGER IF EXISTS stockShop $$
CREATE TRIGGER stockShop
AFTER UPDATE ON Sklep
FOR EACH ROW
BEGIN
    IF (
        NEW.ilosc = 0
    )
    THEN
        UPDATE Sklep
        SET NEW.ilosc = 1
        WHERE Sklep.id = NEW.id;
        UPDATE Magazyn
        SET Magazyn.ilosc = Magazyn.ilosc - 1
        WHERE Magazyn.id_produktu = NEW.id_produktu;
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE OR REPLACE PROCEDURE updateCustomerLevel(IN id_klienta INT)
BEGIN
	IF( 
		SELECT Klienci.poziom_lojalnosci where Klienci.id = id_klienta
	) = 'brazowy'
     	THEN 
     		UPDATE Klienci
     		SET poziom_lojalnosci = 'srebrny'
     		WHERE id = id_klienta;
     	ELSEIF(
     		SELECT Klienci.poziom_lojalnosci where Klienci.id = id_klienta
     	) = 'srebrny'
     	THEN
     		UPDATE Klienci
     		SET poziom_lojalnosci = 'zloty'
     		WHERE id = id_klienta;
     	END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE yearPayRise()
BEGIN
     UPDATE Pracownicy
     SET pensja = pensja * 1.5;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE yearPayDown()
BEGIN
     UPDATE Pracownicy
     SET pensja = pensja * 0.5;
END $$
DELIMITER ;

CREATE EVENT e
    ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 HOUR
    DO
    	UPDATE Produkty
    	SET cena = cena * 0.5
    	WHERE Produkty.id = (
    		SELECT id FROM Produkty
        	ORDER BY RAND()
        	LIMIT 1
    	);

