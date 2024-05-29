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

import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.service.BicicletaService;
import isel.sisinf.jpa.dal.service.ClienteService;
import isel.sisinf.jpa.dal.service.ReservaService;
import isel.sisinf.model.dto.BicicletaDTO;
import isel.sisinf.model.dto.ClienteDTO;
import isel.sisinf.model.dto.ReservaDTO;
import org.glassfish.jaxb.core.v2.TODO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    private static final int TAB_SIZE = 24;

    private void createCostumer() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer name:");
        String name = scanner.nextLine();
        System.out.println("Enter customer address:");
        String address = scanner.nextLine();
        System.out.println("Enter customer email:");
        String email = scanner.nextLine();
        System.out.println("Enter customer phone number:");
        String phone = scanner.nextLine();
        System.out.println("Enter customer numero do CC ou Passaporte:");
        String numeroCCPassaporte = scanner.nextLine();
        System.out.println("Enter customer nacionalidade:");
        String nacionalidade = scanner.nextLine();


        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(name);
        clienteDTO.setMorada(address);
        clienteDTO.setEnderecoEletronico(email);
        clienteDTO.setNumeroTelefone(phone);
        clienteDTO.setNumeroCCPassaporte(numeroCCPassaporte);
        clienteDTO.setNacionalidade(nacionalidade);


        ClienteService clienteService = new ClienteService();
        clienteService.createClient(clienteDTO);

        System.out.println("Customer created successfully!");
    }

    private void listExistingBikes() {
        List<BicicletaDTO> bikes = BicicletaService.listBikes();
        if (bikes != null) {
            System.out.println("Bikes:");
            for (BicicletaDTO bike : bikes) {
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
        } else {
            System.out.println("Error listing bikes");
        }
    }
    private void checkBikeAvailability()
    {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter bike identifier:");
        String bikeIdentifier = scanner.nextLine();

        // Create a BicicletaDTO object from the bikeIdentifier
        BicicletaDTO bike = new BicicletaDTO(bikeIdentifier);

        // Check bike availability
        boolean isAvailable = BicicletaService.checkBikeAvailability(bike);

        if (isAvailable) {
            System.out.println("Bike is available for rental.");
        } else {
            System.out.println("Bike is not available for rental.");
        }

    }

    private void obtainBookings() {
        //TODO()
    }

    private void makeBooking() {

        Scanner scanner = new Scanner(System.in);

        // Prompt the user to input booking details
        System.out.println("Enter customer id:");
        String customerName = scanner.nextLine();
        System.out.println("Enter bike identifier:");
        String bikeIdentifier = scanner.nextLine();
        System.out.println("Enter start date (yyyy-MM-dd HH:mm:ss):");
        LocalDateTime startDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("Enter end date (yyyy-MM-dd HH:mm:ss):");
        LocalDateTime endDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Create a ReservaDTO object with the provided details
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setNumeroCliente(customerName);
        reservaDTO.setNumeroBicicleta(bikeIdentifier);
        reservaDTO.setDataInicio(startDate);
        reservaDTO.setDataFim(endDate);

        // Call the createBooking method with the ReservaDTO object
        ReservaService.createBooking(reservaDTO);

        System.out.println("Booking created successfully!");
    }

    private void cancelBooking()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter booking number:");
        String bookingNumber = scanner.nextLine();

        ReservaService reservaService = new ReservaService();
        reservaService.cancelBooking(bookingNumber);
    }
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
        UI.getInstance().Run();
    }
}