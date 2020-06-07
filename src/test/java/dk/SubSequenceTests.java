package dk;

import org.junit.Assert;
import org.junit.Test;

public class SubSequenceTests {

    @Test
    public void perform() {
        final Rope r = Ropes.from("Java and JavaScript");
        Assert.assertEquals("Java and JavaScript", r.toString());

        Assert.assertEquals("Java", r.subSequence(0, 4).toString());
        Assert.assertEquals("and", r.subSequence(5, 8).toString());
        Assert.assertEquals("JavaScript", r.subSequence(9, 19).toString());

        Assert.assertEquals("Java", r.subRope(0, 4).toString());
        Assert.assertEquals("and", r.subRope(5, 8).toString());
        Assert.assertEquals("JavaScript", r.subRope(9, 19).toString());
    }
}
