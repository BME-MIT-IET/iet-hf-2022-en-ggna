package com.complexible.pinto;

import com.complexible.common.util.Namespaces;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import org.openrdf.model.*;
import org.openrdf.model.impl.SimpleNamespace;
import org.openrdf.model.impl.SimpleValueFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class RDFMapperBuilderTestsIET 
{
    @Test
    public void createBuilderWithCustomValueFactory() 
    {
        // creation of aObj using SimpleValueFactory(used for creating singlton instances)
        RDFMapperTests.ClassWithEnum aObj = new RDFMapperTests.ClassWithEnum();
        aObj.id(SimpleValueFactory.getInstance().createIRI("urn:testWriteEnum"));
        aObj.setValue(RDFMapperTests.TestEnum.Bar);

        RDFMapper.Builder builder = RDFMapper.builder().valueFactory(SimpleValueFactory.getInstance());
        Model aResult = builder.set(MappingOptions.IGNORE_INVALID_ANNOTATIONS, false)
                .build()
                .writeValue(aObj);
        // result of building will be stored in aObj --> writeValue(aObj);
        RDFMapperTests.ClassWithEnum aReturned = builder.set(MappingOptions.IGNORE_INVALID_ANNOTATIONS, false)
                .build()
                .readValue(aResult, RDFMapperTests.ClassWithEnum.class);
        assertEquals(aObj, aReturned);
        // testing if aObj is same as aReturned
    }
    @Test
    public void createBuilderWithNamespaces() {
            // mock --> give me class with enum, later I add some fields and data that is relly neded insied of that instance(type: classWithEnum)
        RDFMapperTests.ClassWithEnum aObj = new RDFMapperTests.ClassWithEnum();
        aObj.id(SimpleValueFactory.getInstance().createIRI("urn:testWriteEnum"));
        aObj.setValue(RDFMapperTests.TestEnum.Bar);
        ArrayList<Namespace> customNameSpaces = new ArrayList<Namespace>();
        customNameSpaces.add(new SimpleNamespace("foaf", Namespaces.FOAF));
        customNameSpaces.add(new SimpleNamespace("owl", Namespaces.OWL));
        customNameSpaces.add(new SimpleNamespace("rdfs", Namespaces.RDFS));
        RDFMapper.Builder builder = RDFMapper.builder().namespaces(customNameSpaces);
        Model aResult = builder.set(MappingOptions.IGNORE_INVALID_ANNOTATIONS, false)
                .build()
                .writeValue(aObj);
        RDFMapperTests.ClassWithEnum aReturned = builder.set(MappingOptions.IGNORE_INVALID_ANNOTATIONS, false)
                .build()
                .readValue(aResult, RDFMapperTests.ClassWithEnum.class);
        assertEquals(aObj, aReturned);
        // mapperTests will create expected data, afterwards we give some data that the program will really use, in final step we compare them with assertEqueal
    }
    @Test
    public void createBuilderWithMapFactory() throws Exception {
        RDFMapperTests.ClassWithEnum aObj = new RDFMapperTests.ClassWithEnum();
        aObj.id(SimpleValueFactory.getInstance().createIRI("urn:testWriteEnum"));
        aObj.setValue(RDFMapperTests.TestEnum.Bar);
        RDFMapper.Builder builder = RDFMapper.builder().mapFactory(
                new RDFMapper.DefaultMapFactory());
        Model aResult = builder.set(MappingOptions.IGNORE_INVALID_ANNOTATIONS, false)
                .build()
                .writeValue(aObj);
        RDFMapperTests.ClassWithEnum aReturned = builder.set(MappingOptions.IGNORE_INVALID_ANNOTATIONS, false)
                .build()
                .readValue(aResult, RDFMapperTests.ClassWithEnum.class);
        assertEquals(aObj, aReturned);
    }
}
