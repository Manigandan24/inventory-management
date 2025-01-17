package sl.ms.inventorymanagement.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

@Override
public void doFilter(ServletRequest req, ServletResponse res,
        FilterChain chain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
    response.setHeader("Access-Control-Allow-Methods", "GET,POST,PATCH,DELETE,PUT,OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "*");
    response.setHeader("Access-Control-Max-Age", "86400");
    chain.doFilter(req, res);
}
}