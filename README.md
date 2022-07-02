# Spring  .readme

# excercise1

url/endpoint : localhost:8080/grant/excercise1
method: GET METHOD
requestPrameter: year年分 , month 月份
responseType:

Object{
year:年分
month:月份
spaces:第一週需印多少空格
dasArrays:List<List<Number/>/> 每週的數字陣列
}


localhost:8080/grant/excercise1?year=2022&month=1
```json=
{
    "year": 2022,
    "month": 1,
    "spaces": 6,
    "daysArrays": [
        [
            1
        ],
        [
            2,
            3,
            4,
            5,
            6,
            7,
            8
        ],
        [
            9,
            10,
            11,
            12,
            13,
            14,
            15
        ],
        [
            16,
            17,
            18,
            19,
            20,
            21,
            22
        ],
        [
            23,
            24,
            25,
            26,
            27,
            28,
            29
        ],
        [
            30,
            31
        ]
    ]
}
```

---

# excercise02

url/endpoint:localhost:8080/grant/excercise2
method: GET METHOD
requestPrameter: dimensions骰子幾面 , howManyDices 多少骰子 , howManyTimes 骰幾次
responseType:

Array[Object{
    "sum":總和點數,
    "times":在總次數中出現幾次
    "stars":星號
},Obeject{}...]

localhost:8080/grant/excercise2?dimensions=6&howManyDices=3&howManyTimes=100
```json=
[
    {
        "sum": 3,
        "times": 1,
        "stars": "*"
    },
    {
        "sum": 4,
        "times": 2,
        "stars": "**"
    },
    {
        "sum": 5,
        "times": 2,
        "stars": "**"
    },
    {
        "sum": 6,
        "times": 9,
        "stars": "*********"
    },
    {
        "sum": 7,
        "times": 6,
        "stars": "******"
    },
    {
        "sum": 8,
        "times": 5,
        "stars": "*****"
    },
    {
        "sum": 9,
        "times": 8,
        "stars": "********"
    },
    {
        "sum": 10,
        "times": 14,
        "stars": "**************"
    },
    {
        "sum": 11,
        "times": 12,
        "stars": "************"
    },
    {
        "sum": 12,
        "times": 13,
        "stars": "*************"
    },
    {
        "sum": 13,
        "times": 10,
        "stars": "**********"
    },
    {
        "sum": 14,
        "times": 7,
        "stars": "*******"
    },
    {
        "sum": 15,
        "times": 6,
        "stars": "******"
    },
    {
        "sum": 16,
        "times": 2,
        "stars": "**"
    },
    {
        "sum": 17,
        "times": 1,
        "stars": "*"
    },
    {
        "sum": 18,
        "times": 2,
        "stars": "**"
    }
]
```

---

# excercis303

## a新增訂單

url/endpoint : localhost:8080/grant/order
method: POST METHOD
requestBody:

```json=
 {
        "orderId": 19,
        "customerId": 2,
        "paymentId": "1",
        "orderStatus": "未出貨",
        "address": "台北市松山區",
        "receiver": "王大明",
        "tel": "09666",
        "totalPrice": 800,
        "items": [
            {    
                "productId": 1,
                "itemPrice":100,
                "quantity": 5,
                "subtotal":500
            },
            {
                "productId": 3,
                "itemPrice":300,
                "quantity": 1,
                "subtotal":300
            }
        ]
    }
```

responseBody:

```json=
{
    "orderId": 38,
    "items": [
        {
            "itemId": 42,
            "product": {
                "productId": 1,
                "productName": "product1",
                "productInfo": "productInfo1",
                "inStock": 100,
                "price": 100,
                "available": true,
                "unit": "輛"
            },
            "itemPrice": 100,
            "quantity": 5,
            "subtotal": 500,
            "productId": 1
        },
        {
            "itemId": 43,
            "product": {
                "productId": 3,
                "productName": "product3",
                "productInfo": "productInfo3",
                "inStock": 600,
                "price": 300,
                "available": false,
                "unit": "張"
            },
            "itemPrice": 300,
            "quantity": 1,
            "subtotal": 300,
            "productId": 3
        }
    ],
    "payment": {
        "paymentId": 1,
        "type": "超商取貨"
    },
    "orderStatus": "未出貨",
    "address": "台北市松山區",
    "receiver": "王大明",
    "tel": "09666",
    "totalPrice": 800,
    "createdDate": "2022-06-28T16:00:00.000+00:00",
    "updatedDate": "2022-06-28T16:00:00.000+00:00",
    "customerId": 2,
    "paymentId": 1
}
```

在新增時有扣減存貨
存貨不足拋出異常

## b修改訂單

### 批次新增

url/endpoint : localhost:8080/grant/order/{orderid}
method: PUT METHOD
requestBody:
包含 itemid newprice newquantity 屬性的物件陣列
ex 41 300  1, 42 100 5, 43 300 1
```json=
[
    {
        "itemid":41,
        "newprice":500,
        "newquantity":2
    },
    {
        "itemid":42,
        "newprice":500,
        "newquantity":1
    },
    {
        "itemid":43,
        "newprice":800,
        "newquantity":1
    }
]
```

responseBody:
包含訂單明細 陣列中被選明細 的所有所有 order 所組成的陣列

```json=
[
    {
        "orderId": 37,
        "items": [
            {
                "itemId": 40,
                "product": {
                    "productId": 1,
                    "productName": "product1",
                    "productInfo": "productInfo1",
                    "inStock": 95,
                    "price": 100,
                    "available": true,
                    "unit": "輛"
                },
                "itemPrice": 100,
                "quantity": 5,
                "subtotal": 500,
                "productId": 1
            },
            {
                "itemId": 41,
                "product": {
                    "productId": 3,
                    "productName": "product3",
                    "productInfo": "productInfo3",
                    "inStock": 599,
                    "price": 300,
                    "available": false,
                    "unit": "張"
                },
                "itemPrice": 500,
                "quantity": 2,
                "subtotal": 1000,
                "productId": 3
            }
        ],
        "payment": {
            "paymentId": 1,
            "type": "超商取貨"
        },
        "orderStatus": "未出貨",
        "address": "台北市松山區",
        "receiver": "王大明",
        "tel": "09666",
        "totalPrice": 1500,
        "createdDate": "2022-06-28T16:00:00.000+00:00",
        "updatedDate": "2022-06-28T16:00:00.000+00:00",
        "customerId": 2,
        "paymentId": 1
    },
    {
        "orderId": 38,
        "items": [
            {
                "itemId": 42,
                "product": {
                    "productId": 1,
                    "productName": "product1",
                    "productInfo": "productInfo1",
                    "inStock": 95,
                    "price": 100,
                    "available": true,
                    "unit": "輛"
                },
                "itemPrice": 500,
                "quantity": 1,
                "subtotal": 500,
                "productId": 1
            },
            {
                "itemId": 43,
                "product": {
                    "productId": 3,
                    "productName": "product3",
                    "productInfo": "productInfo3",
                    "inStock": 599,
                    "price": 300,
                    "available": false,
                    "unit": "張"
                },
                "itemPrice": 800,
                "quantity": 1,
                "subtotal": 800,
                "productId": 3
            }
        ],
        "payment": {
            "paymentId": 1,
            "type": "超商取貨"
        },
        "orderStatus": "未出貨",
        "address": "台北市松山區",
        "receiver": "王大明",
        "tel": "09666",
        "totalPrice": 1300,
        "createdDate": "2022-06-28T16:00:00.000+00:00",
        "updatedDate": "2022-06-28T16:00:00.000+00:00",
        "customerId": 2,
        "paymentId": 1
    }
]
```

### 更改訂單狀態

url/endpoint : localhost:8080/grant/ecommerce/order/updateorderstatus/4
method : PUT METHOD
pathvariable : orderid 
requestparameter : newOrderStatus , dataType:String
response:

localhost:8080/grant/ecommerce/order/updateorderstatus/4?newOrderStatus=0629已取貨
```json=
true
```

### 更改訂單明細

url/endpoint : localhost:8080/grant/ecommerce/order/updateorderreceiver/{orderid}
method: PUT METHOD
pathVariable: orderid
requestBody :

```json=
{
    "newAddress":"0629新地址",
    "newReceiver":"0629新收件人",
    "newTel":"0629新手機"
}
```

responseType: 

```json=

```

## c刪除訂單

url/endpoint : localhost:8080/grant/order/{orderid}
method: DELETE METHOD
pathVariable: orderid
responseType: boolean

localhost:8080/grant/ecommerce/order/1
```json=
true
```


## d查詢訂單

### d-1 查詢主表欄位 回傳明細

url/endpoint : localhost:8080/grant/ecommerce/selectbydates
method: GET METHOD
requestPrameter: begindate 起始日期 , enddate 結束日期
responseType:

localhost:8080/grant/ecommerce/selectbydates?begindate=2022-06-19&enddate=2022-06-28
```json=
[
    {
        "orderId": 19,
        "customer_id": 2,
        "customer_name": "李四",
        "payment": "超商取貨",
        "order_status": "未出貨",
        "address": "台北市信義區",
        "receiver": "王曉明",
        "telephone": "09666",
        "total_price": 800,
        "created_date": "2022-06-23T16:00:00.000+00:00",
        "updated_date": "2022-06-23T16:00:00.000+00:00",
        "items": [
            {
                "product_name": "product1",
                "quantity": 5
            },
            {
                "product_name": "product3",
                "quantity": 1
            }
        ]
    },
    {
        "orderId": 17,
        "customer_id": 2,
        "customer_name": "李四",
        "payment": "超商取貨",
        "order_status": "未出貨",
        "address": "台北市信義區",
        "receiver": "王曉明",
        "telephone": "09666",
        "total_price": 12500,
        "created_date": "2022-06-22T16:00:00.000+00:00",
        "updated_date": "2022-06-22T16:00:00.000+00:00",
        "items": [
            {
                "product_name": "product1",
                "quantity": 100
            },
            {
                "product_name": "product3",
                "quantity": 50
            }
        ]
    }
```

### d-2 查詢訂單明細

url/endpoint : localhost:8080/grant/ecommerce/item/{itemid}
url/endpoint : localhost:8080/grant/ecommerce/item/{itemid}
method: GET METHOD
pathvariable: item 表格 id
responseType:

localhost:8080/grant/ecommerce/item/12
```json=
{
    "item_id": 12,
    "order_id": 19,
    "product_id": 1,
    "product_name": "product1",
    "item_price": 100,
    "quantity": 5,
    "subtotal": 500
}
```

###### tags: `ALP`