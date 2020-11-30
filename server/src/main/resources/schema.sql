-- Scripts para la creacion de la base de datos (tablas)
PRAGMA encoding = "UTF-8"; 
PRAGMA foreign_keys = ON;

CREATE TABLE Categoria (
    id_categoria INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT UNIQUE
);

CREATE TABLE Dia (
    id_dia INTEGER PRIMARY KEY AUTOINCREMENT, 
    dia TEXT
);

CREATE TABLE Evento (
    id_evento INTEGER PRIMARY KEY AUTOINCREMENT, 
    id_categoria INTEGER,
    titulo TEXT,
    descripcion TEXT,
    fecha_inicio TEXT,
    fecha_finalizacion TEXT,
    tiempo_aviso_previo TEXT, 
    FOREIGN KEY (id_categoria)
        REFERENCES Categoria (id_categoria)
        ON DELETE CASCADE
);

CREATE TABLE Recordatorio (
    id_recordatorio INTEGER PRIMARY KEY AUTOINCREMENT,
    id_categoria INTEGER,
    titulo TEXT,
    descripcion TEXT,
    fecha TEXT,
    completado INTEGER,
    repite_cada INTEGER,
    duracion_del_aviso TEXT,
    FOREIGN KEY (id_categoria)
        REFERENCES Categoria (id_categoria)
);

CREATE TABLE Exclusion_Recordatorio_Dia (
    id_recordatorio INTEGER,
    id_dia INTEGER,
    PRIMARY KEY (id_recordatorio, id_dia),
    FOREIGN KEY (id_recordatorio)
        REFERENCES Recordatorio (id_recordatorio),
    FOREIGN KEY (id_dia)
        REFERENCES Dia (id_dia)
);

INSERT INTO Dia (dia) 
    VALUES ("Lunes"), ("Martes"), ("Miércoles"), ("Jueves"), ("Viernes"), ("Sábado"), ("Domingo");
