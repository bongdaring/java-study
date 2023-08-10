package tv;

public class TV {
	private int channel; // 1 ~ 255
	private int volume; // 0 ~ 100
	private boolean power;
	
	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}
	
	public void power(boolean on) {
		System.out.println("TV가 " + (on ? "켜졌습니다." : "꺼졌습니다."));
		power = on;
	}
	
	public void channel(boolean up) {
		if(up) {
			channel(this.channel + 1);
			return;
		} 
		channel(this.channel - 1);
	}
	
	public void channel(int channel) {
		checkPower();
		if(channel > 255) {
			this.channel = 1;
			return;
		} else if(channel < 1) {
			
		}
		this.channel = channel;
	}
	
	public void volume(boolean up) {
		volume(this.volume + 1);
	}
	
	public void volume(int volume) {
		checkPower();
		if(volume > 100) {
			this.volume = 0;
			return;
		}
		this.volume = volume;
	}
	
	public void status() {
		System.out.println("TV[power=" + (power ? "ON" : "OFF")
				+ ", channel = "+channel+", volume = "+volume);
	}
	
	public void checkPower() {
		if(!power) {
			System.out.println("TV가 꺼져있습니다.");
		}
	}

}
