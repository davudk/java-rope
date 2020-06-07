package dk;

import org.junit.Assert;
import org.junit.Test;

public class ReplaceTests {

    @Test
    public void perform() {
        Rope r = Ropes.from("It was the best of times, ", "it was the worst of times...");
        Assert.assertEquals("It was the best of times, it was the worst of times...", r.toString());

        r = r.replace(11, 24, "age of wisdom");
        Assert.assertEquals("It was the age of wisdom, it was the worst of times...", r.toString());

        r = r.replace(37, 51, "age of foolishness");
        Assert.assertEquals("It was the age of wisdom, it was the age of foolishness...", r.toString());

        r = r.replace(0, 27, "I");
        Assert.assertEquals("It was the age of foolishness...", r.toString());

        r = r.replace(6, 32, "...");
        Assert.assertEquals("It was...", r.toString());
    }
}
