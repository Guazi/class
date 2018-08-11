import java.util.Date;

public class Mammal extends Animal {
    public Mammal(String name, Date birthDate, Integer age, String species) {
        super(name, birthDate, age, species);

    }

    public Mammal(String XMLString) {
        super(XMLString);
    }

    @Override
    public Integer calculateLifeExpectancy() {
        int lifeExpectancy = 15 - age;
        return lifeExpectancy;
    }
}
