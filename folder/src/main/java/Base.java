package fun;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {

    @BeforeMethod
    public void BeforeMethod(){
        System.out.println("I am in Before Method! Test is starting!");
    }

    @AfterMethod
    public void AfterMethod(){
        System.out.println("I am in After Method! Test is ending!");

    }
}
