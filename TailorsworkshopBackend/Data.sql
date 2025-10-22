USE TailorsWorkshopDB;
GO


ALTER TABLE PRODUCT_OPTIONS NOCHECK CONSTRAINT ALL;
ALTER TABLE OPTION_TYPE_CHOICE NOCHECK CONSTRAINT ALL;
ALTER TABLE PRODUCTS NOCHECK CONSTRAINT ALL;
ALTER TABLE OPTION_CHOICE NOCHECK CONSTRAINT ALL;
ALTER TABLE OPTION_TYPE NOCHECK CONSTRAINT ALL;
ALTER TABLE PRODUCT_CATEGORIES NOCHECK CONSTRAINT ALL;
GO


DELETE FROM PRODUCT_OPTIONS;
DELETE FROM OPTION_TYPE_CHOICE;
DELETE FROM PRODUCTS;
DELETE FROM OPTION_CHOICE;
DELETE FROM OPTION_TYPE;
DELETE FROM PRODUCT_CATEGORIES;
GO


DBCC CHECKIDENT ('PRODUCTS', RESEED, 0);
DBCC CHECKIDENT ('OPTION_CHOICE', RESEED, 0);
DBCC CHECKIDENT ('OPTION_TYPE', RESEED, 0);
DBCC CHECKIDENT ('PRODUCT_CATEGORIES', RESEED, 0);
GO


ALTER TABLE PRODUCT_OPTIONS CHECK CONSTRAINT ALL;
ALTER TABLE OPTION_TYPE_CHOICE CHECK CONSTRAINT ALL;
ALTER TABLE PRODUCTS CHECK CONSTRAINT ALL;
ALTER TABLE OPTION_CHOICE CHECK CONSTRAINT ALL;
ALTER TABLE OPTION_TYPE CHECK CONSTRAINT ALL;
ALTER TABLE PRODUCT_CATEGORIES CHECK CONSTRAINT ALL;
GO


INSERT INTO PRODUCT_CATEGORIES (NAME)
VALUES ('SKIRT'),
       ('PANTS'),
       ('BAG');

INSERT INTO OPTION_TYPE (NAME)
VALUES ('COLOR'),
       ('MATERIAL'),
       ('SIZE'),
       ('LENGTH');

INSERT INTO OPTION_CHOICE (NAME)
VALUES ('RED'),
       ('BLUE'),
       ('GREEN'),
       ('BLACK'),
       ('COTTON'),
       ('POLYESTER'),
       ('WOOL'),
       ('S'),
       ('M'),
       ('L'),
       ('XL'),
       ('XXL'),
       ('NUMBER');


INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_CHOICE_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);


INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_CHOICE_ID)
VALUES (2, 5),
       (2, 6),
       (2, 7);


INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_CHOICE_ID)
VALUES (3, 8),
       (3, 9),
       (3, 10),
       (3, 11),
       (3, 12);


INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_CHOICE_ID)
VALUES (4, 13);


INSERT INTO PRODUCTS (NAME, DESCRIPTION, PRICE, CATEGORY_ID)
VALUES ('A-linija Suknja', 'Šik A-linija suknje, idealna za svakodnevne i kancelarijske prilike.', 2500.00, 1),
       ('Elegantna Olovka Suknja', 'Elegantna olovka suknja pogodna za formalne prilike.', 3000.00, 1),
       ('Naborana Suknja', 'Lepršava naborana suknja, savršena za letnje dane.', 2800.00, 1),
       ('Poslovna Torba', 'Poslovna torba za sve potrebne stvari za posao.', 4000.00, 3),
       ('Tote Torba Handmade', 'Ručno rađena tote torba, idealna za svakodnevnu upotrebu.', 3500.00, 3),
       ('Crossbody Torba', 'Stilizovana crossbody torba, udobna i moderna.', 3700.00, 3),
       ('Elegantne Pantalone', 'Elegantne pantalone za kancelariju i svečane prilike.', 3200.00, 2),
       ('Široke Pantalone', 'Široke pantalone za udobnost i svakodnevno nošenje.', 3000.00, 2),
       ('Skraćene Pantalone', 'Skraćene pantalone, trendi i moderne.', 2800.00, 2);


INSERT INTO PRODUCT_OPTIONS (PRODUCT_ID, OPTION_TYPE_ID)
VALUES

    (1, 1), (1, 2), (1, 3), (1, 4),
    (2, 1), (2, 2), (2, 3), (2, 4),
    (3, 1), (3, 2), (3, 3), (3, 4),


    (4, 1), (4, 2), (4, 4),
    (5, 1), (5, 2), (5, 4),
    (6, 1), (6, 2), (6, 4),


    (7, 1), (7, 2), (7, 3), (7, 4),
    (8, 1), (8, 2), (8, 3), (8, 4),
    (9, 1), (9, 2), (9, 3), (9, 4);
GO


SELECT * FROM PRODUCT_CATEGORIES;
SELECT * FROM OPTION_TYPE;
SELECT * FROM OPTION_CHOICE;
SELECT * FROM OPTION_TYPE_CHOICE;
SELECT * FROM PRODUCTS;
SELECT * FROM PRODUCT_OPTIONS;
