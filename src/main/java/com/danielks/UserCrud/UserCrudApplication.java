package com.danielks.UserCrud;

import com.danielks.UserCrud.service.UserService;
import com.danielks.UserCrud.view.MainScreen;

public class UserCrudApplication  {
	public static void main(String[] args) {
		UserService userService = new UserService();
		MainScreen mainScreen = new MainScreen(userService);

	}
}
