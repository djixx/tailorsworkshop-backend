INSERT INTO users(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, ROLE)
VALUES (1, 'Admin', 'Adminic', 'admin@gmail.com', '$2a$10$AMUwKxdqfjWa0HeztDmC7OuJHAEBHMqcb5M1LXLZ30lnCjerrwqfK',
        'ADMIN'),
       (2, 'Andjela', 'User', 'andjela@gmail.com', '$2a$10$AMUwKxdqfjWa0HeztDmC7OuJHAEBHMqcb5M1LXLZ30lnCjerrwqfK',
        'USER'); -- PWD admin123

INSERT INTO PRODUCT_CATEGORIES (ID, NAME)
VALUES (1, 'SKIRT'),
       (2, 'PANTS'),
       (3, 'BAG');

-- Products
INSERT INTO OPTION_TYPE (ID, NAME)
VALUES (1, 'COLOR'),
       (2, 'MATERIAL'),
       (3, 'SIZE'),
       (4, 'LENGTH');

INSERT INTO OPTION_CHOICE (ID, NAME)
VALUES (1, 'RED'),
       (2, 'BLUE'),
       (3, 'GREEN'),
       (4, 'BLACK'),
       (5, 'COTTON'),
       (6, 'POLYESTER'),
       (7, 'WOOL'),
       (8, 'S'),
       (9, 'M'),
       (10, 'L'),
       (11, 'XL'),
       (12, 'XXL'),
       (13, 'NUMBER');
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

INSERT INTO PRODUCTS (ID, NAME, DESCRIPTION, PRICE, CATEGORY_ID)
VALUES (1, 'A-linija Suknja', 'Šik A-linija suknje, idealna za svakodnevne i kancelarijske prilike.', 2500.00, 1),
       (2, 'Elegantna Olovka Suknja', 'Elegantna olovka suknja pogodna za formalne prilike.', 3000.00, 1),
       (3, 'Naborana Suknja', 'Lepršava naborana suknja, savršena za letnje dane.', 2800.00, 1),
       (4, 'Poslovna Torba', 'Poslovna torba za sve potrebne stvari za posao.', 4000.00, 3),
       (5, 'Tote Torba Handmade', 'Rucno radena tote torba, idealna za svakodnevnu upotrebu.', 3500.00, 3),
       (6, 'Crossbody Torba', 'Stilizovana crossbody torba, udobna i moderna.', 3700.00, 3),
       (7, 'Elegantne Pantalone', 'Elegantne pantalone za kancelariju i svecane prilike.', 3200.00, 2),
       (8, 'Široke Pantalone', 'Široke pantalone za udobnost i svakodnevno nošenje.', 3000.00, 2),
       (9, 'Skracene Pantalone', 'Skracene pantalone, trendi i moderne.', 2800.00, 2);


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
