import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {

    private static final String fileName = "ksiazkaAdresowa.txt";

    public static void main(String[] args) {
        System.out.println("Książka adresowa:");
        try {
            createInitialData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address address = new Address(args[3], args[4], args[5], args[6], args[7]);
        Person person = new Person(args[0], args[1], args[2], address);
        ArrayList<String> records = new ArrayList<>();

        try {
            saveToFile(fileName, person);
            records = readFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Person> people = mapRecordToPerson(records);
        printAllPeople(people);
    }

    private static void saveToFile(String fileName, Person person) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        StringBuilder personRecord = new StringBuilder().append(person.getFirstName())
                .append(",").append(person.getLastName())
                .append(",").append(person.getPhoneNumber())
                .append(",").append(person.getAddress().getStreet())
                .append(",").append(person.getAddress().getStreetNo())
                .append(",").append(person.getAddress().getCity())
                .append(",").append(person.getAddress().getPostalCode())
                .append(",").append(person.getAddress().getCountry())
                .append("\n");
        System.out.println("Dodano nową osobę: \n" + personRecord + "\n");
        writer.write(String.valueOf(personRecord));
        writer.close();
    }

    private static ArrayList<String> readFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        return reader.lines().collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<Person> mapRecordToPerson(ArrayList<String> records) {
        ArrayList<Person> people = new ArrayList<>();
        for (String s : records) {
            String[] record = s.split(",");
            people.add(new Person(record[0], record[1], record[2],
                    new Address(record[3], record[4], record[5], record[6], record[7])));
        }
        return people;
    }

    private static String mapPersonToRecord(Person person) {
        return new StringBuilder().append(person.getFirstName())
                .append(",").append(person.getLastName())
                .append(",").append(person.getPhoneNumber())
                .append(",").append(person.getAddress().getStreet())
                .append(",").append(person.getAddress().getStreetNo())
                .append(",").append(person.getAddress().getCity())
                .append(",").append(person.getAddress().getPostalCode())
                .append(",").append(person.getAddress().getCountry())
                .toString();
    }
//użycie programowania funkcyjnego
    private static void printAllPeople(ArrayList<Person> people) {
        System.out.println("Lista wszystkich osób:");
        people.stream().sorted(Comparator.comparing(Person::getLastName)).forEach(System.out::println);
    }

    private static void createInitialData() throws IOException {
        Address address1 = new Address("Kwiatowa", "45", "Warszawa", "05-345", "Polska");
        Person person1 = new Person("Mateusz", "Kowalczyk", "+48925245452", address1);
        saveToFile(fileName, person1);
        Address address2 = new Address("Wspolna", "234", "Warszawa", "12-245", "Polska");
        Person person2 = new Person("Andrzej", "Malina", "+48888967223", address2);
        saveToFile(fileName, person2);
        Address address3 = new Address("Krakowska", "1B", "Warszawa", "05-345", "Polska");
        Person person3 = new Person("Michal", "Nowak", "+48468666967", address3);
        saveToFile(fileName, person3);
        Address address4 = new Address("Olejowa", "45/345", "Kraków", "01-352", "Polska");
        Person person4 = new Person("Piotr", "Krawczyk", "+48603746977", address4);
        saveToFile(fileName, person4);
        Address address5 = new Address("Wspolna", "434C", "Łódź", "02-524", "Polska");
        Person person5 = new Person("Robert", "Zuber", "+48697468355", address5);
        saveToFile(fileName, person5);
        Address address6 = new Address("Osiedlowa", "B/345", "Warszawa", "05-345", "Polska");
        Person person6 = new Person("Mateusz", "Grodzki", "+48513524588", address6);
        saveToFile(fileName, person6);
        Address address7 = new Address("Zlota", "14", "Warszawa", "05-345", "Polska");
        Person person7 = new Person("Hubert", "Debski", "+48932523523", address7);
        saveToFile(fileName, person7);
        Address address8 = new Address("Zlota", "14", "Warszawa", "05-345", "Polska");
        Person person8 = new Person("Konstanty", "Matwiej", "+48932523523", address8);
        saveToFile(fileName, person8);
    }


}
