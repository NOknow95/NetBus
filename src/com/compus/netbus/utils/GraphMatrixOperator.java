package com.compus.netbus.utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.compus.netbus.bean.EdgeType;
import com.compus.netbus.bean.VertexType;
 
public class GraphMatrixOperator
{
    private static final int INFINITY = 65535;
    private ArrayList<ArrayList<EdgeType>> arcList = new ArrayList<ArrayList<EdgeType>>();
    private ArrayList<VertexType> vertexes = new ArrayList<VertexType>();
    private HashMap<String, VertexType> vertexNodesHashMap = new HashMap<String, VertexType>();
    private int numVertexes;
    private int numEdges;
 
    public ArrayList<ArrayList<EdgeType>> getArcList()
    {
        return arcList;
    }
 
 
    public ArrayList<VertexType> getVertexes()
    {
        return vertexes;
    }
 
 
    public HashMap<String, VertexType> getVertexNodesHashMap()
    {
        return vertexNodesHashMap;
    }
 
 
    /* 建立无向网图的邻接矩阵表示 */
    public static void initMGraph(GraphMatrixOperator g)
    {
 
    }
 
    protected static void initVertexes(GraphMatrixOperator g)
    {
        for (int i = 0; i < g.numVertexes; i++) /* 读入顶点信息,建立顶点表 */
        {
            g.getVertexes().set(i, new VertexType(i, ""));
        }
    }
 
    protected static void initArcWithIJ(GraphMatrixOperator g)
    {
        for (int i = 0; i < g.getNumVertexes(); i++)
        {
            for (int j = i; j < g.getNumVertexes(); j++)
            {
                g.getArcList().get(j).get(i).setWeight(g.getArcList().get(i).get(j).getWeight());
            }
        }
    }
 
 
    protected static void initNet(GraphMatrixOperator g)
    {
        for (int i = 0; i < g.numVertexes; i++)/* 初始化图 */
        {
            for (int j = 0; j < g.numVertexes; j++)
            {
                if (i == j)
                {
                    g.getArcList().get(i).set(j, new EdgeType(0));
                }
                else
                {
                    g.getArcList().get(i).set(j, new EdgeType(INFINITY));
                    g.getArcList().get(j).set(i, new EdgeType(INFINITY));
                }
            }
        }
    }
 
    private static void initArcList(GraphMatrixOperator g, int size)
    {
        for (int i = 0; i < size; i++)
        {
            ArrayList<EdgeType> al = new ArrayList<EdgeType>();
            g.getArcList().add(al);
 
            for (int j = 0; j < size; j++)
            {
                if (i == j)
                {
                    al.add(new EdgeType(0));
                }
                else
                {
                    al.add(new EdgeType(INFINITY));
                }
            }
        }
    }
 
    public static void addArcFromI2J(GraphMatrixOperator g, String a, String b, int w)
    {
        addVex(g, a);
        addVex(g, b);
        g.getArcList().get(g.getVertexNodesHashMap().get(a).getId()).get(g.getVertexNodesHashMap().get(b).getId()).setWeight(w);
        g.getArcList().get(g.getVertexNodesHashMap().get(b).getId()).get(g.getVertexNodesHashMap().get(a).getId()).setWeight(w);
    }
 
    private static void addVex(GraphMatrixOperator g, String a)
    {
        if (!g.getVertexNodesHashMap().containsKey(a))
        {
            int size = g.getVertexes().size();
            VertexType e = new VertexType(size, a);
            g.getVertexes().add(e);
            g.getVertexNodesHashMap().put(a, e);
        }
    }
 
 
 
    public static GraphMatrixOperator createMGraph(String[] src)
    {
        GraphMatrixOperator g = new GraphMatrixOperator();
        initArcList(g, src.length);
 
        for (String s : src)
        {
            System.out.println(s);
            String[] line = s.split(",");
            addArcFromI2J(g, line[0], line[1], Integer.valueOf(line[2]));
        }
        int size = g.getVertexes().size();
        g.setNumVertexes(size);
        g.setNumEdges(src.length);
 
        return g;
    }
 
    public void setNumVertexes(int numVertexes)
    {
        this.numVertexes = numVertexes;
    }
 
    public int getNumVertexes()
    {
        return numVertexes;
    }
 
    public void setNumEdges(int numEdges)
    {
        this.numEdges = numEdges;
    }
 
    public int getNumEdges()
    {
        return numEdges;
    }
 
    /* Floyd算法，求网图G中各顶点v到其余顶点w的最短路径p[v][w]及带权长度d[v][w]。 */
    public static void shortestPath_Floyd(GraphMatrixOperator g, int[][] pList, int[][] dList)
    {
        int size = g.numVertexes;
        for (int v = 0; v < size; ++v) /* 初始化D与P */
        {
            for (int w = 0; w < size; ++w)
            {
                dList[v][w] = g.arcList.get(v).get(w).getWeight();	/* d[v][w]值即为对应点间的权值 */
                pList[v][w] = w;				/* 初始化P */
            }
        }
        for (int k = 0; k < size; ++k)
        {
            for (int v = 0; v < size; ++v)
            {
                for (int w = 0; w < size; ++w)
                {
                    if (dList[v][w] > dList[v][k] + dList[k][w])
                    {/* 如果经过下标为k顶点路径比原两点间路径更短 */
                        dList[v][w] = dList[v][k] + dList[k][w];/* 将当前两点间权值设为更小的一个 */
                        pList[v][w] = pList[v][k];/* 路径设置为经过下标为k的顶点 */
                    }
                }
            }
        }
    }
 
    public static String main_floyd(GraphMatrixOperator g)
    {	String shortpath = null;
        int size = g.getVertexes().size();
        int[][] pList = new int[size][size];
        int[][] dList = new int[size][size];
 
        shortestPath_Floyd(g, pList, dList);
 
        shortpath=printPath(size, pList, dList);
        return shortpath;
    }
 
 
    public static String printPath(int size, int[][] pList, int[][] dList)
    {	String shortpath =null;
    	String shortpath1 ="";
        System.out.println("各顶点间最短路径如下:");
        for (int v = 0; v < size; ++v)
        {
            for (int w = v + 1; w < size; w++)
            {
            	
                System.out.println(v + "-" + w + ":" + dList[v][w]);
                
                shortpath = printPathFromV2W(pList, v, w);
                
                shortpath1+=v+"-"+w+":"+shortpath+";";
                System.out.println(shortpath);    
            }
            
        }
        return shortpath1;
    }
 
    //打印最短路径经过的点
    public static String printPathFromV2W(int[][] pList, int v, int w)
    {
        int k;
        String str = "";
        k = pList[v][w];				/* 获得第一个路径顶点下标 */
//        System.out.print(v + ",");	/* 打印源点 */
        str+= v+",";
        while (k != w)				/* 如果路径顶点下标不是终点 */
        {
//            System.out.print(k + ",");	/* 打印路径顶点 */
            str+=k+",";
            k = pList[k][w];			/* 获得下一个路径顶点下标 */
        }
//        System.out.println(w);	/* 打印终点 */
        str+=w;
        return str;
    }
}