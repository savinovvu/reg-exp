package ru.inbox.savinov_vu.common.util;

import java.util.UUID;



public class MailNotifier {

  public static UUID getUniqueToken(Long currentDate, Long userId) {
    return new UUID(currentDate, userId);
  }
}
