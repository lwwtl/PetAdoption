### 项目概述
*一个基于springboot的宠物领养管理系统*  
项目地址http://117.50.5.166
### 开发环境
|工具|版本|
|-|-|
|os|Windows 10|
|jdk|1.8|
|IDE|IntelliJ IDEA 2020.2|
|Boostrap|4|
|MySql|5.1.47|
|Maven|3.6.0|

### 功能介绍
- 用户可以注册登录修改个人资料，在网站上进行宠物的领养申请操作
- 管理员可以登录后台对宠物、用户的信息进行CRUD。同时对申请的领养信息进行审批。

### 项目截图
- *登录页面*
![用户登录](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2Flogin.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=EbFksOhPW5%2Fh1L0Yu2u%2FhAs6s6g%3D&Expires=1603025981)  
- *主页*
![主页](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2Findex.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=zFFCUDRWcau9I5MdJwJK2r%2BECQo%3D&Expires=1603026372)
- *个人资料*
![个人资料](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2Finfo.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=iuptE%2BvxfzCbeif5%2BpXyoAZSymc%3D&Expires=1603026397)
- *宠物列表*
![宠物列表](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2Fshow.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=KvIvSulYekva6%2FZJJMe8zQeLZjU%3D&Expires=1603026426)
- *宠物信息*
![宠物领养](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2Fadoption.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=dObXzuu0DprQkOYqMquWZ8noXLU%3D&Expires=1603026443)
- *后台管理（管理员身份登录）*
![后台管理](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2Fmanage.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=HaAMPq7HF7JeHKANO8bEeokBxFc%3D&Expires=1603026459)
- *用户的CRUD*
![user](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2Fuser.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=1lSLsri8hY%2F%2F7zg%2FFhGZq3yWIOs%3D&Expires=1603026476)
- *增加用户*
![adduser](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2Fadduser.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=GNsCP1bc%2FANjq2BFPS1bt2IeW0A%3D&Expires=1603026496)
- *宠物的CRUD*
![pet](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2Fpet.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=RIMJK3V4EPrMR%2Bs05Ae4qh%2BBLDk%3D&Expires=1603026513)
- *申请领养*
![adoption](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2Fapply.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=E%2BE1bx%2F7Fd6GPcWWS140Ls95k0g%3D&Expires=1603026533)

### ER图
*(数据表之间没有约束关系，数据类型上也偷懒了)*
![ER图](http://rlw.cn-bj.ufileos.com/%2FpetAdoption%2FER%E5%9B%BE.PNG?UCloudPublicKey=3xeS246CmmzEiKl4GZUEKG2BBLDOssOzPyT3yvJFy&Signature=DDJ1d62J3vFsGV0puseHV8wTMYU%3D&Expires=1603026878)