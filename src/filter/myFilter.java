package filter;

// spring 写法

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by logan on 2017/5/23.
 */
@WebFilter(filterName = "Filter", urlPatterns = {"/*"})
public class myFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=utf-8");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String Origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", Origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "36000");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        System.out.print("SessionId:");
        System.out.println(request.getSession().getId());
        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}