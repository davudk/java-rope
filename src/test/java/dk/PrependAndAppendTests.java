package dk;

import org.junit.Assert;
import org.junit.Test;

public class PrependAndAppendTests {

    @Test
    public void perform() {
        Rope r = Ropes.from("Four");
        Assert.assertEquals("Four", r.toString());

        r = r.prepend("Two ", "Three ");
        Assert.assertEquals("Two Three Four", r.toString());

        r = r.prepend("One ");
        Assert.assertEquals("One Two Three Four", r.toString());

        r = r.append(" Five", " Six");
        Assert.assertEquals("One Two Three Four Five Six", r.toString());

        r = r.append(" Seven");
        Assert.assertEquals("One Two Three Four Five Six Seven", r.toString());
    }
}
