package dataStructure;


import utils.Point3D;

public class Vertex implements node_data {
	double weight;
	int key;
	Point3D p;
	int tag;
	int lastKey;// "lastkey(int),weightpaid(double)"

	public Vertex(Point3D p) {
		this.p=new Point3D(p);
		this.tag=0;
		this.weight=Integer.MAX_VALUE;
	}
	
	public Vertex(int key,Vertex other) {
		this.key=key;
		this.p=new Point3D(other.p);
		this.weight=Integer.MAX_VALUE;
		this.tag=0;
	}


	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return this.p;
	}

	@Override
	public void setLocation(Point3D p) {
		Point3D tmp=new Point3D(p);
		this.p=tmp;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight=w;
	}

	@Override
	public String getInfo() {
		return "Vertex:"+key+"\nWeight:"+weight+"\nPoint:"+p;
	}
	
	public int getLastKey() {
		return this.lastKey;
	}
	
	public void setLastKey(int key) {
		this.lastKey=key;
	}

	@Override
	public void setInfo(String s) {
		for (int i = 0; i < s.length(); i++) {
			if(s.substring(i,i+7).equals("Vertex:")) {
				i=i+7;
				for (int j = i; j < s.length(); j++) {
					if(s.charAt(j)=='\n') {
						this.key=Integer.parseInt(s.substring(i, j));
						break;
					}
				}
			}
			if(s.substring(i,i+7).equals("Weight:")) {
				i=i+7;
				for (int j = i; j < s.length(); j++) {
					if(s.charAt(j)=='\n') {
						this.weight=Double.parseDouble(s.substring(i, j));
						break;
					}
				}
			}
			if(s.substring(i,i+6).equals("Point:")) {
				i=i+6;
				this.p=new Point3D(s.substring(i));
				break;
			}
		}
	}


	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
	}
	
	public String toString() {
		return "Vertex:"+key+" Weight:"+weight+" Point:"+p+"\n";
	}
	

}
