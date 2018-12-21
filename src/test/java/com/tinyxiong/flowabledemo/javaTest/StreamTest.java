package com.tinyxiong.flowabledemo.javaTest;

import com.jayway.jsonpath.internal.function.numeric.Average;
import lombok.Data;
import org.joda.time.Period;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

public class StreamTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private List<Person> personList = Arrays.asList(
            new Person("刘德华", 47, Person.Sex.MALE, LocalDate.parse("1988-12-01", formatter), "中山市"),
            new Person("福原爱", 28, Person.Sex.FEMALE, LocalDate.parse("1990-08-05", formatter), "日本"),
            new Person("黎明", 50, Person.Sex.MALE, LocalDate.parse("1986-02-01", formatter), "香港"),
            new Person("李小龙", 53, Person.Sex.MALE, LocalDate.parse("1974-12-11", formatter), "广东"),
            new Person("张学友", 49, Person.Sex.MALE, LocalDate.parse("1979-10-28", formatter), "台州"),
            new Person("郭富城", 47, Person.Sex.MALE, LocalDate.parse("2000-06-13", formatter), "东莞市"),
            new Person("周杰伦", 25, Person.Sex.MALE, LocalDate.parse("1993-11-04", formatter), "中国台湾"),
            new Person("陈奕迅", 52, Person.Sex.MALE, LocalDate.parse("1984-04-15", formatter), "铜锣湾"),
            new Person("Lady GaGa", 30, Person.Sex.FEMALE, LocalDate.parse("1989-12-23", formatter), "New York"),
            new Person("Avail", 25, Person.Sex.FEMALE, LocalDate.parse("1993-01-02", formatter), "洛杉矶")
    );

    @Test
    public void filter() {
        personList.stream()
                .filter(p -> p.getGender() == Person.Sex.FEMALE)
                .forEach(System.out::println);
    }

    @Test
    public void average() {
        double age = personList.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();
        System.out.println(age);
    }

    @Test
    public void totalAge() {
        int total = personList.stream()
                .mapToInt(Person::getAge)
                .sum();
        System.out.println(total);
    }

    @Test
    public void reduceSum() {
        int total = personList.stream()
                .mapToInt(Person::getAge)
                .reduce(0, (sum, n) -> sum + n);
        System.out.println(total);

        Integer total2 = personList.stream()
                .map(Person::getAge)
                .reduce(0, (sum, n) -> sum + n);
        System.out.println("total2: " + total2);
    }

    @Test
    public void averager() {
        Averager averageCollect = personList.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .map(Person::getAge)
                .collect(Averager::new, Averager::accept, Averager::combine);

        System.out.println("Average age of male members: " +
                averageCollect.average());
    }

    @Test
    public void collectList() {
        List<String> names = personList.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(names);
    }

    @Test
    public void goupByGenger() {
        Map<Person.Sex, List<String>> groupByGender = personList.stream()
                .collect(Collectors.groupingBy(Person::getGender
                        , Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println(groupByGender);
    }

    @Test
    public void groupByAndReduce() {
        Map<Person.Sex, Integer> ages = personList.stream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.reducing(0, Person::getAge, Integer::sum)));
        System.out.println(ages);
    }

    @Test
    public void groupByAndAverage() {
        Map<Person.Sex, Double> ages = personList.stream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.averagingInt(Person::getAge)));
        System.out.println(ages);
    }
}

@Data
class Person {

    public enum Sex {
        MALE, FEMALE;
    }

    public Person(String name, Integer age, Sex gender, LocalDate birthday, String emailAddress) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.emailAddress = emailAddress;
    }

    private String name;
    private Integer age;
    private Sex gender;
    private LocalDate birthday;
    private String emailAddress;
}

class Averager implements IntConsumer
{
    private int total = 0;
    private int count = 0;

    public double average() {
        return count > 0 ? ((double) total)/count : 0;
    }

    public void accept(int i) { total += i; count++; }
    public void combine(Averager other) {
        total += other.total;
        count += other.count;
    }
}

