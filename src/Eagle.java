import java.util.Date;

public class Eagle extends Bird {
    public Eagle(String name, Date birthDate, Integer age, String species) {
        super(name, birthDate, age, species);
    }

    public Eagle(String XMLString) {
        super(XMLString);
    }


}
