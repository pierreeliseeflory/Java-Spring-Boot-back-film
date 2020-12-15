CREATE TABLE IF NOT EXISTS Realisateur(id INT primary key auto_increment, prenom VARCHAR(100), nom VARCHAR(100), dateNaissance DATE);
CREATE TABLE IF NOT EXISTS Genre(id INT primary key auto_increment, nom VARCHAR(100));
CREATE TABLE IF NOT EXISTS Film(id INT primary key auto_increment, titre VARCHAR(100), duration INT, realisateur INT, genre INT, FOREIGN KEY (realisateur) REFERENCES Realisateur(id) ON DELETE CASCADE, FOREIGN KEY (genre) REFERENCES Genre(id));


INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Peter', 'Jackson', '1961-10-31');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Steven', 'Spielberg', '1946-12-18');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Christopher', 'Nolan', '1970-07-30');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('James', 'Wan', '1977-02-26');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Lana', 'Wachowski', '1965-12-21');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Jonathan', 'Demme', '1944-02-22');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Alfred', 'Hitchcock', '1899-08-13');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('David', 'Fincher', '1962-08-28');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Orson', 'Welles', '1915-05-06');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Martin', 'Scorsese', '1942-11-17');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Quentin', 'Tarantino', '1963-03-27');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Ridley', 'Scott', '1937-11-30');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Francis Ford', 'Coppola', '1939-04-07');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Stanley', 'Kubrick', '1928-06-26');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Woody', 'Allen', '1935-12-01');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Clint', 'Eastwood', '1930-05-31');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Paul', 'Verhoeven', '1938-07-18');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('George', 'Lucas', '1944-05-14');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('François', 'Truffaut', '1932-02-06');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Michael', 'Bay', '1965-02-17');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('Mel', 'Gibson', '1956-01-03');
INSERT INTO Realisateur(prenom, nom, dateNaissance) VALUES('David', 'Lynch', '1946-01-20');

INSERT INTO Genre(nom) VALUES('action');
INSERT INTO Genre(nom) VALUES('thriller');
INSERT INTO Genre(nom) VALUES('policier');
INSERT INTO Genre(nom) VALUES('horreur');
INSERT INTO Genre(nom) VALUES('comédie');
INSERT INTO Genre(nom) VALUES('science-fiction');
INSERT INTO Genre(nom) VALUES('drame');
INSERT INTO Genre(nom) VALUES('biopic');
INSERT INTO Genre(nom) VALUES('fantasy');

INSERT INTO Film(titre, duration, realisateur, genre) VALUES('Matrix', 150, 5, 6);
INSERT INTO Film(titre, duration, realisateur, genre) VALUES('Le silence des Agneaux', 138, 6, 2);
INSERT INTO Film(titre, duration, realisateur, genre) VALUES('Saw', 103, 4, 4);
INSERT INTO Film(titre, duration, realisateur, genre) VALUES('Le seigneur des Anneaux', 208, 1, 9);
INSERT INTO Film(titre, duration, realisateur, genre) VALUES('E.T. the Extra-Terrestrial', 114 , 2, 6);
INSERT INTO Film(titre, duration, realisateur, genre) VALUES('Jaws', 124, 2, 2);
INSERT INTO Film(titre, duration, realisateur, genre) VALUES('Jurassic Park', 128, 2, 6);