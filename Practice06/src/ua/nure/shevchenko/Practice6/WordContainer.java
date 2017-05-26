package ua.nure.shevchenko.Practice6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordContainer {

	private ArrayList<Word> ar;

	public WordContainer() {
		this.ar = new ArrayList<Word>();
	}

	public boolean add(String in) {
		if (ar.contains(new Word(in))) {
			ar.get(ar.indexOf(new Word(in))).inc();
		} else {
			ar.add(new Word(in));
		}
		return true;
	}

	public void print() {
		Collections.sort(ar);
		for (Word x : ar) {
			System.out.println(x.value + " : " + x.frequency);
		}
	}

	public void printThree() {
		Collections.sort(ar);
		int i = 0;
		for (Word x : ar) {
			if (i == 3) {
				break;
			}
			System.out.println(x.value + " : " + x.frequency);
			i++;
		}
	}

	public void maxThree() {
		ar.sort(new Comparator<Word>() {
			@Override
			public int compare(Word w1, Word w2) {
				if (w1.value.length() > w2.value.length()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		int i = 0;
		for (Word x : ar) {
			if (i == 3) {
				break;
			}
			System.out.println(x.value + " : " + x.value.length());
			i++;
		}
	}

	public void printThreeDuplicates() {
		Collections.sort(ar);
		int i = 0;
		for (Word x : ar) {
			if (i == 3) {
				break;
			}
			if (x.frequency > 1) {
				StringBuilder sb = new StringBuilder(x.value);
				System.out.println(sb.reverse().toString().toUpperCase());
			}
			i++;
		}
	}
}
