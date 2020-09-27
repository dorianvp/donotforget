-- Scripts para la creacion de la base de datos (tablas)

CREATE TABLE Categoria (
    id_categoria INTEGER PRIMARY KEY,
    nombre TEXT UNIQUE
);

CREATE TABLE Dia (
    id_dia INTEGER PRIMARY KEY, 
    dia TEXT
);

CREATE TABLE Evento (
    id_evento INTEGER PRIMARY KEY, 
    id_categoria INTEGER, 
    FOREIGN KEY (id_categoria)
        REFERENCES Categoria (id_categoria),
    titulo INTEGER,
    descripcion TEXT,
    fecha_inicio TEXT,
    fecha_finalizacion TEXT,
    tiempo_aviso_previo TEXT
);

CREATE TABLE Recordatorio (
    id_recordatorio INTEGER PRIMARY KEY,
    id_categoria INTEGER,
    FOREIGN KEY (id_categoria)
        REFERENCES Categoria (id_categoria),
    titulo TEXT,
    descripcion TEXT,
    fecha TEXT,
    completado INTEGER,
    repite_cada INTEGER,
    duracion_del_aviso TEXT
);

CREATE TABLE Exclusion_Recordatorio_Dia (
    id_recordatorio INTEGER PRIMARY KEY,
    FOREIGN KEY (id_recordatorio)
        REFERENCES Recordatorio (id_recordatorio),
    id_dia INTEGER PRIMARY KEY,
    FOREIGN KEY (id_dia)
        REFERENCES Dia (id_dia)
);