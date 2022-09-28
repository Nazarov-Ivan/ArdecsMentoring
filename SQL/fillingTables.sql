/* todo --------------filling in the table "role"---------------------*/
INSERT INTO role 
(id, name) VALUES 
(1, 'Admin'), 
(2, 'User');


/* todo --------------filling in the table "user"---------------------*/
INSERT INTO user 
(email, name, password, role_id) VALUES 
('alex@mail.ru', 'Alex', '7h9tRdrLVk9Wr8W', 1), 
('max@gmail.com', 'Max', 'oNFVN2hwmE0IA9l', 2);


/* todo --------------filling in the table "accessory"---------------------*/
INSERT INTO accessory 
(id, name) VALUES 
(1, 'спойлер');
(2, 'хромированная накладка');
(3, 'бокс для груза');
(4, 'крепление для велосипеда');
(5, 'поперечены для багажника');
(6, 'дефлекторы на окна');
(7, 'фаркоп съемный');
(8, 'защита для дисков');
(9, 'защита для кузова');
(10, 'защитная пленка');
(11, 'комплект брызговиков');
(12, 'молдинги для дверей');
(13, 'секретные колесные гайки');
(14, 'навигационный блок');
(15, 'пепельница');
(16, 'комплект резиновых ковриков');
(17, 'комплект текстильных ковриков');
(18, 'коврик багажника');
(19, 'ремонтный комплект шины');
(20, 'легкосплавные диски 16"');
(21, 'легкосплавные диски 17"');
(22, 'легкосплавные диски 18"');
(23, 'легкосплавные диски 19"');
(24, 'легкосплавные диски 20"');
(25, 'легкосплавные диски 21"');

/* todo --------------filling in the table "brand"---------------------*/
INSERT INTO brand 
(id, name) VALUES 
(1, 'BMW');
(2, 'Lexus');
(3, 'Toyota');
(4, 'Volkswagen');

/* todo --------------filling in the table "color"---------------------*/
INSERT INTO color 
(id, name) VALUES
(1, 'черный');
(2, 'белый');
(3, 'синий');
(4, 'красный');
(5, 'зеленый');
(6, 'желтый');
(7, 'серый');

/* todo --------------filling in the table "complectation"---------------------*/
INSERT INTO complectation 
(id, name) VALUES 
(1, 'Base');
(2, 'Advantage');
(3, 'M Sport Edition');
(4, 'Advantage Plus');
(5, 'Sport Line');
(6, 'M Sport');
(7, 'M Sport X');
(8, 'Lifestyle');
(9, 'M Sport Pure');
(10, 'M Special');
(11, 'Business Plus');
(12, 'Luxury');
(13, 'Premium');
(14, 'Comfort');
(15, 'Respect');
(16, 'Status');
(17, 'Exclusive');


/* todo --------------filling in the table "engine"---------------------*/
INSERT INTO engine 
(id, description, name, power) VALUES 
(1, 'бензин, 6,2л/100км', 'BMW sDrive18i', 140);
(2, 'бензин, 7,1л/100км', 'BMW xDrive20i', 192);
(3, 'дизель, 5,4л/100км', 'BMW xDrive18d', 150);
(4, 'бензин, 7,9л/100км', 'BMW xDrive30i', 249);
(5, 'бензин, 8,9л/100км', 'BMW M40i', 387);
(6, 'дизель, 5,6л/100км', 'BMW xDrive20d', 190);
(7, 'дизель, 6,4л/100км', 'BMW xDrive30d', 249);

/* todo --------------filling in the table "model"---------------------*/
INSERT INTO model 
(id, name, brand_id, price) VALUES 
(1, '', , 2300000);

/* todo --------------filling in the table "model_complectation"---------------------*/
INSERT INTO model_complectation 
(model_id, comp_id) VALUES 
();

/* todo --------------filling in the table "accessory_model_complect"---------------------*/
INSERT INTO accessory_model_complect 
(id, access_id, model_id, comp_id, price) VALUES 
();


/* todo --------------filling in the table "color_model_complect"---------------------*/
INSERT INTO color_model_complect 
(id, color_id, model_id, comp_id, price) VALUES 
();


/* todo --------------filling in the table "engine_model_complect"---------------------*/
INSERT INTO engine_model_complect 
(id, engine_id, model_id, comp_id, price) VALUES 
();


/* todo --------------filling in the table "transmissoin"---------------------*/
INSERT INTO transmissoin
(id, name, description) VALUES 
();

/* todo --------------filling in the table "trans_model_complect"---------------------*/
INSERT INTO trans_model_complect
(id, model_id, comp_id, price) VALUES 
();
