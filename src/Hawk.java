import java.util.Date;

public class Hawk extends Bird {
    public Hawk(String name, Date birthDate, Integer age, String species) {
        super(name, birthDate, age, species);
    }

    public Hawk(String XMLString) {
        super(XMLString);
    }


}
