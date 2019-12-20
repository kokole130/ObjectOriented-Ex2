package dataStructure;

import utils.Point3D;

public class Edge implements edge_data{
	int src,dest;
	double weight;
	boolean tag;

	public static void main(String[] args) {
		Vertex s=new Vertex(1, new Point3D("2,2,2"));
		Vertex d=new Vertex(2, new Point3D(4,-1,0));

		Edge e=new Edge(s, d, 6.2);
		System.out.println(e.getInfo());
		e.setInfo("Source:1\n" + 
				"Destination:3\n" + 
				"Weight:75");
		System.out.println(e.getInfo());

	}

	public Edge(Vertex s,Vertex d,double w) {
		this.src=s.key;
		this.dest=d.key;
		this.weight=w;
		this.tag=false;
	}

	@Override
	public int getSrc() {
		return this.src;
	}

	@Override
	public int getDest() {
		return this.dest;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getInfo() {
		//return "Edge details:\nSource Point: "+this.src.p.toString()+"\nDestination Point: "+this.dest.p.toString()+"\nWeight: "+this.weight;
		return "Source:"+this.src+"\nDestination:"+this.dest+"\nWeight:"+this.weight;
	}

	@Override
	public void setInfo(String s) {
		for (int i = 0; i < s.length(); i++) {
			if(i+7<s.length()&&s.substring(i,i+7).equals("Source:")) {
				i=i+7;
				for (int j = i; j < s.length(); j++) {
					if(s.charAt(j)=='\n') {
						this.src=Integer.parseInt(s.substring(i, j));
						break;
					}
				}
			}
			if(i+12<s.length()&&s.substring(i,i+12).equals("Destination:")) {
				i=i+12;
				for (int j = i; j < s.length(); j++) {
					if(s.charAt(j)=='\n') {
						this.dest=Integer.parseInt(s.substring(i, j));
						break;
					}
				}
			}
			if(i+7<s.length()&&s.substring(i,i+7).equals("Weight:")) {
				i=i+7;
				this.weight=Double.parseDouble(s.substring(i));
				break;
			}
		}
	}

	@Override
	public int getTag() {
		return 0;
	}

	@Override
	public void setTag(int t) {
		return;
	}

}
