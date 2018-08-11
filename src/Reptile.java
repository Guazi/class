import java.util.Date;

abstract public class Reptile extends Animal {
    public Reptile(String name, Date birthDate, Integer age, String species) {
        super(name, birthDate, age, species);
    }

    public Reptile(String XMLString) {
        super(XMLString);
    }

    abstract public Integer calculateLifeExpectancy();

}
