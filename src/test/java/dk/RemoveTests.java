package dk;

import org.junit.Assert;
import org.junit.Test;

public class RemoveTests {

    @Test
    public void perform() {
        Rope r = Ropes.concat(" ", "Lorem", "ipsum", "dolor", "sit", "amet.");
        Assert.assertEquals("Lorem ipsum dolor sit amet.", r.toString());

        r = r.remove(11);
        Assert.assertEquals("Lorem ipsum", r.toString());

        r = r.remove(0, 6);
        Assert.assertEquals("ipsum", r.toString());

        r = r.remove(1, 4);
        Assert.assertEquals("im", r.toString());
    }
}
