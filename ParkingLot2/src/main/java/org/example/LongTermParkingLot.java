package org.example;

public class LongTermParkingLot implements ParkingLot{
    TicketImpl ticket= new TicketImpl();
    @Override
    public int calculateFee(Ticket ticket) {
// ToDo:  Use TDD to Implement this function
//  You will need to mock the Ticket interface
//  Business rules for this parking lot are as follows:
//        Basic fee is $2 per hour or fraction thereof
//        This means we always round up; ie, 4 hours 2 minutes is billed as 5 hours.
//        There is no pro-ration
//        Daily max is $15
//        On the first day, the first half hour is free

        int dailyRate = 15;
        int hourlyRate =2;
        int cost = 0;
        int maxDay= 10;
        if (ticket.getDays() < 1 && ticket.getHours() == 0 && ticket.getMinutes() <= 30) {
            cost = 0;
            return cost;
        } else if (ticket.getHours() >= maxDay) {
            cost = getDailyCost(ticket, dailyRate) + (dailyRate);
            return cost;
        } else if (ticket.getHours() < 10 && ticket.getMinutes() > 0) {
            cost = getDailyCost(ticket, dailyRate) + getHourlyCost(ticket, hourlyRate) + (hourlyRate);
            return cost;
        } else {
            cost = getDailyCost(ticket, dailyRate) + getHourlyCost(ticket, hourlyRate);
            return cost;
        }

    }

    private  int getHourlyCost(Ticket ticket, int hourlyRate) {
        return ticket.getHours() * hourlyRate;
    }

    private int getDailyCost(Ticket ticket, int dailyRate) {
        return ticket.getDays() * dailyRate;
    }
}
