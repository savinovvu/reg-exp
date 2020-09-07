package ru.inbox.savinov_vu.core.security.jwt.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.inbox.savinov_vu.core.exception.AuthenticationException;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;
import ru.inbox.savinov_vu.core.security.service.SecurityService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static ru.inbox.savinov_vu.configuration.WebSecurityConfig.PUBLIC_PATHS;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {


  private final UserDetailsService userDetailsService;

  private final JwtHelper jwtHelper;

  private final SecurityService securityService;


  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws ServletException, IOException {
    if (PUBLIC_PATHS.contains(request.getRequestURI())) {
      chain.doFilter(request, response);
      return;
    }

    LOG.debug("processing authentication for '{}'", request.getRequestURL());

    String authToken = jwtHelper.getAuthToken(request);
    String username = jwtHelper.getUserNameOrNull(authToken);

    if (isNullUserNameOrNullAuthentication(request, response, chain, username)) {
      chain.doFilter(request, response);
      return;
    }


    SecurityUser securityUser = (SecurityUser) this.userDetailsService.loadUserByUsername(username);
    validateAndAuthenticate(request, authToken, username, securityUser);
    chain.doFilter(request, response);
  }


  private boolean isNullUserNameOrNullAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, String username) throws IOException, ServletException {
    LOG.debug("checking authentication for user '{}'", username);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return isNull(username) || nonNull(authentication);
  }


  private void validateAndAuthenticate(HttpServletRequest request, String authToken, String username, SecurityUser securityUser) {
    checkId(request, securityUser);
    checkLogoutToken(authToken);

    if (!jwtHelper.validateToken(authToken, securityUser)) {
      return;
    }

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    LOG.info("authorizated securityUser '{}', setting security context", username);
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
  }


  private void checkId(HttpServletRequest request, SecurityUser securityUser) {
    if (!String.valueOf(securityUser.getId()).equals(request.getHeader("id"))) {
      throw new AuthenticationException("JwtAuthorizationTokenFilter. method checkId: id in header and in securityUser must be equal");
    }
  }


  private void checkLogoutToken(String authToken) {
    boolean isLogout = securityService.isLogoutToken(authToken);
    if (isLogout) {
      throw new AuthenticationException("JwtAuthorizationTokenFilter. method checkLogoutToken: this token was logout");
    }
  }

}
