package org.example;

public class PremiumLot implements ParkingLot {

    @Override
    public int calculateFee(Ticket ticket) {
// ToDo:  Use TDD to Implement this function
//  You will need to mock the Ticket interface
//  Business rules for this parking lot are as follows:
//        Basic fee is $4 per hour or fraction thereof
//        This means we always round up; ie, 4 hours 2 minutes is billed as 5 hours.
//        There is no pro-ration
//        Daily max is $27
//        Every 7th day or fraction thereof is free.  (ie, a stay of 13 days and 4 hours will be billed for 12 days

        int days = ticket.getDays();
        int hours = ticket.getHours();
        int mins = ticket.getMinutes();
        int freeDays = days/7;
        int actualBilledDays = 0;

        int dailyRate = 27;
        int hourlyRate =4;
        int cost = 0;

        if (ticket.getDays() >= 7) {
            if (ticket.getHours() >= 7) {
                actualBilledDays = ticket.getDays() + 1;
                cost = ((actualBilledDays - freeDays) * dailyRate);
            }
            return cost;
        } else if (ticket.getHours() < 7) {
            if (ticket.getMinutes() == 0) {
                actualBilledDays = ticket.getDays();
                cost = ((actualBilledDays - freeDays) * dailyRate) + ticket.getHours() * hourlyRate;
            } else {
                cost = ((actualBilledDays - freeDays) * dailyRate) + (ticket.getHours() * hourlyRate) + hourlyRate;
            }
            return cost;
        } else {
            cost = (ticket.getDays() * dailyRate) + (ticket.getHours() * hourlyRate);
            return cost;
        }

    }

}
