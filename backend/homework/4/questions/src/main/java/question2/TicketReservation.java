package question2;

import com.ganesh.LogMaster;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class TicketReservation {

    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();

    public List<Passenger> getConfirmedList() {
        return confirmedList;
    }
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass, String confirmationNumber) {
        if(confirmedList.size() == CONFIRMEDLIST_LIMIT && waitingList.size() == WAITINGLIST_LIMIT){
            return false;
        }
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);
        if(confirmedList.size() == CONFIRMEDLIST_LIMIT){
            waitingList.add(passenger);
        }
        else{
            confirmedList.add(passenger);
        }
        return true;
    }

    public boolean cancel(String confirmationNumber) {
        Iterator<Passenger> it = confirmedList.iterator();
        if(removePassenger(it,confirmationNumber)){
            confirmedList.add(waitingList.poll());
            return true;
        }
        it = waitingList.iterator();
        return removePassenger(it,confirmationNumber);
    }

    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while (iterator.hasNext()){
            Passenger passenger = iterator.next();
            if(passenger.getConfirmationNumber().equals(confirmationNumber)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

}