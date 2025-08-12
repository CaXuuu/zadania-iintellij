import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Adam", "Nowak", LocalDate.of(2005, 5, 21));
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(new Person("Pawel", "Kowalski", LocalDate.of(2003, 2, 10)));
        for (Person value : people) {
            System.out.println(value);
            Person child = new Person("Pawel", "Kowalski", LocalDate.of(2003, 2, 10));
            Person child1 = new Person("Jan", "Nowak", LocalDate.of(2010, 6, 13));
            Person child2 = new Person("Dan", "Nowak", LocalDate.of(2015, 7, 18));
            Person child3 = new Person("Gan", "Nowak", LocalDate.of(2020, 8, 25));
            person.adopt(child);
            person.adopt(child1);
            person.adopt(child2);
            person.adopt(child3);
            System.out.println(person.getYoungestChild());
            String filePath = "src/family1.csv";
            try {
                List<Person> eople = Person.fromCsv(filePath);
                System.out.println("Wczytane Osobby: ");
                for (Person p : eople)
                    System.out.println(p);
            } catch (Exception e) {
                System.out.println(e.getMessage() + "Zle wczytano");
            }
            System.out.println("-----");
            System.out.println("-----");
            System.out.println("-----");
        }
        try {


            // 1. Tworzymy przykładową listę osób
            List<Person> peopleToSave = new ArrayList<>();
            peopleToSave.add(new Person("Jan", "Kowalski", LocalDate.of(1980, 1, 1)));
            peopleToSave.add(new Person("Anna", "Nowak", LocalDate.of(1990, 1, 1)));

            // 2. Zapis do pliku binarnego
            Person.toBinaryFile(peopleToSave, "people.bin");
            System.out.println("Zapisano osoby do pliku people.bin");

            // 3. Odczyt z pliku binarnego
            List<Person> loadedPeople = Person.fromBinaryFile("people.bin");
            System.out.println("Odczytano osoby z pliku:");

            // 4. Wypisujemy wynik
            for (Person p : loadedPeople) {
                System.out.println(p);
            }

        } catch (IOException e) {
            System.err.println("Błąd IO: " + e.getMessage());
        }


    }
}
