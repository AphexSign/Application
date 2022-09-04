import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyClassTest {

    private MyClass myClass;

    @Before
    public void create(){

        myClass=new MyClass();
    }

    @Test
    public void showValue() {
        Assert.assertEquals(5,myClass.showValue());
    }
}