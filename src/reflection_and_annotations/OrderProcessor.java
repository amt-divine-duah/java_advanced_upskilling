package reflection_and_annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@MyAnnotation(name = "processOrder")
public class OrderProcessor {

    public void processOrder(String orderId) {
        // Order processing logic
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> orderProcessorClass = OrderProcessor.class;
        Method processOrderMethod = orderProcessorClass.getMethod("processOrder", String.class);

        // Check if the method is annotated with MyAnnotation
        if (processOrderMethod.isAnnotationPresent(MyAnnotation.class)) {
            System.out.println("Log something here to indicate that the method is annotated with MyAnnotation");
            MyAnnotation annotation = processOrderMethod.getAnnotation(MyAnnotation.class);
            System.out.println("Method name (from annotation): " + annotation.name());
        }

        // Invoke the method using reflection
        OrderProcessor processor = new OrderProcessor();
        processOrderMethod.invoke(processor, "12345");
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String name();
}

