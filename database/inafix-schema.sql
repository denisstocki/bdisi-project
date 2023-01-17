DROP SCHEMA IF EXISTS Inafix;

CREATE SCHEMA Inafix;
USE Inafix;

DROP TABLE IF EXISTS Produkty;

CREATE TABLE Produkty(
	id INT UNSIGNED AUTO_INCREMENT,
	nazwa VARCHAR(30) NOT NULL,
	opis VARCHAR(500),
	cena DECIMAL(4,2) NOT NULL,
	producent VARCHAR(30) NOT NULL,
	kod_kreskowy INT UNSIGNED NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT niedodatnia_cena_towaru CHECK(cena > 0)
);

DROP TABLE IF EXISTS Dzial;

CREATE TABLE Dzial(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	nazwa VARCHAR(30) NOT NULL,
	powierzchnia DECIMAL(4,2) NOT NULL,
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
	pensja DECIMAL(4,2) NOT NULL,
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
	CONSTRAINT ujemna_liczba_towaru CHECK(ilosc > 0)
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
	CONSTRAINT ujemna_liczba_towaru CHECK(ilosc > 0)
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
