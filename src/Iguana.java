import java.util.Date;

public class Iguana extends Reptile {
    public Iguana(String name, Date birthDate, Integer age, String species) {
        super(name, birthDate, age, species);
    }

    public Iguana(String XMLString) {
        super(XMLString);
    }

    @Override
    public Integer calculateLifeExpectancy() {
        int lifeExpectancy = (1 / age)*10;
        return lifeExpectancy;
    }
}
