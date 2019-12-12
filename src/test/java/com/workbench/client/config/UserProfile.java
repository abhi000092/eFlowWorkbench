package com.workbench.client.config;

import com.github.javafaker.Faker;
import com.workbench.client.core.CommonUtils;
import com.workbench.client.core.Config.UserDetails;

public class UserProfile extends CommonUtils {
	static Faker random;
	private static String fullName;
	private static String firstName = null;
	private static String lastName = null;
	private static String address;
	private static String city;
	private static String postalCode;
	private static String[] nameHolder;
	private static String value = null;
	private static String subjectLine = null;
	private static String contactName=null;
	private static String customerName=null;
	private static String opportunityName=null;
	private static String Note = null;
	private static String Publication = null;
	private static String Complaint = null;
	private static String Campaign = null;
	private static String CampaignProject = null;
	private static String Template =null;
	


	public UserProfile() {
		random = new Faker();
		fullName = null;
	}

	
	
	public String getDetails(UserDetails aProfile) {
		switch (aProfile) {
		case CUSTOMER_NAME:
			setCustomerName(random.name().fullName());
			value = getCustomerName();
			break;
		case FIRST_NAME:
			if (fullName != null) {
				nameHolder = split(fullName, " ");
				value = nameHolder[0];
				setFirstName(value);
			} else {
				setFirstName(random.name().firstName());
				value = getFirstName();
			}

			break;
		case LAST_NAME:
			if (fullName != null) {
				nameHolder = split(fullName, " ");
				value = nameHolder[1];
				setFirstName(value);
			} else {
				setLastName(random.name().lastName());
				value = getLastName();
			}

			break;
		case ADDRESS:
			setAddress(random.address().streetAddress());
			value = getAddress();
			break;
		case CITY:
			setCity(random.address().cityName());
			value = getCity();
			break;
		case POSTCODE:
			setPostalCode(random.address().zipCode());
			value = getPostalCode();
			break;
		case ACTIVITY_NAME:
			setActivityName(random.company().bs());
			value = getActivityName();
			break;
		case CONTACT_NAME:
			setContactName(random.name().fullName());
			value = getContactName();
			break;
		case OPPORTUNITY_NAME:
			setOpportunityName((random.company().name()));
			value = getOpportunityName();
			break;
		case NOTE:
			setNote((random.commerce().productName()));
			value = getNote();
			break;
		case PUBLICATION:
			setPublication((random.team().name()));
			value = getPublication();
			break;
		case COMPLAINT:
			setComplaint((random.beer().name()));
			value = getComplaint();
			break;
		case CAMPAIGN:
			setCampaign(random.app().name());
			value = getCampaign();
			break;
		case CAMPAIGNPROJECT:
			setCampaignProject(random.book().title());
			value = getCampaignProject();
			break;
		case TEMPLATE:
			setTemplate(random.university().name());
			value = getTemplate();
			break;

		default:
			break;
		}
		return value;

	}

	public  String getCustomerName() {
		return customerName;
	}
	
	public  void setCustomerName(String customerName) {
		UserProfile.customerName = customerName;
	}

	
	
	public  String getFirstName() {
		return firstName;
	}

	public  void setFirstName(String userFirstName) {
		UserProfile.firstName = userFirstName;
	}

	
	
	public  String getLastName() {
		return lastName;
	}

	public  void setLastName(String userLastName) {
		UserProfile.lastName = userLastName;
	}

	
	
	public  String getAddress() {
		return address;
	}

	public  void setAddress(String address) {
		UserProfile.address = address;
	}

	
	
	public  String getPostalCode() {
		return postalCode;
	}

	public  void setPostalCode(String postalCode) {
		UserProfile.postalCode = postalCode;
	}

	
	
	public  String getCity() {
		return city;
	}

	public  void setCity(String city) {
		UserProfile.city = city;
	}
	
	
	
	public String getActivityName() {
		return subjectLine;
	}

	public  void setActivityName(String subjectLine) {
		UserProfile.subjectLine = subjectLine;
	}
	
	
	
	public String getContactName() {
		return contactName;
	}

	public  void setContactName(String contactName) {
		UserProfile.contactName = contactName;
	}



	public  String getOpportunityName() {
		return opportunityName;
	}

	public  void setOpportunityName(String opportunityName) {
		UserProfile.opportunityName = opportunityName;
	}



	public String getNote() {
		return Note;
	}

	public  void setNote(String note) {
		Note = note;
	}



	public  String getPublication() {
		return Publication;
	}

	public  void setPublication(String publication) {
		Publication = publication;
	}



	public  String getComplaint() {
		return Complaint;
	}

	public  void setComplaint(String complaint) {
		Complaint = complaint;
	}



	public String getCampaign() {
		return Campaign;
	}

	public  void setCampaign(String campaign) {
		Campaign = campaign;
	}



	public  String getCampaignProject() {
		return CampaignProject;
	}

	public  void setCampaignProject(String campaignProject) {
		CampaignProject = campaignProject;
	}



	public  String getTemplate() {
		return Template;
	}

	public  void setTemplate(String template) {
		Template = template;
	}
	
	

}
