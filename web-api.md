
## 1.用户登录接口
>接口地址： http://xxx.com/login.json
- 可选参数<无>
- 返回
- 用户不存在{"fid":0,"flymeId":0,"userStatus":-1}
- 用户存在{"fid":11111,"flymeId":222222,"userStatus":3,"userName":"gavinfengzi","nickName":"fengzi","isDisabled":0}
- userStatus:-1  未加入,0  已加入  , 1  已提交资料  ,2  实名失败  ,3  实名成功


``` json
{
    "code": 200,
    "dateline": 1497606011991,
    "success": true,
    "value": {
        "promotionTitle": "撒打发士大夫",
        "code": 200,
        "shareContent": "分享文字3333",
        "shortUrl": "www.mklink.com/z8LLDN",
        "mkid": "z8LLDN",
        "promotionPic": "http://172.17.60.52/group1/M00/00/2F/rBE8NFkkKiCAJ-_wAAPeUpHrQlo947.jpg"
    }
}

## 1.用户登出接口
>接口地址： http://xxx.com/logout.json
- 参数：
	| 参数名      | 参数描述     | 参数类型  | 参数长度  | 是否可为空 |  备注	|
	| ------|-------:|-------:|	-------:|-------:|-------:|
    | userId|用户ID|Integer	     | 11	     |	否      |	    |
```

- 失败
- 5001,"活动ID格式错误!"
- 5002,"活动不存在"
- 5003,"活动未开始"
- 5004,"活动已结束"
- 5006,"活动未启用"
- 5005,"新增失败"

