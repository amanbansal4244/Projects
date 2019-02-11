package Movie;

import java.util.Enumeration;
import java.util.Vector;

class Customer {
	private String _name;
	private Vector _rentals = new Vector();

	public Customer (String name){
		_name = name;
	};

	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}
	public String getName (){
		return _name;
	};

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			double thisAmount = 0;
			Rental each = (Rental) rentals.nextElement();

			//determine amounts for each line
			thisAmount = each.getCharge();

			// add frequent renter points
			frequentRenterPoints+= each.getFrequentRenterPoints();

			
			//show figures for this rental
			result += "\t" + each.getMovie().getTitle()+ "\t" +
					String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;

		}
		//add footer lines
		result +=  "Amount owed is " + String.valueOf(/*totalAmount*/getTotalCharge()) + "\n";
		result += "You earned " + String.valueOf(/*frequentRenterPoints*/ getTotalFrequentRenterPoints()) +
				" frequent renter points";
		return result;
	}

	private double getTotalCharge() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }
	
	private int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }
	
	private double amountFor(Rental aRental) {
		return aRental.getCharge();
	}

}