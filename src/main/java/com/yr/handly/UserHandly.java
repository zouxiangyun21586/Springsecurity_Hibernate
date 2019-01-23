package com.yr.handly;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Page;
import com.yr.entity.User;
import com.yr.service.UserService;

@Controller
public class UserHandly {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 添加
	 * @author zxy
	 * @param user
	 * @return
	 * 2018年4月11日 下午10:16:12 
	 *
	 */
	@Transactional
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public String add(User user, ModelMap map) {
		try {
			Boolean boo = userService.add(user);
			if(boo){
				map.put("addlogin", "用户添加成功");
				return "login";
			}else{
				map.put("error","添加失败!");
				return "add";
			}
		} catch (Exception e) {
			map.put("error","添加失败!");
			return "add";
		}
	}
	
	/**
	 * 删除
	 * @author zxy
	 * @param user
	 * @return
	 * 2018年4月11日 下午10:16:07 
	 *
	 */
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public String del(@PathVariable("id") Long id, ModelMap map) {
		try {
			Boolean bool = userService.delete(id);
			if (bool) {
				map.put("dlt", 2);
				return "show";
			}else{
				map.put("dlt", 1);
				return "show";
			}
		} catch (Exception e) {
			map.put("dlt", 1);
			return "show";
		}
		
	}
	
	/**
	 * 修改
	 * 
	 * @author zxy
	 * @param id
	 * @param modelMap
	 * @return
	 * 2018年3月12日 下午7:47:00 
	 *
	 */
    @RequestMapping(value="/user",method=RequestMethod.PUT)
	public String upd(User user, ModelMap map) {
    	try {
    		Boolean bool = userService.update(user);
    		if (bool) {
    			map.put("upd", 2);
    			return "show";
    		}else{
    			map.put("upd", 1);
    			return "show";
    		}
		} catch (Exception e) {
			map.put("upd", 1);
			return "show";
		}
	}
    
    @RequestMapping(value="/get",method=RequestMethod.GET)
	public @ResponseBody User get(Long id, ModelMap map) {
    	User listUser = userService.get(id);
    	return listUser;
	}
	
	/**
	 * 查询
	 * 
	 * @author zxy
	 * @return
	 * 2018年3月12日 下午7:51:37 
	 *
	 */
    @ResponseBody
	@RequestMapping(value = "/user",method=RequestMethod.GET)
	public Page<User> sel(HttpServletResponse response,HttpServletRequest request) {
		Page<User> page = new Page<User>();

		String pageNow = request.getParameter("page");
		String pageSize = request.getParameter("pageSize");
		if (pageNow != null && !"".equals(pageNow)) {
			page.setPage(Integer.valueOf(pageNow));
			page.setPageSize(Integer.valueOf(pageSize));
		}
		page = userService.select(page);
		return page;
	}
	
}
