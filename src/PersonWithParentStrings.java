import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PersonWithParentStrings {
    public final Person person;
    private final List<String> parents;

    public PersonWithParentStrings(Person person, List<String> parents) {
        this.person = person;
        this.parents = parents;
    }


    public static PersonWithParentStrings fromCsvLine(String line) throws NegativeLifespanException {
        Person person = Person.fromCsvLine(line);
        String[] tokens = line.split(",");
        List<String> parents = new ArrayList<>();
        for (int i = 3; i <= 4; i++) {
            if (i < tokens.length && !tokens[i].trim().isEmpty()) {
                parents.add(tokens[i].trim());
            }
        }
        return new PersonWithParentStrings(person, parents);
    }

    public static void linkRelatives(Map<String, PersonWithParentStrings> PersonWithParentStrings) throws ParentingAgeException {
        Scanner scanner = new Scanner(System.in);
for (PersonWithParentStrings parents : PersonWithParentStrings.values()) {
    Person person = parents.person;
    for (PersonWithParentStrings possibleChild : PersonWithParentStrings.values()) {
        if (possibleChild.parents.contains(person.getName())) {
            person.adopt(possibleChild.person);
            try {
                if (person.getBirthDate().plusYears(15).isBefore(possibleChild.person.getBirthDate())) {
                    person.adopt(possibleChild.person);
                } else {
                    throw new ParentingAgeException("Person is to young");
                }
            } catch (ParentingAgeException e) {
                System.out.println("Person is to young");
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("Y")) {
                    person.adopt(possibleChild.person);
                }
            }
        }
    }
        }
    }

}



