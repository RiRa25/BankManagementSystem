package com.cjc.bms.serviceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import com.cjc.bms.model.Account;
import com.cjc.bms.service.RBI;

public class SBI implements RBI{
	
	Scanner sc = new Scanner(System.in);
	
	int num=0;
	Account acc = new Account();
	
	
	public void createAccount() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "@25Nov2004");
		
		String sql = "insert into bank value(?,?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		System.out.println("Enter Account Number : ");
		acc.setAccNo(sc.nextInt());
		ps.setInt(1, acc.getAccNo());
		
		System.out.println("Enter your name : ");
		acc.setName(sc.next());
		ps.setString(2, acc.getName());
		
		System.out.println("Enter your Mobile Number : ");
		acc.setMobNo(sc.next());
		ps.setString(3, acc.getMobNo());
			
		System.out.println("Enter your Aadhar Number : ");
		acc.setAdharNo(sc.next());
		ps.setString(4, acc.getAdharNo());
		
		System.out.println("Enter your Gender : ");
		acc.setGender(sc.next());
		ps.setString(5, acc.getGender());
		
		System.out.println("Enter your age : ");
		acc.setAge(sc.nextInt());
		ps.setInt(6, acc.getAge());
		
		System.out.println("Enter amount to be deposited : ");
		acc.setBalance(sc.nextDouble());
		ps.setDouble(7, acc.getBalance());
		
		ps.execute();
		ps.close();
		con.close();
		
	}
	public void DisplayAllDetails() throws Exception
	{		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "Select * from bank";
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "@25Nov2004");
		Statement smt = con.createStatement();
		
		ResultSet rs = smt.executeQuery(sql);
		
		while(rs.next())
		{
			System.out.println("Account Number : " + rs.getInt(1));
			System.out.println("Name : " + rs.getString(2));
			System.out.println("Mobile Number : " + rs.getString(3));
			System.out.println("Aadhar Number : " + rs.getString(4));
			System.out.println("Gender : " + rs.getString(5));
			System.out.println("Age : " + rs.getInt(6));
			System.out.println("Balance : " + rs.getDouble(7));
			System.out.println();
		}
	}
	public void DepositMoney() throws Exception
	{
		System.out.println("Enter account number in which you want to deposit money : ");
		int n = sc.nextInt();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "Select Balance from bank where AccountNo = " + n;
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "@25Nov2004");
		Statement smt = con.createStatement();
		PreparedStatement ps = con.prepareStatement("update bank set Balance=? where AccountNo=?");
		ResultSet rs = smt.executeQuery(sql);
		
		if(rs.next()) {
			
			System.out.println("Enter the amount you want to deposit: ");
			int amt = sc.nextInt();
			ps.setDouble(1, rs.getDouble(1)+amt);
			ps.setInt(2, n);
			ps.execute();
			System.out.println(amt + " deposited to account number " + n);
			//smt.execute(sql);
		}
		
		
		
	}
	public void withdrawal() throws Exception
	{
		System.out.println("Enter account number from which you want to withdraw money : ");
		int n = sc.nextInt();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "Select Balance from bank where AccountNo = " + n;
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "@25Nov2004");
		Statement smt = con.createStatement();
		PreparedStatement ps = con.prepareStatement("update bank set Balance=? where AccountNo=?");
		ResultSet rs = smt.executeQuery(sql);
		
		if(rs.next()) {
			
			System.out.println("Enter the amount you want to withdraw: ");
			int amt = sc.nextInt();
			if(amt > rs.getDouble(1))
			{
				System.out.println("Insufficient Balance");
			}
			else
			{
				ps.setDouble(1, rs.getDouble(1)-amt);
				ps.setInt(2, n);
				ps.execute();
				System.out.println(amt + " withdrawed from account number " + n);
			}
			
		}
		
		
	
	}
	public void BalanceCheck() throws Exception
	{
		System.out.println("Enter account number to check balance : ");
		int n = sc.nextInt();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "Select Balance from bank where AccountNo = " + n;
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "@25Nov2004");
		Statement smt = con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		if(rs.next()) {
			System.out.println("The balance in account number " + n + " is: " + rs.getDouble(1));
		}
		
	}
	
	public void TransferMoney() throws Exception
	{
		System.out.print("Transfer Money from Account number : ");
		int n = sc.nextInt();
		System.out.print("Transfer Money to Account number : ");
		int n1 = sc.nextInt();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "Select Balance from bank where AccountNo = " + n;
		String sql1 = "Select Balance from bank where AccountNo = " + n1;
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "@25Nov2004");
		
		Statement smt = con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		
		Statement smt1 = con.createStatement();
		ResultSet rs1 = smt1.executeQuery(sql1);
		
		if(rs.next() && rs1.next()) {
			
			System.out.println("Enter the amount you want to transfer: ");
			int amt = sc.nextInt();
			if(amt > rs.getDouble(1))
			{
				System.out.println("Insufficient Balance");
			}
			else
			{
				PreparedStatement ps = con.prepareStatement("update bank set Balance=? where AccountNo=?");
				ps.setDouble(1, rs.getDouble(1)-amt);
				ps.setInt(2, n);
				ps.execute();
				
				PreparedStatement ps1 = con.prepareStatement("update bank set Balance=? where AccountNo=?");
				ps1.setDouble(1, rs1.getDouble(1)+amt);
				ps1.setInt(2, n1);
				ps1.execute();
				
				System.out.println(amt + " transfered from account number " + n + " to account number " + n1);
			}
		}
	}
}
