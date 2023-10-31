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


        int dailyRate = 27;
        int hourlyRate =4;
        int cost = 0;


        if (mins > 0) {
            hours++;
        }

        // If hours are 7 or more, consider it as an additional day, but not if it leads to a free day
        if (hours >= 7 && (days + 1) % 7 != 0) {
            days++;
            hours -= 7;
        }


        int freeDays = days/7;
        int actualBilledDays = days - freeDays;

        cost += actualBilledDays * dailyRate;

        // If there are 6 hours or less, charge hourly rate but don't exceed daily rate
        if (hours <= 6) {
            int hourlyCharge = Math.min(hours * hourlyRate, dailyRate);
            cost += hourlyCharge;
        }


        return cost;

    }

}
