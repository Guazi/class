import java.util.Date;

public class Turtle extends Reptile{
    public Turtle(String name, Date birthDate, Integer age, String species) {
        super(name, birthDate, age, species);
    }

    public Turtle(String XMLString) {
        super(XMLString);
    }

    @Override
    public Integer calculateLifeExpectancy() {
        int lifeExpectancy = 110 - age;
        return lifeExpectancy;
    }
}
