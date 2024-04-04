package labs.lab_one.reflection_and_annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@Retention(RetentionPolicy.RUNTIME)
@interface LogExecutionTime {}


class ExampleService {
    @LogExecutionTime
    public void method1() {
        // Simulating some task
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @LogExecutionTime
    public void method2() {
        // Simulating some task
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ExecutionTimeLoggerService {
    public static void logExecutionTime(Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                long startTime = System.currentTimeMillis();
                try {
                    method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                long endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;
                System.out.println("Method " + method.getName() + " executed in " + executionTime + " milliseconds.");
            }
        }
    }

    public static void main(String[] args) {
        ExampleService exampleService = new ExampleService();
        logExecutionTime(exampleService);
    }
}
