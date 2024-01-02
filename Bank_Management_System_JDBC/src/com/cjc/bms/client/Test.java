package com.cjc.bms.client;

import java.util.*;
import com.cjc.bms.service.RBI;
import com.cjc.bms.serviceImpl.SBI;

public class Test {

	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		RBI bank = new SBI();
		
		int a=0;
		
		while(a==0)
		{
			System.out.println("\n-----MENU-----");
			System.out.println("1. Create Account");
			System.out.println("2. Display All Accounts");
			System.out.println("3. Deposit Money");
			System.out.println("4. Withdraw Money");
			System.out.println("5. Check Balance");
			System.out.println("6. Transfer Money");
			System.out.println("0. Exit");
			System.out.println();
			System.out.println("Enter choice : ");
			int choice = sc.nextInt();
			
			
			switch(choice)
			{
				case 1:
					System.out.println("CREATE ACCOUNT");
					System.out.println();
					bank.createAccount();
					System.out.println();
					break;
					
				case 2:
					System.out.println("ALL ACCOUNT DETAILS ");
					System.out.println();
					bank.DisplayAllDetails();
					System.out.println();
					break;
					
				case 3:
					System.out.println("DEPOSIT MONEY");
					System.out.println();
					bank.DepositMoney();
					System.out.println();
					break;
					
				case 4:
					System.out.println("WITHDRAW MONEY");
					System.out.println();
					bank.withdrawal();
					System.out.println();
					break;
					
				case 5:
					System.out.println("CHECK BALANCE");
					System.out.println();
					bank.BalanceCheck();
					System.out.println();
					break;
					
				case 6:
					System.out.println("TRANSFER MONEY");
					System.out.println();
					bank.TransferMoney();
					System.out.println();
					break;
					
				case 0:
					System.out.println("THANK YOU!");
					a = 1;
					break;
					
				default:
					System.out.println("Re-enter correct choice");
					break;
		}
		}
	}

}
