package dk;

import org.junit.Assert;
import org.junit.Test;

public class TrimTests {

    @Test
    public void performTrimStart() {
        Assert.assertEquals("First case. ", Ropes.from("  \t\n\r\n First case. ")
                .trimStart().toString());

        Assert.assertEquals("Second case.\r\n", Ropes.from("mmmSecond case.\r\n")
                .trimStart('m').toString());

        Assert.assertEquals("Third case.\t", Ropes.from("3.14159,Third case.\t")
                .trimStart('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', ',').toString());

        Assert.assertEquals("Fourth case.", Ropes.from("beefFourth case.")
                .trimStart(c -> c >= 'a' && c <= 'f').toString());
    }

    @Test
    public void performTrimEnd() {
        Assert.assertEquals(" First case.", Ropes.from(" First case.  \t\n\r\n ")
                .trimEnd().toString());

        Assert.assertEquals("\r\nSecond case.", Ropes.from("\r\nSecond case.mmm")
                .trimEnd('m').toString());

        Assert.assertEquals("\tThird case", Ropes.from("\tThird case.,3.14159")
                .trimEnd('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', ',').toString());

        Assert.assertEquals("Fourth case.", Ropes.from("Fourth case.dead")
                .trimEnd(c -> c >= 'a' && c <= 'f').toString());
    }

    @Test
    public void performTrim() {
        Assert.assertEquals("Red", Ropes.from("\r \t\n  Red \r")
                .trim().toString());

        Assert.assertEquals("Green", Ropes.from("``Green``")
                .trim('`').toString());

        Assert.assertEquals("Blue", Ropes.from("!@#Blue!@#")
                .trim('!', '@', '#', '$').toString());

        Assert.assertEquals("Yellow", Ropes.from("01234Yellow56789")
                .trim(c -> c >= '0' && c <= '9').toString());
    }
}
