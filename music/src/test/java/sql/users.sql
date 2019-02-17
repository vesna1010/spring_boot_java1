DELETE FROM USERS;

INSERT INTO USERS(ID, NAME, EMAIL, PASSWORD, ENABLED, ROLE) 
VALUES(1, 'Name A', 'emailA@gmail.com', '$2a$10$ef926YFMa//f/Xlgvqk/S.QmYvRY9Qd261wUT8sIXWd2b42zDcemO', true, 'ADMIN');
INSERT INTO USERS(ID, NAME, EMAIL, PASSWORD, ENABLED, ROLE) 
VALUES(2, 'Name B', 'emailB@gmail.com', '$2a$10$S0tc2t4UQrw.NfynbqWWduvZvrMzWcbhLiss7TfmFy5Qk07ODMeFu', true, 'USER');
INSERT INTO USERS(ID, NAME, EMAIL, PASSWORD, ENABLED, ROLE) 
VALUES(3, 'Name C', 'emailC@gmail.com', '$2a$10$EwStELgMIHue0uzJwSPJluHE2lxq136HlE82RZ2NakjjsrKbEHl.W', true, 'GUEST');
INSERT INTO USERS(ID, NAME, EMAIL, PASSWORD, ENABLED, ROLE) 
VALUES(4, 'Name D', 'emailD@gmail.com', '$2a$10$XjR9TLJsgBfv7faXktEIcOpIi0/zpUEtivgxT3NK1wvgRR45GtbtC', true, 'GUEST');
