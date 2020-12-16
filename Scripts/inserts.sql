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
insert parroquia values(1,"San Camilo",1,1,'2019-07-23',null,2);
insert parroquia values(2,"Parroquia Nuestra Senora Del Carmen",1,1,'2019-07-23',null,3);
insert parroquia values(3,"Parroquia San Lorenzo",1,1,'2019-07-23',null,4);
insert parroquia values(4,"Parroquia Nuestra Señora de las Angustias",1,1,'2019-07-23',null,5);
insert parroquia values(5,"Parroquia Nuestra Señora Del Tránsito",1,1,'2019-07-23',null,6);

insert parroquia values(6,"Eglise Notre Dame De Rumengol",1,1,'2019-07-23',null,7);
insert parroquia values(7,"Parroquia Saint-Luc du Val d'Orne",1,1,'2019-07-23',null,8);
insert parroquia values(8,"Iglesia de San Francisco de Asís",1,1,'2019-07-23',null,12);
insert parroquia values(9,"Grace Bible Church",1,1,'2019-07-23',null,13);
insert parroquia values(10,"Christ the King Mission",1,1,'2019-07-23',null,14);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`usuario`
-- -----------------------------------------------------  
  insert usuario values (1,"Antonio","Nohra","atag102@gmail.com","encuestado","activo",1,'2019-07-23',null);
  insert usuario values (2,"Zeus","Berg","ZB@gmail.com","encuestado","activo",1,"2019-07-23",null);
  insert usuario values (3,"Addison","Gomez","ADDG@gmail.com","encuestado","activo",1,"2019-07-23",null);
  insert usuario values (4,"Jamal","Stevenson","jamal_Stevenson@gmail.com","encuestado","activo",1,"2019-07-23",null);
  insert usuario values (5,"Elmo","Rose","Elmo@gmail.com","encuestado","activo",1,"2019-07-23",null);

  insert usuario values (6,"Macon","Mcleod","MM10@gmail.com","administrador","activo",1,'2019-07-23',null);
  insert usuario values (7,"Warren","Torres","WARREN@gmail.com","administrador","activo",1,"2019-07-23",null);
  insert usuario values (8,"Jonas","Mccray","JON_M@gmail.com","administrador","activo",1,"2019-07-23",null);
  insert usuario values (9,"Barclay","Holt","HOLT10@gmail.com","administrador","activo",1,"2019-07-23",null);
  insert usuario values (10,"Wyatt","Jackson","Wtt@gmail.com","administrador","activo",1,"2019-07-23",null);
  
  insert usuario values (11,"Caesar","Mosley","CM10@gmail.com","cliente","activo",1,'2019-07-23',null);
  insert usuario values (12,"Brent","Luna","Bluna@gmail.com","cliente","activo",1,"2019-07-23",null);
  insert usuario values (13,"Victor","Paul","V1@gmail.com","cliente","activo",1,"2019-07-23",null);
  insert usuario values (14,"Kasper","Whitaker","KW@gmail.com","cliente","activo",1,"2019-07-23",null);
  insert usuario values (15,"Solomon","Bentley","B123@gmail.com","cliente","activo",1,"2019-07-23",null);
  
  insert usuario values (16,"Harper","Vance","Harper20@gmail.com","analista","activo",1,'2019-07-23',null);
  insert usuario values (17,"Harrison","Dorsey","HARRI@gmail.com","analista","activo",1,"2019-07-23",null);
  insert usuario values (18,"Nehru","Winters","NEHR@gmail.com","analista","activo",1,"2019-07-23",null);
  insert usuario values (19,"Reese","Dillon","R_D@gmail.com","analista","activo",1,"2019-07-23",null);
  insert usuario values (20,"Hammett","Schneider","HMLETSCH456@gmail.com","analista","activo",1,"2020-07-23",null);
  
  -- -----------------------------------------------------
-- Table `mercadeoucab`.`ocupacion`
-- -----------------------------------------------------

insert ocupacion values(1,"Ingeniero informatico",1,"2019-05-23",null);
insert ocupacion values(2,"Mecanico",1,"2019-05-23",null);
insert ocupacion values(3,"Cocinero",1,"2019-05-23",null);
insert ocupacion values(4,"Comerciante",1,"2019-05-23",null);
insert ocupacion values(5,"Policia",1,"2019-05-23",null);
insert ocupacion values(6,"Arquitecto",1,"2019-05-23",null);
  
-- -----------------------------------------------------
-- Table `mercadeoucab`.`dato_encuestado`
-- -----------------------------------------------------

  insert dato_encuestado values (1,null,"Hooper",26987321,"laptop","1997-02-23","masculino",1,"Licenciado",4,1,'2019-07-23',null,6,1,1);
  insert dato_encuestado values (2,null,"Harmon",6987423,"laptop","1997-03-19","masculino",1,"Licenciado",4,1,"2019-07-23",null,7,2,2);
  insert dato_encuestado values (3,null,"Petty",58796354,"laptop","1997-04-18","masculino",1,"Licenciado",2,1,"2019-07-23",null,8,3,3);
  insert dato_encuestado values (4,null,"Schultz",29856321,"laptop","1997-05-14","masculino",1,"Licenciado",3,1,"2019-07-23",null,9,4,4);
  insert dato_encuestado values (5,null,"Coffey",78964445,"laptop","1997-06-10","masculino",1,"Licenciado",5,1,"2019-07-23",null,10,5,5);
  
  insert dato_encuestado values (6,null,"Mccall",78965411,"pc","1997-07-06","masculino",1,"Magíster",4,1,'2019-07-23',null,16,6,6);
  insert dato_encuestado values (7,null,"Rosales","75777888","pc","1997-08-15","masculino",1,"Magíster",2,1,"2019-07-23",null,17,7,1);
  insert dato_encuestado values (8,null,"Harris",96547823,"pc","1997-09-21","masculino",1,"Magíster",3,1,"2019-07-23",null,18,8,2);
  insert dato_encuestado values (9,null,"Saunders",23654789,"pc","1997-10-20","masculino",1,"Magíster",4,1,"2019-07-23",null,19,9,3);
  insert dato_encuestado values (10,null,"Benson",15632478,"pc","1997-11-19","masculino",1,"Magíster",2,1,"2019-07-23",null,20,10,4);
  
  insert dato_encuestado values (11,null,"Marks",88954756,"tableta","1997-12-16","masculino",1,"Doctorado",3,1,'2019-07-23',null,11,1,5);
  insert dato_encuestado values (12,null,"Dean",66354789,"tableta","2000-02-13","masculino",1,"Doctorado",1,1,"2019-07-23",null,12,2,6);
  insert dato_encuestado values (13,null,"Quinn",44236987,"tableta","2000-02-11","masculino",1,"Doctorado",4,1,"2019-07-23",null,13,3,1);
  insert dato_encuestado values (14,null,"Harding",5236987,"tableta","2000-03-10","masculino",1,"Doctorado",6,1,"2019-07-23",null,14,4,2);
  insert dato_encuestado values (15,null,"Carver",78555632,"tableta","2000-04-09","masculino",1,"Doctorado",5,1,"2019-07-23",null,15,5,3);  
  
  insert dato_encuestado values (16,null,"Bean",23645897,"telefono","1980-05-08","masculino",1,"Bachiller",4,1,'2019-07-23',null,1,6,4);
  insert dato_encuestado values (17,null,"Wiley",74523654,"telefono","1985-06-13","masculino",1,"Bachiller",3,1,"2019-07-23",null,2,6,5);
  insert dato_encuestado values (18,null,"Livingston",36987524,"telefono","1986-07-11","masculino",1,"Bachiller",2,1,"2019-07-23",null,3,8,6);
  insert dato_encuestado values (19,null,"David",45236987,"telefono","1987-08-21","masculino",1,"Bachiller",4,1,"2019-07-23",null,4,8,1);
  insert dato_encuestado values (20,null,"Snyder",22658987,"telefono","1988-09-22","masculino",1,"Bachiller",5,1,"2019-07-23",null,5,8,2);

    
    
-- -----------------------------------------------------
-- Table `mercadeoucab`.`muestra_poblacion`
-- -----------------------------------------------------
    
insert muestra_poblacion values (1,"masculino",1,"Bachiller",10,50,2,1,"2020-07-23",null,6,1);
insert muestra_poblacion values (2,"masculino",1,"Bachiller",15,40,3,1,"2020-07-23",null,8,2);
insert muestra_poblacion values (3,"masculino",1,"Bachiller",16,30,4,1,"2020-07-23",null,7,3);
insert muestra_poblacion values (4,"masculino",1,"Bachiller",18,40,3,1,"2020-07-23",null,9,4);
insert muestra_poblacion values (5,"femenino",1,"Bachiller",19,50,3,1,"2020-07-23",null,10,5);

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

insert solicitud values(1,"solicitada",1,"2020-08-10",null,11,9);-- vestimenta --
insert solicitud values(2,"solicitada",1,"2020-08-10",null,12,15);-- Limpieza --
insert solicitud values(3,"solicitada",1,"2020-08-10",null,13,16);-- Limpieza --
insert solicitud values(4,"aceptada",1,"2020-08-10",null,14,10);-- vestimenta --
insert solicitud values(5,"aceptada",1,"2020-08-11",null,15,9);-- vestimenta --

-- -----------------------------------------------------
-- Table `mercadeoucab`.`estudio`
-- -----------------------------------------------------


insert estudio values(1,"En ejecucion","En linea",1,1,"2021-08-10",null,1,6,1);
insert estudio values(2,"En ejecucion","En linea",1,1,"2021-08-10",null,2,7,2);
insert estudio values(3,"Culminado","En linea",1,0,"2021-08-10",null,3,8,3);
insert estudio values(4,"Procesando","En linea",1,1,"2021-08-10",null,4,9,4);
insert estudio values(5,"Culminado","En linea",1,0,"2021-08-10",null,5,10,5);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`pregunta`
-- -----------------------------------------------------

insert pregunta values(1,"Pregunta 1: Le parecio comodo el mueble? ","abierta",null,1,"2021-08-10",null,6);
insert pregunta values(2,"Pregunta 2: Recomendaria este mueble a otras personas?","boolean",null,1,"2021-08-10",null,6);
insert pregunta values(3,"Pregunta 3: El precio del mueble le parece que esta bien justificado?","abierta",null,1,"2021-08-10",null,6);
insert pregunta values(4,"Pregunta 4: Que problemas encontro en nuestro mueble?","abierta",null,1,"2021-08-10",null,6);
insert pregunta values(5,"Pregunta 1: El vestido lo encontro comodo ? ","abierta",null,1,"2021-08-10",null,8);
insert pregunta values(7,"Pregunta 2: El precio del vestido le parece que esta bien justificado?","abierta",null,1,"2021-08-10",null,8);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`encuesta_estudio`
-- -----------------------------------------------------

insert encuesta_estudio values(1,1,1);
insert encuesta_estudio values(2,2,1);
insert encuesta_estudio values(3,3,1);
insert encuesta_estudio values(4,4,1);
insert encuesta_estudio values(5,5,2);
insert encuesta_estudio values(6,7,2);

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

insert hijo values(1,"masculino","2000-09-22",1,"2019-07-23",null,16);
insert hijo values(2,"femenino","1997-09-22",1,"2019-07-23",null,16);
insert hijo values(3,"masculino","2015-09-22",1,"2019-07-23",null,16);
insert hijo values(4,"femenino","2012-09-22",1,"2019-07-23",null,16);
insert hijo values(5,"masculino","2010-09-22",1,"2019-07-23",null,17);
insert hijo values(6,"femenino","1997-09-22",1,"2019-07-23",null,17);
insert hijo values(7,"masculino","2015-09-22",1,"2019-07-23",null,17);
insert hijo values(8,"femenino","1997-09-12",1,"2019-07-23",null,18);
insert hijo values(9,"masculino","1999-09-20",1,"2019-07-23",null,18);
insert hijo values(10,"masculino","2000-09-22",1,"2019-07-23",null,19);
insert hijo values(11,"femenino","1997-09-22",1,"2019-07-23",null,19);
insert hijo values(12,"masculino","2015-09-22",1,"2019-07-23",null,19);
insert hijo values(13,"femenino","2012-09-22",1,"2019-07-23",null,19);
insert hijo values(14,"masculino","2000-09-22",1,"2019-07-23",null,20);
insert hijo values(15,"femenino","1997-09-22",1,"2019-07-23",null,20);
insert hijo values(16,"masculino","2015-09-22",1,"2019-07-23",null,20);
insert hijo values(17,"femenino","2012-09-22",1,"2019-07-23",null,20);
insert hijo values(18,"femenino","2013-09-22",1,"2019-07-23",null,20);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`opcion`
-- -----------------------------------------------------

insert opcion values(1,"Si",1,"2021-08-10",null,2);
insert opcion values(2,"No",1,"2021-08-10",null,2);

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

insert presentacion_solicitud values(1,2,1);
insert presentacion_solicitud values(2,1,2);
insert presentacion_solicitud values(3,3,3);
insert presentacion_solicitud values(4,10,4);
insert presentacion_solicitud values(5,9,5);

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

insert respuesta values(1,"Me ha parecido de lo mejor que he utilizado en años",1,"2020-01-01",null,1,null,1);
insert respuesta values(2,null,1,"2020-10-01",null,1,2,2);
insert respuesta values(3,"Debo admitir que es un mueble bonito que puede",1,"2020-10-01",null,1,null,3);
insert respuesta values(4,"C de lo que es y no dura mucho",1,"2020-10-01",null,1,null,4);

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

insert sub_categoria_solicitud values(1,2,1);
insert sub_categoria_solicitud values(2,2,4);
insert sub_categoria_solicitud values(3,2,5);

-- -----------------------------------------------------
-- Table `mercadeoucab`.`telefono`
-- -----------------------------------------------------




-- -----------------------------------------------------
-- Table `mercadeoucab`.`tipo`
-- -----------------------------------------------------



-- -----------------------------------------------------
-- Table `mercadeoucab`.`tipo_solicitud`
-- -----------------------------------------------------


 insert usuario values (21,"Elmo","Rose","Elmo@gmail.com","encuestado","activo",1,"2019-07-23",null);
 insert dato_encuestado values (21,null,"Hooper",25545624,"laptop","1997-02-23","masculino",1,"Licenciado",1,1,'2019-07-23',null,21,1,1);
 insert hijo values(19,"femenino","2013-09-22",1,"2019-07-23",null,21);
 
 insert muestra_poblacion values (6,"masculino",1,"Licenciado",10,50,1,1,"2020-07-23",null,1,1);
 
  insert estudio values(6,"En ejecucion","En linea",1,1,"2021-08-10",null,1,6,6);
 
 
 
 
 
 
 
 
 
 