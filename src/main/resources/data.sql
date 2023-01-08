CREATE TABLE IF NOT EXISTS persona ( 
   id INT NOT NULL PRIMARY KEY auto_increment, 
   nombre VARCHAR(50) NOT NULL,
   genero VARCHAR(50) NOT NULL,
   edad INT NOT NULL,
   identificacion INT NOT NULL,
   direccion VARCHAR(50) NOT NULL,
   telefono VARCHAR(50) NOT NULL,
   fecha_creacion DATE NOT NULL,
   fecha_actualizacion DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente ( 
   id INT NOT NULL PRIMARY KEY auto_increment, 
   persona_id VARCHAR(50) NOT NULL,
   contrasenia VARCHAR(50) NOT NULL,
   estado INT NOT NULL,
   fecha_creacion DATE NOT NULL,
   fecha_actualizacion DATE NOT NULL,
   constraint persona_fk foreign key (persona_id) references persona(id)
);


CREATE TABLE IF NOT EXISTS cuenta ( 
   id INT NOT NULL PRIMARY KEY auto_increment,
   cliente_id INT NOT NULL, 
   numero_cuenta VARCHAR(50) NOT NULL,
   tipo_cuenta VARCHAR(50) NOT NULL,
   saldo_inicial DOUBLE NOT NULL,
   estado INT NOT NULL,
   clave varchar(120) NOT NULL,
   fecha_creacion DATE NOT NULL,
   fecha_actualizacion DATE NOT NULL,
   constraint cliente_fk foreign key (cliente_id) references cliente(id)
);


CREATE TABLE IF NOT EXISTS movimientos ( 
   id INT NOT NULL PRIMARY KEY auto_increment,
   cuenta_id INT NOT NULL,
   tipo_movimiento VARCHAR(50) NOT NULL,
   valor double NOT NULL,
   saldo double NOT NULL,
   fecha_creacion DATE NOT NULL,
   fecha_actualizacion DATE NOT NULL,
   constraint cuenta_fk foreign key (cuenta_id) references cuenta(id)
);


