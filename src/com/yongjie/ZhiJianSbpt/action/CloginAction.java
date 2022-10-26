package com.yongjie.ZhiJianSbpt.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yongjie.ZhiJianSbpt.container.ServiceProvider;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import com.yongjie.ZhiJianSbpt.service.ICloginService;
import com.yongjie.ZhiJianSbpt.utilities.*;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller("CloginAction")
@Scope("prototype") // 每次访问此action都会创建实例，确保数据安全
public class CloginAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    ICloginService cloginService = (ICloginService) ServiceProvider.getService(ICloginService.SERVICE_NAME);
    private HttpServletRequest req = ServletActionContext.getRequest();
    private HttpServletResponse response = ServletActionContext.getResponse();

    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    public void doLogin() throws Exception {
        String username = req.getParameter("name");
        String psd = req.getParameter("pwd");
        String str = "";
        List<AccountSbpt> list = cloginService.doLogin(username, psd);
        if (list.size() > 0) {// 说明用户名 密码正确 进一步判断IsAction状态0未激活 1 已激活
            if (list.get(0).getIsAction() == 1) {
                str = "ok";
                req.getSession().setAttribute("userName", username);
                req.getSession().setAttribute("areaId", list.get(0).getAreaId());
                response.getWriter().print(str);
            }
        } else {
            str = "error";//在前台 提示 用户名或密码错误
            response.getWriter().print(str);
        }
    }

    //当用户输入账号后要判断此账号是否为激活状态 给出提醒
    public void chechAction() throws Exception {
        String userName = req.getParameter("name");
        String str = "";
        List<AccountSbpt> list = cloginService.checkAction(userName);
        if (list != null && !list.isEmpty()) {
            if (list.get(0).getIsAction() == 0) {//说明未激活
                str = "locked";//锁定了 在前台提示账号还没激活
                response.getWriter().print(str);
            } else {
                str = "ok";//说明已被激活 通过验证
                response.getWriter().print(str);
            }
        } else {
            str = "error";//在前台 提示 此账号还没注册
            response.getWriter().print(str);
        }
    }

    public void getScrollList() throws Exception {
        response.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("userName");
        ArrayList list = cloginService.getScrollList(userName);
        String json = JSON.Encode(list);
        //向页面输出json语句
        response.getWriter().write(json);

    }

    public void getTrueName() throws Exception {
        String userName = req.getParameter("userName");
        ArrayList list = cloginService.getTrueName(userName);
        String json = JSON.Encode(list);
        response.setContentType("text/html;charset=UTF-8");
        //向页面输出json语句
        response.getWriter().write(json);
    }

    public void logout() throws Exception {
        req.getSession().invalidate();
    }

    // 用户登录
    public void doLogins() throws Exception {
        response.setContentType("text/html;charset=utf-8");
        String resultJson = "";
        // 验证码
        String verCode = req.getParameter("verCode");
        if (!StringUtil.isNullOrEmpty(verCode)) {
            String seVerCode = req.getSession().getAttribute(SessionUtil.SESSION_VER_CODE) + "";
            if (verCode.equalsIgnoreCase(seVerCode)) {
                String username = req.getParameter("username");
                String pwd = req.getParameter("pwd");
                if (!StringUtil.isNullOrEmpty(username) && !StringUtil.isNullOrEmpty(pwd)) {
                    try {
                        AccountSbpt dbAccountSbpt = cloginService.getAccountSbptByUserName(username);
                        if (dbAccountSbpt != null) {
                            String md5Pwd = MD5EncryptHelper.getEncryption(pwd);
                            if (md5Pwd.equals(dbAccountSbpt.getPassword())) {
                                if (dbAccountSbpt.getIsAction() == 1) { // 1 已激活
                                    resultJson = R.ok("认证成功!");
                                    // 保存 session
                                    req.getSession().setAttribute(SessionUtil.SESSION_USER_NAME, username);
                                    req.getSession().setAttribute(SessionUtil.SESSION_QYMC, dbAccountSbpt.getQymc());
                                    req.getSession().setAttribute(SessionUtil.SESSION_ZZJGDM, dbAccountSbpt.getZzjgdm());
                                    req.getSession().setAttribute(SessionUtil.SESSION_USER, dbAccountSbpt);
                                } else {
                                    resultJson = R.error("账号未激活,请联系管理员!");
                                }
                            } else {
                                resultJson = R.error("用户名或密码输入有误!");
                            }
                        } else {
                            resultJson = R.error("用户名或密码输入有误!");
                        }
                    } catch (Exception e) {
                        resultJson = R.error("登录失败!", e);
                    }
                } else {
                    resultJson = R.error("用户名或密码输入有误!");
                }
            } else {
                resultJson = R.error("验证码输入错误！");
            }
        } else {
            resultJson = R.error("验证码输入错误！");
        }
        response.getWriter().println(resultJson);
    }
}
