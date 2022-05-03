package TestOne;


import com.sun.xml.internal.ws.util.StringUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestIntermediateOperator {
    List<String> myList = Arrays.asList("J", "a","v", "a", "R","a", "s","h" );
    List<Integer> myIntList = Arrays.asList(77,84,3,-99,58,-69,777,0,87,-3,12,47);

    public void testOne(){
        String[] array = {"Java", "Ruuuuusssshhhh"};
        Stream<String> streamOfArray = Arrays.stream(array);
        streamOfArray.map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);
    }
    public void TestTwo(){
        String[] array = {"Java", "Ruuuuusssshhhh"};
        Stream<String> streamOfArray = Arrays.stream(array);
        streamOfArray.map(s -> s.split("")).map(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);
    }
    public void TestThreeLimit(){
        Stream<String> stream = myList.stream();
        stream.sorted().distinct().limit(5).forEach(System.out::println);
    }
    public void TestFourSorted(){
        Stream<String> stream = myList.stream();
        stream.sorted().forEach(System.out::println);
    }
    public void TestFivePredicate(){
        Stream<Integer> myIntStream = myIntList.stream();
        myIntStream.map(x -> x > 0).forEach(System.out::println);
    }
    public void TestFindFirstOperator(){
       Optional<Integer> a = Stream.of(74,55,41,778,98).findFirst();
        System.out.println(a);
    }
    public void TestAllMatchOperator(){
        Stream<Integer> myInt = myIntList.stream();
          boolean b =  myInt.allMatch(x -> x < 100);
        System.out.println(b);
    }
    public void TestCollectorsJoining(){
        Stream<String> myStream = myList.stream();
        String a = myStream.collect(Collectors.joining("- -","(",")"));
        System.out.println(a);
    }
    public void TestCollectorsSumming(){
        Stream<Integer> myStream = myIntList.stream();

        long a = myStream.collect(Collectors.summingInt(x -> x));
        System.out.println(a);

    }

    public void TestFilterOperator(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        numbers.stream().filter(i -> i%2 == 0).forEach(System.out::println);
    }
    public void TestCountAndFilter(){
        List<String> names = Arrays.asList("John", "Jan", "Virion", "Marry", "Nikolay's", "Narek");
        long count = names.stream().filter(a -> a.length() > 4).count();
        System.out.println(count);
    }

    public void TestMultipleFilter(){
        List<String> names = Arrays.asList("John", "Jan", "Virion", "Marry", null, "", "Nikolay's", "Narek");
        String n = names.stream().filter(Objects::nonNull).filter(i -> !i.isEmpty() && i.contains("a"))
                .collect(Collectors.joining(": ","(",")"));
        System.out.println(n);
    }
    public void changeFirstSymbol(){
        List<String> names = Arrays.asList("narek", "davit", "anag");
        names.stream().map(StringUtils::capitalize).forEach(System.out::println);
    }
    public void carClassMapTester(){
        List<Car> cars = Arrays.asList(
                new Car("AA1111BX", 2007),
                new Car("AM1111BE", 2009),
                new Car("AK5555IT", 2010),
                new Car("AI5601CC", 2015),
                new Car("AI3838PP", 2017));
        String a = cars.stream().map(Car::getNumber).collect(Collectors.joining(": ","{ "," }"));
        System.out.println(a);
    }

    public void filterCars(){
        List<Car> cars = Arrays.asList(
                new Car("AA1111BX", 2007),
                new Car("AK5555IT", 2001),
                new Car(null, 2012),
                new Car("", 2015),
                new Car("AI3838PP", 2017));

        cars.stream().filter(a -> a.getYear() > 2010).map(Car::getNumber).filter(b -> b != null && !b.isEmpty())
                .forEach(System.out::println);
    }
    public void collectEvenNumbers(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> evenNumbers = numbers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(evenNumbers);
    }
    public void collectSetNames(){
        List<String> names = Arrays.asList("John", "Narek", "Anag", "David", "Narek");
        Set<String> upperCaseName = names.stream().map(String::toUpperCase).collect(Collectors.toCollection(HashSet::new));
        System.out.println(upperCaseName);
    }

    public void collectorGroupTest(){
        List<Human> humans = Arrays.asList(
                new Human("Ned", "Stark", 1),
                new Human("Robb", "Stark", 2),
                new Human("Arya", "Stark", 1),
                new Human("Aegon", "Targaryen", 6),
                new Human("Daenerys", "Targaryen", 4),
                new Human("Jaime", "Lannister", 1),
                new Human("Tyrion", "Lannister", 3));
        Map<String, Long> map = humans.stream()
                .collect(Collectors.groupingBy(Human::getSurname, Collectors.counting()));
        System.out.println(map);
    }
}

class Car{
    public Car(String number, int year){
        this.number = number;
        this.year = year;
    }

    public String getNumber(){
        return this.number;
    }

    public int getYear(){
        return  this.year;
    }

    private String number;
    private int year;
}
class Human{
    private final String name, surname;
    private final int friendsAmount;
    public Human(String name, String surname, int friendsAmount){
        this.name = name;
        this.surname = surname;
        this.friendsAmount = friendsAmount;
    }

    public String getSurname(){
        return this.surname;
    }
    public int getFriendsAmount(){
        return  this.friendsAmount;
    }
}