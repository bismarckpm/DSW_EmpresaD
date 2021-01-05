-- -----------------------------------------------------
-- Table `mercadeoucab`.`categoria`
-- -----------------------------------------------------

insert categoria values(1,"Muebles de dormitorio",1,"2019-07-23",null);
insert categoria values(2,"Maquillaje",1,"2019-07-23",null);
insert categoria values(3,"Electronicos",1,"2019-07-23",null);
insert categoria values(4,"Utiles escolares",1,"2019-07-23",null);
insert categoria values(5,"Vestimenta",1,"2019-07-23",null);
insert categoria values(6,"Libros",1,"2019-07-23",null);
insert categoria values(7,"Salud",1,"2019-07-23",null);
insert categoria values(8,"Limpieza",1,"2019-07-23",null); 


-- -----------------------------------------------------
-- Table `mercadeoucab`.`pais`
-- -----------------------------------------------------

insert pais values(1,"Venezuela",1,'2019-07-23',null);
insert pais values(2,"Argentina",1,'2019-07-23',null);
insert pais values(3,"Colombia",1,'2019-07-23',null);
insert pais values(4,"Francia",1,'2019-07-23',null);
insert pais values(5,"Brazil",1,'2019-07-23',null);
insert pais values(6,"Canada",1,'2019-07-23',null);
insert pais values(7,"Estados Unidos",1,'2019-07-23',null);
insert pais values(8,"Inglaterra",1,'2019-07-23',null);
insert pais values(9,"España",1,'2019-07-23',null);
insert pais values(10,"Uruguay",1,'2019-07-23',null);


-- -----------------------------------------------------
-- Table `mercadeoucab`.`estado`
-- -----------------------------------------------------

insert estado values(1,"Amazonas",1,'2019-07-23',null,1);
insert estado values(2,"Apure",1,'2019-07-23',null,1);
insert estado values(3,"Buenos Aires",1,'2019-07-23',null,2);
insert estado values(4,"Santa Fe",1,'2019-07-23',null,2); 
insert estado values(5,"Medellín",1,'2019-07-23',null,3);
insert estado values(6,"Pasto",1,'2019-07-23',null,3); 
insert estado values(7,"Breteña",1,'2019-07-23',null,4);
insert estado values(8,"Normandia",1,'2019-07-23',null,4); 
insert estado values(9,"Alagoas",1,'2019-07-23',null,5);
insert estado values(10,"Para",1,'2019-07-23',null,5); 
insert estado values(11,"Columbia Britanica",1,'2019-07-23',null,6);
insert estado values(12,"Ontario",1,'2019-07-23',null,6);
insert estado values(13,"Texas",1,'2019-07-23',null,7);
insert estado values(14,"California",1,'2019-07-23',null,7);  
insert estado values(15,"Devon",1,'2019-07-23',null,8);
insert estado values(16,"Dorset",1,'2019-07-23',null,8);  
insert estado values(17,"Malaga",1,'2019-07-23',null,9);
insert estado values(18,"Cadiz",1,'2019-07-23',null,9);
insert estado values(19,"Rocha",1,'2019-07-23',null,10);
insert estado values(20,"Soriano",1,'2019-07-23',null,10);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`municipio`
-- -----------------------------------------------------
insert municipio values(1,"Manaos",1,'2019-07-23',null,1);
insert municipio values(2,"La Nula",1,'2019-07-23',null,2);
insert municipio values(3,"Avellaneda",1,'2019-07-23',null,3);
insert municipio values(4,"San Lorenzo",1,'2019-07-23',null,4);
insert municipio values(5,"Aranjuez",1,'2019-07-23',null,5);
insert municipio values(6,"Consacá",1,'2019-07-23',null,6);
insert municipio values(7,"Le Faou",1,'2019-07-23',null,7);
insert municipio values(8,"Orne",1,'2019-07-23',null,8);
insert municipio values(9,"Penedo",1,'2019-07-23',null,9);
insert municipio values(10,"Averio",1,'2019-07-23',null,10);
insert municipio values(11,"Victoria",1,'2019-07-23',null,11);
insert municipio values(12,"Toronto",1,'2019-07-23',null,12);
insert municipio values(13,"Dallas",1,'2019-07-23',null,13);
insert municipio values(14,"Fresno",1,'2019-07-23',null,14);
insert municipio values(15,"Exeter",1,'2019-07-23',null,15);
insert municipio values(16,"Powerstock",1,'2019-07-23',null,16);
insert municipio values(17,"Vélez-Málaga",1,'2019-07-23',null,17);
insert municipio values(18,"Algeciras",1,'2019-07-23',null,18);
insert municipio values(19,"La Paloma",1,'2019-07-23',null,19);
insert municipio values(20,"Dolores",1,'2019-07-23',null,20);


-- -----------------------------------------------------
-- Table `mercadeoucab`.`parroquia`
-- -----------------------------------------------------
/*`id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `valor_socio_economico` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_municipio` INT NOT NULL,
*/  
insert parroquia values(1,"San Camilo",1,1,'2019-07-23',null,1);
insert parroquia values(2,"Parroquia Nuestra Senora Del Carmen",1,1,'2019-07-23',null,2);
insert parroquia values(3,"Parroquia San Lorenzo",1,1,'2019-07-23',null,3);
insert parroquia values(4,"Parroquia Nuestra Señora de las Angustias",1,1,'2019-07-23',null,4);
insert parroquia values(5,"Parroquia Nuestra Señora Del Tránsito",1,1,'2019-07-23',null,5);
insert parroquia values(6,"Eglise Notre Dame De Rumengol",1,1,'2019-07-23',null,6);
insert parroquia values(7,"Parroquia Saint-Luc du Val d'Orne",1,1,'2019-07-23',null,7);
insert parroquia values(8,"Iglesia de San Francisco de Asís",1,1,'2019-07-23',null,8);
insert parroquia values(9,"Grace Bible Church",1,1,'2019-07-23',null,9);
insert parroquia values(10,"Christ the King Mission",1,1,'2019-07-23',null,10);
insert parroquia values(11,"San Sebastian",1,1,'2019-07-23',null,11);
insert parroquia values(12,"Parroquia Nuestra Jose Obrero",1,1,'2019-07-23',null,12);
insert parroquia values(13,"Parroquia San Cristo",1,1,'2019-07-23',null,13);
insert parroquia values(14,"Parroquia Nuestra Señora del Valle",1,1,'2019-07-23',null,14);
insert parroquia values(15,"Parroquia Nuestra Señora Del Socorro",1,1,'2019-07-23',null,15);
insert parroquia values(16,"Eglise Notre",1,1,'2019-07-23',null,16);
insert parroquia values(17,"Parroquia Salvador",1,1,'2019-07-23',null,17);
insert parroquia values(18,"Iglesia de San Francisco",1,1,'2019-07-23',null,18);
insert parroquia values(19,"Los palomares",1,1,'2019-07-23',null,19);
insert parroquia values(20,"Christ",1,1,'2019-07-23',null,20);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`usuario`
-- -----------------------------------------------------  
-- Encuestados
  insert usuario values (1,"Antonio","Nohra","atag102@gmail.com","encuestado","activo",1,'2019-07-23',null);
  insert usuario values (2,"Zeus","Berg","ZB@gmail.com","encuestado","activo",1,"2019-07-23",null);
  insert usuario values (3,"Addison","Gomez","ADDG@gmail.com","encuestado","activo",1,"2019-07-23",null);
  insert usuario values (4,"Jamal","Stevenson","jamal_Stevenson@gmail.com","encuestado","activo",1,"2019-07-23",null);
  insert usuario values (5,"Elmo","Rose","Elmo@gmail.com","encuestado","activo",1,"2019-07-23",null);
  insert into usuario values (6, 'Constanta', 'Bilson', 'cbilson0@marriott.com', 'encuestado', 'activo', 1, '2020-10-19', null);
  insert into usuario values (7, 'Rikki', 'Jannequin', 'rjannequin1@theguardian.com', 'encuestado', 'activo', 1, '2020-03-26', null);
  insert into usuario values (8, 'Donia', 'Mulgrew', 'dmulgrew2@cpanel.net', 'encuestado', 'activo', 1, '2020-10-18', null);
  insert into usuario values (9, 'Kirby', 'Duck', 'kduck3@chicagotribune.com', 'encuestado', 'activo', 1, '2020-04-27', null);
  insert into usuario values (10, 'Anderson', 'Reason', 'areason4@linkedin.com', 'encuestado', 'activo', 1, '2020-07-07', null);
  insert into usuario values (11, 'Addie', 'Norkutt', 'anorkutt5@youku.com', 'encuestado', 'activo', 1, '2020-05-27', null);
  insert into usuario values (12, 'Celene', 'Goodison', 'cgoodison6@plala.or.jp', 'encuestado', 'activo', 1, '2020-03-01', null);
  insert into usuario values (13, 'Hildegaard', 'Pettit', 'hpettit7@walmart.com', 'encuestado', 'activo', 1, '2020-07-22', null);
  insert into usuario values (14, 'Lesly', 'Vasishchev', 'lvasishchev8@usnews.com', 'encuestado', 'activo', 1, '2020-09-04', null);
  insert into usuario values (15, 'Vivienne', 'Dunsford', 'vdunsford9@businesswire.com', 'encuestado', 'activo', 1, '2020-10-12', null);
  insert into usuario values (16, 'Ailene', 'Maggs', 'amaggsa@ted.com', 'encuestado', 'activo', 1, '2020-01-06', null);
  insert into usuario values (17, 'Leonanie', 'Keeler', 'lkeelerb@pbs.org', 'encuestado', 'activo', 1, '2020-12-04', null);
  insert into usuario values (18, 'Orion', 'Gagan', 'ogaganc@ebay.com', 'encuestado', 'activo', 1, '2020-11-03', null);
  insert into usuario values (19, 'Samuele', 'Robertsen', 'srobertsend@bbc.co.uk', 'encuestado', 'activo', 1, '2020-04-29', null);
  insert into usuario values (20, 'Vite', 'Holcroft', 'vholcrofte@google.ru', 'encuestado', 'activo', 1, '2020-06-16', null);
  insert into usuario values (21, 'Jeffrey', 'Tudball', 'jtudballf@cdbaby.com', 'encuestado', 'activo', 1, '2020-10-14', null);
  insert into usuario values (22, 'Sebastian', 'Riddiough', 'sriddioughg@surveymonkey.com','encuestado', 'activo', 1, '2020-09-25', null);
  insert into usuario values (23, 'Merci', 'Menendez', 'mmenendezh@opera.com', 'encuestado', 'activo', 1, '2020-11-13', null);
  insert into usuario values (24, 'Lothaire', 'Rohfsen', 'lrohfseni@usa.gov', 'encuestado', 'activo', 1, '2020-06-03', null);
  insert into usuario values (25, 'Annamarie', 'Beckley', 'abeckleyj@ihg.com', 'encuestado', 'activo', 1, '2020-10-25', null);

-- administradores
  insert usuario values (26,"Macon","Mcleod","MM10@gmail.com","administrador","activo",1,'2019-07-23',null);
  insert usuario values (27,"Warren","Torres","WARREN@gmail.com","administrador","activo",1,"2019-07-23",null);
  insert usuario values (28,"Jonas","Mccray","JON_M@gmail.com","administrador","activo",1,"2019-07-23",null);
  insert usuario values (29,"Barclay","Holt","HOLT10@gmail.com","administrador","activo",1,"2019-07-23",null);
  insert usuario values (30,"Wyatt","Jackson","Wtt@gmail.com","administrador","activo",1,"2019-07-23",null);
  
  -- clientes
  insert usuario values (31,"Caesar","Mosley","CM10@gmail.com","cliente","activo",1,'2019-07-23',null);
  insert usuario values (32,"Brent","Luna","Bluna@gmail.com","cliente","activo",1,"2019-07-23",null);
  insert usuario values (33,"Victor","Paul","V1@gmail.com","cliente","activo",1,"2019-07-23",null);
  insert usuario values (34,"Kasper","Whitaker","KW@gmail.com","cliente","activo",1,"2019-07-23",null);
  insert usuario values (35,"Solomon","Bentley","B123@gmail.com","cliente","activo",1,"2019-07-23",null);
  
  -- analistas
  insert usuario values (36,"Harper","Vance","Harper20@gmail.com","analista","activo",1,'2019-07-23',null);
  insert usuario values (37,"Harrison","Dorsey","HARRI@gmail.com","analista","activo",1,"2019-07-23",null);
  insert usuario values (38,"Nehru","Winters","NEHR@gmail.com","analista","activo",1,"2019-07-23",null);
  insert usuario values (39,"Reese","Dillon","R_D@gmail.com","analista","activo",1,"2019-07-23",null);
  insert usuario values (40,"Hammett","Schneider","HMLETSCH456@gmail.com","analista","activo",1,"2020-07-23",null);
  
  -- -----------------------------------------------------
-- Table `mercadeoucab`.`ocupacion`
-- -----------------------------------------------------

insert ocupacion values(1,"Ingeniero informatico",1,"2019-05-23",null);
insert ocupacion values(2,"Mecanico",1,"2019-05-23",null);
insert ocupacion values(3,"Cocinero",1,"2019-05-23",null);
insert ocupacion values(4,"Comerciante",1,"2019-05-23",null);
insert ocupacion values(5,"Policia",1,"2019-05-23",null);
insert ocupacion values(6,"Arquitecto",1,"2019-05-23",null);
insert ocupacion values(7,"Contador",1,"2019-05-23",null);
insert ocupacion values(8,"Actor",1,"2019-05-23",null);
insert ocupacion values(9,"Panadero",1,"2019-05-23",null);
insert ocupacion values(10,"Carpintero",1,"2019-05-23",null);
insert ocupacion values(11,"Doctor",1,"2019-05-23",null);
insert ocupacion values(12,"Pescador",1,"2019-05-23",null);
insert ocupacion values(13,"Abogado",1,"2019-05-23",null);
-- -----------------------------------------------------
-- Table `mercadeoucab`.`dato_encuestado`
-- -----------------------------------------------------

  insert dato_encuestado values (1,null,"Hooper","1","laptop","1997-02-23","masculino",1,"Licenciado",1,1,'2019-07-23',null,1,1,1);
  insert dato_encuestado values (2,null,"Harmon","2","laptop","1997-03-19","masculino",1,"Licenciado",1,1,"2019-07-23",null,2,1,1);
  insert dato_encuestado values (3,null,"Petty","3","laptop","1997-04-18","masculino",1,"Licenciado",1,1,"2019-07-23",null,3,1,1);
  insert dato_encuestado values (4,null,"Schultz","4","laptop","1997-05-14","masculino",1,"Licenciado",1,1,"2019-07-23",null,4,1,1);
  insert dato_encuestado values (5,null,"Coffey","5","laptop","1997-06-10","masculino",1,"Licenciado",1,1,"2019-07-23",null,5,1,1);

  insert dato_encuestado values (6,null,"Mccall","6","pc","1997-07-06","masculino",1,"Magister",1,1,'2019-07-23',null,6,1,2);
  insert dato_encuestado values (7,null,"Rosales","7","pc","1997-08-15","masculino",1,"Magister",1,1,"2019-07-23",null,7,1,2);
  insert dato_encuestado values (8,null,"Harris","8","pc","1997-09-21","masculino",1,"Magister",1,1,"2019-07-23",null,8,1,2);
  insert dato_encuestado values (9,null,"Saunders","9","pc","1997-10-20","masculino",1,"Magister",1,1,"2019-07-23",null,9,1,2);
  insert dato_encuestado values (10,null,"Benson","10","pc","1997-11-19","masculino",1,"Magister",1,1,"2019-07-23",null,10,1,2);
  insert dato_encuestado values (11,null,"Marks","11","tableta","1997-12-16","masculino",1,"Magister",1,1,'2019-07-23',null,11,1,2);

  insert dato_encuestado values (12,null,"Dean","12","tableta","2000-02-13","femenino",1,"Doctorado",1,1,"2019-07-23",null,12,1,3);
  insert dato_encuestado values (13,null,"Quinn","13","tableta","2000-02-11","femenino",1,"Doctorado",1,1,"2019-07-23",null,13,1,3);
  insert dato_encuestado values (14,null,"Harding","14","tableta","2000-03-10","femenino",1,"Doctorado",1,1,"2019-07-23",null,14,1,3);
  insert dato_encuestado values (15,null,"Carver","15","tableta","2000-04-09","femenino",1,"Doctorado",1,1,"2019-07-23",null,15,1,3);  

  insert dato_encuestado values (16,null,"Bean","16","telefono","1980-05-08","femenino",1,"Bachiller",1,1,'2019-07-23',null,16,1,5);
  insert dato_encuestado values (17,null,"Wiley","17","telefono","1985-06-13","femenino",1,"Bachiller",1,1,"2019-07-23",null,17,1,5);
  insert dato_encuestado values (18,null,"Livingston","18","telefono","1986-07-11","femenino",1,"Bachiller",1,1,"2019-07-23",null,18,1,5);
  insert dato_encuestado values (19,null,"David","19","telefono","1987-08-21","femenino",1,"Bachiller",1,1,"2019-07-23",null,19,1,5);
  insert dato_encuestado values (20,null,"Snyder","20","telefono","1988-09-22","femenino",1,"Bachiller",1,1,"2019-07-23",null,20,1,5);
  insert dato_encuestado values (21,null,"nyder","21","telefono","1988-09-22","femenino",1,"Bachiller",1,1,"2019-07-23",null,21,1,5);

  insert dato_encuestado values (22,null,"Snder","22","telefono","1988-09-22","femenino",1,"Bachiller",1,1,"2019-07-23",null,22,1,4);
  insert dato_encuestado values (23,null,"Snyer","23","telefono","1988-09-22","femenino",1,"Bachiller",1,1,"2019-07-23",null,23,1,4);
  insert dato_encuestado values (24,null,"Snyde","24","telefono","1988-09-22","femenino",1,"Bachiller",1,1,"2019-07-23",null,24,1,4);
  insert dato_encuestado values (25,null,"Snyer","25","telefono","1988-09-22","femenino",1,"Bachiller",1,1,"2019-07-23",null,25,1 ,4);

    
    
-- -----------------------------------------------------
-- Table `mercadeoucab`.`muestra_poblacion`
-- -----------------------------------------------------
    
insert muestra_poblacion values (1,"masculino",1,"Licenciado",15,80,1,1,"2020-07-23",null,1,1);
insert muestra_poblacion values (2,"masculino",1,"Licenciado",15,80,1,1,"2020-07-23",null,1,1);

insert muestra_poblacion values (3,"masculino",1,"Magister",16,80,1,1,"2020-07-23",null,1,2);
insert muestra_poblacion values (4,"masculino",1,"Magister",16,80,1,1,"2020-07-23",null,1,2);

insert muestra_poblacion values (5,"femenino",1,"Doctorado",18,80,1,1,"2020-07-23",null,1,3);
insert muestra_poblacion values (6,"femenino",1,"Doctorado",19,80,1,1,"2020-07-23",null,1,3);

insert muestra_poblacion values (7,"femenino",1,"Bachiller",19,80,1,1,"2020-07-23",null,1,5);
insert muestra_poblacion values (8,"femenino",1,"Bachiller",19,80,1,1,"2020-07-23",null,1,4);
-- -----------------------------------------------------
-- Table `mercadeoucab`.`marca`
-- -----------------------------------------------------
/*
insert categoria values(1,"Muebles de dormitorio",1,"2020-07-23",null);
insert categoria values(2,"Maquillaje",1,"2020-07-23",null);
insert categoria values(3,"Electronicos",1,"2020-07-23",null);
insert categoria values(4,"Utiles escolares",1,"2020-07-23",null);
insert categoria values(5,"Vestimenta",1,"2020-07-23",null);
insert categoria values(6,"Libros",1,"2020-07-23",null);
insert categoria values(7,"Salud",1,"2020-07-23",null);
insert categoria values(8,"Limpieza",1,"2020-07-23",null); 
*/

insert marca values(1,"Matteograssi",1,"2020-07-23",null);
insert marca values(2,"Treku",1,"2020-07-23",null);
insert marca values(3,"MAC Cosmetics",1,"2020-07-23",null);
insert marca values(4,"Clinique",1,"2020-07-23",null);
insert marca values(5,"GENIUS",1,"2020-07-23",null);
insert marca values(6,"Midland",1,"2020-07-23",null);
insert marca values(7,"Faber Castell",1,"2020-07-23",null);
insert marca values(8,"Staedtler",1,"2020-07-23",null);
insert marca values(9,"Christian Dior",1,"2020-07-23",null);
insert marca values(10,"Ferragamo",1,"2020-07-23",null);
insert marca values(11,"Anagrama",1,"2020-07-23",null);
insert marca values(12,"Aguilar",1,"2020-07-23",null);
insert marca values(13,"A-DERMA",1,"2020-07-23",null);
insert marca values(14,"BIRETIX",1,"2020-07-23",null);
insert marca values(15,"Frosch",1,"2020-07-23",null);
insert marca values(16,"Seistan",1,"2020-07-23",null);


-- -----------------------------------------------------
-- Table `mercadeoucab`.`solicitud`
-- -----------------------------------------------------

insert solicitud values(1,"solicitada",1,"2020-08-10",null,31,9);-- vestimenta --
insert solicitud values(2,"solicitada",1,"2020-08-10",null,32,15);-- Limpieza --
insert solicitud values(3,"solicitada",1,"2020-08-10",null,33,16);-- Limpieza --
insert solicitud values(4,"solicitada",1,"2020-08-10",null,34,10);-- vestimenta --
insert solicitud values(5,"solicitada",1,"2020-08-11",null,35,9);-- vestimenta --
insert solicitud values(6,"solicitada",1,"2020-08-11",null,31,9);
insert solicitud values(7,"solicitada",1,"2020-08-11",null,32,15);
insert solicitud values(8,"solicitada",1,"2020-08-11",null,33,16);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`estudio`
-- -----------------------------------------------------


insert estudio values(1,"En ejecucion","En linea",1,1,"2021-08-10",null,1,36,1);
insert estudio values(2,"En ejecucion","En linea",1,1,"2021-08-10",null,2,37,2);
insert estudio values(3,"En ejecucion","En linea",1,1,"2021-08-10",null,3,38,3);
insert estudio values(4,"En ejecucion","En linea",1,1,"2021-08-10",null,4,39,4);
insert estudio values(5,"En ejecucion","En linea",1,1,"2021-08-10",null,5,40,5);
insert estudio values(6,"En ejecucion","En linea",1,1,"2021-08-10",null,6,36,6);
insert estudio values(7,"En ejecucion","En linea",1,1,"2021-08-10",null,7,37,7);
insert estudio values(8,"En ejecucion","En linea",1,1,"2021-08-10",null,8,38,8);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`pregunta`
-- -----------------------------------------------------
-- 26 30
-- abiertas
insert pregunta values(1,"Que opina del producto? ","abierta",null,1,"2021-08-10",null,26);
insert pregunta values(2,"Cuentenos, tuvo algun problema con el producto?","abierta",null,1,"2021-08-10",null,26);
insert pregunta values(3,"Como fue su experiencia con el producto?","abierta",null,1,"2021-08-10",null,26);

-- simple
insert pregunta values(4,"Como se entero del producto?","simple",null,1,"2021-08-10",null,27);
insert opcion values(1,"redes sociales",1,"2021-08-10",null,4);
insert opcion values(2,"radio",1,"2021-08-10",null,4);
insert opcion values(3,"TV",1,"2021-08-10",null,4);
insert opcion values(4,"conocidos",1,"2021-08-10",null,4);

insert pregunta values(5,"cuanto uso el producto?","simple",null,1,"2021-08-10",null,28);
insert opcion values(5,"Mucho",1,"2021-08-10",null,5);
insert opcion values(6,"Poco",1,"2021-08-10",null,5);
insert opcion values(7,"Nada",1,"2021-08-10",null,5);

insert pregunta values(6,"Como describiria el producto?","simple",null,1,"2021-08-10",null,29);
insert opcion values(8,"Muy util",1,"2021-08-10",null,6);
insert opcion values(9,"Util",1,"2021-08-10",null,6);
insert opcion values(10,"Poco util",1,"2021-08-10",null,6);
insert opcion values(11,"Nada util",1,"2021-08-10",null,6);

-- boolean
insert pregunta values(7,"Recomendaria el producto?","boolean",null,1,"2021-08-10",null,29);
insert pregunta values(8,"Le gusto el producto?","boolean",null,1,"2021-08-10",null,29);
insert pregunta values(9,"Lo volveria a comprar","boolean",null,1,"2021-08-10",null,29);

-- rango
insert pregunta values(10,"Cuanta calidad le da al producto?","rango","1&10",1,"2021-08-10",null,30);
insert pregunta values(11,"Cuanto le da al acabado del producto?","rango","1&10",1,"2021-08-10",null,30);
insert pregunta values(12,"En que grado recomendaria el producto?","rango","1&10",1,"2021-08-10",null,30);

-- multiple
insert pregunta values(13,"Como se entero del producto?","multiple",null,1,"2021-08-10",null,28);
insert opcion values(12,"redes sociales",1,"2021-08-10",null,13);
insert opcion values(13,"radio",1,"2021-08-10",null,13);
insert opcion values(14,"TV",1,"2021-08-10",null,13);
insert opcion values(15,"conocidos",1,"2021-08-10",null,13);

insert pregunta values(14,"a quienes lo recomendaria","multiple",null,1,"2021-08-10",null,28);
insert opcion values(16,"amigos",1,"2021-08-10",null,14);
insert opcion values(17,"familiares",1,"2021-08-10",null,14);
insert opcion values(18,"pareja",1,"2021-08-10",null,14);
insert opcion values(19,"conocidos",1,"2021-08-10",null,14);


-- -----------------------------------------------------
-- Table `mercadeoucab`.`encuesta_estudio`
-- -----------------------------------------------------
-- 1
insert encuesta_estudio values(1,1,1);
insert encuesta_estudio values(2,2,1);
insert encuesta_estudio values(3,3,1);
 -- 2
insert encuesta_estudio values(4,4,2);
insert encuesta_estudio values(5,5,2);
insert encuesta_estudio values(6,7,2);

 -- 3
insert encuesta_estudio values(7,8,3);
insert encuesta_estudio values(8,9,4);
insert encuesta_estudio values(9,10,4);

 -- 4
insert encuesta_estudio values(10,11,4);
insert encuesta_estudio values(11,12,4);
insert encuesta_estudio values(12,13,4);

 -- 5
insert encuesta_estudio values(13,14,5);
insert encuesta_estudio values(14,1,5);
insert encuesta_estudio values(15,2,5);

 -- 6
insert encuesta_estudio values(16,3,6);
insert encuesta_estudio values(17,4,6);
insert encuesta_estudio values(18,5,6);

 -- 7
insert encuesta_estudio values(19,6,8);
insert encuesta_estudio values(20,7,8);
insert encuesta_estudio values(21,8,8);

 -- 8
insert encuesta_estudio values(22,9,7);
insert encuesta_estudio values(23,10,7);
insert encuesta_estudio values(24,11,7);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`hijo`
-- -----------------------------------------------------
/*
  insert dato_encuestado values (16,null,"Bean",23645897,"telefono","1980-05-08","masculino",1,"Bachiller",4,1,'2020-01-01 00:00:01',null,1,6);
  insert dato_encuestado values (17,null,"Wiley",74523654,"telefono","1985-06-13","masculino",1,"Bachiller",3,1,"2020-01-01 00:00:01",null,2,6);
  insert dato_encuestado values (18,null,"Livingston",36987524,"telefono","1986-07-11","masculino",1,"Bachiller",2,1,"2020-01-01 00:00:01",null,3,8);
  insert dato_encuestado values (19,null,"David",45236987,"telefono","1987-08-21","masculino",1,"Bachiller",4,1,"2020-01-01 00:00:01",null,4,8);
  insert dato_encuestado values (20,null,"Snyder",22658987,"telefono","1988-09-22","masculino",1,"Bachiller",5,1,"2020-01-01 00:00:01",null,5,8);
*/

insert hijo values(1,"masculino","2000-09-22",1,"2019-07-23",null,1);
insert hijo values(2,"femenino","1997-09-22",1,"2019-07-23",null,2);
insert hijo values(3,"masculino","2015-09-22",1,"2019-07-23",null,3);
insert hijo values(4,"femenino","2012-09-22",1,"2019-07-23",null,4);
insert hijo values(5,"masculino","2010-09-22",1,"2019-07-23",null,5);
insert hijo values(6,"femenino","1997-09-22",1,"2019-07-23",null,6);
insert hijo values(7,"masculino","2015-09-22",1,"2019-07-23",null,7);
insert hijo values(8,"femenino","1997-09-12",1,"2019-07-23",null,8);
insert hijo values(9,"masculino","1999-09-20",1,"2019-07-23",null,9);
insert hijo values(10,"masculino","2000-09-22",1,"2019-07-23",null,10);
insert hijo values(11,"femenino","1997-09-22",1,"2019-07-23",null,11);
insert hijo values(12,"masculino","2015-09-22",1,"2019-07-23",null,12);
insert hijo values(13,"femenino","2012-09-22",1,"2019-07-23",null,13);
insert hijo values(14,"masculino","2000-09-22",1,"2019-07-23",null,14);
insert hijo values(15,"femenino","1997-09-22",1,"2019-07-23",null,15);
insert hijo values(16,"masculino","2015-09-22",1,"2019-07-23",null,16);
insert hijo values(17,"femenino","2012-09-22",1,"2019-07-23",null,17);
insert hijo values(18,"femenino","2013-09-22",1,"2019-07-23",null,18);
insert hijo values(19,"femenino","2013-09-22",1,"2019-07-23",null,19);
insert hijo values(20,"femenino","2013-09-22",1,"2019-07-23",null,20);
insert hijo values(21,"femenino","2013-09-22",1,"2019-07-23",null,22);
insert hijo values(22,"femenino","2013-09-22",1,"2019-07-23",null,22);
insert hijo values(23,"femenino","2013-09-22",1,"2019-07-23",null,23);
insert hijo values(24,"femenino","2013-09-22",1,"2019-07-23",null,24);
insert hijo values(25,"femenino","2013-09-22",1,"2019-07-23",null,25);


-- -----------------------------------------------------
-- Table `mercadeoucab`.`opcion`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mercadeoucab`.`presentacion`
-- -----------------------------------------------------

insert presentacion values(1,"2 l","volumen",1,"2020-01-01",null);
insert presentacion values(2,"SS","vestimenta",1,"2020-01-01",null);
insert presentacion values(3,"1000 g","peso",1,"2020-01-01",null);
insert presentacion values(4,"2 l","volumen",1,"2020-01-01",null);
insert presentacion values(5,"2000 g","peso",1,"2020-01-01",null);
insert presentacion values(6,"M","vestimenta",1,"2020-01-01",null);
insert presentacion values(7,"1.5 ml","volumen",1,"2020-01-01",null);
insert presentacion values(8,"200 kg","peso",1,"2020-01-01",null);
insert presentacion values(9,"XL","vestimenta",1,"2020-01-01",null);
insert presentacion values(10,"L","vestimenta",1,"2020-01-01",null);


-- -----------------------------------------------------
-- Table `mercadeoucab`.`presentacion_solicitud`
-- -----------------------------------------------------

insert presentacion_solicitud values(1,1,1);
insert presentacion_solicitud values(2,2,2);
insert presentacion_solicitud values(3,3,3);
insert presentacion_solicitud values(4,4,4);
insert presentacion_solicitud values(5,5,5);
insert presentacion_solicitud values(6,6,6);
insert presentacion_solicitud values(7,7,7);
insert presentacion_solicitud values(8,8,8);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`respuesta`
-- -----------------------------------------------------

/*
insert pregunta values(1,"Pregunta 1: Le parecio comodo el mueble? ","abierta",null,1,"2020-01-01",null,6);
insert pregunta values(2,"Pregunta 2: Recomendaria este mueble a otras personas?","boolean",null,1,"2020-01-01",null,6);
insert pregunta values(3,"Pregunta 3: El precio del mueble le parece que esta bien justificado?","abierta",null,1,"2020-01-01",null,6);
insert pregunta values(4,"Pregunta 4: Que problemas encontro en nuestro mueble?","abierta",null,1,"2020-01-01",null,6);
insert pregunta values(5,"Pregunta 1: El vestido lo encontro comodo ? ","abierta",null,1,"2020-01-01",null,8);
insert pregunta values(7,"Pregunta 2: El precio del vestido le parece que esta bien justificado?","abierta",null,1,"2020-01-01",null,8);
*/
-- -----------------------------------------------------
-- Table `mercadeoucab`.`sub_categoria`
-- -----------------------------------------------------

/*
insert categoria values(1,"Muebles de dormitorio",1,"2020-07-23",null);
insert categoria values(2,"Maquillaje",1,"2020-07-23",null);
insert categoria values(3,"Electronicos",1,"2020-07-23",null);
insert categoria values(4,"Utiles escolares",1,"2020-07-23",null);
insert categoria values(5,"Vestimenta",1,"2020-07-23",null);
insert categoria values(6,"Libros",1,"2020-07-23",null);
insert categoria values(7,"Salud",1,"2020-07-23",null);
insert categoria values(8,"Limpieza",1,"2020-07-23",null); 
*/

insert sub_categoria values(1,"Para dormitorios",1,"2019-07-23",null,8);
insert sub_categoria values(2,"Para salas",1,"2019-07-23",null,8);
insert sub_categoria values(3,"Computadoras de escritorio",1,"2019-07-23",null,3);
insert sub_categoria values(4,"TVs",1,"2019-07-23",null,3);
insert sub_categoria values(5,"Libros de fantasia",1,"2019-07-23",null,6);
insert sub_categoria values(6,"Poesia",1,"2019-07-23",null,6);
insert sub_categoria values(7,"Politica",1,"2019-07-23",null,6);
insert sub_categoria values(8,"Politica",1,"2019-07-23",null,6);
insert sub_categoria values(9,"Vestidos",1,"2019-07-23",null,5);



-- -----------------------------------------------------
-- Table `mercadeoucab`.`sub_categoria_solicitud`
-- -----------------------------------------------------

/*
insert solicitud values(1,"solicitada",1,"2020-01-01",null,11,9);-- vestimenta --
insert solicitud values(2,"solicitada",1,"2020-01-01",null,12,15);-- Limpieza --
insert solicitud values(3,"solicitada",1,"2020-01-01",null,13,16);-- Limpieza --
insert solicitud values(4,"aceptada",1,"2020-01-01",null,14,10);-- vestimenta --
insert solicitud values(5,"aceptada",1,"2020-01-01",null,15,9);-- vestimenta --
*/

insert sub_categoria_solicitud values(1,1,1);
insert sub_categoria_solicitud values(2,2,2);
insert sub_categoria_solicitud values(3,3,3);
insert sub_categoria_solicitud values(4,4,4);
insert sub_categoria_solicitud values(5,5,5);
insert sub_categoria_solicitud values(6,6,6);
insert sub_categoria_solicitud values(7,7,7);
insert sub_categoria_solicitud values(8,8,8);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`telefono`
-- -----------------------------------------------------

insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (1, '2912944114', 1, '2020-03-06', null, 1);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (2, '1166237280', 1, '2020-09-21', null, 2);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (3, '7213262334', 1, '2019-12-27', null, 3);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (4, '3826629123', 1, '2020-10-29', null, 4);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (5, '5233317817', 1, '2020-01-18', null, 5);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (6, '3004394090', 1, '2020-02-10', null, 6);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (7, '1195441041', 1, '2020-11-08', null, 7);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (8, '3988188070', 1, '2020-04-04', null, 8);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (9, '5981079315', 1, '2020-01-09', null, 9);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (10, '2169979219', 1, '2020-08-28', null, 10);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (11, '7168612119', 1, '2020-09-15', null, 11);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (12, '7159250013', 1, '2020-08-09', null, 12);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (13, '1884127129', 1, '2020-12-13', null, 13);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (14, '7354131530', 1, '2020-10-23', null, 14);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (15, '4342897506', 1, '2020-01-07', null, 15);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (16, '5389317774', 1, '2020-04-13', null, 16);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (17, '8172896049', 1, '2020-06-20', null, 17);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (18, '7017185719', 1, '2020-05-23', null, 18);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (19, '3441127331', 1, '2020-10-09', null, 19);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (20, '7693901143', 1, '2020-02-02', null, 20);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (21, '7606969273', 1, '2020-09-16', null, 21);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (22, '4717829433', 1, '2020-08-19', null, 22);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (23, '9532491455', 1, '2020-04-24', null, 23);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (24, '1997526605', 1, '2020-03-24', null, 24);
insert into telefono (id, telefono, activo, creado_el, modificado_el, fk_dato_encuestado) values (25, '4944200342', 1, '2020-07-31', null, 25);


-- -----------------------------------------------------
-- Table `mercadeoucab`.`tipo`
-- -----------------------------------------------------
INSERT INTO tipo VALUES (1,"Barra",1,now(),null);
INSERT INTO tipo VALUES (2,"Eerosol",1,now(),null);
INSERT INTO tipo VALUES (3,"Liquido",1,now(),null);
INSERT INTO tipo VALUES (4,"Polvo",1,now(),null);
INSERT INTO tipo VALUES (5,"Solvente",1,now(),null);
INSERT INTO tipo VALUES (6,"Spray",1,now(),null);
INSERT INTO tipo VALUES (7,"formal",1,now(),null);
INSERT INTO tipo VALUES (8,"Informal",1,now(),null);


-- -----------------------------------------------------
-- Table `mercadeoucab`.`tipo_solicitud`
-- -----------------------------------------------------
INSERT INTO tipo_solicitud VALUES (1, 1, 1);
INSERT INTO tipo_solicitud VALUES (2, 2, 2);
INSERT INTO tipo_solicitud VALUES (3, 3, 3);
INSERT INTO tipo_solicitud VALUES (4, 4, 4);
INSERT INTO tipo_solicitud VALUES (5, 5, 5);
INSERT INTO tipo_solicitud VALUES (6, 6, 6);
INSERT INTO tipo_solicitud VALUES (7, 7, 7);
INSERT INTO tipo_solicitud VALUES (8, 8, 8);