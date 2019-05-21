package commonLibs.contract;

public interface IAlerts {
	
	public void acceptAlert() throws Exception;

	public void rejectAlert() throws Exception;

	public String getMessageFromAlert() throws Exception;

	public boolean checkAlertPresent(int timeoutInseconds) throws Exception;


}
