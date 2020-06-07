package dk;

import org.junit.Assert;
import org.junit.Test;

public class InsertTests {

    @Test
    public void perform() {
        Rope r = Ropes.from("Hello!");
        Assert.assertEquals("Hello!", r.toString());

        r = r.insert(5, ", world");
        Assert.assertEquals("Hello, world!", r.toString());

        r = r.insert(0, "And so it read: ");
        Assert.assertEquals("And so it read: Hello, world!", r.toString());

        r = r.insert(29, " And that was the end of it.");
        Assert.assertEquals("And so it read: Hello, world! And that was the end of it.", r.toString());
    }
}
