package com.bank.utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Util {

    public static Map<String, String> validateForm(
            String name, String gender, String email, String dob, String phoneNumber,
            String occupation, String accountType, String address, String city, String state,
            String zip, String nationality, String aadhaar, String pan, String income,
            String maritalStatus, String nominee, String relationship) {

        Map<String, String> errors = new HashMap<>();

        if (!isValidName(name)) errors.put("name", "Invalid name");
        if (!isValidGender(gender)) errors.put("gender", "Invalid gender");
        if (!isValidEmail(email)) errors.put("email", "Invalid email format");
        if (!isValidDOB(dob)) errors.put("dob", "Invalid date of birth format");
        if (!isValidPhoneNumber(phoneNumber)) errors.put("phoneNumber", "Invalid phone number");
        if (!isValidOccupation(occupation)) errors.put("occupation", "Invalid occupation");
        if (!isValidAccountType(accountType)) errors.put("accountType", "Invalid account type");
        if (!isValidAddress(address)) errors.put("address", "Invalid address");
        if (!isValidCity(city)) errors.put("city", "Invalid city");
        if (!isValidState(state)) errors.put("state", "Invalid state");
        if (!isValidZip(zip)) errors.put("zip", "Invalid zip code");
        if (!isValidNationality(nationality)) errors.put("nationality", "Invalid nationality");
        if (!isValidAadhaar(aadhaar)) errors.put("aadhaar", "Invalid Aadhaar number");
        if (!isValidPan(pan)) errors.put("pan", "Invalid PAN number");
        if (!isValidIncome(income)) errors.put("income", "Invalid income");
        if (!isValidMaritalStatus(maritalStatus)) errors.put("maritalStatus", "Invalid marital status");
        if (!isValidNominee(nominee)) errors.put("nominee", "Invalid nominee name");
        if (!isValidRelationship(relationship)) errors.put("relationship", "Invalid relationship");

        return errors;  
    }
	public static boolean isValidName(String name) {
		return name != null ;
	}

	public static boolean isValidGender(String gender) {
		if (gender == null) {
			return false;
		}
		String lowerGender = gender.trim().toLowerCase();
		return lowerGender.equals("male") || lowerGender.equals("female") || lowerGender.equals("others");
	}

	public static boolean isValidEmail(String email) {
		if (email == null) {
			return false;
		}
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		return Pattern.matches(emailRegex, email);
	}

	public static boolean isValidDOB(String dob) {
		if (dob == null) {
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
		if (phoneNumber == null) {
			return false;
		}
		return phoneNumber.matches("\\d{10}");
	}

	public static boolean isValidOccupation(String occupation) {
		return occupation != null && !occupation.trim().isEmpty();
	}

	public static boolean isValidAccountType(String accountType) {
		if (accountType == null) {
			return false;
		}
		String lowerAccountType = accountType.trim().toLowerCase();
		return lowerAccountType.equals("savings") || lowerAccountType.equals("current")
				|| lowerAccountType.equals("fixed");
	}

	public static boolean isValidAddress(String address) {
		return address != null && !address.trim().isEmpty();
	}

	public static boolean isValidCity(String city) {
		return city != null && !city.trim().isEmpty();
	}

	public static boolean isValidState(String state) {
		return state != null && !state.trim().isEmpty();
	}

	public static boolean isValidZip(String zip) {
		if (zip == null) {
			return false;
		}
		return zip.matches("\\d{5,6}");
	}

	public static boolean isValidNationality(String nationality) {
		return nationality != null && !nationality.trim().isEmpty();
	}

	public static boolean isValidAadhaar(String aadhaar) {
		if (aadhaar == null) {
			return false;
		}
		return aadhaar.matches("\\d{12}");
	}

	public static boolean isValidPan(String pan) {
		if (pan == null) {
			return false;
		}
		return pan.matches("[A-Z]{5}[0-9]{4}[A-Z]");
	}

	public static boolean isValidIncome(String income) {
		if (income == null) {
			return false;
		}
		return true;
	}

	public static boolean isValidMaritalStatus(String maritalStatus) {
		if (maritalStatus == null) {
			return false;
		}
		String lowerStatus = maritalStatus.trim().toLowerCase();
		return lowerStatus.equals("single") || lowerStatus.equals("married") || lowerStatus.equals("divorced")
				|| lowerStatus.equals("widowed");
	}

	public static boolean isValidNominee(String nominee) {
		return nominee != null || nominee.trim().isEmpty() ;
	}

	public static boolean isValidRelationship(String relationship) {
		return relationship != null || relationship.trim().isEmpty() ;
	}
}
