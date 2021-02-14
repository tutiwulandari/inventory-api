INSERT INTO unit (id, code, description, created_date) VALUES (1, 'kg', 'Kilogram', '2001-01-01');
INSERT INTO unit (id, code, description, created_date) VALUES (2, 'l', 'Liter', '2001-01-01');

INSERT INTO item (id, name, price, unit_id, created_date) VALUES (1, 'Air Mineral', 5000.0, 2, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (2, 'Beras',3000.0, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (3, 'Gula Pasir',1800.0, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (4, 'Gula Merah',10000.0, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (5, 'Garam Halus',5000.0, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (6, 'Tepung Terigu',8000.0, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (7, 'Bawang Merah',35000.0, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (8, 'Bawang Putih',3000.0, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (9, 'Cabai Merah Kering',40000.0, 1, '2001-01-01');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (10, 'Cabai Rawit Setan', 50000.0,2, '2001-01-01');


INSERT INTO stock (item_id, quantity, created_date) VALUES (1, 200, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (2, 350, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (3, 75, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (4, 100, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (5, 250, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (6, 250, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (7, 120, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (8, 100, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (9, 30, '2020-01-01');
INSERT INTO stock (item_id, quantity, created_date) VALUES (10, 50, '2020-01-01');
