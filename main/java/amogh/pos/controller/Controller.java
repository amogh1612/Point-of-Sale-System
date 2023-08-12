package amogh.pos.controller;

import java.io.Serializable;
import java.util.ArrayList;

public class Controller<T extends Serializable> extends ArrayList<T> implements Serializable {

	@Override
	public T get(int index) {
		if (index < 0 || index >= size()) {
			return null;
		}
		return super.get(index);
	}
	
}
