package br.com.project.shopstyle.mscustomer.security.service;

import br.com.project.shopstyle.mscustomer.entity.Customer;
import br.com.project.shopstyle.mscustomer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final CustomerRepository customerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenFromHeader = getTokenFromHeader(request);
        boolean tokenIsValid = tokenService.tokenIsValid(tokenFromHeader);
        if (tokenIsValid){
            this.authenticate(tokenFromHeader);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest servletRequest){
        String token = servletRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")){
            return token.substring(7, token.length());
        }
        return null;
    }

    private void authenticate(String tokenFromHeader){

        Optional<Customer> optionalCustomer = customerRepository.findById(tokenService.getIdFromToken(tokenFromHeader));

        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
