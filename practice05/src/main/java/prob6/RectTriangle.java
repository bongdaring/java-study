package prob6;

public class RectTriangle extends Shape{
	private double width;
	private double height;
	
	public RectTriangle(double w, double h) {
		this.width = w;
		this.height = h;
	}

	@Override
	public double getArea() {
		return (this.width*this.height)/2;
	}

	@Override
	public double getPerimeter() {
		return this.width 
				+ this.height 
				+ Math.sqrt(Math.pow(this.width, 2)
						+ Math.pow(this.height, 2));
	}
}
