    drop database if exists prubea;
    create schema prubea;
    use prubea;

    insert into tipo_encuesta (id_tipo_encuesta, ds_tipo_encuesta) values  ("1","Personalizada"), ("2","Satisfaccion");

-- Insert Tipo Pregunta
    insert into Tipo_Pregunta (id_tipo_pregunta, ds_tipo_pregunta) values ("1","Texto corto"), ("2","Texto largo"),
            ("3","Numerica"), ("4","Fecha"),("5","Opcion multiple, unica respuesta"),("6","Opcion multiple, multiple respuesta");

    SELECT * FROM prubea.encuesta;

    SELECT * FROM prubea.preguntae;

    SELECT * FROM prubea.tipo_encuesta;

    SELECT * FROM prubea.tipo_pregunta;

    insert into prubea.tipo_encuesta (id_tipo_encuesta, ds_tipo_encuesta) values  ("1","Personalizada"), ("2","Satisfaccion");