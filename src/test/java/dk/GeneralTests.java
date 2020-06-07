package dk;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class GeneralTests {

    @Test
    public void perform() {
        Rope r = Ropes.from("Hello, world!");
        Assert.assertEquals("Hello, world!", r.toString());

        r = r.prepend("Ready?! ");
        Assert.assertEquals("Ready?! Hello, world!", r.toString());

        r = r.append(" Ok.");
        Assert.assertEquals("Ready?! Hello, world! Ok.", r.toString());

        r = r.prepend("are possible. ");
        r = r.prepend("Multiple ", "prepends ");
        Assert.assertEquals("Multiple prepends are possible. Ready?! Hello, world! Ok.", r.toString());

        r = r.append(" Multiple appends").append(" as ").append("well.");
        Assert.assertEquals("Multiple prepends are possible. Ready?! Hello, world! Ok. " +
                "Multiple appends as well.", r.toString());

        r = r.remove(0, "Multiple prepends are possible. ".length());
        Assert.assertEquals("Ready?! Hello, world! Ok. Multiple appends as well.", r.toString());

        r = r.replace(0, "Ready?! ".length(), "Hmm. ");
        Assert.assertEquals("Hmm. Hello, world! Ok. Multiple appends as well.", r.toString());

        r = r.remove("Hmm. Hello, world!".length(), r.length());
        Assert.assertEquals("Hmm. Hello, world!", r.toString());

        r = r.insert(0, "[START] ");
        Assert.assertEquals("[START] Hmm. Hello, world!", r.toString());

        r = r.insert(r.length(), " [END]");
        Assert.assertEquals("[START] Hmm. Hello, world! [END]", r.toString());

        r = r.remove("[START] ".length(), "[START] Hmm. ".length());
        Assert.assertEquals("[START] Hello, world! [END]", r.toString());
    }
}
