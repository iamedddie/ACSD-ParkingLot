package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class NewPremiumParkingTest {
    NewPremiumLot uut;
    Ticket mockTicket;

    @BeforeEach
    void setUp() {
        mockTicket = Mockito.mock(Ticket.class);
        uut = new NewPremiumLot();
    }

    @AfterEach
    void tearDown() {
        uut = null;
    }


    @ParameterizedTest
    @CsvSource({"0,1,4","1,1,8","6,0,24","7,0,28","7,1,32"})
    void testFirstDay(int hour, int minutes, double cost) {
        when(mockTicket.getDays()).thenReturn(0);
        when(mockTicket.getHours()).thenReturn(hour);
        when(mockTicket.getMinutes()).thenReturn(minutes);
        assertEquals(cost,uut.calculateFee(mockTicket));
    }

    @ParameterizedTest
    @CsvSource({"0,9,1,40","6,0,1,162","6,0,0,162","0,0,1,4","0,10,1,27"})
    void testFirstSixDays(int days,int hour, int minutes, double cost) {
        when(mockTicket.getDays()).thenReturn(days);
        when(mockTicket.getHours()).thenReturn(hour);
        when(mockTicket.getMinutes()).thenReturn(minutes);
        assertEquals(cost,uut.calculateFee(mockTicket));
    }


    @ParameterizedTest
    @CsvSource({"13,4,0,324","7,9,1,202","14,10,1,351","12,5,1,321","7,0,1,166"})
    void testFreeDays(int days,int hours, int minutes, double cost) {
        when(mockTicket.getDays()).thenReturn(days);
        when(mockTicket.getMinutes()).thenReturn(minutes);
        when(mockTicket.getHours()).thenReturn(hours);
        assertEquals(cost,uut.calculateFee(mockTicket));
    }



}
