INSERT INTO categoria (nombre, descripcion, activo) VALUES
    ('Electronica', 'Dispositivos y gadgets electronicos', TRUE),
    ('Hogar', 'Articulos para el hogar', TRUE),
    ('Ropa', 'Prendas de vestir', TRUE);

INSERT INTO producto (nombre, descripcion, precio, stock, activo, categoria_id) VALUES
    ('Audifonos Bluetooth', 'Audifonos inalambricos con cancelacion de ruido', 899.00, 25, TRUE, 1),
    ('Lampara LED', 'Lampara de escritorio regulable', 349.50, 40, TRUE, 2),
    ('Playera basica', 'Playera de algodon 100%', 199.00, 100, TRUE, 3);
