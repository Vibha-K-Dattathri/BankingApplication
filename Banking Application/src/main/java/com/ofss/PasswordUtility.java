package com.ofss;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtility {

	public static void main(String[] args) {
		BCryptPasswordEncoder bcrpt=new BCryptPasswordEncoder();
		String p1="rahul123";
		String p2="priya123";
		String p3="amit123";
		String p4="sneha123";
		String p5="vidhya123";
		String p6="vikram123";
		String p7="dharshini123";
		
		
		System.out.println(bcrpt.encode(p1));
		System.out.println(bcrpt.encode(p2)); 
		System.out.println(bcrpt.encode(p3));
		System.out.println(bcrpt.encode(p4));
		System.out.println(bcrpt.encode(p5));
		System.out.println(bcrpt.encode(p6));
		System.out.println(bcrpt.encode(p7));
		
	}

}
