package ftn.euprava.mupvozila.util.jwt;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.List.of;
import static java.util.Optional.ofNullable;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // filterChain.doFilter(request, response) - means the request will continue to the controller, otherwise
        // do nothing and return 200
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        System.out.println(path);
        if (path.contains("collect_jwt") || path.contains("jwt")){
            filterChain.doFilter(request, response);
            return;
        }

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        // Get authorization header and validate
        if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "This request requires authorized token.");
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.tokenVerified(token)) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
            return;
        }

        filterChain.doFilter(request, response);
    }

}
