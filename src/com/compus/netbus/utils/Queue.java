package com.compus.netbus.utils;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	private int[] data = null;
	private int maxSize; // 队列容量
	private int front; // 队列头，允许删除
	private int rear; // 队列尾，允许插入

	// 构造函数
	public Queue() {
		this(10000);
	}

	// 对队列初始化
	public Queue(int initialSize) {
		if (initialSize >= 0) {
			this.maxSize = initialSize;
			data = new int[initialSize];
			front = rear = 0;
		} else {
			throw new RuntimeException("初始化大小不能小于0：" + initialSize);
		}
	}

	// 判空
	public boolean empty() {
		return rear == front ? true : false;
	}

	// 插入
	public boolean push(int e) {
		if (rear == maxSize) {
			throw new RuntimeException("队列已满，无法插入新的元素！");
		} else {
			data[rear++] = e;
			return true;
		}
	}

	// 返回队首元素，但不删除
	public int peek() {
		if (empty()) {
			throw new RuntimeException("空队列异常！");
		} else {
			return data[front];
		}
	}

	// 遍历所有元素
	public List<Integer> bianli() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = front; i < rear; i++) {
			list.add(data[i]);
		}
		return list;
	}

	// 后进先出的出队
	public int pop() {
		if (empty()) {
			throw new RuntimeException("空队列异常！");
		} else {
			int value = data[front]; // 保留队列的front端的元素的值
			data[front++] = 0; // 释放队列的front端的元素
			return value;
		}
	}

	// 先进先出的出队
	public int pop1() {
		if (empty()) {
			throw new RuntimeException("空队列异常！");
		} else {
			int value = data[rear]; // 保留队列的front端的元素的值
			data[rear--] = 0; // 释放队列的front端的元素
			return value;
		}
	}

	// 队列长度
	public int length() {
		return rear - front;
	}
}
