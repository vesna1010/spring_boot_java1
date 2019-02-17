DELETE FROM SINGERS;
DELETE FROM ALBUMS;
DELETE FROM SONGS;

INSERT INTO SINGERS(ID, NAME, BIRTH_DATE, PHOTO) VALUES(1, 'Singer search A', '1987-12-01', 
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\image.jpg'));

INSERT INTO SINGERS(ID, NAME, BIRTH_DATE, PHOTO) VALUES(2, 'Singer B', '1977-06-06', 
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\image.jpg'));

INSERT INTO SINGERS(ID, NAME, BIRTH_DATE, PHOTO) VALUES(3, 'Singer C', '1972-11-13', 
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\image.jpg'));

INSERT INTO SINGERS(ID, NAME, BIRTH_DATE, PHOTO) VALUES(4, 'Singer D', '1985-11-09', 
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\image.jpg'));

INSERT INTO ALBUMS(ID, TITLE, RELEASE_DATE, SINGER_ID, LOGO) VALUES(1, 'Title search A', '2019-01-02', 1, 
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\image.jpg'));

INSERT INTO ALBUMS(ID, TITLE, RELEASE_DATE, SINGER_ID, LOGO) VALUES(2, 'Title B', '2019-01-01', 1, 
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\image.jpg'));

INSERT INTO ALBUMS(ID, TITLE, RELEASE_DATE, SINGER_ID, LOGO) VALUES(3, 'Title C', '2019-01-02', 2, 
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\image.jpg'));

INSERT INTO ALBUMS(ID, TITLE, RELEASE_DATE, SINGER_ID, LOGO) VALUES(4, 'Title D', '2019-01-02', 3, 
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\image.jpg'));

INSERT INTO ALBUMS(ID, TITLE, RELEASE_DATE, SINGER_ID, LOGO) VALUES(5, 'Title E', '2019-01-02', 4, 
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\image.jpg'));

INSERT INTO SONGS(ID, TITLE, ALBUM_ID, COUNTER, FILE) VALUES(1, 'Title SEARch A', 1, 0,
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\song.mp3'));

INSERT INTO SONGS(ID, TITLE, ALBUM_ID, COUNTER, FILE) VALUES(2, 'Title B', 1, 0,
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\song.mp3'));

INSERT INTO SONGS(ID, TITLE, ALBUM_ID, COUNTER, FILE) VALUES(3, 'Title SEARch C', 2, 0,
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\song.mp3'));

INSERT INTO SONGS(ID, TITLE, ALBUM_ID, COUNTER, FILE) VALUES(4, 'Title D', 3, 0,
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\song.mp3'));

INSERT INTO SONGS(ID, TITLE, ALBUM_ID, COUNTER, FILE) VALUES(5, 'Title search E', 4, 0,
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\song.mp3'));

INSERT INTO SONGS(ID, TITLE, ALBUM_ID, COUNTER, FILE) VALUES(6, 'Title F', 5, 0,
LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\song.mp3'));








