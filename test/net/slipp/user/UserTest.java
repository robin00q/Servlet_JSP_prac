package net.slipp.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;


public class UserTest {
	public static User TEST_USER = new User("userId", "password", "name", "robin00q@naver.com");
	private UserDAO userDao;
	
	@Before
	public void setup() throws Exception {
		userDao = new UserDAO();
		userDao.removeUser(TEST_USER.getUserId());
	}

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
		userDao.addUser(user);
		assertTrue(User.login(TEST_USER.getUserId(), TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExisteduser() throws Exception {
		User.login("userId2", TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void loginWhenPasswordMismatch() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.addUser(user);
		User.login(TEST_USER.getUserId(), "password2");
	}
}
