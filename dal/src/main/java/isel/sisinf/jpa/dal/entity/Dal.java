/*
MIT License

Copyright (c) 2024, Nuno Datia, ISEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package isel.sisinf.jpa.dal.entity;

import isel.sisinf.model.dto.BicicletaDTO;
import isel.sisinf.model.dto.ClienteDTO;
import isel.sisinf.model.dto.ReservaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dal
{

    private static final String DB_URL = "sisinfvlab0.dyn.fil.isel.pt"; // Replace with your database URL
    private static final String USER = "t41dg9"; // Replace with your database username
    private static final String PASS = "isel2024"; // Replace with your database password
    private final Connection connection;

    //For Demonstration purpose only
    public static String version(){ return "1.0";}

    public Dal() {
        try {
            // Initialize the database connection
            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to the database", e);
        }
    }

    public void createCustomer(ClienteDTO customer) {
        // Create a new customer in the database
        String query = "INSERT INTO Cliente (nome, morada, endereco_eletronico, numero_telefone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getNome());
            stmt.setString(2, customer.getMorada());
            stmt.setString(3, customer.getEnderecoEletronico());
            stmt.setString(4, customer.getNumeroTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            finish();
        }
    }

    public List<BicicletaDTO> listBikes() throws SQLException {
        List<BicicletaDTO> bikes = new ArrayList<>();
        String query = "SELECT * FROM Bicicleta";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                BicicletaDTO bike = new BicicletaDTO();
                bike.setIdentificador(rs.getString("identificador"));
                bike.setModelo(rs.getString("modelo"));
                bike.setMarca(rs.getString("marca"));
                // Set other fields of BicicletaDTO
                bikes.add(bike);
            }
        }finally {
            finish();
        }
        return bikes;
    }

    public boolean checkBikeAvailability(BicicletaDTO bike) {
        // Check if the bike is available
        String query = "SELECT * FROM Bicicleta WHERE identificador = ? AND estado = 'Disponivel'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, bike.getIdentificador());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            finish();
        }
    }

    public List<ReservaDTO> listBookings() throws SQLException {
        List<ReservaDTO> bookings = new ArrayList<>();
        String query = "SELECT * FROM Reserva";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ReservaDTO reservaDTO = new ReservaDTO();
                reservaDTO.setNumeroReserva(rs.getString("numeroReserva"));
                // Set other fields of ReservaDTO
                bookings.add(reservaDTO);
            }
        }finally {
            finish();
        }
        return bookings;
    }


    public void createBooking(ReservaDTO reservaDTO) {
        // Create a new booking in the database
        String query = "INSERT INTO Reserva (numeroCliente, numeroBicicleta, data_inicio, data_fim) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, reservaDTO.getNumeroCliente());
            stmt.setString(2, reservaDTO.getNumeroBicicleta());
            stmt.setTimestamp(3, Timestamp.valueOf(reservaDTO.getDataInicio()));
            stmt.setTimestamp(4, Timestamp.valueOf(reservaDTO.getDataFim()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            finish();
        }
    }

    public void cancelBooking(String numeroReserva) {
        // Cancel the booking using the reservation number
        String query = "DELETE FROM Reserva WHERE numeroReserva = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, numeroReserva);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            finish();
        }
    }

    public void finish() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}