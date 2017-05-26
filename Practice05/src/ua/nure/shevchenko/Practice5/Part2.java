package ua.nure.shevchenko.Practice5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Part2 {


	private static final InputStream STD_IN = System.in;

	private static final String ENCODING = "Cp1251";
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		InputStream stdIn = System.in;
		 
        // create input stream with line terminator (=ENTER)
        ByteArrayInputStream bais = new ByteArrayInputStream(System.lineSeparator().getBytes());
 
        // move internal pointer of input stream to the end of input
        bais.skip(System.lineSeparator().length());
 
        // assign new value of standard input
        System.setIn(bais);
 
        Spam s = new Spam(new long[]{500,600,700,800},new String[]{"A","B","C","D"});
		s.main(args);
        // main functionality
        Thread.sleep(5000);
        // move internal pointer to begin of input
        bais.reset();
	}

}
