package ru.inbox.savinov_vu.common.util;

import javax.servlet.http.HttpServletRequest;



public class HttpRequestUtil {

  public static Integer getUserId(HttpServletRequest request) {
    String id = request.getHeader("id");
    return Integer.valueOf(id);
  }
}
