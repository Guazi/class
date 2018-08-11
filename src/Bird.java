import java.util.Date;

public class Bird extends Animal {

    public Bird(String name, Date birthDate, Integer age, String species) {
        super(name, birthDate, age, species);
    }

    public Bird(String XMLString) {
        super(XMLString);
    }

    @Override
    public Integer calculateLifeExpectancy() {
        int lifeExpectancy = 5;
        return lifeExpectancy;
    }
}
