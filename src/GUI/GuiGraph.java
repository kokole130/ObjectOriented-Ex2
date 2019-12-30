package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import algorithms.Graph_Algo;
import utils.Point3D;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Vertex;
import dataStructure.node_data;

public class GuiGraph extends JFrame implements MouseListener,ActionListener{

	public static void main(String[] args) {
		Graph_Algo g=new Graph_Algo();
		Vertex v1=new Vertex(new Point3D(118,368,0));
		Vertex v2=new Vertex(new Point3D(50,150,0));
		Vertex v3=new Vertex(new Point3D(282,228,0));
		Vertex v4=new Vertex(new Point3D(222,285,0));
		Vertex v5=new Vertex(new Point3D(414,116,0));
		Vertex v6=new Vertex(new Point3D(58,136,0));


		g.init(new DGraph());

		GuiGraph t = new GuiGraph();
		t.ga.init(new DGraph());

	}

	public Graph_Algo ga;
	List<node_data> sp=new LinkedList<>();
	List<node_data> tsp=new LinkedList<>();
	Label connectedl=new Label();


	public GuiGraph() {
		ga=new Graph_Algo();
		InitGui();
	}

	public void InitGui() {
		this.setSize(500, 500);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();

		Menu menu1 = new Menu("File");
		menuBar.add(menu1);

		MenuItem save=new MenuItem("Save");
		MenuItem open=new MenuItem("Open");

		menu1.add(save);
		menu1.add(open);

		Menu menu2 = new Menu("Edit");
		menuBar.add(menu2);

		MenuItem connect=new MenuItem("Connect");
		MenuItem removeEdge=new MenuItem("Remove edge");
		MenuItem removeVertex=new MenuItem("Remove vertex");

		menu2.add(connect);
		menu2.add(removeEdge);
		menu2.add(removeVertex);

		Menu menu3 = new Menu("Algorithms");
		menuBar.add(menu3);

		//MenuItem shortPathWeight=new MenuItem("shortest path distance");
		MenuItem shortPathList=new MenuItem("Shortest path");
		MenuItem connected=new MenuItem("The graph is connected ?");
		MenuItem Tsp=new MenuItem("TSP");

		menu3.add(connected);
		menu3.add(shortPathList);
		menu3.add(Tsp);

		this.setMenuBar(menuBar);

		save.addActionListener(this);
		open.addActionListener(this);
		connect.addActionListener(this);
		removeEdge.addActionListener(this);
		removeVertex.addActionListener(this);
		shortPathList.addActionListener(this);
		connected.addActionListener(this);
		Tsp.addActionListener(this);

		this.addMouseListener(this);

		this.setVisible(true);

	}
	public void actionPerformed(ActionEvent e) 
	{
		int src=0,dest=0;
		double weight=0;


		String file=e.getActionCommand();

		if(file.equals("Save")) {
			this.ga.save("graph.txt");
			System.out.println("the graph is saved in the project folder named 'graph.txt'");
		}
		if(file.equals("Open")) {
			this.ga.init("graph.txt");
			System.out.println("the graph is opened and presented in the window");
		}
		if(file.equals("Connect")) {
			JFrame frame = new JFrame();
			String name = JOptionPane.showInputDialog(frame,"Source: ");
			if(name!=null&&(!name.equals(""))) {
				src=Integer.parseInt(name);
			}
			name = JOptionPane.showInputDialog(frame,"Destination: ");
			if(name!=null&&(!name.equals(""))) {
				dest=Integer.parseInt(name);
			}
			name = JOptionPane.showInputDialog(frame,"Weight: ");
			if(name!=null&&(!name.equals(""))) {
				weight=Double.parseDouble(name);
			}
			this.ga.graph.connect(src, dest, weight);
		}
		if(file.equals("Remove edge")) {
			JFrame frame = new JFrame();
			String name = JOptionPane.showInputDialog(frame,"Source: ");
			if(name!=null&&(!name.equals(""))) {
				src=Integer.parseInt(name);
			}
			name = JOptionPane.showInputDialog(frame,"Destination: ");
			if(name!=null&&(!name.equals(""))) {
				dest=Integer.parseInt(name);
			}
			this.ga.graph.removeEdge(src, dest);
		}
		if(file.equals("Remove vertex")) {
			JFrame frame = new JFrame();
			String name = JOptionPane.showInputDialog(frame,"Key: ");
			if(name!=null&&(!name.equals(""))) {
				src=Integer.parseInt(name);
			}
			this.ga.graph.removeNode(src);
		}
		if(file.equals("Shortest path")) {
			JFrame frame = new JFrame();
			String name = JOptionPane.showInputDialog(frame,"Source: ");
			if(name!=null&&(!name.equals(""))) {
				src=Integer.parseInt(name);
			}
			name = JOptionPane.showInputDialog(frame,"Destination: ");
			if(name!=null&&(!name.equals(""))) {
				dest=Integer.parseInt(name);
			}
			if(this.ga.isConnected(src,dest,src)) {
			this.sp=this.ga.shortestPath(src, dest);
			}
			else {
				connectedl.setText("There is not a path between "+src+" and "+dest);
				connectedl.setBounds(1, 1, 200, 30);
				add(connectedl);
			}
		}
		if(file.equals("The graph is connected ?")) {
			connectedl.setText("The graph is connected?: "+this.ga.isConnected());
			connectedl.setBounds(1, 1, 200, 30);
			add(connectedl);
		}
		if(file.equals("TSP")) {
			JFrame frame = new JFrame();
			String name = JOptionPane.showInputDialog(frame,"add number of keys with ',' between them: ");
			LinkedList<Integer> s =new LinkedList<>();
			int j=0;
			if(name!=null) {
				for (int i = 0; i < name.length(); i++) {
					if(name.charAt(i)==',') {
						s.add(Integer.parseInt(name.substring(j,i)));
						j=i+1;
					}
					if(i==name.length()-1) {
						s.add(Integer.parseInt(name.substring(i)));
						break;
					}
				}
				this.tsp=this.ga.TSP(s);
			}
		}
		repaint();

	}

	public void paint(Graphics g)
	{
		super.paint(g);

		g=(Graphics2D)g;
		for (int i = 0; i < this.ga.graph.ver.size(); i++) {


			if(this.ga.graph.ver.get(i) != null)
			{
				Vertex p = this.ga.graph.ver.get(i).copy();
				g.setColor(Color.BLUE);
				g.fillOval(p.getLocation().ix()-7, p.getLocation().iy()-7, 14, 14);
				g.setFont(new Font("Monaco", Font.PLAIN, 22));
				g.drawString(""+p.getKey(), ((p.getLocation().ix())),((p.getLocation().iy()-15)));
			}
		}
		g.setColor(Color.RED);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		for (int i = 0; i < this.ga.graph.ver.size(); i++) {
			if(this.ga.graph.edge.get(i)!=null) {
				for (int j = 0; j < this.ga.graph.ver.size(); j++) {
					if(this.ga.graph.edge.get(i).containsKey(j)) {
						Vertex v1=this.ga.graph.ver.get(this.ga.graph.edge.get(i).get(j).getSrc()).copy();
						Vertex v2=this.ga.graph.ver.get(this.ga.graph.edge.get(i).get(j).getDest()).copy();

						g.drawLine((int)v1.getLocation().x(),(int)v1.getLocation().y(),
								(int)v2.getLocation().x(),(int)v2.getLocation().y());
						int x=0;
						int y=0;
						int tmp1=v1.getLocation().ix();
						int tmp2=v1.getLocation().iy();
						for (int k = 0; k < 3; k++) {
							x=(tmp1+v2.getLocation().ix())/2;
							y=(tmp2+v2.getLocation().iy())/2;
							tmp1=x;
							tmp2=y;
						}

						g.setColor(Color.yellow);
						g.fillOval(x-5, y-5, 10, 10);
						g.setColor(Color.RED);

						g.drawString(this.ga.graph.edge.get(i).get(j).getWeight()+"",
								(int)((v1.getLocation().x()+v2.getLocation().x())/2),
								(int)((v1.getLocation().y()+v2.getLocation().y())/2));


					}
				}
			}
		}

		for (int i = 1; i < sp.size(); i++) {
			g.setColor(Color.CYAN);
			g.drawLine(sp.get(i-1).getLocation().ix(),sp.get(i-1).getLocation().iy(),
					sp.get(i).getLocation().ix(),sp.get(i).getLocation().iy());		
		}
		if(tsp!=null) {
			for (int i = 1; i < tsp.size(); i++) {
				g.setColor(Color.CYAN);
				g.drawLine(tsp.get(i-1).getLocation().ix(),tsp.get(i-1).getLocation().iy(),
						tsp.get(i).getLocation().ix(),tsp.get(i).getLocation().iy());
			}
		}
	}



	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		this.ga.graph.addNode(new Vertex(new Point3D(x,y,0)));
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//		System.out.println("mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//		System.out.println("mouseExited");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//		System.out.println("mouseReleased");

	}
}