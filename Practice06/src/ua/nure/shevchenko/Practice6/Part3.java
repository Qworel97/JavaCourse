package ua.nure.shevchenko.Practice6;

public class Part3 {
	public static void main(String[] args) {
		Parking park = new Parking(4);
		park.add("test1");
		park.add("test2");
		park.add("test3");
		park.add("test3");
		park.add("test3");
		System.out.println(park.toString());
		park.remove("test1");
		System.out.println(park.toString());
		park.add("test4");
		System.out.println(park.toString());
	}

}
