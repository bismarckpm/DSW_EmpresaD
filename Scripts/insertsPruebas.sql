INSERT INTO `mercadeoucab`.`pais`
(
`id`,
`nombre`,
`activo`,
`creado_el`)
VALUES
(
1,
"Venezuela",
1,
now());

INSERT INTO `mercadeoucab`.`estado`
(`id`,
`nombre`,
`activo`,
`creado_el`,
`fk_pais`)
VALUES
(1,
"Dto.Capital",
1,
now(),
1);

INSERT INTO `mercadeoucab`.`municipio`
(`id`,
`nombre`,
`activo`,
`creado_el`,
`fk_estado`)
VALUES
(1,
"Sucre",
1,
now(),
1);

INSERT INTO `mercadeoucab`.`parroquia`
(`id`,
`nombre`,
`valor_socio_economico`,
`activo`,
`creado_el`,
`fk_municipio`)
VALUES
(1,
"Los robles",
30000,
1,
now(),
1);

INSERT INTO `mercadeoucab`.`categoria`
(`id`,
`nombre`,
`activo`,
`creado_el`)
VALUES
(1,
"Bebida",
1,
now());

INSERT INTO `mercadeoucab`.`sub_categoria`
(`id`,
`nombre`,
`activo`,
`creado_el`,
`fk_categoria`)
VALUES
(1,
"Alholica",
1,
now(),
1);

INSERT INTO `mercadeoucab`.`presentacion`
(`id`,
`cantidad`,
`tipo`,
`activo`,
`creado_el`)
VALUES
(1,
"presentacion",
"volumen",
1,
now());

INSERT INTO `mercadeoucab`.`marca`
(`id`,
`nombre`,
`activo`,
`creado_el`)
VALUES
(1,
"Pepsi",
1,
now());

INSERT INTO `mercadeoucab`.`ocupacion`
(`id`,
`nombre`,
`activo`,
`creado_el`)
VALUES
(1,
"Streamer",
1,
now());

INSERT INTO `mercadeoucab`.`tipo`
(`id`,
`nombre`,
`activo`,
`creado_el`)
VALUES
(1,
"Cosa",
1,
now());

INSERT INTO `mercadeoucab`.`usuario`
(`id`,
`nombre`,
`apellido`,
`correo`,
`rol`,
`estado`,
`activo`,
`creado_el`)
VALUES
(1,
"Daren",
"Gonzalez",
"mail@mail.com",
"administrador",
"activo",
1,
now());

INSERT INTO `mercadeoucab`.`solicitud`
(`id`,
`estado`,
`activo`,
`creado_el`,
`fk_usuario`,
`fk_marca`)
VALUES
(1,
"solicitada",
1,
now(),
1,
1);

INSERT INTO `mercadeoucab`.`muestra_poblacion`
(`id`,
`genero`,
`nivel_economico`,
`nivel_academico`,
`rango_edad_inicio`,
`rango_edad_fin`,
`cantidad_hijos`,
`activo`,
`creado_el`,
`fk_lugar`,
`fk_ocupacion`)
VALUES
(1,
"masculino",
700,
"universitario",
20,
35,
2,
1,
now(),
1,
1);

INSERT INTO `mercadeoucab`.`estudio`
(`id`,
`estado`,
`tipo`,
`encuestas_esperadas`,
`activo`,
`creado_el`,
`fk_solicitud`,
`fk_usuario`,
`fk_muestra_poblacion`)
VALUES
(1,
"En ejecucion",
"En linea",
20,
1,
now(),
1,
1,
1);

INSERT INTO `mercadeoucab`.`dato_encuestado`
(`id`,
`segundo_nombre`,
`segundo_apellido`,
`cedula`,
`medio_conexion`,
`edad`,
`genero`,
`nivel_economico`,
`nivel_academico`,
`personas_hogar`,
`activo`,
`creado_el`,
`fk_usuario`,
`fk_lugar`,
`fk_ocupacion`)
VALUES
(1,
"Concepcion",
"Arevalo",
"25545624",
"Laptop",
"1997-02-28",
"masculino",
50000,
"universitario",
4,
1,
now(),
1,
1,
1);

INSERT INTO `mercadeoucab`.`hijo`
(`id`,
`genero`,
`edad`,
`activo`,
`creado_el`,
`fk_dato_encuestado`)
VALUES
(1,
"masculino",
now(),
1,
now(),
1);

INSERT INTO `mercadeoucab`.`telefono`
(`id`,
`telefono`,
`activo`,
`creado_el`,
`fk_dato_encuestado`)
VALUES
(1,
"04125784358",
1,
now(),
1);

INSERT INTO `mercadeoucab`.`pregunta`
(`id`,
`nombre_pregunta`,
`tipo`,
`rango`,
`activo`,
`creado_el`,
`fk_usuario`)
VALUES
(1,
"pregunta",
"abierta",
"rango",
1,
now(),
1);
INSERT INTO `mercadeoucab`.`pregunta`
(`id`,
`nombre_pregunta`,
`tipo`,
`rango`,
`activo`,
`creado_el`,
`fk_usuario`)
VALUES
(2,
"pregunta",
"abierta",
"rango",
1,
now(),
1);
INSERT INTO `mercadeoucab`.`pregunta`
(`id`,
`nombre_pregunta`,
`tipo`,
`rango`,
`activo`,
`creado_el`,
`fk_usuario`)
VALUES
(3,
"pregunta",
"abierta",
"rango",
1,
now(),
1);
INSERT INTO `mercadeoucab`.`pregunta`
(`id`,
`nombre_pregunta`,
`tipo`,
`rango`,
`activo`,
`creado_el`,
`fk_usuario`)
VALUES
(4,
"pregunta",
"abierta",
"rango",
1,
now(),
1);
INSERT INTO `mercadeoucab`.`pregunta`
(`id`,
`nombre_pregunta`,
`tipo`,
`rango`,
`activo`,
`creado_el`,
`fk_usuario`)
VALUES
(5,
"pregunta",
"abierta",
"rango",
1,
now(),
1);
INSERT INTO `mercadeoucab`.`pregunta`
(`id`,
`nombre_pregunta`,
`tipo`,
`rango`,
`activo`,
`creado_el`,
`fk_usuario`)
VALUES
(6,
"pregunta",
"abierta",
"rango",
1,
now(),
1);
INSERT INTO `mercadeoucab`.`pregunta`
(`id`,
`nombre_pregunta`,
`tipo`,
`rango`,
`activo`,
`creado_el`,
`fk_usuario`)
VALUES
(7,
"pregunta",
"abierta",
"rango",
1,
now(),
1);
INSERT INTO `mercadeoucab`.`pregunta`
(`id`,
`nombre_pregunta`,
`tipo`,
`rango`,
`activo`,
`creado_el`,
`fk_usuario`)
VALUES
(8,
"pregunta",
"abierta",
"rango",
1,
now(),
1);

INSERT INTO `mercadeoucab`.`opcion`
(`id`,
`nombre_opcion`,
`activo`,
`creado_el`,
`fk_pregunta`)
VALUES
(1,
"opcion",
1,
now(),
1);

INSERT INTO `mercadeoucab`.`encuesta_estudio`
(`id`,
`fk_pregunta`,
`fk_estudio`)
VALUES
(1,
1,
1);

INSERT INTO `mercadeoucab`.`respuesta`
(`id`,
`respuesta`,
`activo`,
`creado_el`,
`fk_usuario`,
`fk_opcion`,
`fk_encuesta_estudio`)
VALUES
(1,
"respuesta",
1,
now(),
1,
1,
1);