package ua.nure.shevchenko.Practice4.part1;


import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.shevchenko.Practice4.Part1;

public class Part1Test {
	@Test
	public void test() throws IOException {
		String actual = Part1.goUp();
		String expected = "���� ���������� ��������� ���������� �� �����,"
				+ System.lineSeparator()
				+ "�� ���������� ������� ���������, � "
				+ System.lineSeparator() + "������� ��� (����������) ��������.";
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testMain() throws IOException {
		Part1 p = new Part1();
		Part1.main(null);
	}
}
