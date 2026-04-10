Instrukcja:
1. Dodaj biblioteki:
 - sqlite-jdbc
 - jbcrypt

2. Utwórz bazę database.db i tabele:

CREATE TABLE users (
 id INTEGER PRIMARY KEY AUTOINCREMENT,
 username TEXT UNIQUE,
 password TEXT,
 role TEXT
);

CREATE TABLE shoes (
 id INTEGER PRIMARY KEY AUTOINCREMENT,
 name TEXT,
 brand TEXT,
 size INTEGER,
 price REAL
);

3. Uruchom Main.java
Login admin dodaj ręcznie w DB.

Projekt spełnia wymagania na ocenę 5.
