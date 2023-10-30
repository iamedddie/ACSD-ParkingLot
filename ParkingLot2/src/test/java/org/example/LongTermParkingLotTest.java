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

    double expectedResult;
    double actualResult;
    LocalDateTime entryTime,exitTime;





//    @ParameterizedTest
//    @CsvSource({"25/09/2023 03:58, 28/09/2023 04:59, 49",
//            "01/10/2023 08:30, 03/10/2023 08:29, 30",
//            "10/11/2023 12:15, 12/11/2023 12:16, 32",
//            "15/12/2023 00:01, 16/12/2023 00:00, 15",
//            })
//
//    public void longTermParkingTest(String entryTime, String exitTime, double expectedResult) throws IOException {
//        actualResult = uut.calculateFee(new Ticket(entryTime, exitTime, parkingType));
//
//        assertEquals(expectedResult, actualResult);
//
//    }

    @Test
    void testFirstDay30MinsIsFree() {
        when(mockTicket.getDays()).thenReturn(0);
        when(mockTicket.getMinutes()).thenReturn(40);
        when(mockTicket.getHours()).thenReturn(0);
       cost= uut.calculateFee(mockTicket);
       assertEquals(0,cost);
    }

    @ParameterizedTest
    @CsvSource({"29,0","30,0","31,2"})
    void testFirstDay30Mins(int time, double cost) {
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
    @CsvSource({"1,9,1,35","0,11,1,15","2,8,1,48"})
    void testGeneralCost(int days,int hrs,int min, double cost) {
        when(mockTicket.getDays()).thenReturn(days);
        when(mockTicket.getMinutes()).thenReturn(min);
        when(mockTicket.getHours()).thenReturn(hrs);
        assertEquals(cost,uut.calculateFee(mockTicket));
    }

}
