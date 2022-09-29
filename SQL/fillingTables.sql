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
(26, 'Рулевое управление M Sport');

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
(8, 'бензин, 7,0/100км', 'M20A-FKS', 160);
(9, 'бензин, 7,5/100км', '3ZR-FAE', 150);
(10, 'бензин, 9,0/100км', '8AR-FTS турбо', 235);
(11, 'бензин, 7,9/100км', 'A25A-FKS', 200);
(12, 'дизель, 9,7/100км', 'F33A-FTV турбо', 305);
(13, 'бензин, 9,5/100км', '2AR-FE', 180);
(14, 'бензин, 6,4/100км', 'MPI 90', 90);
(15, 'бензин, 6,4/100км', 'MPI 110', 110);
(16, 'бензин, 6,0/100км', 'TSI 125', 125);
(17, 'бензин, 7,5/100км', 'TSI 4 MOTION 150', 150);
(18, 'бензин, 8,6/100км', 'TSI 4 MOTION 180', 180);
(19, 'бензин, 9,2/100км', 'TSI 4 MOTION 220', 220);
(20, 'бензин, 7,5/100км', 'TSI 150', 150);
(21, 'дизель, 7,1/100км', 'V6 TDI 4Motion 249', 249);

/* todo --------------filling in the table "model"---------------------*/
INSERT INTO model 
(id, name, brand_id, price) VALUES 
(1, 'X1', 1, 3280000);
(2, 'X2', 1, 3520000);
(3, 'X3', 1, 5320000);
(4, 'X4', 1, 5700000);
(5, 'X5', 1, 7190000);
(6, 'X6', 1, 8400000);
(7, 'X7', 1, 9350000);
(8, '2 серии Coupe', 1, 3970000);
(9, '3 серии', 1, 3930000);
(10, 'UX', 2, 3286000);
(11, 'NX', 2, 4853000);
(12, 'RX', 2, 5348000);
(13, 'RX L', 2, 6730000);
(14, 'GX', 2, 7525000);
(15, 'LX', 2, 10017000);
(16, 'ES', 2, 4314000);
(17, 'LS', 2, 9682000);
(18, 'Corolla', 3, 1955000);
(19, 'Camry', 3, 2498000);
(20, 'RAV4', 3, 2671000);
(21, 'Land Cruiser Prado', 3, 3951000);
(22, 'Polo', 4, 1620900);
(23, 'Tiguan', 4, 3375000);
(24, 'Touareg', 4, 8669000);

/* todo --------------filling in the table "model_complectation"---------------------*/
INSERT INTO model_complectation 
(model_id, comp_id) VALUES 
(1, 1);
(1, 2);
(1, 3);
(1, 4);
(1, 5);
(1, 6);

/* todo --------------filling in the table "accessory_model_complect"---------------------*/
INSERT INTO accessory_model_complect 
(id, access_id, model_id, comp_id, price) VALUES 
(1, 26, 1, 1, 23500);


/* todo --------------filling in the table "color_model_complect"---------------------*/
INSERT INTO color_model_complect 
(id, color_id, model_id, comp_id, price) VALUES 
(1, 1, 1, 1, 0);


/* todo --------------filling in the table "engine_model_complect"---------------------*/
INSERT INTO engine_model_complect 
(id, engine_id, model_id, comp_id, price) VALUES 
(1, 1, 1, 1, 0);



/* todo --------------filling in the table "transmissoin"---------------------*/
INSERT INTO transmissoin
(id, name, description) VALUES 
(1, '4WDА8', 'Полный привод, АКПП 8');
(2, 'FFА8', 'Передний привод, АКПП 8');
(3, 'FRА8', 'Задний привод, АКПП 8');
(4, '4WDM6', 'Полный привод, МКПП 6');
(5, 'FFM6', 'Передний привод, МКПП 6');
(6, 'FRM6', 'Задний привод, МКПП 6');

/* todo --------------filling in the table "trans_model_complect"---------------------*/
INSERT INTO trans_model_complect
(id,  trans_id, model_id, comp_id, price) VALUES 
(1, 3, 1, 1, 0);
