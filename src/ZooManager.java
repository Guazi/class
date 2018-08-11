import com.sun.org.apache.xml.internal.utils.XMLString;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.Date;

public class ZooManager {
    public static void main(String[] args) {

        ZooOrganizer myZoo = new ZooOrganizer();
        try {
            myZoo.openFile("animals.xml");
        } catch (InvalidAnimalException ex) {
            System.out.println("The file you tried to load has invalid class" + ex);
        }

        Animal carl = new Lion("carl", new Date("06/18/1986"), 12, "Lion");
        Animal jackie = new Pig("jackie", new Date("06/18/1986"), 12, "Pig");


        String xml =    " <Animal>" +
                        "   <name>John</name>" +
                        "   <birthDate>06/18/1986</birthDate>" +
                        "   <age>12</age>" +
                        "   <species>Lion</species>" +
                        " </Animal>";


        Animal dude = new Lion(xml);

        myZoo.addAnimal(dude);
        myZoo.addAnimal(carl);
        myZoo.addAnimal(jackie);

//        myZoo.deleteAnimal(carl);
//
//        Animal newcarl = new Eagle("carl", new Date("06/18/1986"), 44, "Eagle");
//        myZoo.updateAnimal(1, newcarl);
//
//        Animal newani = myZoo.getAnimal(1);
//        newani.age = 1;

        System.out.println("Jackie will live this much longer :" + jackie.calculateLifeExpectancy());
//

        myZoo.saveFile("animalsout.xml");

    }
}
