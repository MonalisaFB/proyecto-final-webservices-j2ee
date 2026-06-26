INSERT INTO tematica (nombre) VALUES ('Copa do Mundo');
INSERT INTO tematica (nombre) VALUES ('Países');
INSERT INTO tematica (nombre) VALUES ('Cultura');
INSERT INTO tematica (nombre) VALUES ('Comida');

INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Qual país ganhou a Copa do Mundo de 2022?', 'UNICA', 'Argentina', STRINGDECODE('Brasil\nArgentina\nFrança\nAlemanha'), 1);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('A Copa do Mundo de 2014 foi realizada no Brasil.', 'VF', 'Verdadeiro', NULL, 1);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Quais países ganharam a Copa do Mundo mais de uma vez?', 'MULTIPLE', 'Brasil,Alemanha,Itália,Argentina', STRINGDECODE('Brasil\nAlemanha\nItália\nArgentina'), 1);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Em que ano foi realizada a primeira Copa do Mundo?', 'UNICA', '1930', STRINGDECODE('1926\n1930\n1934\n1938'), 1);

INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Qual é o país mais populoso do mundo?', 'UNICA', 'Índia', STRINGDECODE('China\nÍndia\nEstados Unidos\nIndonésia'), 2);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('O Canadá é o maior país do mundo em área territorial.', 'VF', 'Falso', NULL, 2);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Quais países fazem fronteira com o Brasil?', 'MULTIPLE', 'Argentina,Colômbia,Peru', STRINGDECODE('Argentina\nColômbia\nPeru\nChile'), 2);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Qual é a capital do Japão?', 'UNICA', 'Tóquio', STRINGDECODE('Pequim\nSeul\nTóquio\nBangkok'), 2);

INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Quem pintou a Mona Lisa?', 'UNICA', 'Leonardo da Vinci', STRINGDECODE('Michelangelo\nLeonardo da Vinci\nRafael\nVan Gogh'), 3);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('O samba é um gênero musical originário da Argentina.', 'VF', 'Falso', NULL, 3);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Quais são considerados patrimônios culturais da humanidade?', 'MULTIPLE', 'Machu Picchu,Cristo Redentor,Coliseu', STRINGDECODE('Machu Picchu\nCristo Redentor\nColiseu\nTimes Square'), 3);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Em que país surgiu o tango?', 'UNICA', 'Argentina', STRINGDECODE('Brasil\nArgentina\nUruguai\nEspanha'), 3);

INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Qual é o ingrediente principal do guacamole?', 'UNICA', 'Abacate', STRINGDECODE('Tomate\nAbacate\nCebola\nLimão'), 4);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('A paella é um prato típico do México.', 'VF', 'Falso', NULL, 4);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Quais são ingredientes comuns na culinária japonesa?', 'MULTIPLE', 'Arroz,Peixe,Alga,Soja', STRINGDECODE('Arroz\nPeixe\nAlga\nSoja'), 4);
INSERT INTO pregunta (enunciado, tipo, respuesta_correcta, opciones, tematica_id) VALUES ('Qual país é conhecido como o berço da pizza?', 'UNICA', 'Itália', STRINGDECODE('França\nEspanha\nItália\nGrécia'), 4);