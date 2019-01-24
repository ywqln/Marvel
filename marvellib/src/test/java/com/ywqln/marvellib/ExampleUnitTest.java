package com.ywqln.marvellib;

import static org.junit.Assert.assertEquals;

import com.ywqln.marvellib.net.guide.TestAnnotation;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        new TestAnnotation().testAnnotation();
        assertEquals(4, 2 + 2);
    }
}