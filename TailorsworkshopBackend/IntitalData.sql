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
INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_ID)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1);

-- MATERIAL → COTTON, POLYESTER, WOOL
INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_ID)
VALUES (5, 2),
       (6, 2),
       (7, 2);

-- SIZE → S, M, L, XL, XXL
INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_ID)
VALUES (8, 3),
       (9, 3),
       (10, 3),
       (11, 3),
       (12, 3);

-- LENGTH → NUMBER
INSERT INTO OPTION_TYPE_CHOICE (OPTION_TYPE_ID, OPTION_ID)
VALUES (13, 4);

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
INSERT INTO PRODUCT_OPTIONS (ID, PRODUCT_ID, OPTION_TYPE_ID)
VALUES
    -- All skirts (1–3) have COLOR, MATERIAL, SIZE, LENGTH
    (1, 1, 1),
    (2, 1, 2),
    (3, 1, 3),
    (4, 1, 4),
    (5, 2, 1),
    (6, 2, 2),
    (7, 2, 3),
    (8, 2, 4),
    (9, 3, 1),
    (10, 3, 2),
    (11, 3, 3),
    (12, 3, 4),

    -- Bags (4–6) have COLOR, MATERIAL, LENGTH
    (13, 4, 1),
    (14, 4, 2),
    (15, 4, 4),
    (16, 5, 1),
    (17, 5, 2),
    (18, 5, 4),
    (19, 6, 1),
    (20, 6, 2),
    (21, 6, 4),

    -- Pants (7–9) have COLOR, MATERIAL, SIZE, LENGTH
    (22, 7, 1),
    (23, 7, 2),
    (24, 7, 3),
    (25, 7, 4),
    (26, 8, 1),
    (27, 8, 2),
    (28, 8, 3),
    (29, 8, 4),
    (30, 9, 1),
    (31, 9, 2),
    (32, 9, 3),
    (33, 9, 4);