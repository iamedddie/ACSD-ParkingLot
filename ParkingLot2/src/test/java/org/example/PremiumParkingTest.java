package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PremiumParkingTest {
    PremiumLot uut;
    Ticket mockTicket;

    @BeforeEach
    void setUp() {
        mockTicket = Mockito.mock(Ticket.class);
        uut = new PremiumLot();
    }

    @AfterEach
    void tearDown() {
        uut = null;
    }

    double expectedResult;
    double actualResult;
    LocalDateTime entryTime,exitTime;

    @ParameterizedTest
    @CsvSource({"0,1,4","1,1,8","6,0,24","7,0,27","7,1,27"})
    void testFirstDay(int hour, int minutes, double cost) {
        when(mockTicket.getDays()).thenReturn(0);
        when(mockTicket.getMinutes()).thenReturn(minutes);
        when(mockTicket.getHours()).thenReturn(hour);
        assertEquals(cost,uut.calculateFee(mockTicket));
    }


    @ParameterizedTest
    @CsvSource({"9,0,0,216","11,1,1,278","15,0,1,355", "13,4,30,324"})
    void testFreeDays(int days,int hours, int minutes, double cost) {
        when(mockTicket.getDays()).thenReturn(days);
        when(mockTicket.getMinutes()).thenReturn(minutes);
        when(mockTicket.getHours()).thenReturn(hours);
        assertEquals(cost,uut.calculateFee(mockTicket));
    }

//
//    @ParameterizedTest
//    @CsvSource({"1,9,1,35","0,11,1,15","2,8,1,48"})
//    void testGeneralCost(int days,int hrs,int min, double cost) {
//        when(mockTicket.getDays()).thenReturn(days);
//        when(mockTicket.getMinutes()).thenReturn(min);
//        when(mockTicket.getHours()).thenReturn(hrs);
//        assertEquals(cost,uut.calculateFee(mockTicket));
//    }


}
