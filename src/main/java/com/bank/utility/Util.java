package com.bank.utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.bank.registration.User;

public class Util {

	public static Map<String,String> validateRegisterForm(User user,Map<String,String> errors)
	{
		if (!Util.isValidName(user.getName()))
			errors.put("name", "Invalid name");
		if (!Util.isValidGender(user.getGender()))
			errors.put("gender", "Invalid gender");
		if (!Util.isValidEmail(user.getEmail()))
			errors.put("email", "Invalid email format");
		if (!Util.isValidDOB(user.getDob()))
			errors.put("dob", "Invalid date of birth format");
		if (!Util.isValidPhoneNumber(user.getPhoneNumber()))
			errors.put("phoneNumber", "Invalid phone number");
		if (!Util.isValidOccupation(user.getOccupation()))
			errors.put("occupation", "Invalid occupation");
		if (!Util.isValidAccountType(user.getAccountType()))
			errors.put("accountType", "Invalid account type");
		if (!Util.isValidDebitCard(user.getDebitCard()))
			errors.put("debitCard", "Invalid debitCard");
		if (!Util.isValidAddress(user.getAddress()))
			errors.put("address", "Invalid address");
		if (!Util.isValidCity(user.getCity()))
			errors.put("city", "Invalid city");
		if (!Util.isValidState(user.getState()))
			errors.put("state", "Invalid state");
		if (!Util.isValidZip(user.getZip()))
			errors.put("zip", "Invalid zip code");
		if (!Util.isValidNationality(user.getNationality()))
			errors.put("nationality", "Invalid nationality");
		if (!Util.isValidAadhaar(user.getAadhaar()))
			errors.put("aadhaar", "Invalid Aadhaar number");
		if (!Util.isValidPan(user.getPan()))
			errors.put("pan", "Invalid PAN number");
		if (!Util.isValidIncome(user.getIncome()))
			errors.put("income", "Invalid income");
		if (!Util.isValidMaritalStatus(user.getMaritalStatus()))
			errors.put("maritalStatus", "Invalid marital status");
		if (!Util.isValidNominee(user.getNominee()))
			errors.put("nominee", "Invalid nominee name");
		if (!Util.isValidRelationship(user.getRelationship()))
			errors.put("relationship", "Invalid relationship");
		
		return errors;
	}
	
	
	public static boolean isValidName(String name) {
		if (name == null || name.trim().isEmpty()) {
			return false;
		}

		String nameRegex = "^[A-Za-z][A-Za-z .']*$";
		return Pattern.matches(nameRegex, name);
	}

	public static boolean isValidGender(String gender) {
		if (gender == null || gender.trim().isEmpty()) {
			return false;
		}
		String lowerGender = gender.trim().toLowerCase();
		return lowerGender.equals("male") || lowerGender.equals("female") || lowerGender.equals("others");
	}

	public static boolean isValidEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			return false;
		}
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		return Pattern.matches(emailRegex, email);
	}

	public static boolean isValidDOB(String dob) {
		if (dob == null || dob.trim().isEmpty()) {
			return false;
		}
		try {
			LocalDate.parse(dob);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
			return false;
		}
		return phoneNumber.matches("\\d{10}");
	}

	public static boolean isValidOccupation(String occupation) {
		if (occupation == null || occupation.trim().isEmpty()) {
			return false;
		}

		return occupation.matches("^[A-Za-z\\s-]+$");
	}

	public static boolean isValidAccountType(String accountType) {
		if (accountType == null || accountType.trim().isEmpty()) {
			return false;
		}
		String lowerAccountType = accountType.trim().toLowerCase();
		return lowerAccountType.equals("savings") || lowerAccountType.equals("current")
				|| lowerAccountType.equals("fixed");
	}

	public static boolean isValidDebitCard(String debitCard) {
		if (debitCard == null || debitCard.trim().isEmpty()) {
			return false;
		}

		String lowerAccountType = debitCard.trim().toLowerCase();
		return lowerAccountType.equals("visa") || lowerAccountType.equals("master") || lowerAccountType.equals("rupay");

	}

	public static boolean isValidAddress(String address) {
		if (address == null || address.trim().isEmpty()) {
			return false;
		}
		return address.matches("^[A-Za-z0-9\\s,./():-]+$");
	}

	public static boolean isValidCity(String city) {
		if (city == null || city.trim().isEmpty()) {
			return false;
		}
		return city.matches("^[A-Za-z\\s-]+$");
	}

	public static boolean isValidState(String state) {
		if (state == null || state.trim().isEmpty()) {
			return false;
		}
		return state.matches("^[A-Za-z\\s-]+$");
	}

	public static boolean isValidZip(String zip) {
		if (zip == null || zip.trim().isEmpty()) {
			return false;
		}
		return zip.matches("\\d{5,6}");
	}

	public static boolean isValidNationality(String nationality) {
		if (nationality == null || nationality.trim().isEmpty()) {
			return false;
		}
		return nationality.matches("^[A-Za-z\\s-]+$");

	}

	public static boolean isValidAadhaar(String aadhaar) {
		if (aadhaar == null || aadhaar.trim().isEmpty()) {
			return false;
		}
		return aadhaar.matches("\\d{12}");
	}

	public static boolean isValidPan(String pan) {
		if (pan == null || pan.trim().isEmpty()) {
			return false;
		}
		return pan.matches("[A-Z]{5}[0-9]{4}[A-Z]");
	}

	public static boolean isValidIncome(String income) {
		if (income == null || income.trim().isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean isValidMaritalStatus(String maritalStatus) {
		if (maritalStatus == null || maritalStatus.trim().isEmpty()) {
			return false;
		}
		String lowerStatus = maritalStatus.trim().toLowerCase();
		return lowerStatus.equals("single") || lowerStatus.equals("married") || lowerStatus.equals("divorced")
				|| lowerStatus.equals("widowed");
	}

	public static boolean isValidNominee(String nominee) {
		if (nominee == null || nominee.trim().isEmpty()) {
			return false;
		}
		String nomineeRegex = "^[A-Za-z][A-Za-z .']*$";
		return Pattern.matches(nomineeRegex, nominee);
	}

	public static boolean isValidRelationship(String relationship) {
		if (relationship == null || relationship.trim().isEmpty()) {
			return false;
		}
		return relationship.matches("^[A-Za-z\\s-]+$");
	}

}
