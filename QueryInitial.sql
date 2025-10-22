INSERT INTO USERS (EMAIL, NAME)
VALUES ('djixx@gmail.com', 'Djixx');

INSERT INTO PRODUCT_CATEGORIES (NAME)
VALUES ('SKIRT'),
       ('PANTS'),
       ('BAG');

-- Products
INSERT INTO OPTION_TYPE ( NAME)
VALUES ('COLOR'),
       ('MATERIAL'),
       ( 'SIZE'),
       ('LENGTH');

INSERT INTO OPTION_CHOICE ( NAME)
VALUES ( 'RED'),
       ( 'BLUE'),
       ( 'GREEN'),
       ( 'BLACK'),
       ( 'COTTON'),
       ( 'POLYESTER'),
       ( 'WOOL'),
       ( 'S'),
       ( 'M'),
       ( 'L'),
       ( 'XL'),
       ('XXL'),
       ('NUMBER');
-- for length

-- COLOR → RED, BLUE, GREEN, BLACK
INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_CHOICE_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);

-- MATERIAL → COTTON, POLYESTER, WOOL
INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_CHOICE_ID)
VALUES (2, 5),
       (2, 6),
       (2, 7);

-- SIZE → S, M, L, XL, XXL
INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_CHOICE_ID)
VALUES (3, 8),
       (3, 9),
       (3, 10),
       (3, 11),
       (3, 12);

-- LENGTH → NUMBER
INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_CHOICE_ID)
VALUES (4, 13);

INSERT INTO PRODUCTS (NAME, DESCRIPTION, PRICE, CATEGORY_ID)
VALUES ( 'A-linija Suknja', 'Šik A-linija suknje, idealna za svakodnevne i kancelarijske prilike.', 2500.00, 1),
       ( 'Elegantna Olovka Suknja', 'Elegantna olovka suknja pogodna za formalne prilike.', 3000.00, 1),
       ( 'Naborana Suknja', 'Lepršava naborana suknja, savršena za letnje dane.', 2800.00, 1),
       ( 'Poslovna Torba', 'Poslovna torba za sve potrebne stvari za posao.', 4000.00, 3),
       ( 'Tote Torba Handmade', 'Rucno radena tote torba, idealna za svakodnevnu upotrebu.', 3500.00, 3),
       ( 'Crossbody Torba', 'Stilizovana crossbody torba, udobna i moderna.', 3700.00, 3),
       ( 'Elegantne Pantalone', 'Elegantne pantalone za kancelariju i svecane prilike.', 3200.00, 2),
       ( 'Široke Pantalone', 'Široke pantalone za udobnost i svakodnevno nošenje.', 3000.00, 2),
       ( 'Skracene Pantalone', 'Skracene pantalone, trendi i moderne.', 2800.00, 2);


-- PRODUCT ↔ OPTION TYPE mapping
INSERT INTO PRODUCT_OPTIONS (PRODUCT_ID, OPTION_TYPE_ID)
VALUES
    -- All skirts (1–3) have COLOR, MATERIAL, SIZE, LENGTH
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (3, 1),
    (3, 2),
    (3, 3),
    (3, 4),

    -- Bags (4–6) have COLOR, MATERIAL, LENGTH
    (4, 1),
    (4, 2),
    (4, 4),
    (5, 1),
    (5, 2),
    (5, 4),
    (6, 1),
    (6, 2),
    (6, 4),

    -- Pants (7–9) have COLOR, MATERIAL, SIZE, LENGTH
    (7, 1),
    (7, 2),
    (7, 3),
    (7, 4),
    (8, 1),
    (8, 2),
    (8, 3),
    (8, 4),
    (9, 1),
    (9, 2),
    (9, 3),
    (9, 4);
