package Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        // Create an array of Person objects
        Person[] personArray = {
                new Person("John", 20),
                new Person("Jane", 21),
                new Person("Adam", 22),
                new Person("Eve", 23)
        };
        List<Person> people = Arrays.asList(personArray);

        // Create an array of Fruit objects
        Fruit[] fruitArray = {
                new Fruit("Apple", 10),
                new Fruit("Banana", 20),
                new Fruit("Cherry", 30),
                new Fruit("Date", 40)
        };
        List<Fruit> fruits = Arrays.asList(fruitArray);

        // Create an array of Sport objects
        Sport[] sportArray = {
                new Sport("Basketball"),
                new Sport("Football"),
                new Sport("Tennis"),
                new Sport("Volleyball")
        };
        List<Sport> sports = Arrays.asList(sportArray);

        // 1. uses a for loop
        List<Person> peopleResult = findPeopleByName(people, "Jane");
        displayResult("People with name Jane: ", peopleResult);
        peopleResult = findPeopleByAge(people, 22);
        displayResult("People with age 22: ", peopleResult);

        // 2. call methods which use Function with specific type
        List<Person> peopleResult2 = findPeople(people, p -> p.getName().equals("Jane"));
        displayResult("People with name Jane: ", peopleResult2);
        List<Person> peopleResult3 = findPeople(people, p -> p.getAge() == 22);
        displayResult("People with age 22: ", peopleResult3);

        // 3. call methods which use Function with generic type
        List<Person> peopleResult4 = find(people, p -> p.getAge() == 22);
        displayResult("People with age 22: ", peopleResult4);
        List<Fruit> fruitResult1 = find(fruits, f -> f.getName().equals("Apple"));
        displayResult("Fruit with name: ", fruitResult1);
        List<Fruit> fruitResult2 = find(fruits, f -> f.getQuantity() > 10 && f.getName().startsWith("D"));
        displayResult("Fruit with quantity > 10: ", fruitResult2);
        List<Sport> sportResult = find(sports, s -> s.getName().startsWith("B"));
        displayResult("Sports start with B: ", sportResult);

        // 4. Use a stream
        Stream<Person> presult = people.stream()
                .filter(person -> person.getAge() > 10);
        System.out.println("Person stream starts-------");
        presult.forEach(person -> System.out.println(person.getName() + " "));
        System.out.println("Person stream ends---------");

        Stream<Fruit> fresult = fruits.stream()
                .filter(fruit -> fruit.getName().startsWith("B"));
        System.out.println("Fruit stream starts-------");
        fresult.forEach(fruit -> System.out.println(fruit.toString()));
        System.out.println("Fruit stream ends----------");

        Stream<Sport> sresult = sports.stream()
                .filter(sport -> sport.getName().startsWith("b") ||
                        sport.getName().startsWith("B"));
        System.out.println("Sport stream starts-------");
        sresult.forEach(sport -> System.out.println(sport.toString()));
        System.out.println("Sport stream ends----------");

    }


    // 1. uses a for loop
    public static List<Person> findPeopleByName(List<Person> people, String name) {
        List<Person> result = new ArrayList<>();
        for (Person p : people) {
            if (p.getName().equals(name)) {
                result.add(p);
            }
        }
        return result;
    }

    public static List<Person> findPeopleByAge(List<Person> people, int age) {
        List<Person> result = new ArrayList<>();
        for (Person p : people) {
            if (p.getAge() == age) {
                result.add(p);
            }
        }
        return result;
    }

    // 2. call methods which use Function with specific type
    public static List<Person> findPeople(List<Person> people, Predicate<Person> predicate) {
        List<Person> result = new ArrayList<Person>();
        for (Person p : people) {
            if (predicate.test(p)){
                result.add(p);
            }
        }
        return result;
    }

    // 3. call methods which use function with generic type
    public static <T> List<T> find(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }





    // Utility method to display results
    public static <T> void displayResult(String prefix, List<T> results){
        System.out.print(prefix);
        System.out.println(results);
    }

}
