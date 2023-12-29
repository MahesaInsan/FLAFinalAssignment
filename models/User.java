package models;

import java.util.regex.Pattern;

public abstract class User {
  protected String username;
  protected String password;
  protected Boolean isAdmin;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public static String validate(String username, String password) {
    // username must be a alphanumeric string with length between 3 and 50
    String usernameRegex = "^[A-Za-z][A-Za-z0-9]{2,49}$";
    Pattern pattern = Pattern.compile(usernameRegex);

    if (pattern.matcher(username).matches() == false) {
      return "Username must be alphanumeric with length between 3 and 50 at least 1 letter";
    }

    if (username == null || username.length() == 0) {
      return "Username is required";
    }

    if (username.isEmpty() || username.isBlank()) {
      return "Username is required";
    }

    if (username.length() > 50) {
      return "Username must be less than 50 characters";
    }

    if (username.length() < 3) {
      return "Username must be at least 3 characters";
    }

    if (password == null || password.length() == 0) {
      return "Password is required";
    }

    if (password.isEmpty() || password.isBlank()) {
      return "Password is required";
    }

    if (password.length() > 50) {
      return "Password must be less than 50 characters";
    }

    if (password.length() < 3) {
      return "Password must be at least 3 characters";
    }

    return null;
  }
}
