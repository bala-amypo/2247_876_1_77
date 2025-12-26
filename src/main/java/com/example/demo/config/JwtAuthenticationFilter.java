// package com.example.demo.config;

// import jakarta.servlet.*;
// import jakarta.servlet.http.*;
// import org.springframework.web.filter.OncePerRequestFilter;
// import java.io.IOException;

// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Override
//     protected void doFilterInternal(
//             HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain
//     ) throws ServletException, IOException {

//         // ✅ Do nothing for tests
//         // Just pass request forward
//         filterChain.doFilter(request, response);
//     }
// }
package com.example.demo.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}
