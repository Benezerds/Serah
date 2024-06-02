package com.neztech.serah;


import com.neztech.serah.adapter.ReservationAdapter;
import com.neztech.serah.model.Reservation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReservationAdapterTest {

    private ReservationAdapter reservationAdapter;
    private List<Reservation> reservationList;

    @Before
    public void setUp() {
        reservationList = new ArrayList<>();
        // Add mock data to the list
        Reservation reservation1 = Mockito.mock(Reservation.class);
        Reservation reservation2 = Mockito.mock(Reservation.class);
        reservationList.add(reservation1);
        reservationList.add(reservation2);

        reservationAdapter = new ReservationAdapter(reservationList);
    }

    @Test
    public void getItemCount_returnsCorrectSize() {
        int itemCount = reservationAdapter.getItemCount();
        assertEquals(reservationList.size(), itemCount);
    }
}

