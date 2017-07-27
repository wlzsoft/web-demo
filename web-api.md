## 1.户基登录接口
> http://profileapi.mk.meizu.com/profile/getUserBaseInfo.json
- 参数

   名称   |    类型  | 是否必须 | 描述  
 :------: | :------: | :-------:|: ---:
 userName |  String |    是    | 用户名称 
 password |  String |    是    | 用户密码 

- 返回结果

|   名称   |    类型  | 是否必须 | 描述 
| :------: | :------: | :-------:|: ---:
| id       |  String |    是    | 用户ID 
| birthday |  String |    否    | 出生年月 
| userName |  String |    是    | 用户名 
| email    |  String |    否    | 邮箱 
| telphone |  String |    否    | 联系电话 
| degree   |  String |    是    | 用户等级 
| registerTime |  String |    是    | 注册时间 
| lasterLoginTime |  String |    否    | 最后登陆时间 
| remark |  String |    否    | 备注 

- 示例

First Header | Second Header
------------ | -------------
Content from cell 1 | Content from cell 2
Content in the first column | Content in the second column



``` json
{
    "code": 200,
    "success": true,
    "value": {
        "id": "1",
        "birthday": 2000-06-11,
        "userName": "LiWang",
        "email": "liwang@163.com",
        "telphone": "13365847895",
        "degree": "1",
        "registerTime":"2017-06-11 12:25:45",
        "lasterLoginTime":"2017-07-11 12:25:45",
        "remark":""
    }
}
