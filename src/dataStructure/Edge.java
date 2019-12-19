package dataStructure;

import utils.Point3D;

public class Edge implements edge_data{
	Vertex src,dest;
	double weight;
	boolean tag;
	
	public static void main(String[] args) {
		Vertex s=new Vertex(1, new Point3D("2,2,2"));
		Vertex d=new Vertex(2, new Point3D(4,-1,0));
		
		Edge e=new Edge(s, d, 6.2);
		System.out.println(e.getInfo());
		
	}
	
	public Edge(Vertex s,Vertex d,double w) {
		this.src=new Vertex(s.key,s.p);
		this.dest=new Vertex(d.key,d.p);
		this.weight=w;
		this.tag=false;
	}
	
	@Override
	public int getSrc() {
		return src.key;
	}

	@Override
	public int getDest() {
		return dest.key;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getInfo() {
		//return "Edge details:\nSource Point: "+this.src.p.toString()+"\nDestination Point: "+this.dest.p.toString()+"\nWeight: "+this.weight;
		return "Source:"+this.src.key +" ," +"Destination:"+this.dest.key +" ,"+"Weight:"+this.weight;
	}

	@Override
	public void setInfo(String s) {
		int count=0;
//		for (int i = 0; i < s.length(); i++) {
//			if(s.charAt(i)==':') {
//				count++;
//				if(count==2) {
//					int j=i;
//					for(;s.charAt(j)!='\n';j++);
//					this.src.p=new Point3D(s.substring(i+2, j));
//				}
//				else if(count==3) {
//					int j=i;
//					for(;s.charAt(j)!='\n';j++);
//					this.dest.p=new Point3D(s.substring(i+2, j));
//				}
//				else if(count==4) {
//					this.weight=Double.parseDouble(s.substring(i+2));
//				}
//			}
//			
//		}
		
		for (int i = 0,j=0; i < s.length(); i++) {
			if(s.charAt(i)==':') {
				if(count==0) {
					j=s.indexOf(' ');
					int sourceKey=Integer.parseInt(s.substring(i+1, j));
					//Vertex src=new Vertex(sourceK, p)
					//this.src=
					s=s.substring(j+2);
					count++;
				}
				else if(count==1) {
					j=s.indexOf(' ');
					int DestKey=Integer.parseInt(s.substring(i+1,j));
					s=s.substring(j+2);
					count++;
				}
				else {
					double weight=Double.parseDouble(s.substring(i+1));
				}
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
