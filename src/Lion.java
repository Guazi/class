import java.util.Date;

public class Lion extends Mammal {
    public Lion(String name, Date birthDate, Integer age, String species) {
        super(name, birthDate, age, species);
    }

    public Lion(String XMLString) {
        super(XMLString);
    }
}
