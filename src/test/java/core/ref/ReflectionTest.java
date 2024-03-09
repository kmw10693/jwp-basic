package core.ref;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        logger.debug(clazz.getName());

        logger.debug(Arrays.toString(clazz.getDeclaredFields()));
        logger.debug(Arrays.toString(clazz.getDeclaredConstructors()));
        logger.debug(Arrays.toString(clazz.getDeclaredMethods()));
    }
    
    @Test
    public void newInstanceWithConstructorArgs() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        Object o = null;
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
           o = declaredConstructor.newInstance("a", "b", "c", "d");
        }
        System.out.println(o);
    }
    
    @Test
    public void privateFieldAccess() throws NoSuchFieldException, IllegalAccessException {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
        Student student = new Student();
        Field name = clazz.getDeclaredField("name");
        Field age = clazz.getDeclaredField("age");
        name.setAccessible(true);
        age.setAccessible(true);

        name.set(student, "주한");
        age.set(student, 14);

        System.out.println(student.getAge() + student.getName());
    }
}
