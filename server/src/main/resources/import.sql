-- Categorias de miniaturas de automobilismo
INSERT INTO tb_category (name) VALUES ('Formula 1');
INSERT INTO tb_category (name) VALUES ('IndyCar');
INSERT INTO tb_category (name) VALUES ('Endurance e WEC');
INSERT INTO tb_category (name) VALUES ('Rally');
INSERT INTO tb_category (name) VALUES ('Turismo');

-- Imagens de demonstração. Substitua os links por fotos licenciadas dos produtos reais antes da publicação.
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Ferrari SF-24 #16 - Charles Leclerc', 'Miniatura do monoposto Ferrari SF-24 da temporada 2024 da Formula 1.', 129.90, 'https://placehold.co/600x400/png?text=Ferrari+SF-24', 1);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('McLaren MCL38 #4 - Lando Norris', 'Miniatura do monoposto McLaren MCL38 da temporada 2024 da Formula 1.', 129.90, 'https://placehold.co/600x400/png?text=McLaren+MCL38', 1);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Mercedes-AMG F1 W15 #44 - Lewis Hamilton', 'Miniatura do monoposto Mercedes-AMG F1 W15 da temporada 2024 da Formula 1.', 129.90, 'https://placehold.co/600x400/png?text=Mercedes+W15', 1);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Red Bull RB20 #1 - Max Verstappen', 'Miniatura do monoposto Red Bull RB20 da temporada 2024 da Formula 1.', 139.90, 'https://placehold.co/600x400/png?text=Red+Bull+RB20', 1);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Dallara DW12 #2 - Josef Newgarden', 'Miniatura do IndyCar Dallara DW12 pilotado por Josef Newgarden.', 119.90, 'https://placehold.co/600x400/png?text=IndyCar+DW12', 2);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Dallara DW12 #10 - Alex Palou', 'Miniatura do IndyCar Dallara DW12 pilotado por Alex Palou.', 119.90, 'https://placehold.co/600x400/png?text=IndyCar+Palou', 2);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Porsche 963 #6 - Porsche Penske Motorsport', 'Miniatura do prototipo Porsche 963 da equipe Porsche Penske Motorsport.', 159.90, 'https://placehold.co/600x400/png?text=Porsche+963', 3);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Ferrari 499P #50 - Ferrari AF Corse', 'Miniatura do prototipo Ferrari 499P da equipe Ferrari AF Corse.', 159.90, 'https://placehold.co/600x400/png?text=Ferrari+499P', 3);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Toyota GR010 Hybrid #8 - Toyota Gazoo Racing', 'Miniatura do Hypercar Toyota GR010 Hybrid da Toyota Gazoo Racing.', 159.90, 'https://placehold.co/600x400/png?text=Toyota+GR010', 3);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Ford Puma Rally1 #8 - Ott Tanak', 'Miniatura do Ford Puma Rally1 pilotado por Ott Tanak no WRC.', 149.90, 'https://placehold.co/600x400/png?text=Ford+Puma+Rally1', 4);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Hyundai i20 N Rally1 #11 - Thierry Neuville', 'Miniatura do Hyundai i20 N Rally1 pilotado por Thierry Neuville no WRC.', 149.90, 'https://placehold.co/600x400/png?text=Hyundai+i20+Rally1', 4);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Toyota GR Yaris Rally1 #69 - Kalle Rovanpera', 'Miniatura do Toyota GR Yaris Rally1 pilotado por Kalle Rovanpera no WRC.', 149.90, 'https://placehold.co/600x400/png?text=Toyota+GR+Yaris', 4);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Chevrolet Camaro ZL1 #5 - Kyle Larson', 'Miniatura do Chevrolet Camaro ZL1 da NASCAR Cup Series pilotado por Kyle Larson.', 139.90, 'https://placehold.co/600x400/png?text=Camaro+NASCAR', 5);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('BMW M4 GT3 #46 - Team WRT', 'Miniatura do BMW M4 GT3 da equipe Team WRT.', 139.90, 'https://placehold.co/600x400/png?text=BMW+M4+GT3', 5);
INSERT INTO tb_products (name, description, price, url_imagem, category_id) VALUES
('Porsche 911 GT3 R #911 - Manthey EMA', 'Miniatura do Porsche 911 GT3 R da equipe Manthey EMA.', 139.90, 'https://placehold.co/600x400/png?text=Porsche+911+GT3+R', 5);

-- Conta de demonstração: username admin, senha P4ssword
INSERT INTO tb_user (username, email, password) VALUES
('admin', 'admin@example.com', '$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');
