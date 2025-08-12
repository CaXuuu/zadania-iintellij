import java.util.*;

public class Family {
    Person person ;
    Map<String , List<Person> > mapa = new HashMap<>();

    public void add(Person... persons){
        for( Person person : persons) {
            String key = person.getName() + " " + person.getSurname();
      mapa.putIfAbsent(key , new ArrayList<>());
      mapa.get(key).add(person);
        }
    }
    public Person[] Persons (String Fullname){
        List<Person> lista = mapa.get(Fullname);
        if( lista == null) System.out.println("Nie ma takiej osoby");
        lista.sort(Comparator.comparing(Person::getBirthDate));
                return lista.toArray(new Person[0]);
    }
}
