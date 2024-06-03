start transaction ;
do
$$
begin
drop table if exists Cliente;
drop table if exists Bicicleta;
drop table if exists DispositivoGPS;
drop table if exists Loja;
drop table if exists Reserva;

CREATE TABLE Cliente (
     NUMEROCLIENTE INT PRIMARY KEY,
     EMAIL VARCHAR(255),
     MORADA VARCHAR(255),
     NACIONALIDADE VARCHAR(255),
     NOME VARCHAR(255),
     NUMEROCCPASSAPORTE VARCHAR(255),
     TELEFONE VARCHAR(255)
    );

CREATE TABLE Bicicleta (
   identificador VARCHAR(255) NOT NULL PRIMARY KEY,
   pesoGramas INT NOT NULL,
   modelo VARCHAR(255) NOT NULL,
   marca VARCHAR(255) NOT NULL,
   numeroVelocidades INT NOT NULL,
   estado VARCHAR(50) NOT NULL,
   autonomia INT,
   velocidadeMaxima INT
);

CREATE TABLE DispositivoGPS (
    numeroSerie VARCHAR(255) NOT NULL PRIMARY KEY,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    percentagemBateria INT NOT NULL
);

CREATE TABLE Loja (
    codigo VARCHAR(50) NOT NULL PRIMARY KEY,
    gestor VARCHAR(255) NOT NULL,
    morada VARCHAR(255) NOT NULL,
    localidade VARCHAR(100) NOT NULL,
    numeroTelefone VARCHAR(20) NOT NULL,
    enderecoEletronico VARCHAR(255) NOT NULL
);

CREATE TABLE Reserva (
     numeroReserva VARCHAR(50) NOT NULL PRIMARY KEY,
     datainicio TIMESTAMP NOT NULL,
     datafim TIMESTAMP NOT NULL,
     valorPagar DOUBLE PRECISION NOT NULL
);

end;
$$ Language 'plpgsql';
commit;


-- insert data to bicicleta
INSERT INTO Bicicleta (identificador, pesogramas, modelo, marca, numerovelocidades, estado, autonomia, velocidadeMaxima) VALUES
    ('BIKE001', 15000, 'Mountain Pro', 'BikeBrand', 21, 'livre', 0, NULL),
    ('BIKE002', 13500, 'City Cruiser', 'UrbanCycles', 7, 'livre', 0, NULL),
    ('BIKE003', 16000, 'Road Racer', 'Speedster', 18, 'ocupado', 0, NULL),
    ('BIKE004', 12500, 'Hybrid Elite', 'EcoWheels', 24, 'ocupado', 0, NULL),
    ('BIKE005', 18000, 'Electric Adventure', 'VoltBikes', 8, 'em reserva', 50, 25),
    ('BIKE006', 14000, 'Folding Mini', 'CompactRides', 6, 'livre', 0, NULL),
    ('BIKE007', 15500, 'Touring Expert', 'Traveler', 27, 'ocupado', 0, NULL),
    ('BIKE008', 13000, 'Single Speed', 'Simplicity', 1, 'em reserva', 0, NULL),
    ('BIKE009', 17500, 'Electric Commuter', 'ElectroRide', 5, 'em manutenção', 40, 20),
    ('BIKE010', 12000, 'Kids Fun', 'TinyRiders', 3, 'livre', 0, NULL);

