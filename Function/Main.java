package Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Person[] personArray = {
                new Person("John", 5),
                new Person("MARY", 12),
                new Person("David", 17),
                new Person("TOM", 13)};
        List<Person> people = Arrays.asList(personArray);

        Fruit[] fruitArray = {
                new Fruit("Apple", 11),
                new Fruit("Orange", 22),
                new Fruit("Banana", 33)};
        List<Fruit> fruits = Arrays.asList(fruitArray);

        Sport[] sportArray = {
                new Sport("football"),
                new Sport("swimming"),
                new Sport("baseball")
        };
        List<Sport> sports = Arrays.asList(sportArray);

        // call methods using for loop
        convertPeopleUpper(people);
        displayResult("Upper using for loop: ", people);
        convertPeopleLower(people);
        displayResult("Lower using for loop: ", people);

        // call method with functions with specific type
        convertPeople(people, person -> {
            person.setName(person.getName().toUpperCase());
            return person;
        });
        displayResult("Upper using function specific type: ", people);

        convertPeople(people, person -> {
            person.setName(person.getName().toLowerCase());
            return person;
        });
        displayResult("Lower using function specific type: ", people);

        convertPeople(people, person -> {
            String CamelName = person.getName().substring(0, 1).toUpperCase() +
                    person.getName().substring(1).toLowerCase();
            person.setName(CamelName);
            return person;
        });
        displayResult("Camel using function specific type: ", people);

        // call method with Functions of generic type
        convert(people, person -> {
            person.setName(person.getName().toUpperCase());
            return person;
        });
        displayResult("Upper using function generic type: ", people);

        convert(people, person -> {
            String camelName = person.getName().substring(0, 1).toUpperCase() +
                    person.getName().substring(1).toLowerCase();
            person.setName(camelName);
            return person;
        });
        displayResult("Camel using function generic type: ", people);

        Function<Fruit, Fruit> fruitFunction = fruit -> {
            if (fruit.getQuantity() < 20)
                fruit.setQuantity(20);
            return fruit;
        };
        convert(fruits, fruitFunction);
        displayResult("Fruit with generic function", fruits);

        Function<Sport, Sport> sportFunction = sport -> {
            if (sport.getName().startsWith("B") || sport.getName().startsWith("b"))
                sport.setName("A " + sport.getName().substring(1));
            return sport;
        };
        convert(sports, sportFunction);
        displayResult("Sport with generic function", sports);

        // use a stream
        Stream<Person> personStream = people.stream()
                .map(person -> {
                    person.setName(person.getName().toUpperCase());
                    return person;
                });
        personStream.forEach(person -> System.out.println(person.getName() + " "));

        Stream<Fruit> fruitStream = fruits.stream()
                .map(fruitFunction);
        fruitStream.forEach(fruit -> System.out.println(fruit.toString() + " "));

        Stream<Sport> sportStream = sports.stream().map(sportFunction);
        sportStream.forEach(sport -> System.out.println(sport.toString() + " "));


    }

    // 1. use a for loop
    public static void convertPeopleUpper(List<Person> people) {
        for (Person p : people) {
            p.setName(p.getName().toUpperCase());
        }
    }

    public static void convertPeopleLower(List<Person> people) {
        for (Person p : people) {
            p.setName(p.getName().toLowerCase());
        }
    }

    // 2. use Functions with specific type
    public static void convertPeople(List<Person> people, Function<Person, Person> function) {
        for (Person p : people) {
            function.apply(p);
        }
    }

    // 3. use Functions with generic type
    public static <T, R> void convert(List<T> list, Function<T, R> function){
        for (T item : list) {
            function.apply(item);
        }
    }

    // Utility method to display list of items
    public static <T> void displayResult(String prefix, List<T> results) {
        System.out.print(prefix);
        System.out.println(results);
    }


}
