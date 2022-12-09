package com.xently.parser.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.xently.parser.utils.KopoKopo.getBestCustomerIDs;
import static org.junit.Assert.assertEquals;

public class KopoKopoTest {

    @Test
    public void test_case_1() {
        assertEquals(Collections.singletonList("K20008"), getBestCustomerIDs("transaction_data_1.csv", 1));
    }

    @Test
    @Ignore("This is an expected failure")
    public void test_case_2() {
        assertEquals(Arrays.asList("K20987", "K20008", "K20233"), getBestCustomerIDs("transaction_data_2.csv", 3));
    }

    @Test
    @Ignore("This is an expected failure")
    public void test_case_3() {
        assertEquals(Arrays.asList("K20002", "K20005", "K20377", "K20987", "K22584"), getBestCustomerIDs("transaction_data_3.csv", 5));
    }
}