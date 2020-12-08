-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mercadeoucab
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mercadeoucab` ;

-- -----------------------------------------------------
-- Schema mercadeoucab
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mercadeoucab` DEFAULT CHARACTER SET utf8 ;
USE `mercadeoucab` ;

-- -----------------------------------------------------
-- Table `mercadeoucab`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`categoria` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_categoria_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`pais`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`pais` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`pais` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`estado` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`estado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_pais` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Estado_Pais1_idx` (`fk_pais` ASC) VISIBLE,
  CONSTRAINT `fk_Estado_Pais1`
    FOREIGN KEY (`fk_pais`)
    REFERENCES `mercadeoucab`.`pais` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`municipio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`municipio` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`municipio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_estado` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Municipio_Estado1_idx` (`fk_estado` ASC) VISIBLE,
  CONSTRAINT `fk_Municipio_Estado1`
    FOREIGN KEY (`fk_estado`)
    REFERENCES `mercadeoucab`.`estado` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`parroquia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`parroquia` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`parroquia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `valor_socio_economico` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_municipio` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_lugar_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Parroquia_Municipio1_idx` (`fk_municipio` ASC) VISIBLE,
  CONSTRAINT `fk_Parroquia_Municipio1`
    FOREIGN KEY (`fk_municipio`)
    REFERENCES `mercadeoucab`.`municipio` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`usuario` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NOT NULL,
  `apellido` VARCHAR(25) NOT NULL,
  `correo` VARCHAR(60) NOT NULL,
  `rol` ENUM('administrador', 'cliente', 'encuestado', 'analista') NOT NULL,
  `estado` ENUM('activo', 'bloqueado', 'inactivo') NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_usuario_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`dato_encuestado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`dato_encuestado` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`dato_encuestado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `segundo_nombre` VARCHAR(45) NULL DEFAULT NULL,
  `segundo_apellido` VARCHAR(45) NULL DEFAULT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `medio_conexion` ENUM('laptop', 'pc', 'tableta', 'telefono') NOT NULL,
  `edad` TIMESTAMP NOT NULL,
  `genero` ENUM('masculino', 'femenino') NOT NULL,
  `nivel_economico` INT NOT NULL,
  `nivel_academico` VARCHAR(60) NOT NULL,
  `personas_hogar` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_usuario` INT NOT NULL,
  `fk_lugar` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC) VISIBLE,
  UNIQUE INDEX `fk_usuario_UNIQUE` (`fk_usuario` ASC) VISIBLE,
  UNIQUE INDEX `id_dato_encuestado_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_lugar_idx` (`fk_lugar` ASC) VISIBLE,
  CONSTRAINT `fk_lugar1`
    FOREIGN KEY (`fk_lugar`)
    REFERENCES `mercadeoucab`.`parroquia` (`id`),
  CONSTRAINT `fk_usuario4`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `mercadeoucab`.`usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`muestra_poblacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`muestra_poblacion` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`muestra_poblacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genero` ENUM('masculino', 'femenino') NOT NULL,
  `nivel_economico` INT NOT NULL,
  `nivel_academico` VARCHAR(60) NOT NULL,
  `rango_edad_inicio` INT NOT NULL,
  `rango_edad_fin` INT NOT NULL,
  `cantidad_hijos` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_lugar` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_muestra_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_lugar_idx` (`fk_lugar` ASC) VISIBLE,
  CONSTRAINT `fk_lugar`
    FOREIGN KEY (`fk_lugar`)
    REFERENCES `mercadeoucab`.`parroquia` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`marca` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`marca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_marca_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`solicitud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`solicitud` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`solicitud` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `estado` ENUM('solicitada', 'aceptada') NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_usuario` INT NOT NULL,
  `fk_marca` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_solicitud_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_usuario_idx` (`fk_usuario` ASC) VISIBLE,
  INDEX `fk_marca_idx` (`fk_marca` ASC) VISIBLE,
  CONSTRAINT `fk_marca`
    FOREIGN KEY (`fk_marca`)
    REFERENCES `mercadeoucab`.`marca` (`id`),
  CONSTRAINT `fk_usuario1`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `mercadeoucab`.`usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`estudio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`estudio` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`estudio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `estado` ENUM('En ejecucion', 'Culminado', 'Procesando') NOT NULL,
  `tipo` ENUM('En linea', 'encuesta') NOT NULL,
  `encuestas_esperadas` INT NULL DEFAULT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_solicitud` INT NOT NULL,
  `fk_usuario` INT NULL DEFAULT NULL,
  `fk_muestra_problacion` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_estudio_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `id_solicitud_idx` (`fk_solicitud` ASC) VISIBLE,
  INDEX `fk_usuario_idx` (`fk_usuario` ASC) VISIBLE,
  INDEX `fk_muestra_poblacion_idx` (`fk_muestra_problacion` ASC) VISIBLE,
  CONSTRAINT `fk_muestra_poblacion`
    FOREIGN KEY (`fk_muestra_problacion`)
    REFERENCES `mercadeoucab`.`muestra_poblacion` (`id`),
  CONSTRAINT `fk_solicitud`
    FOREIGN KEY (`fk_solicitud`)
    REFERENCES `mercadeoucab`.`solicitud` (`id`),
  CONSTRAINT `fk_usuario2`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `mercadeoucab`.`usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`pregunta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`pregunta` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`pregunta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre_pregunta` VARCHAR(150) NULL DEFAULT NULL,
  `tipo` ENUM('abierta', 'multiple', 'simple', 'boolean', 'rango') NOT NULL,
  `rango` VARCHAR(18) NULL DEFAULT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_pregunta_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_usuario_idx` (`fk_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_usuario`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `mercadeoucab`.`usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`encuesta_estudio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`encuesta_estudio` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`encuesta_estudio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_pregunta` INT NOT NULL,
  `fk_estudio` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_encuesta_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_pregunta_idx` (`fk_pregunta` ASC) VISIBLE,
  INDEX `fk_estudio_idx` (`fk_estudio` ASC) VISIBLE,
  CONSTRAINT `fk_estudio`
    FOREIGN KEY (`fk_estudio`)
    REFERENCES `mercadeoucab`.`estudio` (`id`),
  CONSTRAINT `fk_pregunta1`
    FOREIGN KEY (`fk_pregunta`)
    REFERENCES `mercadeoucab`.`pregunta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`hijo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`hijo` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`hijo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genero` ENUM('masculino', 'femenino') NOT NULL,
  `edad` TIMESTAMP NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_dato_encuestado` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_hijo_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_dato_encuestado_idx` (`fk_dato_encuestado` ASC) VISIBLE,
  CONSTRAINT `fk_dato_encuestado`
    FOREIGN KEY (`fk_dato_encuestado`)
    REFERENCES `mercadeoucab`.`dato_encuestado` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`ocupacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`ocupacion` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`ocupacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_ocupacion_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`ocupacion_encuestado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`ocupacion_encuestado` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`ocupacion_encuestado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_ocupacion` INT NOT NULL,
  `fk_dato_encuestado` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_ocupacion_encuestado_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_ocupacion_idx` (`fk_ocupacion` ASC) VISIBLE,
  INDEX `fk_dato_encuestado_idx` (`fk_dato_encuestado` ASC) VISIBLE,
  CONSTRAINT `fk_dato_encuestado2`
    FOREIGN KEY (`fk_dato_encuestado`)
    REFERENCES `mercadeoucab`.`dato_encuestado` (`id`),
  CONSTRAINT `fk_ocupacion`
    FOREIGN KEY (`fk_ocupacion`)
    REFERENCES `mercadeoucab`.`ocupacion` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`ocupacion_muestra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`ocupacion_muestra` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`ocupacion_muestra` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_ocupacion` INT NOT NULL,
  `fk_muestra_poblacion` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_ocupacion_muestra_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_ocupacion_idx` (`fk_ocupacion` ASC) VISIBLE,
  INDEX `fk_muestra_poblacion_idx` (`fk_muestra_poblacion` ASC) VISIBLE,
  CONSTRAINT `fk_muestra_poblacion1`
    FOREIGN KEY (`fk_muestra_poblacion`)
    REFERENCES `mercadeoucab`.`muestra_poblacion` (`id`),
  CONSTRAINT `fk_ocupacion1`
    FOREIGN KEY (`fk_ocupacion`)
    REFERENCES `mercadeoucab`.`ocupacion` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`opcion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`opcion` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`opcion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre_opcion` VARCHAR(60) NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_pregunta` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_opcion_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_pregunta_idx` (`fk_pregunta` ASC) VISIBLE,
  CONSTRAINT `fk_pregunta`
    FOREIGN KEY (`fk_pregunta`)
    REFERENCES `mercadeoucab`.`pregunta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`presentacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`presentacion` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`presentacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cantidad` VARCHAR(45) NOT NULL,
  `tipo` ENUM('volumen', 'peso', 'vestimenta') NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_presentacion_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`presentacion_solicitud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`presentacion_solicitud` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`presentacion_solicitud` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_presentacion` INT NOT NULL,
  `fk_solicitud` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_presentacion_solicitud_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_solicitud_idx` (`fk_solicitud` ASC) VISIBLE,
  INDEX `fk_presentacion_idx` (`fk_presentacion` ASC) VISIBLE,
  CONSTRAINT `fk_presentacion`
    FOREIGN KEY (`fk_presentacion`)
    REFERENCES `mercadeoucab`.`presentacion` (`id`),
  CONSTRAINT `fk_solicitud1`
    FOREIGN KEY (`fk_solicitud`)
    REFERENCES `mercadeoucab`.`solicitud` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`respuesta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`respuesta` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`respuesta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `respuesta` VARCHAR(80) NULL DEFAULT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_usuario` INT NOT NULL,
  `fk_opcion` INT NULL DEFAULT NULL,
  `fk_encuesta_estudio` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_respuesta_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_opcion_idx` (`fk_opcion` ASC) VISIBLE,
  INDEX `fk_encuesta_estudio_idx` (`fk_encuesta_estudio` ASC) VISIBLE,
  INDEX `fk_usuario_idx` (`fk_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_encuesta_estudio`
    FOREIGN KEY (`fk_encuesta_estudio`)
    REFERENCES `mercadeoucab`.`encuesta_estudio` (`id`),
  CONSTRAINT `fk_opcion`
    FOREIGN KEY (`fk_opcion`)
    REFERENCES `mercadeoucab`.`opcion` (`id`),
  CONSTRAINT `fk_usuario3`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `mercadeoucab`.`usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`sub_categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`sub_categoria` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`sub_categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_categoria` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_sub_categoria_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_categoria_idx` (`fk_categoria` ASC) VISIBLE,
  CONSTRAINT `fk_categoria`
    FOREIGN KEY (`fk_categoria`)
    REFERENCES `mercadeoucab`.`categoria` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`sub_categoria_solicitud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`sub_categoria_solicitud` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`sub_categoria_solicitud` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_sub_categoria` INT NOT NULL,
  `fk_solicitud` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_sub_categoria_solicitud_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_sub_categoria_idx` (`fk_sub_categoria` ASC) VISIBLE,
  INDEX `fk_solicitud_idx` (`fk_solicitud` ASC) VISIBLE,
  CONSTRAINT `fk_solicitud2`
    FOREIGN KEY (`fk_solicitud`)
    REFERENCES `mercadeoucab`.`solicitud` (`id`),
  CONSTRAINT `fk_sub_categoria`
    FOREIGN KEY (`fk_sub_categoria`)
    REFERENCES `mercadeoucab`.`sub_categoria` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`telefono`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`telefono` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`telefono` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `telefono` VARCHAR(15) NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_dato_encuestado` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_telefono_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_dato_encuestado1`
    FOREIGN KEY (`fk_dato_encuestado`)
    REFERENCES `mercadeoucab`.`dato_encuestado` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`tipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`tipo` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`tipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_tipo_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mercadeoucab`.`tipo_solicitud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercadeoucab`.`tipo_solicitud` ;

CREATE TABLE IF NOT EXISTS `mercadeoucab`.`tipo_solicitud` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activo` TINYINT NOT NULL,
  `creado_el` TIMESTAMP NOT NULL,
  `modificado_el` TIMESTAMP NULL DEFAULT NULL,
  `fk_solicitud` INT NOT NULL,
  `fk_tipo` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_tipo_solicitud_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_solicitud_idx` (`fk_solicitud` ASC) VISIBLE,
  INDEX `fk_tipo_idx` (`fk_tipo` ASC) VISIBLE,
  CONSTRAINT `fk_solicitud3`
    FOREIGN KEY (`fk_solicitud`)
    REFERENCES `mercadeoucab`.`solicitud` (`id`),
  CONSTRAINT `fk_tipo`
    FOREIGN KEY (`fk_tipo`)
    REFERENCES `mercadeoucab`.`tipo` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;