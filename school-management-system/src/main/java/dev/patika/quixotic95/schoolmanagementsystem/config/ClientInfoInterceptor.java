package dev.patika.quixotic95.schoolmanagementsystem.config;

import dev.patika.quixotic95.schoolmanagementsystem.entity.logger.ClientInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@RequiredArgsConstructor
public class ClientInfoInterceptor implements HandlerInterceptor, WebMvcConfigurer {

    private final ClientInfo clientInfo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        clientInfo.setClientIpAddress(request.getRemoteAddr());
        clientInfo.setClientRequestUrl(request.getRequestURI());
        clientInfo.setClientSessionId(request.getRequestedSessionId());

        return true;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns(("/**"));
    }
}
