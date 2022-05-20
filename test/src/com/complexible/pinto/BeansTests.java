package com.complexible.pinto;

import com.complexible.common.beans.Beans;
import org.junit.Test;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import static org.junit.Assert.*;

public class BeansTests 
{
    // attribute that singlas to compiler that this is nUnit test method
  
    @Test
    public void TestGetDeclaredFields() {
        // crating generic iterable(can be interabe with for or foreach loop) list of type Field
        Iterable<Field> result = Beans.getDeclaredFields(ClassWithTestFields.class);
        // ArrayList of type string that would be filed with item names based on if statment
        ArrayList<String> actual = new ArrayList<String>();
        for (Field item : result) {
            // filtering for all items that doesnt contain "transient" inside themseves
            if (!item.toString().contains("transient"))
                actual.add(item.getName());
        }
        // ArrayList of type string used to compare with what we actually get after filtering 
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("stringField");
        expected.add("integerField");
        // test assumption itself, comparing actual with expected results
        assertEquals(expected, actual);
    }
    ////////////////////////////////////////////////////////////
    static public class ClassWithTestFields {
        private String stringField;
        private int integerField;
    }
}