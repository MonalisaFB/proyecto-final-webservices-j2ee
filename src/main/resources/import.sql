INSERT INTO tematica (nombre) VALUES ('Copa do Mundo');
INSERT INTO tematica (nombre) VALUES ('Países');
INSERT INTO tematica (nombre) VALUES ('Cultura');
INSERT INTO tematica (nombre) VALUES ('Comida');

-- PreguntaSeleccionUnica: 'Qual país ganhou a Copa do Mundo de 2022?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, opcion_correcta) VALUES ('Qual país ganhou a Copa do Mundo de 2022?', 'SELECCION_UNICA', 1, 'Argentina');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (1, 'Brasil'), (1, 'Argentina'), (1, 'França'), (1, 'Alemanha');

-- PreguntaVerdaderoFalso: 'A Copa do Mundo de 2014 foi realizada no Brasil.'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, respuesta_vf) VALUES ('A Copa do Mundo de 2014 foi realizada no Brasil.', 'VERDADERO_FALSO', 1, TRUE);

-- PreguntaSeleccionMultiple: 'Quais países ganharam a Copa do Mundo mais de uma vez?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id) VALUES ('Quais países ganharam a Copa do Mundo mais de uma vez?', 'SELECCION_MULTIPLE', 1);
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (3, 'Brasil'), (3, 'Alemanha'), (3, 'Itália'), (3, 'Argentina');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta) VALUES (3, 'Brasil'), (3, 'Alemanha'), (3, 'Itália'), (3, 'Argentina');

-- PreguntaSeleccionUnica: 'Em que ano foi realizada a primeira Copa do Mundo?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, opcion_correcta) VALUES ('Em que ano foi realizada a primeira Copa do Mundo?', 'SELECCION_UNICA', 1, '1930');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (4, '1926'), (4, '1930'), (4, '1934'), (4, '1938');

-- PreguntaSeleccionUnica: 'Qual é o país mais populoso do mundo?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, opcion_correcta) VALUES ('Qual é o país mais populoso do mundo?', 'SELECCION_UNICA', 2, 'Índia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (5, 'China'), (5, 'Índia'), (5, 'Estados Unidos'), (5, 'Indonésia');

-- PreguntaVerdaderoFalso: 'O Canadá é o maior país do mundo em área territorial.'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, respuesta_vf) VALUES ('O Canadá é o maior país do mundo em área territorial.', 'VERDADERO_FALSO', 2, FALSE);

-- PreguntaSeleccionMultiple: 'Quais países fazem fronteira com o Brasil?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id) VALUES ('Quais países fazem fronteira com o Brasil?', 'SELECCION_MULTIPLE', 2);
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (7, 'Argentina'), (7, 'Colômbia'), (7, 'Peru'), (7, 'Chile');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta) VALUES (7, 'Argentina'), (7, 'Colômbia'), (7, 'Peru');

-- PreguntaSeleccionUnica: 'Qual é a capital do Japão?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, opcion_correcta) VALUES ('Qual é a capital do Japão?', 'SELECCION_UNICA', 2, 'Tóquio');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (8, 'Pequim'), (8, 'Seul'), (8, 'Tóquio'), (8, 'Bangkok');

-- PreguntaSeleccionUnica: 'Quem pintou a Mona Lisa?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, opcion_correcta) VALUES ('Quem pintou a Mona Lisa?', 'SELECCION_UNICA', 3, 'Leonardo da Vinci');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (9, 'Michelangelo'), (9, 'Leonardo da Vinci'), (9, 'Rafael'), (9, 'Van Gogh');

-- PreguntaVerdaderoFalso: 'O samba é um gênero musical originário da Argentina.'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, respuesta_vf) VALUES ('O samba é um gênero musical originário da Argentina.', 'VERDADERO_FALSO', 3, FALSE);

-- PreguntaSeleccionMultiple: 'Quais são considerados patrimônios culturais da humanidade?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id) VALUES ('Quais são considerados patrimônios culturais da humanidade?', 'SELECCION_MULTIPLE', 3);
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (11, 'Machu Picchu'), (11, 'Cristo Redentor'), (11, 'Coliseu'), (11, 'Times Square');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta) VALUES (11, 'Machu Picchu'), (11, 'Cristo Redentor'), (11, 'Coliseu');

-- PreguntaSeleccionUnica: 'Em que país surgiu o tango?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, opcion_correcta) VALUES ('Em que país surgiu o tango?', 'SELECCION_UNICA', 3, 'Argentina');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (12, 'Brasil'), (12, 'Argentina'), (12, 'Uruguai'), (12, 'Espanha');

-- PreguntaSeleccionUnica: 'Qual é o ingrediente principal do guacamole?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, opcion_correcta) VALUES ('Qual é o ingrediente principal do guacamole?', 'SELECCION_UNICA', 4, 'Abacate');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (13, 'Tomate'), (13, 'Abacate'), (13, 'Cebola'), (13, 'Limão');

-- PreguntaVerdaderoFalso: 'A paella é um prato típico do México.'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, respuesta_vf) VALUES ('A paella é um prato típico do México.', 'VERDADERO_FALSO', 4, FALSE);

-- PreguntaSeleccionMultiple: 'Quais são ingredientes comuns na culinária japonesa?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id) VALUES ('Quais são ingredientes comuns na culinária japonesa?', 'SELECCION_MULTIPLE', 4);
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (15, 'Arroz'), (15, 'Peixe'), (15, 'Alga'), (15, 'Soja');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta) VALUES (15, 'Arroz'), (15, 'Peixe'), (15, 'Alga'), (15, 'Soja');

-- PreguntaSeleccionUnica: 'Qual país é conhecido como o berço da pizza?'
INSERT INTO pregunta (enunciado, tipo_pregunta, tematica_id, opcion_correcta) VALUES ('Qual país é conhecido como o berço da pizza?', 'SELECCION_UNICA', 4, 'Itália');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (16, 'França'), (16, 'Espanha'), (16, 'Itália'), (16, 'Grécia');
