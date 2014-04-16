package com.socket.test;

import java.util.Scanner;

import com.socket.server.HttpServer;

public class SocketServerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input=new Scanner(System.in);
		System.out.print("请输入端口号：");
		HttpServer miniServer=new HttpServer(input.nextInt());
	}

}
