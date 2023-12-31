package com.example.demo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AverageValueCalculatorTest {
    private final AverageValueCalculator averageValueCalculator;

    AverageValueCalculatorTest() {
        this.averageValueCalculator = new AverageValueCalculator();
    }

    @ParameterizedTest
    @MethodSource("getTestCases")
    void shouldCalculateAverageOfTheMinimumValues(String[][] keyValueEntries, double expected) {
        Double avgOfMinValues = averageValueCalculator.calculate(keyValueEntries);

        assertThat(avgOfMinValues).isEqualTo(expected);
    }

    public static Stream<Arguments> getTestCases() {
        String[][] originalTestCase = {
                { "Object", "65"   },
                { "Person", "91.0" },
                { "Animal", "23.0" },
                { "Object", "83.0" },
                { "Animal", "10.0" },
                { "Animal", "0.5"  }
        };


        String[][] someMoreComplicatedTestCase = {
                { "Object", "65"   },
                { "Person", "91.0" },
                { "Animal", "23.0" },
                { "Object", "83.0" },
                { "Object", "0.0" },
                { "Animal", "15.0" },
                { "Animal", "13.0" },
                { "Animal", "0.5"  },
                { "Person", "0.21" },
                { "Dog", "0.21" },
                { "Cat", "50." }
        };

        return Stream.of(
                Arguments.of((Object) originalTestCase, (65.0 + 91.0 + 10.0) / 3.),
                Arguments.of((Object) someMoreComplicatedTestCase, (65. + 91. + 13. + 50.) / 4)
        );
    }
}