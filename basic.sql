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
     ENDERECOELETRONICO VARCHAR(255),
     MORADA VARCHAR(255),
     NACIONALIDADE VARCHAR(255),
     NOME VARCHAR(255),
     NUMEROCCPASSAPORTE VARCHAR(255),
     NUMEROTELEFONE VARCHAR(255)
    );

CREATE TABLE Bicicleta (
   identificador INT NOT NULL PRIMARY KEY,
   pesoGramas INT NOT NULL,
   modelo VARCHAR(255) NOT NULL,
   marca VARCHAR(255) NOT NULL,
   numeroVelocidades INT NOT NULL,
   estado VARCHAR(50) NOT NULL,
   autonomia INT,
   velocidadeMaxima INT
);

CREATE TABLE DispositivoGPS (
    numeroSerie INT NOT NULL PRIMARY KEY,
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
     numeroReserva INT NOT NULL PRIMARY KEY,
     numeroCliente INT NOT NULL,
     numeroBicicleta INT NOT NULL,
     datainicio TIMESTAMP NOT NULL,
     datafim TIMESTAMP NOT NULL,
     valorPagar DOUBLE PRECISION NOT NULL
);

end;
$$ Language 'plpgsql';
commit;


-- insert data to bicicleta
INSERT INTO Bicicleta (identificador, pesogramas, modelo, marca, numerovelocidades, estado, autonomia, velocidadeMaxima) VALUES
     (1, 15000, 'Mountain Pro', 'BikeBrand', 21, 'livre', 0, NULL),
     (2, 13500, 'City Cruiser', 'UrbanCycles', 7, 'livre', 0, NULL),
     (3, 16000, 'Road Racer', 'Speedster', 18, 'ocupado', 0, NULL),
     (4, 12500, 'Hybrid Elite', 'EcoWheels', 24, 'ocupado', 0, NULL),
     (5, 18000, 'Electric Adventure', 'VoltBikes', 8, 'em reserva', 50, 25),
     (6, 14000, 'Folding Mini', 'CompactRides', 6, 'livre', 0, NULL),
     (7, 15500, 'Touring Expert', 'Traveler', 27, 'ocupado', 0, NULL),
     (8, 13000, 'Single Speed', 'Simplicity', 1, 'em reserva', 0, NULL),
     (9, 17500, 'Electric Commuter', 'ElectroRide', 5, 'em manutenção', 40, 20),
     (10, 12000, 'Kids Fun', 'TinyRiders', 3, 'livre', 0, NULL);

INSERT INTO Reserva (numeroReserva, numeroCliente, numeroBicicleta, dataInicio, dataFim, valorPagar) VALUES
     (1, 1, 1, '2022-01-01 00:00:00', '2022-01-02 00:00:00', 10.0),
     (2, 2, 2, '2022-01-03 00:00:00', '2022-01-04 00:00:00', 20.0),
     (3, 3, 3, '2022-01-05 00:00:00', '2022-01-06 00:00:00', 30.0),
     (4, 4, 4, '2022-01-07 00:00:00', '2022-01-08 00:00:00', 40.0),
     (5, 5, 5, '2022-01-09 00:00:00', '2022-01-10 00:00:00', 50.0),
     (6, 6, 6, '2022-01-11 00:00:00', '2022-01-12 00:00:00', 60.0),
     (7, 7, 7, '2022-01-13 00:00:00', '2022-01-14 00:00:00', 70.0),
     (8, 8, 8, '2022-01-15 00:00:00', '2022-01-16 00:00:00', 80.0),
     (9, 9, 9, '2022-01-17 00:00:00', '2022-01-18 00:00:00', 90.0),
     (10, 10, 10, '2022-01-19 00:00:00', '2022-01-20 00:00:00', 100.0);


CREATE OR REPLACE PROCEDURE update_bike_state(bike_id INT, new_state VARCHAR)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE Bicicleta
    SET estado = new_state
    WHERE identificador = bike_id;
END;
$$;


