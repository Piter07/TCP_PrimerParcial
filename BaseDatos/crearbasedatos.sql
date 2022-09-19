create table metereoDatos (
id_estacion integer not null,
ciudad varchar(100),
porcentaje_humedad float,
temperatura int,
velocidad_viento float,
fecha varchar(100),
hora varchar(100),
constraint id_estacion_pk primary key (id_estacion)
);