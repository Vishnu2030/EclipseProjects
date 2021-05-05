package daostuff.exceptions;

@SuppressWarnings("serial")
public class DepartmentAlreadyException extends Exception {
	public DepartmentAlreadyException(String msg) {
		super(msg); // forward the msg to the super class hierachy
	}
}
