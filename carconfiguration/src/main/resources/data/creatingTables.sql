CREATE TABLE accessory
(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    name 	VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE brand(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    name 	VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE color
(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    name 	VARCHAR(35) NOT NULL UNIQUE
);

CREATE TABLE complectation
(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    name 	VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE engine
(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    description TEXT(2048),
    name 	VARCHAR(50) NOT NULL UNIQUE,
    power 	INT NOT NULL
);

CREATE TABLE model
(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    name 	VARCHAR(50) NOT NULL UNIQUE,
    brand_id 	BIGINT NOT NULL,
    price 	INT NOT NULL,
    CONSTRAINT model_brand_id_foreign 
	FOREIGN KEY (brand_id) REFERENCES brand (id)
);

CREATE TABLE model_complectation
(
    model_id 	BIGINT NOT NULL,
    comp_id 	BIGINT NOT NULL,
    PRIMARY KEY (model_id, comp_id),
    CONSTRAINT model_complectation_model_id_foreign 
	FOREIGN KEY (model_id) REFERENCES model (id),
    CONSTRAINT model_complectation_comp_id_foreign 
	FOREIGN KEY (comp_id) REFERENCES complectation (id)
);

CREATE TABLE accessory_model_complect
(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    access_id 	BIGINT NOT NULL,
    model_id 	BIGINT NOT NULL,
    comp_id 	BIGINT NOT NULL,
    price 	INT NOT NULL,
    CONSTRAINT accessory_model_complect_access_id_foreign 
	FOREIGN KEY (access_id) REFERENCES accessory (id),
    CONSTRAINT model_complectation_accessory_foreign 
	FOREIGN KEY (model_id, comp_id) REFERENCES model_complectation (model_id, comp_id)
);

CREATE TABLE color_model_complect
(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    color_id 	BIGINT NOT NULL,
    model_id 	BIGINT NOT NULL,
    comp_id 	BIGINT NOT NULL,
    price 	INT NOT NULL,
    CONSTRAINT color_model_complect_color_id_foreign 
	FOREIGN KEY (color_id) REFERENCES color (id),
    CONSTRAINT model_complectation_color_foreign 
	FOREIGN KEY (model_id, comp_id) REFERENCES model_complectation (model_id, comp_id)
);

CREATE TABLE engine_model_complect
(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    engine_id 	BIGINT NOT NULL,
    model_id 	BIGINT NOT NULL,
    comp_id 	BIGINT NOT NULL,
    price 	INT NOT NULL,
    CONSTRAINT engine_model_complect_engine_id_foreign 
	FOREIGN KEY (engine_id) REFERENCES engine (id),
    CONSTRAINT model_complectation_engine_foreign 
	FOREIGN KEY (model_id, comp_id) REFERENCES model_complectation (model_id, comp_id)
);

CREATE TABLE role(
    id 		INT AUTO_INCREMENT PRIMARY KEY,
    name 	VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE user(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    email 	VARCHAR(50) NOT NULL UNIQUE,
    name 	VARCHAR(35) NOT NULL,
    password 	VARCHAR(50) NOT NULL,
    role_id 	INT NOT NULL,
    CONSTRAINT user_role_id_foreign 
	FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE transmissoin(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    name 	VARCHAR(50) NOT NULL UNIQUE,
    description TEXT(2048)
);

CREATE TABLE trans_model_complect(
    id 		BIGINT AUTO_INCREMENT PRIMARY KEY,
    trans_id 	BIGINT NOT NULL,
    model_id 	BIGINT NOT NULL,
    comp_id 	BIGINT NOT NULL,
    price 	INT NOT NULL,
    CONSTRAINT trans_model_complect_trans_id_foreign 
	FOREIGN KEY (trans_id) REFERENCES transmissoin (id),
    CONSTRAINT model_complectation_trans_foreign 
	FOREIGN KEY (model_id, comp_id) REFERENCES model_complectation (model_id, comp_id)
);

ALTER TABLE
    model_complectation ADD INDEX model_complect_model_id_comp_id_index (model_id, comp_id);