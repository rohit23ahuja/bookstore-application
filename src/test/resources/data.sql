delete from book where book_id is not null;
commit;
INSERT INTO BOOK (BOOK_ID, ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES (1,'123ABC','Game Of Thrones','George Martin',1700,1,5);
INSERT INTO BOOK (BOOK_ID, ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES (2,'234DEF','Lord Of Rings','Donot know',3000,1,5);
INSERT INTO BOOK (BOOK_ID, ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES (3,'345EFG','Homo Sapiens','Yuval Harari',500,1,5);
INSERT INTO BOOK (BOOK_ID, ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES (4,'456FGJ','sunt','Rohit A',250,1,5);
INSERT INTO BOOK (BOOK_ID, ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES (5,'556FGH','reprehenderit dolor','Rohit A',250,1,5);
INSERT INTO BOOK (BOOK_ID, ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES (6,'656FGH','sit autem','Rohit A',250,1,5);
