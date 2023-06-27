package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AverageValueCalculator {

    //Napisz funkcje Double getAverageMin(String[][]) ktora przyjmuje 2 wymiarowa tablice stringow. Wierwszem sa pary klucz wartosc.
    //Funkcja ma zwrocic calkowita srednia (jedna liczbe) z minimalnych wartosci - (z pominieciem wartosci < 1 )dla kazdego klucza.
    //uzyj wyrazen labda i streamow

    public Double getAverageMin(String[][] keyValueEntries) {

        Map<String, Optional<String[]>> minByKey = Arrays.stream(keyValueEntries)
                .filter(entry -> Double.parseDouble(entry[1]) >= 1.0)
                .collect(Collectors.groupingBy(entry -> entry[0],
                        Collectors.minBy(Comparator.comparingDouble(entry -> Double.parseDouble(entry[1])))
                    )
                );

        return minByKey.values().stream()
                .filter(Optional::isPresent)
                .mapToDouble(value -> Double.parseDouble(value.get()[1]))
                .average()
                .orElseThrow(() -> new ArithmeticException("Cannot calculate avg"));


        // jednak trochę się namęczyłem. powyżej jest finalna wersja już po kilku próbach i niestety odkrywania niektórych
        // sposobów na bieżąco z dokumentacji. poniżej pierwsze podejścia jeszcze bez wiedzy jak to najszybciej zrobić

        //        Map<String, Double> collect = Stream.of(keyValueEntries)
//                .filter(entry -> Double.parseDouble(entry[1]) > 1.0)
//                .collect(Collectors.groupingBy(
//                        arr -> arr[0],
//                        Collectors.mapping(arr -> Double.parseDouble(arr[1]), Collectors.reducing(BinaryOperator.minBy(Comparator.comparing())))
//                ));


//        Map<String, List<Entry>> groupedByKey = Stream.of(keyValueEntries)
//                .map(entry -> new Entry(entry[0], toDouble(entry[1])))
//                .filter(entry -> entry.value > 1.0)
//                .collect(Collectors.groupingBy(
//                        entry -> entry.key,
//                        Collectors.mapping(innerEntry -> innerEntry.value), Collectors.reducing(v1, v2 -> v1 < v2))
//                );

//        groupedByKey.values()
//                .stream()
    }
//
//    private Entry reduceToMinimumValue(Entry entry, Entry entry2) {
//        return entry.value() < entry2.value() ? entry : entry2;
//    }
//
//    private static double toDouble(String value) {
//        return Double.parseDouble(value);
//    }
//
//    private record Entry(String key, Double value) {
//    }
}
