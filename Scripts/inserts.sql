-- -----------------------------------------------------
-- Table `mercadeoucab`.`categoria`
-- -----------------------------------------------------



-- -----------------------------------------------------
-- Table `mercadeoucab`.`pais`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mercadeoucab`.`estado`
-- -----------------------------------------------------
INSERT INTO `mercadeoucab`.`estado` (`nombre`, `Pais_idPais`,`activo`, `creado_el`, `modificado_el` )
                            VALUES ( "Cordova", 3, 1, "2020-07-23", "2020-07-23");

-- -----------------------------------------------------
-- Table `mercadeoucab`.`municipio`
-- -----------------------------------------------------
INSERT INTO `mercadeoucab`.`Municipio` (`nombre`, `Estado_idPais`,`activo`, `creado_el`, `modificado_el` )
                            VALUES ( "Libertador", 1, 1, "2020-07-23", "2020-07-23");


-- -----------------------------------------------------
-- Table `mercadeoucab`.`parroquia`
-- -----------------------------------------------------
INSERT INTO `mercadeoucab`.`Parroquia`(`nombre`, `Municipio_idPais`, `valor_socio_economico`,`activo`, `creado_el`, `modificado_el` )
                            VALUES ( "Preguntame", 3, 5600, 1, "2020-07-23", "2020-07-23");

-- -----------------------------------------------------
-- Table `mercadeoucab`.`usuario`
-- -----------------------------------------------------
INSERT INTO `mercadeoucab`.`usuario`(`nombre`,`apellido`, `rol`, `estado`, `activo`, `creado_el`, `modificado_el` )
                            VALUES ("Cristiano","Ronaldo", "analista", "activo", 1, "2020-07-23", "2020-07-23");

-- -----------------------------------------------------
-- Table `mercadeoucab`.`dato_encuestado`
-- -----------------------------------------------------
INSERT INTO `mercadeoucab`.`dato_encuestado` (`segundo_nombre`, `segundo_apellido`, `cedula`, `medio_conexion`, `edad`, `genero`, `nivel_economico`, `nivel_academico`, `personas_hogar`, `fk_usuario`, `fk_lugar`, `activo`, `creado_el`, `modificado_el` )
                            VALUES ( "Jose", "Perez", "93969", "laptop", 52, "masculino", 5600, "Ingeniero", 4, 1, 1, 1, "2020-07-23", "2020-07-23");

-- -----------------------------------------------------
-- Table `mercadeoucab`.`muestra_poblacion`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mercadeoucab`.`marca`
-- -----------------------------------------------------
INSERT INTO `mercadeoucab`.`marca`(`nombre`,`activo`, `creado_el`, `modificado_el` )
                            VALUES ( "Nike", 1, "2020-07-23", "2020-07-23");

-- -----------------------------------------------------
-- Table `mercadeoucab`.`solicitud`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mercadeoucab`.`estudio`
-- -----------------------------------------------------



-- -----------------------------------------------------
-- Table `mercadeoucab`.`pregunta`
-- -----------------------------------------------------



-- -----------------------------------------------------
-- Table `mercadeoucab`.`encuesta_estudio`
-- -----------------------------------------------------



-- -----------------------------------------------------
-- Table `mercadeoucab`.`hijo`
-- -----------------------------------------------------



-- -----------------------------------------------------
-- Table `mercadeoucab`.`ocupacion`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mercadeoucab`.`ocupacion_encuestado`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mercadeoucab`.`ocupacion_muestra`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mercadeoucab`.`opcion`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mercadeoucab`.`presentacion`
-- -----------------------------------------------------



-- -----------------------------------------------------
-- Table `mercadeoucab`.`presentacion_solicitud`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mercadeoucab`.`respuesta`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mercadeoucab`.`sub_categoria`
-- -----------------------------------------------------



-- -----------------------------------------------------
-- Table `mercadeoucab`.`sub_categoria_solicitud`
-- -----------------------------------------------------





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