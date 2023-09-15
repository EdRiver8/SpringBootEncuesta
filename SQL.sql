drop database if exists prubea;
create schema prubea;
use prubea;

SELECT * FROM prubea.encuesta;

SELECT * FROM prubea.preguntae;

SELECT * FROM prubea.respuesta;

SELECT * FROM prubea.tipo_encuesta;

SELECT * FROM prubea.tipo_pregunta;

SELECT * FROM prubea.persona;

SELECT * FROM prubea.persona_encuesta;
    
-- Insert Tipo Encuesta
insert into Tipo_Encuesta (id_tipo_encuesta ,ds_tipo_encuesta) values ("1","Personalizada"), ("2","Satisfaccion");

-- Insert Tipo Pregunta
insert into Tipo_Pregunta (id_tipo_pregunta, ds_tipo_pregunta) values ("1","TEXTO"), ("2","NUMERO"), ("3","FECHA"), ("4","MULTIPLE");

-- Insert Encuesta
-- En el campo id_poblacion_objetivo se dejará null cuando la encuesta no esté relacionada a ningun curso, por el contrario se deja el id de la oferta
insert into Encuesta(id_encuesta, ds_encuesta, nombre_encuesta, es_encuesta, cantidad_preguntas, id_tipo_encuesta) 
    values ("1","Descripcion1","Encuesta 1", "Activada", 2, "2"); -- satis activa
insert into Encuesta(id_encuesta,ds_encuesta, nombre_encuesta, es_encuesta, cantidad_preguntas, id_tipo_encuesta) 
    values ("2","Descripcion2","Encuesta 2", "Activada", 3, "1");-- perso activa
insert into Encuesta(id_encuesta,ds_encuesta, nombre_encuesta, es_encuesta, cantidad_preguntas, id_tipo_encuesta) 
    values ("3","Descripcion3","Encuesta 3", "Desactivada", 2, "2");-- satis desact
insert into Encuesta(id_encuesta,ds_encuesta, nombre_encuesta, es_encuesta, cantidad_preguntas, id_tipo_encuesta) 
    values ("4","Descripcion4","Encuesta 4", "Desactivada", 5, "1");-- perso desact
insert into Encuesta(id_encuesta,ds_encuesta, nombre_encuesta, es_encuesta, cantidad_preguntas, id_tipo_encuesta) 
    values ("5","Descripcion5","Encuesta 5", "Activada", 3, "1");-- perso activa
    
insert into Persona (id_persona, nombre_persona, oferta) values ("1", "Edw", "Spring Boot"), ("2", "Sebas", "Python"), ("3", "Dani", "PHP"), ("4", "Nati", "Java");

-- Insert Persona_Encuesta
insert into Persona_Encuesta(id_persona_encuesta, es_respuesta, referencia, id_persona, id_encuesta, tipo_referencia) values ("1",True, "OF10", "1", "1", "Oferta");
insert into Persona_Encuesta(id_persona_encuesta,es_respuesta, id_persona, id_encuesta, tipo_referencia) values ("2",False, "2", "2", "Personalizada");
insert into Persona_Encuesta(id_persona_encuesta, es_respuesta, referencia, id_persona, id_encuesta, tipo_referencia) values ("3",False, "OF38", "3", "3", "Oferta");
insert into Persona_Encuesta(id_persona_encuesta,es_respuesta, id_persona, id_encuesta, tipo_referencia) values ("4", True, "4", "4", "Personalizada");
insert into Persona_Encuesta(id_persona_encuesta,es_respuesta, id_persona, id_encuesta, tipo_referencia) values ("5", True, "1", "5", "Personalizada");
insert into Persona_Encuesta(id_persona_encuesta, es_respuesta, referencia, id_persona, id_encuesta, tipo_referencia) values ("6", True, "OF49", "2", "1", "Oferta");
insert into Persona_Encuesta(id_persona_encuesta, es_respuesta, referencia, id_persona, id_encuesta, tipo_referencia) values ("7", False, "OF50", "3", "1", "Oferta");
insert into Persona_Encuesta(id_persona_encuesta,es_respuesta, id_persona, id_encuesta, tipo_referencia) values ("8", True, "4", "2", "Personalizada");
insert into Persona_Encuesta(id_persona_encuesta, es_respuesta, referencia, id_persona, id_encuesta, tipo_referencia) values ("9", False, "OF10", "1", "1", "Oferta");
insert into Persona_Encuesta(id_persona_encuesta, es_respuesta, referencia, id_persona, id_encuesta, tipo_referencia) values ("10", False, "OF49", "2", "3", "Oferta");

-- INSERT PREGUNTA
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("1",1,"Que te parecio el curso?",'{"subtype":"LARGA"}', "1", "1"); -- 1
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("2",2,"Cuando acabo el curso?",'{"options":""}', "1", "3"); -- 2

insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("3",1,"Quien fue el chapulin?",'{"subtype":"CORTA"}', "2", "1"); -- 3
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("4",2,"Copia un numero",'{"max":200,"min":-200}', "2", "2"); -- 4
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("5",3,"Cuantos años tienes?",'{"max":150,"min":0}', "2", "2"); -- 5

insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("6",1,"Quien fue el chapulin?",'{"subtype":"CORTA"}', "3", "1"); -- 6
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("7",2,"Donde vives?",'{"max":300,"min":0}', "3", "2"); -- 7

insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("8",1,"Describe una persona",'{"subtype":"LARGA"}', "4", "1"); -- 8
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("9",2,"Cuando inició su labor?",'{"options":""}', "4", "3"); -- 9
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("10",3,"Quien fue el chapulin?",'{"subtype":"CORTA"}', "4", "1"); -- 10
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("11",4,"Trabajas en sucursal?",'{"max":300,"min":0}', "4", "2"); -- 11
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("12",5,"El 2 es mayor que el 1?",'{"max":5,"min":0}', "4", "2"); -- 12

insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("13",1,"Describe algo",'{"subtype":"LARGA"}', "5", "1"); -- 13
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("14",2,"Cual es tu numero favorito",'{"max":1000,"min":-1000}', "5", "2"); -- 14
insert into Preguntae(id_pregunta,index_pregunta, ds_pregunta,opciones, id_encuesta, id_tipo_pregunta) values ("15",3,"Fecha de cumpleaños",'{"options":""}', "5", "3"); -- 15
 

-- INSERT RESPUESTA
-- Encuesta 1, tiene dos preguntas, una abierta y otra 4 opciones
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("1","Respuesta de texto", "1", "1");
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("2","31/08/2023", "2", "1");

-- Otra persona responde misma encuesta
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("3","Respuesta de texto", "1", "6");
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("4","22/05/2023", "2", "6");

-- Encuesta 2, tiene tres preguntas, una abierta y 2 f/v
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("5","Respuesta de texto", 3, "8");
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("6", "22", 4, "8");
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("7","30", 5, "8");

-- Encuesta 3, tiene 2 preguntas, una abierta y 2 f/v

-- Encuesta 4, tiene 5 preguntas, 2 abierta, 2 f/v y 1 Multip
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("8","Respuesta de texto", "8", "4");
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("9","12/05/2023", "9", "4");
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("10","Respuesta de texto", "10", "4");
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("11","Falso", "11", "4");
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("12","Verdadero", "12", "4");

-- Encuesta 5 tiene 3 preguntas, 1 abierta, 1 f/v y 1 Multip
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("13","Respuesta de texto", "13", "5");
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("14","12345", "14", "5");
insert into Respuesta(id_respuesta, respuesta, id_pregunta, id_persona_encuesta) values ("15","10/10/2021", "15" , "5");

