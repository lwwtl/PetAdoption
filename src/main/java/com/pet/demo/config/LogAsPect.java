package com.pet.demo.config;

import com.pet.demo.entity.Pet;
import com.pet.demo.entity.SysLog;
import com.pet.demo.entity.UserLog;
import com.pet.demo.service.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

//这个注解表示将当前类视为一个切面类
@Aspect
@Component
public class LogAsPect {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(LogAsPect.class);

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private PetService petService;
    @Autowired
    private HttpSession session;

    //对添加了注解的方法执行
    @Pointcut("@annotation(com.pet.demo.config.Log)")
//    @Pointcut("execution(* com.pet.demo.service.*.*(..))")对所有service执行
    public void pointcut() {}

//   @AfterReturning("pointcut()")
//    public void after(){
//       System.out.println("正常结束后置通知");
//   }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result =null;
        try {

            result = point.proceed();
            log.info("我在目标方法运行之后执行！");
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            insertLog(point,now);
        } catch (Throwable e) {
            // TODO Auto-generated catch block
        }
        return result;
    }

        private void insertLog(ProceedingJoinPoint point ,String time) {
            MethodSignature signature = (MethodSignature)point.getSignature();
            Method method = signature.getMethod();
            SysLog sys_log = new SysLog();
            UserLog userLog = new UserLog();
            Log userAction = method.getAnnotation(Log.class);
            if (userAction != null) {
                // 注解上的描述
                sys_log.setAdminAction(userAction.value());
            }

            // 请求的类名
            String className = point.getTarget().getClass().getName();
            // 请求的方法名
            String methodName = signature.getName();

            // 请求的方法参数值

            String args = Arrays.toString(point.getArgs());

//            String []value = args.substring();
            //从session中获取当前登陆人id

    		String Id = (String) session.getAttribute("Id");


//            sys_log.setAdminAction(methodName);


//将日志插入到数据库中
            if(methodName=="agree"){
                String Name = adminService.findName(Id);
                sys_log.setAId(Name);
                String value = applyService.findApply(args.substring(1,37));
                sys_log.setObject(value);
                sys_log.setAdminAction("同意");
                sys_log.setUrl("/Apply/agreePage");
                sys_log.setCreateTime(time);
                sysLogService.insertLog(sys_log);
            }else if(methodName=="disagree"){
                String Name = adminService.findName(Id);
                sys_log.setAId(Name);
                String value = applyService.findApply(args.substring(1,37));
                sys_log.setObject(value);
                sys_log.setAdminAction("不同意");
                sys_log.setUrl("/Apply/disagreePage");
                sys_log.setCreateTime(time);
                sysLogService.insertLog(sys_log);
            }else if(methodName=="save"){
                String Name = userService.findName(Id);
                userLog.setUserId(Name);
                String value = petService.findName(args.substring(39,75));
                userLog.setPetId(value);
                userLog.setUserAction("申请领养");
                userLog.setUrl("/Apply/find");
                userLog.setCreateTime(time);
                userLogService.insertLog(userLog);
            }


            log.info("当前登陆人：{},类名:{},方法名:{},参数：{},执行时间：{}",Id, className, methodName, args, time);


        }
}
