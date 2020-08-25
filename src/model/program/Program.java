package model.program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;


/**
 *
 * @author Evandro
 */
public class Program {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
        System.out.println(" Enter rental data ");
        System.out.print(" Car model: ");
        String model = sc.nextLine();
        System.out.println();
        System.out.print(" Pickup (dd/MM/yyyy HH:ss): ");
        Date start = sdf.parse(sc.nextLine());
        System.out.print(" Return (dd/MM/yyyy HH:ss): ");
        Date finish = sdf.parse(sc.nextLine());

        CarRental carRental = new CarRental(start, finish, new Vehicle(model));

        System.out.println();
        System.out.print(" Enter price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print(" Enter price per day: ");
        double pricePerDay = sc.nextDouble();
        RentalService service = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
        service.prossessingInvoice(carRental);
        sc.nextLine();
        System.out.println(" INVOICE: ");
        System.out.println(" Basic payment: R%" + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
        System.out.println(" Tax: R%" + String.format("%.2f", carRental.getInvoice().getTax()));
        System.out.println(" Total payment: R$" + String.format("%.2f", carRental.getInvoice().getTotalPayement()));
        sc.close();
    }
}
