class Rectangle{
	Point p1;
	Point p2;

	Rectangle(int x1,int y1,int x2,int y2){
		p1 = new Point(x1,y1);
		p2 = new Point(x2,y2);
	}

	Rectangle (Point p1, Point p2){
		this.p1 = new Point(p1.x,p1.y);
		this.p2 = new Point(p2.x,p2.y);
	}


	public int area(){
		int height;
		int length;

		length = p2.x - p1.x;
		height = p2.y - p1.y;

		return (length * height);
	}

	public int perimeter(){
		int height;
		int length;

		length = p2.x - p1.x;
		height = p2.y - p1.y;

		return ((length * 2) + (height * 2));
	}

	public boolean pointInside(Point p){
		if( p.x >= p1.x && p.x <= p2.x && p.y >= p1.y && p.y <= p2.y )
			return true;
		else
			return false;
	}

	public boolean rectangleInside(Rectangle r){
		if (r.p1.x >= p1.x && r.p2.x <= p2.x && r.p1.y >= p1.y && r.p2.y <= p2.y)
			return true;
		else
			return false;
	}
}