package bl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
	
	private Serializer(){
		super();
	}
	
	public static Serializer instance;
	
	public static Serializer getInstance()
	{
		if (instance==null)
		{
			instance = new Serializer();
		}
		return (instance );
	}


/**
 * serialize the given TaskManager, it saves all his tasks
 * @param myTaskManager
 */
	public void save(TaskManager myTaskManager){


		try {
			FileOutputStream toDoList = new FileOutputStream("toDoList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(toDoList);
			oos.writeObject(myTaskManager);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}


	}
	/**
	 * load the serialized TaskManager that is containing all the tasks
	 * @return
	 */

	public TaskManager load()
	{
		TaskManager myTaskManager = new TaskManager();
		try {
			FileInputStream toDoList = new FileInputStream("toDoList.ser");
			ObjectInputStream ois = new ObjectInputStream(toDoList);
			myTaskManager = (TaskManager) ois.readObject();
			ois.close();
			
			
		} 
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return myTaskManager;


	}

}
