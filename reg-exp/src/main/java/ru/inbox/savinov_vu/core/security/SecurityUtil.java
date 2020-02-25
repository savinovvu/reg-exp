package ru.inbox.savinov_vu.core.security;

import com.google.common.hash.Hashing;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.charset.Charset;



@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {

  public static String encryptSHA(String source) {
    return Hashing.sha256().hashString(source, Charset.forName("UTF-8")).toString();
  }

}
