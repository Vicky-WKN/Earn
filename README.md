## 阅赚宝需求文档(未完成)

### 开屏广告get
| 名称           | 类            |说明                      |
|-------------|-------------|----------------|
|status         |int            |请求接口成功返回1，错误返回0|



### 用户注册(完成)
http://39.108.98.193:8080/EarnServer/RegisterServlet
|参数 |数据类型 |必填 |描述 |
|-----|-------|---------|-----|
|phone |string|yes|手机号，作为账号|
|name |  string    | yes  |用户名，作为交互标识符|
|pwd| string  |yes|   密码|
|code    |string |   yes|  验证码|
```
{
	"status":0
}
{	
	"err":309,
	"status":1
}
{	
	"err":301,
	"status":1
}
```



### 获取验证码(完成)
http://39.108.98.193:8080/EarnServer/GetC
参数
|参数|数据类型|必要/备注||
|-----|------|--|
|id   |String|是|



### 用户修改密码（未完成）
user/change
|参数 |数据类型|必填   |描述 |
|-----|-------|-----  |-----|
|phone| string|    yes|   手机号|
|pwd  |string | yes   |  新密码|
|code   | string |   yes |  验证码|



### 用户登陆并生成口令token（完成）
http://39.108.98.193:8080/EarnServer/LoginServlet
user/login
|参数 |数据类型 |必填 |描述 |
|-----|-------|---------|-----|
|phone |string |   yes|      |
|password |string | yes |  密码|
####返回：
```
{
  "status": 0,  
  "data": {
  "token": "1c6cbe5e583a8ae831f7d64c4cb9a3ea_18819269394"
  }
}
{
  "status":1,
  "err":30
}
```

### 获取用户信息（完成）
http://39.108.98.193:8080/EarnServer/GetDataServlet
####参数
|参数 |数据类型 |必填 |描述 |
|-----|-------|---------|-----|
|token|string|是|用户令牌|

####返回
|参数 |数据类型 |描述 |
|-----|-------|-----|
|status|int|
|name|String|
|myselfMoney|String|
|studentMoney|String|
|err|int|

```
{
	"err":201,
	"status":1
}
{
	"data":{
		"myselfMoney":20.27,
		"name":"老冯",
		"studentMolney":2.22
		},
	"status":0
}
```

###更新自己赚的钱（完成）
http://39.108.98.193:8080/EarnServer/UpdateMoneyServlet
####参数
|参数 |数据类型 |必填 |
|-----|-------|---------|
|token|String |是|
|money|String |是|
 
###更新徒弟赚的钱（完成）
http://39.108.98.193:8080/EarnServer/UpdateStudentMoneyServlet
参数
|参数 |数据类型 |必填 |
|-----|-------|---------|
|token|String |是|
|money|String|是|

#### 返回
```
{
	"status":0
}
{
	"err":201,
	"status":0
}

```

### 更新支付宝和真实姓名（完成)
http://39.108.98.193:8080/EarnServer/AlipayServlet
####参数
|参数 |数据类型 |必填 |
|-----|-------|---------|
|token|String |是|
|alipayId|String|是|
|realName|String|是|

 
###更新微信名（完成）
http://39.108.98.193:8080/EarnServer/WechatServlet
参数
|参数 |数据类型 |必填 |
|-----|-------|---------|
|token|String |是|
|wechat|String|是|


### 提现（完成）
http://39.108.98.193:8080/EarnServer/WithdrawServlet
参数
|参数|数据类型|必要/备注||
|-----|------|--|
|token|String|是|
|pwd  |String|是|
|money|String|是|
|way  |String|是|

```
{"status":0}
{"err":201,"status":201}
{"err":304,"status":304}
```

###更新
|错误代码|错误信息|
|-------|-------|
|201|token不正确或者过期|
|300|获取验证码失败|
|301|手机号已经注册|
|302|验证码错误或过期|
|303|手机号不存在|
|304|密码不正确|
|305|新旧密码相同|
|306|id不存在|
|307|操作异常,ID冻结|
|308|数据库插入异常|
|309|验证码不正确或已过期|
|404|连接不上服务器|

七牛账号：726626303@qq.com
密码：th0608091006