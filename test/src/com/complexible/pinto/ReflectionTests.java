package com.complexible.pinto;
import com.complexible.common.reflect.Classes;
import org.junit.Test;
import static org.junit.Assert.*;
public class ReflectionTests {
    @Test
    public void checkInterfacesTest() {
        // check if this ClassThatImplements(ClassThatImplements.class --> use the class itselve) interface with name TestInterfaceOne. my assumption is that .class is used since the interface will also inherit fuc form class
        assertTrue(Classes._implements(ClassThatImplements.class, TestInterfaceOne.class));
        assertFalse(Classes._implements(ClassThatImplements.class, TestInterfaceThree.class));
    }
    @Test
    public void getInterfacesTest() {
        int result = 0;
        for (Class item :  Classes.interfaces(ClassThatImplements.class)) {
            result++;
            // count how many interfaces 1 class will implement (example: class A : Interface1, Interface2{...})
        }
        assertEquals(2, result);
    }
    public interface TestInterfaceOne {}
    public interface TestInterfaceTwo {}
    public interface TestInterfaceThree {}
    static public class ClassThatImplements implements TestInterfaceOne, TestInterfaceTwo {}
}
