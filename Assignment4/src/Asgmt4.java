import java.util.*;
import java.io.File;
import java.io.IOException;

public class Asgmt4 {
	public static void main(String[] args) throws IOException {
		String[] fnameExt = args[0].split("\\.");
		Scanner kb = new Scanner(new File(args[0]));
		int x = kb.nextInt(), y = kb.nextInt(), n = kb.nextInt();
		ArrayList<PixelVertex> verts = new ArrayList<>(n);
		int[][] adj = new int[n][n];
		for (int i = 0; i < n; i++)
			adj[i][i] = -1;				

		for (int i = 0; i < n; i++) {
			int name = kb.nextInt();
			PixelVertex vert = new PixelVertex(x*y);
			
			for(int j = 0; j < (x*y); j++)
			{
				int index = kb.nextInt();
				if(index == 1)
					vert.pixel[j] = true;
				else if(index == 0)
					vert.pixel[j] = false;
			}
			
			verts.add(vert);
		}
		
		for(int i = 0; i < verts.size(); i++)
		{
			for(int k = 0; k < verts.size(); k++)
			{
				if(i != k)
				{
					adj[i][k] = verts.get(i).difference(verts.get(k));
				}
			}
		}


		for(int i =0; i<n;i++)
		{
			for(int j = 0; j<n;j++)
			{
				adj[j][i] = adj[i][j];
			}
		}
		
		Graph g = new Graph(adj);
		if (fnameExt.length >= 2) {
			g.printGraph(fnameExt[0] + "-GRAPH_out." + fnameExt[1]);
			g.printMST(fnameExt[0] + "-MST_out." + fnameExt[1]);
		}
		else {
			g.printGraph(fnameExt[0] + "-GRAPH_out");
			g.printMST(fnameExt[0] + "-MST_out");
		}
	}
}