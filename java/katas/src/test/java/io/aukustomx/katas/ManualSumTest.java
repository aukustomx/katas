package io.aukustomx.katas;

import io.aukustomx.katas.manualsum.ManualSum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManualSumTest {

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource(value = {
            "1,1,2",
            "121212121212,1,121212121213",
            "123,456,579",
            "888,222,1110",
            "1372,69,1441",
            "12,456,468",
            "100,101,201",
            "63829983432984289347293874,90938498237058927340892374089,91002328220491911630239667963"
    })
    @DisplayName("Sample tests")
    void basicTests(String a, String b, String expected) {
        assertEquals(expected, ManualSum.add(a, b));
    }
}
