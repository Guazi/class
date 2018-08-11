import java.util.Date;

public class Goat extends Mammal {
    public Goat(String name, Date birthDate, Integer age, String species) {
        super(name, birthDate, age, species);
    }

    public Goat(String XMLString) {
        super(XMLString);
    }
}
