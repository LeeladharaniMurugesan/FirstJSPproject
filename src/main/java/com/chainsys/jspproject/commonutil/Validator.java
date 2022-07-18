package com.chainsys.jspproject.commonutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public static boolean checkStringForParse(String data) throws InvalidInputDataException {

		boolean result = false;
		int len = data.length();
		for (int i = 0; i < len; i++) {
			int a = (int) data.charAt(i);
			if ((a > 47) && (a < 58))
				result = true;
			else
				throw new InvalidInputDataException("Enter Number Value Only");
		}

		// if data is invalid throw new InvalidInputDataException ("The value in String
		// must contain only numbers")
		return result;
	}

	public static boolean CheckNumberForGreaterThanZero(int data) throws InvalidInputDataException {

		boolean result = false;
		if (data > 0) {
			result = true;
		} else
			throw new InvalidInputDataException("Enter Number greater than Zero");
		return result;
	}

	public static boolean checklengthOfString(String data) throws InvalidInputDataException {
		boolean result = false;

		int len = data.length();

		if (len < 4)
			throw new InvalidInputDataException("Enter atleast four characters ");
		else if (len > 15)
			throw new InvalidInputDataException("Enter length  less than 15 characters");
		else
			result = true;
		return result;
	}

	public static boolean checkStringOnly(String data) throws InvalidInputDataException {
		boolean result = false;
		int len = data.length();
		for (int i = 0; i < len; i++) {
			String Data = data.toUpperCase();
			int asc = (int) Data.charAt(i);

			if (asc > 64 && asc < 91 ) {
				result = true;
			} else
				throw new InvalidInputDataException("Enter Alphabets Only in  given Input");
		}

		return result;
	}

	public static boolean checkingMail(String email) throws InvalidInputDataException {
		boolean result = false;
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		if (email == null)
			throw new InvalidInputDataException("Enter  valid email id ");
		else if (Pattern.compile(regexPattern).matcher(email).matches()) {
			result = true;
		} else {
			throw new InvalidInputDataException("Enter valid email id ");
		}
		return result;
	}

	public static boolean checkDateFormat(String data) throws InvalidInputDataException {
        boolean result = false;
        int len = data.length();
        String[] data1 = data.split("/");
        int date = Integer.parseInt(data1[0]);
        int mon =Integer.parseInt(data1[1]);
        int yrs =Integer.parseInt(data1[2]);
        for (int index = 0; index < len; index++) {
            int asc = (int) data.charAt(index);
            if ((asc < 46)|| (asc > 58) ) 
                throw new InvalidInputDataException("Enter date in correct format ");
        }
        
        if(yrs>1984 && yrs<2022){
                if (mon == 1||mon==3||mon==5||mon==7||mon==8||mon==10||mon==12)
                    if(date>0 && date<=31)
                        result = true;
                    else
                        throw new InvalidInputDataException("enter valid date");
                else if(mon == 2||mon==4||mon==6||mon==9||mon==11)
                    if(date>0 && date<=30)
                        result = true;
                    else 
                        throw new InvalidInputDataException("enter valid date");
                else 
                    throw new InvalidInputDataException("enter valid month");
            }
             else
                 throw new InvalidInputDataException("enter valid year");
                
        return result;

    
}

	public static boolean checkjobid(String data) throws InvalidInputDataException {

		boolean result = false;
		int len = data.length();
		for (int i = 0; i < len; i++) {
			String s1 =data.toUpperCase();
			int asc = (int) s1.charAt(i);
			if ((asc > 64) && (asc < 91 ) || (asc == 95)) {
				result = true;
			} else
				throw new InvalidInputDataException("Enter Alphabets(A-Z) and underscore(_) only");
		}
		return result;

	}

	public static boolean checkSalary(float data) throws InvalidInputDataException {
		boolean result = false;
		if (data < 1000)
			throw new InvalidInputDataException("Enter Amount greater than 1,000 ");
		else if (data > 1000000)
			throw new InvalidInputDataException("Enter Amount less than 10,00,000");
		else if (data % 500 != 0)
			throw new InvalidInputDataException("Enter amount in multiples of five hundred ");
		else
			result = true;
		return result;
	}
	public static void checkPhoneNumer(String data) throws InvalidInputDataException {
        boolean result = false;
        String pattern = "^[0-9]{10}$";
        Pattern patt = Pattern.compile(pattern);
        Matcher match = patt.matcher(data);
        result = match.matches();
        if (!result)
            throw new InvalidInputDataException("please enter 10 digit ");
    }

	public static boolean Checkfees(float data) throws InvalidInputDataException {
        boolean result = false;
        if (data > 0) {
            result = true;
        } else
            throw new InvalidInputDataException();
        return result;
    }

	public static void checkStringForParseFloat(String phno) {
		// TODO Auto-generated method stub
		
	}

	

	
		
}
