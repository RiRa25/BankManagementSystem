package com.cjc.bms.service;

public interface RBI {
	public void createAccount() throws Exception;
	public void DisplayAllDetails() throws Exception;
	public void DepositMoney() throws Exception;
	public void withdrawal() throws Exception;
	public void BalanceCheck() throws Exception;
	public void TransferMoney() throws Exception;
}
