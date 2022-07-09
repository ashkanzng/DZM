package com.dzm.app.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    @Test
    void testEquals() {
        Station stationA = Station.builder().stationName("A").build();
        Station stationB = Station.builder().stationName("B").build();
        stationA.setId(1L);
        stationB.setId(1L);
        assertEquals(stationA,stationB);
        stationB.setId(2L);
        assertNotEquals(stationA,stationB);
    }
}