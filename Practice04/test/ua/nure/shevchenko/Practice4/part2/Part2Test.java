package ua.nure.shevchenko.Practice4.part2;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.shevchenko.Practice4.Part1;
import ua.nure.shevchenko.Practice4.Part2;

public class Part2Test {
	@Test
	public void testToIntArray(){
		int[] actual = Part2.toIntArray(new String[]{"1","2","3"});
		int[] expected = new int[]{1,2,3};
		boolean result = true;
		for(int i = 0; i<actual.length;i++){
			if(actual[i]!=expected[i]){
				result = false;
			}
		}
		Assert.assertEquals(true, result);
	}

	@Test
	public void testMain() throws IOException {
		Part2 p = new Part2();
		Part2.main(null);
	}
}
