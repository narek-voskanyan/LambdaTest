package TestOne;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.Arrays.asList;

public class TestOperators {

    public void testFlatMap(){
        List<Humans> humans = asList(
                new Humans("Sam", asList("Buddy", "Lucy")),
                new Humans("Bob", asList("Frankie", "Rosie")),
                new Humans("Marta", asList("Simba", "Tilly")));

        List<String> petName = humans.stream()
                .map(a -> a.getPets())
                .flatMap(pets -> pets.stream())
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(petName);

    }

    public void testFilterAndFind(){
        List<String> strings = asList("BLOB", "Java is the best", "Java 8", "Java 9", "Jacoco");
        Optional<String> java = strings.stream().
                filter(a -> a.contains("Java")).findAny();
        System.out.println(java);

    }
    public void parallelStreamTest(){
        List<String> strings = asList("BLOB", "Java is the best", "Java 8", "Java 9", "Java from me", "Jacoco");
        Optional<String> java = strings.parallelStream()
                .filter(a -> a.contains("Java")).findAny();
        System.out.println(java);
    }

    public void findFirstTest(){
        List<Integer> numbers = asList(1,2,3,4,5,6,7,8,9,10,15,14,17,22,54,78,1,4,10,12,14,1254,47);
        Optional<Integer> find = numbers.stream().filter(i -> i > 10).findFirst();
        System.out.println(find);
    }

    public void testSorted(){
        List<Person> people = asList(new Person("Narek"), new Person("Anag"), new Person("David"));
        people.stream().sorted().forEach(System.out::println);
    }
    public void testSortedWithComparator(){
        List<Person> people = asList(new Person("Narek"), new Person("Anag"), new Person("David"));
        people.stream().sorted(Comparator.comparing(Person::getName).reversed()).forEach(System.out::println);
    }

    public void testParallelStream(){
        List<String> people = asList("Jo ", "Zai ", "Lui ", "Andy ");
        people.parallelStream().sequential().forEach(System.out::print);
        System.out.println("");
        people.stream().parallel().forEach(System.out::print);
    }
    public void testReduce(){
        List<Integer> numbers = asList(1,2,3,5);
        Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);
        System.out.println(sum);
    }
    public void testReduceTwo(){
        List<Integer> numbers = asList(1,2,3,5);
        Integer sum = numbers.stream().reduce(10, (a, b) -> a + b);
        System.out.println(sum);
    }
    public void minValue(){
        List<Integer> numbers = asList(1, 2, 3, 5, 7);
        Integer min = numbers.stream()
                .reduce(Integer.MAX_VALUE, (left, right) -> left < right ? left : right);
        System.out.println(min);
    }
    public void testDebug(){
        List<Integer> numbers = asList(1, 2, 3, 4, 5);

        numbers.stream()
                .map(n -> debug(n))
                .forEach(n -> doSomeStaff(n));
    }
    private static Integer debug(Integer n) {
        System.out.println(n);
        return n;
    }

    private static void doSomeStaff(Integer n) {

    }

    public void testRunge(){
        IntStream myInt = IntStream.range(1,26);
        myInt.forEach(System.out::println);
    }

    public void testOverrideStream(){
        DoubleStream myDoubleStream = IntStream.rangeClosed(1,100).asLongStream().asDoubleStream();
        myDoubleStream.forEach(System.out::println);

    }
    public void optionalTest(){
        Optional<String> name = Optional.of("Narek");
        name.ifPresent(a -> System.out.println(a));
    }

}

class Humans{
    private final String name;
    private final List<String> pets;

    public Humans(String name, List<String> pets){
        this.name = name;
        this.pets = pets;
    }
    public List<String> getPets(){
        return pets;
    }
}


class Person implements Comparable<Person> {
    private String name;

    Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{name='" + name + '\'' + '}';
    }

    public int compareTo(Person p){

        return name.compareTo(p.getName());
    }
}
