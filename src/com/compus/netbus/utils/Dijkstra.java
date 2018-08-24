package com.compus.netbus.utils;


public class Dijkstra {
	private static int N =520;
	private static int inf =999999;
	private static int[] disp = new int[N];
    private static Boolean[] visited = new Boolean[N];
    
    public  void memset1(int[] disp,int length) {
    	for(int i=0;i<length;i++) {
    		 disp[i]=inf;
    	}
    }
    public  void memset(Boolean[] visited,int length) {
    	for(int i=0;i<length;i++) {
    		visited[i]=false;
    	}
    }
	public static int[] getDisp() {
		return disp;
	}
	public static void setDisp(int[] disp) {
		Dijkstra.disp = disp;
	}
	public static Boolean[] getVisited() {
		return visited;
	}
	public static void setVisited(Boolean[] visited) {
		Dijkstra.visited = visited;
	}
	public static int getN() {
		return N;
	}
	public static int getInf() {
		return inf;
	}

    
   
	} 

