import java.util.Date;

public class Pig extends Mammal {
    public Pig(String name, Date birthDate, Integer age, String species) {
        super(name, birthDate, age, species);
    }

    public Pig(String XMLString) {
        super(XMLString);
    }


}
