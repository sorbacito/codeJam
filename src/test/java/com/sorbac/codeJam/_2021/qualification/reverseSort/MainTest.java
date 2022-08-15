package com.sorbac.codeJam._2021.qualification.reverseSort;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.sorbac.codeJam._2021.qualification.reverseSort.Main.myAnswer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MainTest {
    @DataProvider(name = "testData")
    public Object[][] dpMethod(){
        return new Object[][] {
                {new int[]{4, 2, 1, 3}, 6},
                {new int[]{1, 2}, 1},
                {new int[]{7, 6, 5, 4, 3, 2, 1}, 12}
        };
    }

    @Test(dataProvider = "testData")
    public void testMyAnswer(int[] array, long sum) {
        assertThat(myAnswer(array), is(sum));
    }
}