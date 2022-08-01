create table if not exists book
(
  book_id BIGSERIAL PRIMARY KEY,
  isbn varchar(255) not null,
  title varchar(2000) not null,
  author varchar(100) not null,
  price integer not null, 
  version integer default 1 not null,
  quantity integer
);

INSERT INTO BOOK (ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES ('123ABC','Game Of Thrones','George Martin',1700,1,5);
INSERT INTO BOOK (ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES ('234DEF','Lord Of Rings','Donot know',3000,1,5);
INSERT INTO BOOK (ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES ('345EFG','Homo Sapiens','Yuval Harari',500,1,5);
INSERT INTO BOOK (ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES ('456FGH','optio reprehenderit','Rohit A',250,1,5);
INSERT INTO BOOK (ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES ('567FGH','reprehenderit dolor','Rohit A',250,1,5);
INSERT INTO BOOK (ISBN, TITLE, AUTHOR, PRICE,VERSION, QUANTITY) VALUES ('678FGH','sit autem','Rohit A',250,1,5);