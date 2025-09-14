import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Provides secure password authentication
 * Hashes provided password and compares to stored tokens
 * Implementation based on http://stackoverflow.com/a/2861125/3474
 */
public class AuthenticationManager {

  public static void main(String[] args){
    if (args != null && args.length == 1){
      System.out.println(hash(args[0]));
    }
  }

  /**
   * Store the users.txt file with pre hashed passwords
   */
  private static HashMap<String, String> encostEmployeeList = new HashMap<>() {
    {
      put("encostUserA", "$31$16$gSMYUDq6_ndUuHOAt9Z-W7ctl_b_Ed9j8iksz3i8v0Y");
      put("encostUserB", "$31$16$tpjwWQdLPw9FzpXch1eQbJtZtpnN_qth8mPX7a6Uwtc");
      put("encostUserC", "$31$16$k7OjhDCWHE3y8B6vXjg_F6cX9d3DINOQoIZut7g2O-g");
      put("encostUserD", "$31$16$awSJVLEaxrHZ1R_d_hGxhZzfqzmok9e9AkntIuMGQb0");
      put("encostUserE", "$31$16$KdayTq0AtvfB0CMLEDVVcgWY9F1oVin6Hpm-n4lAFYk");
      put("encostUserF", "$31$16$px_nY37SMVHfbupUjscK-VJ9CYQTsGNxMdNwMhW5RKk");
      put("encostUserG", "$31$16$o0cxW6ZMZEpNygLluaSQZuHQMGdkC-KGVCFJANmNlKw");
      put("encostUserH", "$31$16$LXHFW6yo9LFKy8GnmVxfMAcwUY5B90BqaWiOriK3h4g");
      put("encostUserI", "$31$16$kCk_7ilF0-bXrQRk4uXCqcXcnjJCTLXPYdHC4PfeKr0");
      put("encostUserJ", "$31$16$Ke89xJ9jybGEm0UB9A3V9Ugaqc9WNiXB-70K7Hwv-mQ");
    }
  };

  //configured settings used for hashing
  private static int cost = 16;
  private static String ID = "$31$";
  private static SecureRandom random = new SecureRandom();
  private static Pattern layout = Pattern.compile("\\$31\\$(\\d\\d?)\\$(.{43})");

  /**
   * Authenticate a username, password pair
   * 
   * @param username       that the password corresponds to
   * @param passwordString to compare to the stored token
   * @return if the password was correct for the given username
   */
  public static boolean authenticate(String username, String passwordString) {

    //settings used for the hashing
    random = new SecureRandom();
    ID = "$31$";
    cost = 16;
    String token;

    //Were we given a valid username?
    try {
      //if yes get the usernames corresponding pre hashed password token
      token = encostEmployeeList.get(username);
    } catch (Exception e) {
      //invalid user
      return false;
    }

    if (token == null) {
      //invalid user
      return false;
    }

    //hash the provided password string and compare to the stored token we retrieved
    char[] password = passwordString.toCharArray();

    Matcher m = layout.matcher(token);
    if (!m.matches())
      throw new IllegalArgumentException("Invalid token format");
    int iterations = iterations(Integer.parseInt(m.group(1)));
    byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
    byte[] salt = Arrays.copyOfRange(hash, 0, 128 / 8);
    byte[] check = pbkdf2(password, salt, iterations);
    int zero = 0;
    for (int idx = 0; idx < check.length; ++idx)
      zero |= hash[salt.length + idx] ^ check[idx];
    return zero == 0;
  }

  /**
   * Hash the provided string, used only at program dev to store the pre-provided passwords 
   * @param passwordString the password to hash
   * @return hashed passwordString
   */
  private static String hash(String passwordString) {

    //hashing settings
    random = new SecureRandom();
    ID = "$31$";
    cost = 16;

    //perform and return hash
    char[] password = passwordString.toCharArray();

    byte[] salt = new byte[128 / 8];
    random.nextBytes(salt);
    byte[] dk = pbkdf2(password, salt, 1 << cost);
    byte[] hash = new byte[salt.length + dk.length];
    System.arraycopy(salt, 0, hash, 0, salt.length);
    System.arraycopy(dk, 0, hash, salt.length, dk.length);
    Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
    return ID + cost + '$' + enc.encodeToString(hash);

  }

  // utility functions
  private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {
    KeySpec spec = new PBEKeySpec(password, salt, iterations, 128);
    try {
      SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      return f.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException ex) {
      throw new IllegalStateException("Missing algorithm: " + "PBKDF2WithHmacSHA1", ex);
    } catch (InvalidKeySpecException ex) {
      throw new IllegalStateException("Invalid SecretKeyFactory", ex);
    }
  }

  private static int iterations(int cost) {
    if ((cost < 0) || (cost > 30))
      throw new IllegalArgumentException("cost: " + cost);
    return 1 << cost;
  }
}