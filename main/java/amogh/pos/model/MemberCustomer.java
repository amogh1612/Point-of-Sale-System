package amogh.pos.model;

import java.time.LocalDate;

public class MemberCustomer extends Customer{

	private int memberID;
	private LocalDate memberSince;
	
	public MemberCustomer(int id, String name, String email, String address, int orderID, int memberID, LocalDate memberSince) {
		super(id, name, email, address, orderID);
		this.memberID = memberID;
		this.memberSince = memberSince;
	}

	//Getters
	public int getMemberID() {
		return memberID;
	}
	
	public LocalDate getMemberSince() {
		return memberSince;
	}
	
	//Setters
	public void setMemberID(int id) {
		memberID = id;
	}
	
	public void getMemberSince(LocalDate str) {
		memberSince = str;
	}

	@Override
	public String[] getTableRowData() {
		return new String[] {getName(), getEmail(), getAddress(), String.valueOf(getOrderID()), 
				String.valueOf(memberID), memberSince.toString()};
	}
	
	
}
