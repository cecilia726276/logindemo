package com.chxyz.demo.authentication;

import com.auth0.jwt.JWT;
import com.chxyz.demo.controller.ResponseData;
import com.chxyz.demo.model.UserDO;
import org.springframework.web.servlet.ModelAndView;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor {
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response, Object handler, Exception arg3)
//            throws Exception {
//    }
//​
//
//    public void postHandle(HttpServletRequest request, HttpServletResponse response,
//                           Object handler, ModelAndView model) throws Exception {
//    }​
//
//
//    //拦截每个请求
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//                             Object handler) throws Exception {
//        response.setCharacterEncoding("utf-8");
//        String token = request.getParameter("token");
//        ResponseData responseData = ResponseData.ok();
//        //token不存在
//        if (null != token) {
//            UserDO userDO = JWT.unsign(token, UserDO.class);
//            String loginId = request.getParameter("loginId");
//            //解密token后的loginId与用户传来的loginId不一致，一般都是token过期
//            if (null != loginId && null != userDO) {
//                if (Integer.parseInt(loginId) == userDO.getId()) {
//                    return true;
//                } else {
//                    responseData = ResponseData.forbidden();
//                    responseMessage(response, response.getWriter(), responseData);
//                    return false;
//                }
//            } else {
//                responseData = ResponseData.forbidden();
//                responseMessage(response, response.getWriter(), responseData);
//                return false;
//            }
//        } else {
//            responseData = ResponseData.forbidden();
//            responseMessage(response, response.getWriter(), responseData);
//            return false;
//        }
//    }​
//    //请求不通过，返回错误信息给客户端
//    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
//        responseData = ResponseData.forbidden();
//        response.setContentType("application/json; charset=utf-8");
//        String json = JSONObject.toJSONString(responseData);
//        out.print(json);
//        out.flush();
//        out.close();
//    }
//​

}
