package org.example;

public class NewPremiumLot implements ParkingLot {
    TicketImpl ticket = new TicketImpl();

    @Override
    public int calculateFee(Ticket ticket) {
// ToDo:  Use TDD to Implement this function
//  You will need to mock the Ticket interface
//  Business rules for this parking lot are as follows:
//        Basic fee is $4 per hour or fraction thereof
//        This means we always round up; ie, 4 hours 2 minutes is billed as 5 hours.
//        There is no pro-ration
//        Daily max is $27
//        Every 7th day or fraction thereof is free.
//        (ie, a stay of 13 days and 4 hours will be billed for 12 days


        int dailyRate = 27;
        int hourlyRate = 4;
        int cost = 0;
        int freeDays = (ticket.getDays()+1) / 7;
        int maxDay= 10;

//

        if (ticket.getDays() < 7) {
            if (ticket.getHours() >= maxDay) {
                cost = getDailyCost(ticket, dailyRate) + (dailyRate);
                return cost;
            } else if (ticket.getDays()==6&&(ticket.getMinutes() >= 0 || ticket.getHours() >= 0) ) {
                cost = getDailyCost(ticket, dailyRate);
                return cost;
            }
            else if (ticket.getHours() <maxDay && ticket.getMinutes() > 0) {
                cost = getDailyCost(ticket, dailyRate) + getHourlyCost(ticket, hourlyRate) + (hourlyRate);
                return cost;
            } else {
                cost = getDailyCost(ticket, dailyRate) + getHourlyCost(ticket, hourlyRate);
                return cost;
            }
        } else if (ticket.getDays() >= 7) {
            if (ticket.getDays() % 7 == 6 && (ticket.getMinutes() > 0 || ticket.getHours() > 0)) {
                cost = ((ticket.getDays() + 1) - freeDays) * dailyRate;
                return cost;
            } else if (ticket.getDays() % 7 <= 6) {
                if (ticket.getMinutes() == 0 && ticket.getHours() == 0) {
                    cost = getFreeDaysCost(ticket, dailyRate, freeDays);
                    return cost;
                } else if (ticket.getMinutes() > 0 && ticket.getHours() < maxDay) {
                    cost = getFreeDaysCost(ticket, dailyRate, freeDays) + getHourlyCost(ticket, hourlyRate) + (hourlyRate);
                    return cost;
                } else if (ticket.getMinutes() == 0 && ticket.getHours() < maxDay) {
                    cost = getFreeDaysCost(ticket, dailyRate, freeDays) + getHourlyCost(ticket, hourlyRate);
                    return cost;
                } else {
                    cost = getFreeDaysCost(ticket, dailyRate, freeDays) + dailyRate;
                    return cost;
                }
            }
        }


        return cost;
    }

    private  int getFreeDaysCost(Ticket ticket, int dailyRate, int freeDays) {
        return ((ticket.getDays()) - freeDays) * dailyRate;
    }

    private  int getHourlyCost(Ticket ticket, int hourlyRate) {
        return ticket.getHours() * hourlyRate;
    }

    private int getDailyCost(Ticket ticket, int dailyRate) {
        return ticket.getDays() * dailyRate;
    }
}
