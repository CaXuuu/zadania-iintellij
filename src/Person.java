import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Person implements Comparable <Person>, Serializable {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Set<Person>children =new HashSet<>();




    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public Person(String name, String surname, LocalDate birthDate, LocalDate deathDate) throws NegativeLifespanException {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        if (deathDate != null && deathDate.isBefore(birthDate)) {
            throw new NegativeLifespanException(
                    "Data śmierci (" + deathDate + ") jest wcześniejsza niż data urodzenia (" + birthDate + ")." );
        }
    }

    public Person(String name, String surname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

public boolean adopt(Person person) {
        return this.children.add(person);
}
public Person getYoungestChild() {
     return children.stream()
             .max(Person :: compareTo)
             .orElse(null);
}

public int compareTo(Person person) {
        return this.birthDate.compareTo(person.birthDate);
}

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", deathDate=" + deathDate +
                ", children=" + children +
                '}';
    }
    public static Person fromCsvLine(String csvLine) throws NegativeLifespanException {
        String [] parts = csvLine.split(",");
String fullname = parts[0].trim();
String[] nameParts = fullname.split(" ", 2);
String name = nameParts[0];
String surname = "";
if(nameParts.length>1){
    surname = nameParts[1];
}
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
LocalDate bDay = LocalDate.parse(parts[1].trim(), formatter);
        LocalDate dDay = null;
        if(parts.length > 2 && !parts[2].trim().isEmpty()) {
            dDay = LocalDate.parse(parts[2].trim(), formatter);
        }
String motherName ="";
if(parts.length>3){
    motherName = parts[3].trim();
}
String dadName = "";
if(parts.length>4){
    dadName = parts[4].trim();
}
        return new Person(name, surname, bDay, dDay);
    }

    public List<Person> getChildren() {
List<Person> sortedChildren = new ArrayList<>(children);
sortedChildren.sort(Person::compareTo);
        return sortedChildren;
    }
    public static List<Person> fromCsv(String filePath) throws IOException, AmbiguousPersonException {
        List <Person> people = new ArrayList<>();
        Set<String> names = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Person p= Person.fromCsvLine(line);
                    String fullname = p.getName()+ " " + p.getSurname();
                    if(names.contains(fullname)){
                        throw new AmbiguousPersonException("Duplikat sie pojawil "+ fullname);
                    }
                    names.add(fullname);
                    people.add(p);

                } catch (NegativeLifespanException e) {
                    System.out.println("Błąd w linii: " + line + " → " + e.getMessage());
                }
            }
            return people;
        }catch (IOException e){
            throw new IOException(e);
        }
    }

public static void toBinaryFile(List<Person> people, String path) throws IOException {
FileOutputStream fos = new FileOutputStream(path);
ObjectOutputStream oos = new ObjectOutputStream(fos);
oos.writeObject(people);
oos.close();
}

public static List<Person> fromBinaryFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Person> people = null;
        try{
            people = (ArrayList <Person>) ois.readObject();

        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    ois.close();
    return people;
    }

}
