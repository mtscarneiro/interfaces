package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.CarRental;
import entities.Vehicle;
import services.BrazilTaxService;
import services.RentalService;

public class App {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        System.out.println("Enter rental data");
        System.out.println("Car model: ");
        String model = input.nextLine();
        System.out.println("Pickup (dd/MM/yyyy HH:ss): ");
        Date start = sdf.parse(input.nextLine());
        System.out.println("Return (dd/MM/yyyy HH:ss): ");
        Date finish = sdf.parse(input.nextLine());

        CarRental cr = new CarRental(start, finish, new Vehicle(model));

        System.out.println("Enter price per hour: ");
        double pricePerHour = input.nextDouble();
        System.out.println("Enter price per day: ");
        double pricePerDay = input.nextDouble();

        RentalService rentServ = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());

        rentServ.processInvoice(cr);

        System.out.println("INVOICE: ");
        System.out.println("Basic payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
        System.out.println("Total payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));

        input.close();
    }
}
