package question2;

import com.ganesh.LogMaster;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TicketReservation mmt = new TicketReservation();
        String ticketBooked = "Ticket booked: ";
        String ticketCancelled = "Ticket cancelled: ";
        String businessClass = "business";
        String economyClass = "economy";
        LogMaster.print(ticketBooked + mmt.bookFlight("Ganesh","Setty",21,"male",businessClass,"A1"));
        LogMaster.print(ticketBooked + mmt.bookFlight("Khushi","Rana",21,"female",businessClass,"A2"));
        LogMaster.print(ticketBooked + mmt.bookFlight("Sahil","Malik",18,"male",economyClass,"A3"));
        LogMaster.print(ticketBooked + mmt.bookFlight("Ankush","Rauniyar",22,"male",businessClass,"A4"));
        LogMaster.print(ticketBooked + mmt.bookFlight("Hema","Nagraj",9,"female",businessClass,"A5"));
        LogMaster.print(ticketBooked + mmt.bookFlight("Harsh","Verma",22,"male",businessClass,"A6"));

        LogMaster.print(ticketCancelled + mmt.cancel("A3"));
        LogMaster.print(ticketCancelled + mmt.cancel("A6"));
        List<Passenger> cnf = mmt.getConfirmedList();

        LogMaster.print("Confirmation List:- ");
        for(Passenger p: cnf){
            if (p != null)
                LogMaster.print(p.getConfirmationNumber());
        }
    }
}