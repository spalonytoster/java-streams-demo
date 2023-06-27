package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.Double.parseDouble;
import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.minBy;

    //Napisz funkcje Double getAverageMin(String[][]) ktora przyjmuje 2 wymiarowa tablice stringow. Wierwszem sa pary klucz wartosc.
    //Funkcja ma zwrocic calkowita srednia (jedna liczbe) z minimalnych wartosci - (z pominieciem wartosci < 1 )dla kazdego klucza.
    //uzyj wyrazen labda i streamow

@Component
public class AverageValueCalculator {
    public static final double MINIMUM_THRESHOLD = 1.0;

    public Double calculate(String[][] keyValueEntries) {
        Map<String, Optional<Entry>> minimumEntriesGroupedByKey = Stream.of(keyValueEntries)
                .map(toEntry())
                .filter(aboveThreshold())
                .collect(groupingBy(
                        Entry::key,
                        minBy(comparingDouble(Entry::value)))
                );

        return minimumEntriesGroupedByKey
                .values()
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .mapToDouble(Entry::value)
                .average()
                .orElseThrow();
    }

    private static Function<String[], Entry> toEntry() {
        return entry -> new Entry(entry[0], parseDouble(entry[1]));
    }

    private static Predicate<Entry> aboveThreshold() {
        return entry -> entry.value > MINIMUM_THRESHOLD;
    }

    private record Entry(String key, Double value) {
    }
}
