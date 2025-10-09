INSERT INTO CATEGORIES (ID, NAME)
VALUES (1, 'SKIRT'),
       (2, 'PANTS'),
       (3, 'BAG');

-- Subtypes
INSERT INTO SUBTYPES (ID, NAME)
VALUES (1, 'A-line suknja'),
       (2, 'Pencil suknja'),
       (3, 'Naborana suknja'),
       (4, 'Poslovna torba'),
       (5, 'Tote ceger'),
       (6, 'Crossbody torba'),
       (7, 'Elegantne pantalone'),
       (8, 'Široke pantalone'),
       (9, 'Skracene pantalone');

-- Categories and Subtypes
INSERT INTO CATEGORIES_AND_SUBTYPES (ID, CATEGORY_ID, SUBTYPE_ID)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 3, 4),
       (5, 3, 5),
       (6, 3, 6),
       (7, 2, 7),
       (8, 2, 8),
       (9, 2, 9);

-- Products
INSERT INTO PRODUCTS (ID, NAME, DESCRIPTION, PRICE, CATEGORY_SUBTYPE_ID)
VALUES (1, 'A-linija Suknja', 'Šik A-linija suknje, idealna za svakodnevne i kancelarijske prilike.', 2500.00, 1),
       (2, 'Elegantna Olovka Suknja', 'Elegantna olovka suknja pogodna za formalne prilike.', 3000.00, 2),
       (3, 'Naborana Suknja', 'Lepršava naborana suknja, savršena za letnje dane.', 2800.00, 3),
       (4, 'Poslovna Torba', 'Poslovna torba za sve potrebne stvari za posao.', 4000.00, 4),
       (5, 'Tote Torba Handmade', 'Rucno radena tote torba, idealna za svakodnevnu upotrebu.', 3500.00, 5),
       (6, 'Crossbody Torba', 'Stilizovana crossbody torba, udobna i moderna.', 3700.00, 6),
       (7, 'Elegantne Pantalone', 'Elegantne pantalone za kancelariju i svecane prilike.', 3200.00, 7),
       (8, 'Široke Pantalone', 'Široke pantalone za udobnost i svakodnevno nošenje.', 3000.00, 8),
       (9, 'Skracene Pantalone', 'Skracene pantalone, trendi i moderne.', 2800.00, 9);
