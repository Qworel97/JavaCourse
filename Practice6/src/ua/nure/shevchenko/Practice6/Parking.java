package ua.nure.shevchenko.Practice6;

import java.util.HashMap;
import java.util.Map;

public class Parking {
	private HashMap<String, Integer> state;
	private int capacity;

	public Parking(int n) {
		state = new HashMap<String, Integer>();
		capacity = n;
	}

	public boolean add(String car) {
		if (state.containsKey(car)) {
			return false;
		} else {
			for (int i = 0; i < capacity; i++) {
				if (!state.containsValue(i)) {
					state.put(car, i);
					return true;
				}
			}
		}
		return false;
	}

	public boolean remove(String car) {
		if (state.containsKey(car)) {
			state.remove(car);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Integer> x : state.entrySet()) {
			sb.append("Car: " + x.getKey() + " Place: " + x.getValue() + System.lineSeparator());
		}
		sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return sb.toString();
	}
}
