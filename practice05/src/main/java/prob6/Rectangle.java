package prob6;

public class Rectangle extends Shape implements Resizable {
	private double width;
	private double height;
	
	public Rectangle(double w, double h) {
		this.width = w;
		this.height = h;
	}

	@Override
	public double getArea() {
		return this.width * this.height;
	}

	@Override
	public double getPerimeter() {
		return (this.width + this.height)*2;
	}

	@Override
	public void resize(double s) {
		this.width = this.width * s;
		this.height = this.height * s;
	}


}
