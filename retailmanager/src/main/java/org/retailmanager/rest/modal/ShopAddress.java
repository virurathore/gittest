package org.retailmanager.rest.modal;

//@Entity
public class ShopAddress {
	private int number;	//This can be bigInteger or intger only
	private String postCode;	// This require as postalCode can alphanumeric
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@Override
	public String toString(){
		return "number : "+number +"\t postCode :"+postCode;
	}
}
