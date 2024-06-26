/*
MIT License

Copyright (c) 2024, Nuno Datia, Matilde Pato, ISEL

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
package isel.sisinf.ui;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import isel.sisinf.jpa.dal.entity.Cliente;
import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.entity.Reserva;
import isel.sisinf.jpa.dal.repo.BicicletaRepo;
import isel.sisinf.jpa.dal.repo.ReservaRepo;
import isel.sisinf.jpa.dal.service.BicicletaService;
import isel.sisinf.jpa.dal.service.ClienteService;
import isel.sisinf.jpa.dal.service.ReservaService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

interface DbWorker
{
    void doWork();
}
class UI
{
    private enum Option
    {
        // DO NOT CHANGE ANYTHING!
        Unknown,
        Exit,
        createCostumer,
        listExistingBikes,
        checkBikeAvailability,
        obtainBookings,
        makeBooking,
        cancelBooking,
        about
    }
    private static UI __instance = null;
  
    private HashMap<Option,DbWorker> __dbMethods;

    private UI()
    {
        // DO NOT CHANGE ANYTHING!
        __dbMethods = new HashMap<Option,DbWorker>();
        __dbMethods.put(Option.createCostumer, () -> UI.this.createCostumer());
        __dbMethods.put(Option.listExistingBikes, () -> UI.this.listExistingBikes()); 
        __dbMethods.put(Option.checkBikeAvailability, () -> UI.this.checkBikeAvailability());
        __dbMethods.put(Option.obtainBookings, new DbWorker() {public void doWork() {UI.this.obtainBookings();}});
        __dbMethods.put(Option.makeBooking, new DbWorker() {public void doWork() {UI.this.makeBooking();}});
        __dbMethods.put(Option.cancelBooking, new DbWorker() {public void doWork() {UI.this.cancelBooking();}});
        __dbMethods.put(Option.about, new DbWorker() {public void doWork() {UI.this.about();}});

    }

    public static UI getInstance()
    {
        // DO NOT CHANGE ANYTHING!
        if(__instance == null)
        {
            __instance = new UI();
        }
        return __instance;
    }

    private Option DisplayMenu()
    {
        Option option = Option.Unknown;
        Scanner s = new Scanner(System.in); //Scanner closes System.in if you call close(). Don't do it
        try
        {
            // DO NOT CHANGE ANYTHING!
            System.out.println("Bicycle reservation");
            System.out.println();
            System.out.println("1. Exit");
            System.out.println("2. Create Costumer");
            System.out.println("3. List Existing Bikes");
            System.out.println("4. Check Bike Availability");
            System.out.println("5. Current Bookings");
            System.out.println("6. Make a booking");
            System.out.println("7. Cancel Booking");
            System.out.println("8. About");
            System.out.print(">");
            int result = s.nextInt();
            option = Option.values()[result];
        }
        catch(RuntimeException ex)
        {
            //nothing to do.
        }
        
        return option;

    }
    private static void clearConsole() throws Exception
    {
        // DO NOT CHANGE ANYTHING!
        for (int y = 0; y < 25; y++) //console is 80 columns and 25 lines
            System.out.println("\n");
    }

    public void Run() throws Exception
    {
        // DO NOT CHANGE ANYTHING!
        Option userInput;
        do
        {
            clearConsole();
            userInput = DisplayMenu();
            clearConsole();
            try
            {
                __dbMethods.get(userInput).doWork();
                System.in.read();
            }
            catch(NullPointerException ex)
            {
                //Nothing to do. The option was not a valid one. Read another.
            }

        }while(userInput!=Option.Exit);
    }

    /**
    To implement from this point forward. Do not need to change the code above.
    -------------------------------------------------------------------------------     
        IMPORTANT:
    --- DO NOT MOVE IN THE CODE ABOVE. JUST HAVE TO IMPLEMENT THE METHODS BELOW ---
    --- Other Methods and properties can be added to support implementation -------
    -------------------------------------------------------------------------------
    
    */

    private void createCostumer() {
        Scanner scanner = new Scanner(System.in);

        // Prompting user for customer details
        System.out.println("Enter customer name:");
        String name = scanner.nextLine();

        System.out.println("Enter customer address:");
        String address = scanner.nextLine();

        System.out.println("Enter customer email:");
        String email = scanner.nextLine();

        // check if email is valid
        if (!isValidEmail(email)) {
            System.out.println("Error: Invalid email format. Please enter a valid email.");
            return;
        }

        System.out.println("Enter customer phone number:");
        String phone = scanner.nextLine();

        // Validating phone number format
        if (!isValidPhoneNumber(phone)) {
            System.out.println("Error: Invalid phone number format. Please enter a valid phone number.");
            return;
        }

        System.out.println("Enter customer ID number or Passport number:");
        String numeroCCPassaporte = scanner.nextLine();

        // Validating ID number format
        if (!isValidIDNumberFormat(numeroCCPassaporte)) {
            System.out.println("Error: Invalid ID number format. Please enter a valid ID number.");
            return;
        }

        // Checking if a customer with the same ID number already exists in the database
        ClienteService clienteService = new ClienteService();
        boolean exists = clienteService.checkIfExistsCCPassaporte(numeroCCPassaporte);
        if (exists) {
            System.out.println("Error: A customer with the same ID number already exists in the database.");
            return;
        }

        System.out.println("Enter customer nationality:");
        String nationality = scanner.nextLine();

        // Creating a new customer object
        Cliente cliente = new Cliente();
        cliente.setNome(name);
        cliente.setMorada(address);
        cliente.setEnderecoEletronico(email);
        cliente.setNumeroTelefone(phone);
        cliente.setNumeroCCPassaporte(numeroCCPassaporte);
        cliente.setNacionalidade(nationality);

        // Saving the new customer to the database
        clienteService.createClient(cliente);

        System.out.println("Customer created successfully!");
    }

    // Auxiliary method to validate email format
    private boolean isValidEmail(String email) {
        // Email must have the following format: <string>@<string>.<string>
        return email.matches("\\w+@\\w+\\.\\w+");
    }

    // Auxiliary method to validate phone number format
    private boolean isValidPhoneNumber(String phone) {
        // Phone number must have 9 digits
        return phone.matches("\\d{9}");
    }

    // Auxiliary method to validate ID number format
    private boolean isValidIDNumberFormat(String idNumber) {
        // ID number must have 8 digits
        return idNumber.matches("\\d{8}");
    }

    private void listExistingBikes() {
        List<Bicicleta> bikes = BicicletaService.listBikes();
        System.out.println("Bikes:");
        for (Bicicleta bike : bikes) {
            System.out.println("Identifier: " + bike.getIdentificador());
            System.out.println("Model: " + bike.getModelo());
            System.out.println("Brand: " + bike.getMarca());
            System.out.println("Number of speeds: " + bike.getNumeroVelocidades());
            System.out.println("State: " + bike.getEstado());
            System.out.println("Weight: " + bike.getPesoGramas() + " grams");
            System.out.println("Max speed: " + bike.getVelocidadeMaxima() + " km/h");
            if (bike.getAutonomia() != 0) {
                System.out.println("Autonomy: " + bike.getAutonomia() + " km");
            }
            System.out.println();
        }
    }

    private void checkBikeAvailability()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter bike identifier:");
        String bikeIdentifier = scanner.nextLine();

        // Check bike availability
        boolean isAvailable = BicicletaRepo.BicicletaRepository.checkBikeAvailability(Integer.valueOf(bikeIdentifier));

        if (isAvailable) {
            System.out.println("Bike is available.");
        } else {
            System.out.println("Bike is not available.");
        }
    }

    private void obtainBookings() {
        List<Reserva> bookings = ReservaRepo.ReservaRepository.listBookings();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("Bookings:");
        for (Reserva booking : bookings) {
            System.out.println("Booking number: " + booking.getNumeroReserva());
            System.out.println("Customer: " + booking.getNumeroCliente());
            System.out.println("Bike: " + booking.getbicicleta().getIdentificador());
            System.out.println("Start date: " + booking.getDataInicio().format(formatter));
            System.out.println("End date: " + booking.getDataFim().format(formatter));
            System.out.println("Value to pay: " + String.format("%.2f", booking.getValorPagar()));
            System.out.println();
        }
    }

    private void makeBooking() {
        Scanner scanner = new Scanner(System.in);

        // Display the list of available bikes to the user
        System.out.println("Available bikes:");
        List<Bicicleta> availableBikes = BicicletaRepo.BicicletaRepository.listBikes().stream()
                .filter(bike -> "livre".equals(bike.getEstado())) // Only include bikes that are "livre"
                .toList();
        for (int i = 0; i < availableBikes.size(); i++) {
            System.out.println((i + 1) + ". " + availableBikes.get(i).getIdentificador());
        }

        // Prompt the user to select a bike
        int bikeIndex = -1;
        while (bikeIndex < 0 || bikeIndex >= availableBikes.size()) {
            System.out.println("Enter the number of the bike you want to book:");
            try {
                bikeIndex = Integer.parseInt(scanner.nextLine()) - 1;
                if (bikeIndex < 0 || bikeIndex >= availableBikes.size()) {
                    System.out.println("Invalid bike number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Get the selected bike
        Bicicleta selectedBike = BicicletaRepo.BicicletaRepository.getBicicleta(availableBikes.get(bikeIndex).getIdentificador());

        // Prompt the user to input customer ID
        String customerId = "";
        boolean customerExists = false;
        while (!customerExists) {
            System.out.println("Enter customer ID:");
            customerId = scanner.nextLine();
            // Check if customer ID is empty or not a number
            if (!customerId.isEmpty() && customerId.matches("\\d+")) {
                // Check if customer exists in the database
                if (ReservaRepo.ReservaRepository.customerExists(Integer.parseInt(customerId))) {
                    customerExists = true;
                } else {
                    System.out.println("Customer does not exist. Please enter a valid ID.");
                }
            } else {
                System.out.println("Invalid customer ID. Please enter a valid ID.");
            }
        }


        LocalDateTime startDate = null;
        while (startDate == null) {
            try {
                System.out.println("Enter start date (yyyy-MM-dd HH:mm:ss):");
                startDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        LocalDateTime endDate = null;
        while (endDate == null) {
            try {
                System.out.println("Enter end date (yyyy-MM-dd HH:mm:ss):");
                endDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        // Generate a random value for valorPagar with 2 decimal places
        Random random = new Random();
        double valorPagar = 0.0 + (100.0 - 0.0) * random.nextDouble();
        // Round to 2 decimal places
        BigDecimal formattedValorPagar = BigDecimal.valueOf(valorPagar).setScale(2, RoundingMode.HALF_UP);

        // Create booking
        Reserva reserva = new Reserva();
        reserva.setNumeroReserva(ReservaRepo.ReservaRepository.getNextBookingNumber());
        reserva.setNumeroCliente(Integer.parseInt(customerId));
        reserva.setDataInicio(startDate);
        reserva.setDataFim(endDate);
        reserva.setValorPagar(formattedValorPagar.doubleValue());
        reserva.setBicicleta(selectedBike);

        // Save the booking
        ReservaService.createBooking(reserva);

        // Update the state of the bike to "em reserva"
        BicicletaRepo.BicicletaRepository.updateBikeState(selectedBike.getIdentificador(), "em reserva");

        System.out.println("Booking created successfully with the following details:");
        System.out.println("Booking Number: " + reserva.getNumeroReserva());
        System.out.println("Customer ID: " + reserva.getNumeroCliente());
        System.out.println("Bike ID: " + selectedBike.getIdentificador());
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Amount to Pay: " + String.format("%.2f", formattedValorPagar));
    }

    /*
    * Done in a way that the user can cancel all the bookings available (since we dont know wich user is looged in)
     */

    private void cancelBooking() {
        Scanner scanner = new Scanner(System.in);

        // Display the list of reservations with bikes in reservation state
        System.out.println("Reservations with bikes currently in reservation:");
        List<Object[]> reservations = ReservaRepo.ReservaRepository.listReservedBikesWithBookings();
        if (reservations.isEmpty()) {
            System.out.println("No bookings to cancel.");
            scanner.nextLine(); // Wait for Enter key press
            return;
        }

        for (Object[] reservation : reservations) {
            System.out.println("Booking ID: " + reservation[0] + ", Bike ID: " + reservation[1]);
        }

        // Prompt the user to enter the booking ID to cancel
        System.out.println("Enter booking id:");
        String bookingNumber = scanner.nextLine();

        // Check if the entered booking ID is in the list of presented bookings
        boolean isBookingIdValid = false;
        for (Object[] reservation : reservations) {
            if (reservation[0].toString().equals(bookingNumber)) {
                isBookingIdValid = true;
                break;
            }
        }

        if (!isBookingIdValid) {
            System.out.println("Invalid booking ID. Please enter a valid booking ID from the list.");
            return;
        }

        // Proceed with cancellation
        ReservaService reservaService = new ReservaService();
        reservaService.removeBooking(Integer.valueOf(bookingNumber));

        System.out.println("Booking cancelled successfully.");
    }

    /*
    private void cancelBookingOptimisticLocking(){
        Scanner scanner = new Scanner(System.in);

        // Display the list of reservations with bikes in reservation state
        System.out.println("Reservations with bikes currently in reservation:");
        List<Object[]> reservations = ReservaRepo.ReservaRepository.listReservedBikesWithBookings();
        for (Object[] reservation : reservations) {
            System.out.println("Booking ID: " + reservation[0] + ", Bike ID: " + reservation[1]);
        }

        // Prompt the user to enter the booking ID to cancel
        System.out.println("Enter booking id:");
        String bookingNumber = scanner.nextLine();

        // Proceed with cancellation
        ReservaService reservaService = new ReservaService();
        reservaService.cancellBookingWithOptimisticLock(Integer.valueOf(bookingNumber));

        System.out.println("Booking cancelled successfully.");
    }

     */

    private void about()
    {
        System.out.println("Group ID: 9");
        System.out.println("Members:");
        System.out.println("- Afonso Santos");
        System.out.println("- Marcel Dabrowski");
        System.out.println("- Tiago Neiva");


        System.out.println("DAL version:"+ Dal.version());
        System.out.println("Core version:"+ isel.sisinf.model.Core.version());
        
    }
}

public class App{
    public static void main(String[] args) throws Exception{
        try {
            UI.getInstance().Run();
        }finally {

            // Close the EntityManagerFactory so that the application can exit
            Dal.closeEntityManagerFactory();
        }
    }
}