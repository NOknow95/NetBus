package com.compus.netbus.utils;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.compus.netbus.bean.Point1;



public class RoutePlan {
	List<Point1> points = new ArrayList<Point1>();
	int head[]=new int[1000];
	int cnt=0;
	Long dis[]=new Long[1000];
//	List dis = new ArrayList();
	int book[]=new int[1000];
	static int pre[]=new int[1000];

	public void add(int from,int to,Long value)		//建图
	{
	 	Point1 point = new Point1();
	 	point.setTo(to);
	 	point.setTotalPeople(value);
	 	point.setFrom(head[from]);
		points.add(point);
		head[from] = cnt++;
	}

	public void init()								//初始化
	{
		memset(points, 0,(long) 0, points.size());
		memset1(head, -1, head.length);
		memset2(dis, (long) 0,dis.length);
		memset3(book, 0, book.length);
		memset4(pre,1,pre.length);
		cnt = 0;
	}

	private void memset4(int[] pre2, int i, int length) {
		// TODO Auto-generated method stub
		int j;
		for(j=0;j<length;j++){
			pre2[j]=i;
		}
	}

	private void memset3(int[] book2, int i, int length) {
		// TODO Auto-generated method stub
		int j;
		for(j=0;j<length;j++){
			book[j]=i;
		}
	}

	private void memset2(Long[] dis2, Long i, int length) {
		// TODO Auto-generated method stub
		int j;
		for(j=0;j<length;j++){
			dis[j]=i;
		}
	}

	private void memset1(int[] head2, int i, int length) {
		// TODO Auto-generated method stub
		int j;
		for(j=0;j<length;j++){
			head2[j]=i;
		}
	}

	private void memset(List<Point1> points, int i,Long m,int length) {
		int j;
		for(j=0;j<length;j++){
		 	Point1 point1=new Point1();
		 	point1.setTo(i);
			point1.setFrom(i) ;
			point1.setTotalPeople(m);
			points.set(j, point1);
		}
	}

	public void spfa(int n)
	{
		
		Queue q=new Queue();
		q.push(n);
		book[n] = 1;
		while (!q.empty())
		{	
			int now = q.peek();
			q.pop();
			book[now] = 0;			
			for (int k = head[now]; k != -1; k = points.get(k).getFrom())
			{
				if (  dis[points.get(k).getTo()]<dis[now] + points.get(k).getTotalPeople())         //松弛操作
				{
					 dis[points.get(k).getTo()]=dis[now]  + points.get(k).getTotalPeople();
					 pre[points.get(k).getTo()]=now;
					if (book[points.get(k).getTo()] == 0)
					{	
						q.push(points.get(k).getTo());
						book[points.get(k).getTo()] = 1;
					}
				}

			}
			
		}

	}
	public void print1(int x){
		if(x!=0)
			{print1(pre[x]);
			}
		LogUtils.f(x);
	}
	LinkedList lists = new LinkedList();
	public void print(int x){		
		if(x!=0)
			{print(pre[x]);
			}
		lists.add(x);
}
	public void output(){
		spfa(0);
		for(int i=0;i<7;i++){			
			LogUtils.f("点0到"+i+"的最长路径为"+dis[i]);
//			LogUtils.f(points[head[i]].getTo());
			print1(i);
		}
	
	}

	public Long[] getDis() {
		return dis;
	}

	public void setDis(Long[] dis) {
		this.dis = dis;
	}

	public LinkedList getLists() {
		return lists;
	}

	public void setLists(LinkedList lists) {
		this.lists = lists;
	}
	
}
