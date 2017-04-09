package com.galaxy.login.impls;


public class LoginService implements IService {

	public void execute () {
		int temp = 0;
		for (int i = 0; i < 100; i++) {
			temp += i;
		}
		System.out.println("In LoginService");
	}
}
