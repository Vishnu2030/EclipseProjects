package daostuff.exceptions;

@SuppressWarnings("serial")
public class DepartmentNotFoundException extends Exception {
	public DepartmentNotFoundException(String nmsg) {
		super(nmsg); // forward the msg to the super class 
	}
}