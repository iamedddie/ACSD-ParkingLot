package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class LongTermParkingLotTest {
    LongTermParkingLot uut;
    Ticket mockTicket;
    int cost;

    @BeforeEach
    void setUp() {
        mockTicket = Mockito.mock(Ticket.class);
        uut = new LongTermParkingLot();

    }

    @AfterEach
    void tearDown() {
        uut = null;
    }


    @Test
    void testFirstDay30MinsIsFree() {
        when(mockTicket.getDays()).thenReturn(0);
        when(mockTicket.getMinutes()).thenReturn(30);
        when(mockTicket.getHours()).thenReturn(0);
       cost= uut.calculateFee(mockTicket);
       assertEquals(0,cost);
    }

    @ParameterizedTest
    @CsvSource({"29,0","31,2","31,2"})
    void testFirstDay30Min(int time, double cost) {
        when(mockTicket.getDays()).thenReturn(0);
        when(mockTicket.getMinutes()).thenReturn(time);
        when(mockTicket.getHours()).thenReturn(0);
        assertEquals(cost,uut.calculateFee(mockTicket));
    }


    @ParameterizedTest
    @CsvSource({"9,1,20","11,1,15","8,1,18"})
    void testDailyMax(int hrs,int min, double cost) {
        when(mockTicket.getDays()).thenReturn(0);
        when(mockTicket.getMinutes()).thenReturn(min);
        when(mockTicket.getHours()).thenReturn(hrs);
        assertEquals(cost,uut.calculateFee(mockTicket));
    }

    @ParameterizedTest
    @CsvSource({"10,9,1,170","0,11,1,15","2,8,1,48"})
    void testGeneralCost(int days,int hrs,int min, double cost) {
        when(mockTicket.getDays()).thenReturn(days);
        when(mockTicket.getMinutes()).thenReturn(min);
        when(mockTicket.getHours()).thenReturn(hrs);
        assertEquals(cost,uut.calculateFee(mockTicket));
    }

}
