
package bl;


import java.io.Serializable;
import java.util.Date;
/**
 * Defines a task by a string and a boolean to indicate if the task is done or not (checked). There are some 
 * other possibilities for the future like a deadline or additional notes.
 */

public class Task implements Serializable{
	
	private String name;
	private String notes; /** additional notes about the task */
	private Date deadLine;
	private boolean checked;
	
	//constructors
	
	public Task(String name) {
		super();
		this.name=name;
		this.notes = "";
		this.checked=false;
	}
	
	public Task(String name, String notes, Date deadLine, boolean checked) {
		super();
		this.name = name;
		this.notes = notes;
		this.deadLine = deadLine;
		this.checked = checked;
	}
	
	public Task(String name, String notes) {
		super();
		this.name = name;
		this.notes = notes;
	}
	
	
	//access

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public String toString()
	{
		String check = new String();
		if (isChecked())
		{
			check = "\u2713";
		}
		else
		{
			check ="";
		}
		return(getName() + " " + check);
	}
	/**
	 * this method is used in the Jlist of the UI
	 * @return
	 */
	public String toStringInfos()
	{
		String check = new String();
		if (isChecked())
		{
			check = "\u2713";
		}
		else
		{
			check ="";
		}
		return(getName() + " " + check + "\n"+	getDeadLine() + "\n" + getNotes() );
		
	}
}
