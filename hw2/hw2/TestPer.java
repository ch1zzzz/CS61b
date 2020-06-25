package hw2;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

public class TestPer {

    @Test
    public void testper() {
        Percolation per = new Percolation(20);
        Assert.assertFalse(per.percolates());
    }

}
