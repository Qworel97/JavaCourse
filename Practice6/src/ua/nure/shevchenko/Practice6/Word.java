package ua.nure.shevchenko.Practice6;

public class Word implements Comparable {

	public String value;
	public int frequency;

	public Word(String in) {
		this.value = in;
		frequency = 1;
	}

	public void inc() {
		this.frequency++;
	}

	@Override
	public boolean equals(Object obj) {
		Word temp = (Word)obj;
		if (temp.value.equals(this.value)){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public int compareTo(Object obj) {
		Word temp = (Word) obj;
		if (this.frequency > temp.frequency) {
			return -1;
		} else if (this.frequency == temp.frequency) {
			if (this.value.compareTo(temp.value) > 0) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return 1;
		}
	}
}
