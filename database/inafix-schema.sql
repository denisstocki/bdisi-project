DROP SCHEMA IF EXISTS Inafix;

CREATE SCHEMA Inafix;
USE Inafix;

DROP TABLE IF EXISTS Produkty;

CREATE TABLE Produkty(
	id_produktu INT UNSIGNED AUTO_INCREMENT,
	nazwa VARCHAR(30) NOT NULL,
	opis VARCHAR(500),
	cena DECIMAL(4,2) NOT NULL,
	producent VARCHAR(30) NOT NULL,
	kod_kreskowy INT UNSIGNED NOT NULL,
	PRIMARY KEY(id_produktu)
);

DROP TABLE IF EXISTS Dzial;

CREATE TABLE Dzial(
	id_dzialu INT UNSIGNED NOT NULL AUTO_INCREMENT,
	nazwa VARCHAR(30) NOT NULL,
	powierzchnia DECIMAL(4,2) NOT NULL,
	PRIMARY KEY(id_dzialu)
);

DROP TABLE IF EXISTS Klienci;

CREATE TABLE Klienci(
	id_klienta INT UNSIGNED NOT NULL AUTO_INCREMENT,
	imie VARCHAR(30) NOT NULL,
	nazwisko VARCHAR(30) NOT NULL,
	poziom_lojalnosci ENUM('brazowy', 'srebrny', 'zloty') NOT NULL,
	plec ENUM('K', 'M') NOT NULL,
	kod_pocztowy VARCHAR(6),
	PRIMARY KEY(id_klienta)
);

DROP TABLE IF EXISTS Faktury;

CREATE TABLE Faktury(
	id_faktury INT UNSIGNED NOT NULL AUTO_INCREMENT,
	numer_faktury VARCHAR(30) NOT NULL,
	data_wystawienia DATE NOT NULL,
	klient INT UNSIGNED NOT NULL,
	PRIMARY KEY(id_faktury),
	FOREIGN KEY(klient) REFERENCES Klienci(id_klienta)
);

DROP TABLE IF EXISTS Elementy_faktur;

CREATE TABLE Elementy_faktur(
	id_faktury INT UNSIGNED NOT NULL,
	id_towaru INT UNSIGNED NOT NULL,
	FOREIGN KEY(id_faktury) REFERENCES Faktury(id_faktury),
	FOREIGN KEY(id_towaru) REFERENCES Produkty(id_produktu)
);

DROP TABLE IF EXISTS Uzytkownicy;

CREATE TABLE Uzytkownicy(
	id_uzytkownika INT UNSIGNED NOT NULL AUTO_INCREMENT,
	login VARCHAR(30),
	haslo VARCHAR(30),
	PRIMARY KEY(id_uzytkownika)
);

DROP TABLE IF EXISTS Pracownicy;

CREATE TABLE Pracownicy(
	id_pracownika INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_uzytkownika INT UNSIGNED NOT NULL,
	imie VARCHAR(30) NOT NULL,
	nazwisko VARCHAR(30) NOT NULL,
	typ_pracownika SET('dyrektor', 'doradca') NOT NULL,
	pensja DECIMAL(4,2) NOT NULL,
	plec ENUM('K', 'M'),
	PRIMARY KEY(id_pracownika),
	FOREIGN KEY(id_uzytkownika) REFERENCES Uzytkownicy(id_uzytkownika)
);

DROP TABLE IF EXISTS Magazyn;

CREATE TABLE Magazyn(
	id_towaru INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_produktu INT UNSIGNED NOT NULL,
	ilosc INT UNSIGNED NOT NULL,
	PRIMARY KEY(id_towaru),
	FOREIGN KEY(id_produktu) REFERENCES Produkty(id_produktu)
);

DROP TABLE IF EXISTS Sklep;

CREATE TABLE Sklep(
	id_przedmiotu INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_produktu INT UNSIGNED NOT NULL,
	ilosc INT UNSIGNED NOT NULL,
	dzial INT UNSIGNED NOT NULL,
	PRIMARY KEY(id_przedmiotu),
	FOREIGN KEY(id_produktu) REFERENCES Produkty(id_produktu),
	FOREIGN KEY(dzial) REFERENCES Dzial(id_dzialu)
);

DROP TABLE IF EXISTS Harmonogram;

CREATE TABLE Harmonogram(
	id_pracownika INT UNSIGNED NOT NULL,
	data DATE NOT NULL,
	rozpoczecie INT UNSIGNED NOT NULL,
	zakonczenie INT UNSIGNED NOT NULL,
	dzial INT UNSIGNED NOT NULL,
	FOREIGN KEY(dzial) REFERENCES Dzial(id_dzialu),
	FOREIGN KEY(id_pracownika) REFERENCES Pracownicy(id_pracownika)
);
