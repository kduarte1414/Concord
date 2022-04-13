package concord;

import java.rmi.Remote;
import java.rmi.RemoteException;



public interface Observed extends Remote
{
	public void addObserver(Observer o) throws RemoteException;
	public void removeObserver(Observer o) throws RemoteException;
}
