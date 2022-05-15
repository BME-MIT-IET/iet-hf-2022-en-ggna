package com.complexible.pinto;
import org.junit.Test;
import org.openrdf.model.Graph;
import org.openrdf.model.Model;
public class RDFMappingExceptionTests {
    @Test(expected = RDFMappingException.class)
    public void testExceptionIsPassedOn_UsingThrowable() throws Exception{
        IllegalStateException e = new IllegalStateException();
        throw new RDFMappingException(e);
        // test if exception will be thrown
    }
    @Test(expected = RDFMappingException.class)
    public void testExceptionIsPassedOn_UsingThrowableAndMessage() throws Exception{
        String message = "This is a test exception message";
        IllegalStateException e = new IllegalStateException();
        throw new RDFMappingException(message, e);
         // test if exception will be thrown with given message
    }
    @Test(expected = RDFMappingException.class)
    public void testRDFMapper_newInstance_noConstructor() {
        NoNewInstanceClass x = new NoNewInstanceClass();
        Model graf = RDFMapper.create().writeValue(x);
        final NoNewInstanceClass xResult = RDFMapper.create().readValue(graf, NoNewInstanceClass.class);
    }
    public class NoNewInstanceClass { }
}