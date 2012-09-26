

package bl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Contains the arrayList of the tasks. Provides the methods to manage this list that corresponds to the main 
 * functionalities. This is the main interaction to the GUI.
 */
public class TaskManager implements Serializable{
	
	private ArrayList<Task> myTasks;
	
	public TaskManager() {
		super();
		this.myTasks = new ArrayList<Task>();
	}
	
	//methods
	
	public void addTask(String name)
	{
		myTasks.add(new Task(name));
	}
	
	
	public void deleteTask(int rank)
	{
		myTasks.remove(rank);
		
	}
	
	/**
	 * reverse the boolean "checked" of the Task at the given rank
	 *if the state is checked it becomes toDo; if its toDo, it becomes checked
	 * @param rank
	 */
	public void changeStateTask(int rank)
	{
		if (myTasks.get(rank).isChecked())
		{
			myTasks.get(rank).setChecked(false);
		}
		else 
		{
			myTasks.get(rank).setChecked(true);

		}
	}
	
	public void checkAllTasks ()
	{
		for ( Task ta: myTasks)
		{
			ta.setChecked(true);
		}

		
	}
	
	public void deleteAllcheckedTasks()
	{
		ListIterator<Task> ite = myTasks.listIterator();
		
		while (ite.hasNext())
		{
			
			if (( ite.next()).isChecked())
			{
				ite.remove();
			}
		}	
	}
	public void deleteAllTasks()
	{
		myTasks.clear();
	}
	/**
	 * switch 2 tasks in the arrayList myTasks
	 * @param currentRank
	 * @param newRank
	 */
	public void permutTask(int currentRank, int newRank)
	{
		
		Task permuted = myTasks.get(currentRank);
		myTasks.set(currentRank, myTasks.get(newRank));
		myTasks.set(newRank, permuted);
	}
	/**
	 * change the position of a task in the arrayList myTasks (by one to the top)
	 *
	 * @param rank
	 */
	public void upTask(int rank)
	{
		if (rank>0)
		{
		this.permutTask(rank,rank-1);
		}
	}
	
	/**
	 * change the position of a task in the arrayList myTasks (by one to the bottom)
	 *
	 * @param rank
	 */
	public void downTask(int rank)
	{
		if(rank < (myTasks.size()-1))
		{
		this.permutTask(rank,rank+1);
		}
	}
	
	//access

	public ArrayList<Task> getMyTasks() {
		return myTasks;
	}

	public void setMyTasks(ArrayList<Task> myTasks) {
		this.myTasks = myTasks;
	}
	
	public Task getTask(int rank)
	{
		return(myTasks.get(rank));
	}
	
	
	
	
	
	

}
