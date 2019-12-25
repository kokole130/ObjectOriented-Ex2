package dataStructure;


public class Edge implements edge_data{
	int src,dest;
	double weight;
	int tag;


	public Edge(int s,int d,double w) {
		this.src=s;
		this.dest=d;
		this.weight=w;
		this.tag=0;
	}

	public Edge(Edge other) {
		this.dest=other.dest;
		this.src=other.src;
		this.weight=other.weight;
		this.tag=0;
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
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
	}

	public boolean equals(Object o) {
		if(o!=null) {
			Edge t=(Edge)o;
			if(this.src==t.src&&this.dest==t.dest)return true;
			return false;
		}
		return false;
	}

	public String toString() {
		return "Source:"+this.src+" Destination:"+this.dest+" Weight:"+this.weight+"\n";
	}
	
	public Edge copy() {
		Edge tmp = new Edge(this);
		return tmp;
	}
}
