package com.yr.handly;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yr.entity.User;
import com.yr.service.UserService;

@Controller
public class LoginHandly {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = { "/", "/loginPage"}, method = RequestMethod.GET)
    public String loginCustom( Model model) {
        return "login";
    }
	
	@RequestMapping(value = "/login_ssj", method = RequestMethod.GET)
	public String selectAllReuse(@RequestParam(value = "error", required = false) String error, Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String account = request.getParameter("account"); // 获取页面输入的参数,用来进行查询是否有权限操作
		String password = request.getParameter("password");
		request.getSession().setAttribute("account", account);
		request.getSession().setAttribute("password", password);
		List<User> list = userService.select();
		String d = null;
		String p = null;
		for (int i = 0; i < list.size(); i++) {
			if(account.equals(list.get(i).getAccount()) || password.equals(list.get(i).getPassword())){
				d = account;
				p = password;
			}
		}
		if (account != null && !account.equals("") && account.equals(d) && password != null && !password.equals("") && password.equals(p)) { // 从session中取值,如果session中有相同值就登录成功,否则失败
//			map.put("selAll", userService.select());
			return "show";
		} else if (error != null) {
			if (!account.equals(d)) {
				map.put("error", "账户不存在,请重新输入");
				return "login";
			} else if (!password.equals(p) || !password.equals("") || password != null) {
				map.put("error", "密码错误,请重新输入");
				return "login";
			}
		}
		return "login";
	}
	
	@RequestMapping(value={"/welcome"},method=RequestMethod.GET)
    public String welcome(){
        return "show";
    }
}
