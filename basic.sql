start transaction ;
do $$
begin

drop table if exists public.Cliente;
drop table if exists public.Bicicleta;
drop table if exists public.DispositivoGPS;
drop table if exists public.Loja;

CREATE TABLE Cliente (
     nome VARCHAR(255) NOT NULL,
     morada VARCHAR(255),
     endereco_eletronico VARCHAR(255),
     numero_telefone VARCHAR(20),
     numero_cc_passaporte VARCHAR(50),
     nacionalidade VARCHAR(100)
    );

CREATE TABLE Bicicleta (
   identificador VARCHAR(255) NOT NULL PRIMARY KEY,
   peso_gramas INT NOT NULL,
   modelo VARCHAR(255) NOT NULL,
   marca VARCHAR(255) NOT NULL,
   numero_velocidades INT NOT NULL,
   estado VARCHAR(50) NOT NULL,
   autonomia INT,
   velocidade_maxima INT
);

CREATE TABLE DispositivoGPS (
    numero_serie VARCHAR(255) NOT NULL PRIMARY KEY,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    percentagem_bateria INT NOT NULL
);

CREATE TABLE Loja (
    codigo VARCHAR(50) NOT NULL PRIMARY KEY,
    gestor VARCHAR(255) NOT NULL,
    morada VARCHAR(255) NOT NULL,
    localidade VARCHAR(100) NOT NULL,
    numero_telefone VARCHAR(20) NOT NULL,
    endereco_eletronico VARCHAR(255) NOT NULL
);

CREATE TABLE Reserva (
     numero_reserva VARCHAR(50) NOT NULL PRIMARY KEY,
     data_inicio TIMESTAMP NOT NULL,
     data_fim TIMESTAMP NOT NULL,
     valor_pagar DOUBLE PRECISION NOT NULL
);





end;
$$ Language 'plpgsql';