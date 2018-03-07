package com.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

public class Client {
	@Test
	public void test() {
		HttpClient httpClient = new HttpClient();
		new Thread(new Sender(httpClient)).start();
	}
	
	public static void main(String[] args) {
		HttpClient httpClient = new HttpClient();
		Thread t1 = new Thread(new Sender(httpClient));
		Thread t2 = new Thread(new Sender(httpClient));
		Thread t3 = new Thread(new Sender(httpClient));
		Thread t4 = new Thread(new Sender(httpClient));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
//		Receiver r = new Receiver();
//		new Thread(r).start();
//		new Thread(r).start();
//		new Thread(r).start();
//		new Thread(r).start();
//		new Thread(r).start();
	}
}

class Sender implements Runnable {
	public static AtomicInteger count = new AtomicInteger(0);
	HttpClient httpClient;

	public Sender(HttpClient client) {
		httpClient = client;
	}

	public void run() {
		PostMethod post = null;
		try {
			System.out.println(Thread.currentThread().getName() + "---Send message-" + count.getAndIncrement());
			post = new PostMethod("http://127.0.0.1:8080/SpringActiveMQ/SendMessage");
			post.addParameter("msg", "Hello world!");
//			int status = httpClient.executeMethod(post);
			System.out.println(Thread.currentThread().getName() + "---Send message Success-" + count.getAndIncrement());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Receiver implements Runnable{
	
	private int ticket = 60;
	
	public void run() {
		while(true) {
			if(ticket <= 0) {
				break;
			}
			try {
				Thread.sleep(100);
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "=======" + ticket--);
		}
	}
}
