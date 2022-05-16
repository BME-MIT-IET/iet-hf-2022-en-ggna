package com.complexible.pinto;

import static java.lang.String.format;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

class ConditionCreator<T> 
{
    private static final String ILLEGAL_CONDITION_PATTERN
            =   "Conditional class '%s' is a member class but was not declared inside the test case using it.\n"
            + "Either make this class a static class, standalone class (by declaring it in it's own file), "
            + "or move it inside the test case using it";
    
    // private fields used for creating ConditionCreator instance
    private final Class<T> conditionType;
    private final Object target;
    // ConditionCreator constructor that takes 2 paramiters
    ConditionCreator( Object target, Class<T> conditionType )
    {    
        this.target = target;
        this.conditionType = conditionType;
    }
    T create() {
        checkConditionType();
        try {
            return createCondition();
        } catch( RuntimeException re ) {
            throw re;
        } catch( Exception e ) {
            throw new RuntimeException( e );
        }
    }
    private T createCondition() throws Exception {
        if( isConditionTypeStandalone() ) {
            return createFromStandaloneType();
        }
        return createFromEmbeddedType();
    }
    private void checkConditionType() {
        if( !isConditionTypeStandalone() && !isConditionTypeDeclaredInTarget() ) {
            throw new IllegalArgumentException( format ( ILLEGAL_CONDITION_PATTERN, conditionType.getName() ) );
        }
    }
    // checks if condition type is not memeber of class or Modifier is static
    private boolean isConditionTypeStandalone() {
        return !conditionType.isMemberClass() || Modifier.isStatic( conditionType.getModifiers() );
    }
    private boolean isConditionTypeDeclaredInTarget() {
        return target.getClass().isAssignableFrom( conditionType.getDeclaringClass() );
    }
 
    // gives access to ConditionType constructor through out conditionType instance and method getDeclaredConstructor(), later just create new instance --> constructor.newInstance();
    private T createFromStandaloneType() throws Exception {
        try {
            return conditionType.newInstance();
        } catch( IllegalAccessException iae ) {
            Constructor<T> constructor = conditionType.getDeclaredConstructor();
            constructor.setAccessible( true );
            return constructor.newInstance();
        }
    }

    // create new instance just based on target (meaning T --> generic class that would be defined later)
    private T createFromEmbeddedType() throws Exception {
        Constructor<T> constructor = conditionType.getDeclaredConstructor( target.getClass() );
        constructor.setAccessible( true );
        return constructor.newInstance( target );
    }
}