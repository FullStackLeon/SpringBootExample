package example.one.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.LocalDateTime;

public class LatencyInterceptor implements HandlerInterceptor {
    private LocalDateTime startTime;
    Logger logger = LoggerFactory.getLogger(LatencyInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.startTime = LocalDateTime.now();
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        LocalDateTime endTime = LocalDateTime.now();
        long millis = Duration.between(this.startTime, endTime).toMillis();
        logger.info("request uri: {}, latency time: {} ms", request.getRequestURI(), millis);
    }
}
