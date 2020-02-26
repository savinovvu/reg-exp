package ru.inbox.savinov_vu.core.security.jwt.config;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {


  private UserDetailsService userDetailsService;

  private JwtHelper jwtHelper;

  private String tokenHeader;


  public JwtAuthorizationTokenFilter(UserDetailsService userDetailsService, JwtHelper jwtHelper, String tokenHeader) {
    this.userDetailsService = userDetailsService;
    this.jwtHelper = jwtHelper;
    this.tokenHeader = tokenHeader;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    LOG.debug("processing authentication for '{}'", request.getRequestURL());

    final String requestHeader = request.getHeader(tokenHeader);

    String username = null;
    String authToken = null;
    if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
      authToken = requestHeader.substring(7);
      try {
        username = jwtHelper.getUserName(authToken);
      } catch (IllegalArgumentException e) {
        LOG.error("an error occured during getting username from token", e);
      } catch (ExpiredJwtException e) {
        LOG.warn("the token is expired and not valid anymore", e);
      }
    } else {
      LOG.warn("couldn't find bearer string, will ignore the header");
    }

    LOG.debug("checking authentication for user '{}'", username);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      LOG.debug("security context was null, so authorizating securityUser");

      // It is not compelling necessary to load the use details from the database. You could also store the information
      // in the token and read it from it. It's up to you ;)
      SecurityUser securityUser = (SecurityUser) this.userDetailsService.loadUserByUsername(username);
      checkId(request, securityUser);

      // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
      // the database compellingly. Again it's up to you ;)
      if (jwtHelper.validateToken(authToken, securityUser)) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        LOG.info("authorizated securityUser '{}', setting security context", username);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    chain.doFilter(request, response);
  }


  private void checkId(HttpServletRequest request, SecurityUser securityUser) {
    if (!String.valueOf(securityUser.getId()).equals(request.getHeader("id"))) {
      throw new RuntimeException("JwtAuthorizationTokenFilter. method checkId: id in header and in securityUser must be equal");
    }
  }
}
