package amogh.pos.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Database<T extends Serializable> {

	private final String FILE_NAME;
	
	public Database(String filename) {
		this.FILE_NAME = filename;
	}
	
	public void write(T t) throws Exception {
		
		ObjectOutputStream stream = new ObjectOutputStream(
				new FileOutputStream(new File(FILE_NAME)));
		stream.writeObject(t);
		stream.close();
		
	}
	
	public T read() {
		
		T controller = null;
		try {
			
			// ObjectInputStream
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File(FILE_NAME)));
			controller = (T) stream.readObject();
			stream.close();
			
		} catch (Exception e) {
			// skip
		}
		return controller;
		
	}
	
}
