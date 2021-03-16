import java.io.PrintWriter;

import java.io.IOException;
import java.util.PriorityQueue;

class Graph {
	int n;
	int[][] adj;

	public Graph(int[][] _adj) {
		adj = _adj;
		n = adj.length;
	}

	public void printGraph(String outFile) throws IOException {
		PrintWriter pr = new PrintWriter(outFile);
		pr.println("Edge\t\tWeight");
		boolean[] vis = new boolean[n];
		this.DFS(0, vis, pr);
		pr.close();
	}

	private void DFS(int u, boolean[] vis, PrintWriter pr) {
		vis[u] = true;
		for(int i = 0;i<n;i++) 
		{
			if(!vis[i] && adj[u][i] != -1)
			{
				pr.print(u + " - " + i + "      	  ");
				pr.println(adj[u][i]);
				DFS(i,vis,pr);
			}
		}
	}
	
	public void printMST(String outFile) throws IOException {
		PrintWriter pr = new PrintWriter(outFile);
		pr.println("Edge\t\tWeight");
		this.prim(pr);
		pr.close();
	}
	
	int minKey(int key[], Boolean mstSet[]) 
    { 
        int min = Integer.MAX_VALUE, min_index=-1; 
  
        for (int v = 0; v < n; v++) 
            if (mstSet[v] == false && key[v] < min) 
            { 
                min = key[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 

	private void prim(PrintWriter pr) {
		
        int par[] = new int[n]; 
        int cost[] = new int [n]; 
        Boolean vis[] = new Boolean[n]; 
  
        for (int i = 0; i < n; i++) 
        { 
            cost[i] = Integer.MAX_VALUE; 
            vis[i] = false; 
        } 
  
        cost[0] = 0;                    
        par[0] = -1; 
   
        for (int count = 0; count < n-1; count++) 
        { 
            int u = minKey(cost, vis); 
  
            vis[u] = true; 
  
            for (int v = 0; v < n; v++) 
 
                if (adj[u][v]!=0 && vis[v] == false && 
                    adj[u][v] < cost[v]) 
                { 
                    par[v] = u; 
                    cost[v] = adj[u][v]; 
                    
                } 
        } 
        for(int i = 1; i<n; i++)
        {
        	pr.println(par[i] + " - " + i + "		  " + adj[i][par[i]]);
        }

	}
}