/*package com.workbench.client.core;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import com.workbench.client.config.UserProfile;
import com.workbench.client.core.Config.UserDetails;
import com.workbench.client.pages.ContactPage;

public class HandleAlert 
{
	private ContactPage contact;
	private UserProfile user;
	public HandleAlert() {
		contact = new ContactPage();
		user = new UserProfile();
	}
	
	public  void readAlert(WebElement element) {
		boolean bValue = true;
		String alertText = null;

		do {
			try {
				CommonUtils.click(element);
				alertText= DriverManager.getDriver().switchTo().alert().getText();
				DriverManager.getDriver().switchTo().alert().accept();
				doHandleAlert(getAlertMessage(alertText));
			} catch (NoAlertPresentException e) {
				bValue = false;
			}
		} while (bValue);

	}
	
	
	
	
	private  void doHandleAlert(Set<String>aSet) {
		if(aSet.contains(AlertMessage.FORENAME.getMessage())) {
			contact.firstNameField.clear();
			CommonUtils.sendKeys(contact.firstNameField, user.getDetails(UserDetails.CONTACT_NAME));
			
		}
		else if (aSet.contains(AlertMessage.SURNAME.getMessage())) {
			contact.lastNameField.clear();
			CommonUtils.sendKeys(contact.lastNameField, user.getDetails(UserDetails.LAST_NAME));
			
		}else if (aSet.contains(AlertMessage.SELECT_CUSTOMER.getMessage())) {
			//contact.selectCustomer();
			
		}else if (aSet.contains(AlertMessage.SELECT_CONTACT.getMessage())) {
			
			
		}else if (aSet.contains(AlertMessage.SUBJECT.getMessage())) {
			
			
		}else if (aSet.contains(AlertMessage.CUSTOMER_NAME.getMessage())) {
			
		}else if (aSet.contains(AlertMessage.SALESPERSON.getMessage())) {
			
		}else if (aSet.contains(AlertMessage.ADDRESS.getMessage())) {
			
			
		}else if (aSet.contains(AlertMessage.FIRSTLINE_ADDRESS.getMessage())) {
			contact.firstLineAddressField.clear();
			CommonUtils.sendKeys(contact.lastNameField, user.getDetails(UserDetails.ADDRESS));
			
		}else if (aSet.contains(AlertMessage.TOWN.getMessage())) {
			contact.townField.clear();
			CommonUtils.sendKeys(contact.townField, user.getDetails(UserDetails.CITY));
			
		}else if (aSet.contains(AlertMessage.POSTCODE.getMessage())) {
			contact.postCodeField.clear();
			CommonUtils.sendKeys(contact.postCodeField, user.getDetails(UserDetails.POSTCODE));
			
		}
	}




	private static Set<String> getAlertMessage(String alertText) {
		String[] alertTextList = alertText.split("-");
		Set<String> alertMessageHolder = new HashSet<String>();

		for (String aMeassage : alertTextList) {
			alertMessageHolder.add(aMeassage);

		}
		return alertMessageHolder;

	}
	
	public enum AlertMessage {
		FORENAME("forename"),
		SURNAME("surname"),
		SELECT_CUSTOMER("Customer"),
		SELECT_CONTACT("contact"),
		SUBJECT("subject"),
		CUSTOMER_NAME("Enter a Customer name"),
		SALESPERSON("salesperson"),
		ADDRESS("address"),
		FIRSTLINE_ADDRESS(" Enter the first line of the address"),
		TOWN("town"),
		POSTCODE("postcode"),
		COUNTRY("country");
		
		
		
		AlertMessage(String aMessage){
			this.aMessage=aMessage;
		}
		
		
		public String getMessage() {
			return this.aMessage;
		}
		
		
		private String aMessage;
		
	}
	

}
*/