package com.offcn.control;

import com.offcn.pojo.User;
import com.offcn.service.UserService;
import com.offcn.util.EmptyUtils;
import com.offcn.util.PageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("userControl")
public class UserControl {
    @Resource
    private UserService userService;


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    /**
     * 登陆判断
     * @param uname
     * @param upwd
     * @return
     */
    @RequestMapping("login")
    public String login(@RequestParam(value = "uname",required = false) String uname,
                        @RequestParam(value = "upwd",required = false) String upwd){
        User user = userService.selectUser(uname,upwd);
        if (EmptyUtils.isNotEmpty(user)){
            return "redirect:/userControl/toUserList";
        }
        return "redirect:/index";
    }


    /**
     * 分页查询
     * @param currentPageNo 当前页
     * @param pageSize 页量
     * @param request 用于页面请求
     * @param model 传数据用
     * @return
     */
    @RequestMapping("toUserList")
    public String toUserList(@RequestParam(value = "currentPageNo",required = false) String currentPageNo,
                             @RequestParam(value = "pageSize",required = false) String pageSize,
                             HttpServletRequest request, Model model){
        //设置当前页
        if (EmptyUtils.isEmpty(currentPageNo)){
            currentPageNo = "1";
        }
        //设置页量
        if (EmptyUtils.isEmpty(pageSize)){
            pageSize = "5";
        }
        //查询出总记录数
        Integer totalCount = userService.selectCount();
        //计算总页数
        Integer totalPageSize = totalCount % Integer.parseInt(pageSize)==0 ? totalCount/Integer.parseInt(pageSize):totalCount/Integer.parseInt(pageSize)+1;
        //查询出集合
        List<User> userList = userService.selectAllUserByPage(Integer.parseInt(currentPageNo),Integer.parseInt(pageSize));
        PageUtils page = new PageUtils();
        page.setCurrentPageNo(Integer.parseInt(currentPageNo));
        page.setPageSize(Integer.parseInt(pageSize));
        page.setTotalCount(totalCount);
        page.setUserList(userList);
        page.setTotalPageSize(totalPageSize);

        model.addAttribute("page",page);
        model.addAttribute("userList",userList);
        return "/userInfo";
    }


    /**
     * 跳转新增页面
     * @return
     */
    @RequestMapping("toAdd")
    public String toadd(){
        return "/addUser";
    }

    /**
     * 往数据库新增信息
     * @param uname 姓名
     * @param upwd 密码
     * @return
     */
    @RequestMapping("addUser")
    public String addUser(@RequestParam(value = "uname",required = false) String uname,
                          @RequestParam(value = "upwd",required = false) String upwd){
        User user = new User();
        user.setUname(uname);
        user.setUpwd(upwd);
        boolean flag = userService.addUser(user);
        if (flag){
            return "redirect:/userControl/toUserList";
        }
        return "redirect:/userControl/toAdd";
    }

    /**
     * 根据id跳转到相应的修改页面
     * @param uid
     * @return
     */
    @RequestMapping("toUpdate")
    public String toUpdate(@RequestParam(value = "uid",required = false) String uid,Model model){
        User user = userService.getUserById(Integer.parseInt(uid));
        if (EmptyUtils.isNotEmpty(user)){
            model.addAttribute("user",user);
            return "/modifyUser";
        }
        return "redirect:/userControl/toUserList";
    }

    /**
     * 通过页面取值，进行修改用户操作
     * @param uname
     * @param upwd
     * @param uid
     * @param user
     * @return
     */
    @RequestMapping("modifyUser")
    public String modifyUser(@RequestParam(value = "uname",required = false) String uname,
                             @RequestParam(value = "upwd",required = false) String upwd,
                             @RequestParam(value = "uid",required = false) String uid,
                             User user){
        User user1 = userService.getUserById(Integer.parseInt(uid));
        user.setUid(user1.getUid());
        user.setUname(uname);
        user.setUpwd(upwd);
        boolean flag = userService.modifyUser(user);
        if (flag){
            return "redirect:/userControl/toUserList";
        }
        return "redirect:/userControl/toUpdate";
    }

    /**
     * 根据页面所取id，进行删除用户操作
     * @param uid
     * @return
     */
    @RequestMapping("deleteUser")
    public String deleteUser(@RequestParam(value = "uid",required = false) String uid){
        boolean flag = userService.deleteUserById(Integer.parseInt(uid));
        if (flag){
            return "redirect:/userControl/toUserList";
        }
        return "/error";
    }
}
