package prob2;

public class SmartPhone extends MusicPhone {
	
	public void execute(String function) {
		if(function.equals("앱")) {
			startApp();
			return;
		}
		super.execute(function);
		
	}

	private void startApp() {
		System.out.println("앱실행");
		
	}

}
