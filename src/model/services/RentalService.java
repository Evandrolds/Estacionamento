package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

/**
 *
 * @author Evandro
 */
public class RentalService {

    private Double pricePerHour;
    private Double pricePerDay;
    private BrazilTaxService taxServices;

    public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService taxServices) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxServices = taxServices;
    }

    public RentalService() {
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void prossessingInvoice(CarRental carRental) {
        long start = carRental.getStart().getTime();
        long finish = carRental.getFinish().getTime();
        double hours = (double) (finish - start) / 1000 / 60 / 60;
        double basicPayment;
        if (hours <= 12.0) {
            basicPayment = pricePerHour * Math.ceil(hours);
        } else {
            basicPayment = pricePerDay * Math.ceil(hours / 24);

        }
        double tax = taxServices.tax(basicPayment);
        carRental.setInvoice(new Invoice(basicPayment, tax));
    }

    public BrazilTaxService getTexServices() {
        return taxServices;
    }

    public void setTexServices(BrazilTaxService texServices) {
        this.taxServices = texServices;
    }
}
