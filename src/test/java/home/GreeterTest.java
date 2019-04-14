package home;

import org.testng.annotations.Test;

public class GreeterTest {
    private Greeter greeter = new Greeter();

    @Test
    public void greeterSaysHello() {
        System.out.println("Test: " + greeter.sayHello());
    }
}
