package net.slipp.user;

import static org.junit.Assert.*;

import org.junit.Test;

import net.slipp.db.Database;

public class UserTest {
	public static User TEST_USER = new User("userId", "password", "name", "robin00q@naver.com");

	@Test
	public void matchPassword() {
		assertTrue(TEST_USER.matchPassword("password"));
	}
	
	@Test
	public void notMatchPassword() {
		assertFalse(TEST_USER.matchPassword("password2"));
	}

	@Test
	public void login() throws Exception {
		User user = UserTest.TEST_USER;
		Database.addUser(user);
		
		assertTrue(User.login(TEST_USER.getUserId(), TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExisteduser() throws Exception {
		User.login("userId2", TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void loginWhenPasswordMismatch() throws Exception {
		User user = UserTest.TEST_USER;
		Database.addUser(user);
		User.login(TEST_USER.getUserId(), "password2");
	}
}
