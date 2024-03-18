package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Unmarshaler {
    public static <T> T transformToPojo(T object) throws JAXBException {
        File file = new File( "Movie.xml" );
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

//the only difference with the marshaling operation is here
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        T object1 = (T)jaxbUnmarshaller.unmarshal(file);
        System.out.println(object1);
        return object1;
}
}
