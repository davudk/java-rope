package dk;

import org.junit.Assert;
import org.junit.Test;

public class InternTests {

    @Test
    public void performTrimStart() {
        Rope r = Rope.EMPTY;
        Assert.assertEquals("", r.toString());

        r = r.append("Angular");
        Assert.assertEquals("Angular", r.toString());

        r = r.prepend("React, ");
        Assert.assertEquals("React, Angular", r.toString());

        r = r.insert(14, " 9 with TypeScript");
        Assert.assertEquals("React, Angular 9 with TypeScript", r.toString());

        r = r.intern();
        Assert.assertEquals("React, Angular 9 with TypeScript", r.toString());
    }
}
