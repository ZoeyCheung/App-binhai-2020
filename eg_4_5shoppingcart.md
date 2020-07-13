## 4.5 案例：购物车

#### 1. MainActivity：商品列表页

##### 构建商品列表页面

(1). 创建一个公共头部文件：activity_shopping_title.xml，包含标题和购物车图标
(2). 在布局文件 activity_main.xml 中引用头部文件
(3). 创建一个滚动视图，用于显示商品列表

##### 引用工具类（util）

复制之前编写过的工具类
(1) DateUtil：日期时间格式化
(2) FileUtil：SD卡文件读写
(3) SharedUtil：共享参数读写
(4) Utils：获取屏幕信息和单位转换

##### 编写数据库帮助器（database）

###### GoodsDBHelper 商品数据库帮助器

(1) 数据库名：goods.db
(2) 表名：goods_info
(3) getInstance(Context context, int version)：利用单例模式获取数据库帮助器的唯一实例
(4) openReadLink()：打开数据库读连接
(5) openWriteLink()：打开数据库写连接
(6) closeLink()：关闭连接
(7) onCreate(SQLiteDatabase db)：创建表
(8) onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)：修改表
(9) insert(GoodsInfo info)：插入单条记录
(10) insert(ArrayList<GoodsInfo> infoArray)：插入多条记录
(11) update(GoodsInfo info, String condition)：按条件更新记录
(12) update(GoodsInfo info)：按rowid行号更新记录
(13) delete(String condition)：按条件删除记录
(14) deleteAll()：删除所有记录
(15) public ArrayList<GoodsInfo> query(String condition)：条件查询
(16) queryById(long rowid)：rowid行号查询

###### CartDBHelper 购物车数据库帮助器

(1) 数据库名：cart.db
(2) 表名：cart_info
(3) getInstance(Context context, int version)：利用单例模式获取数据库帮助器的唯一实例
(4) openReadLink()：打开数据库读连接
(5) openWriteLink()：打开数据库写连接
(6) closeLink()：关闭连接
(7) onCreate(SQLiteDatabase db)：创建表
(8) onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)：修改表
(9) insert(GoodsInfo info)：插入单条记录
(10) insert(ArrayList<GoodsInfo> infoArray)：插入多条记录
(11) update(GoodsInfo info, String condition)：按条件更新记录
(12) update(GoodsInfo info)：按rowid行号更新记录
(13) delete(String condition)：按条件删除记录
(14) deleteAll()：删除所有记录
(15) public ArrayList<GoodsInfo> query(String condition)：条件查询
(16) queryById(long rowid)：rowid行号查询

##### 编写业务逻辑

(1) 定义私有变量：购物车计数显示（tv_count）、商品列表线性布局（ll_channel）、购物车计数器（mCount）、商品数据库帮助器（mGoodsHelper）、购物车数据库帮助器（mCartHelper）
(2) onCreate（）：获取页面控件对象；为购物车按钮添加监听，当按下购物车按钮时，跳转到购物车页面；
(3) onResume()：读取共享参数初始化购物车商品数量；获取商品数据库帮助器对象；获取购物车帮助器对象；打开数据库读写连接；初始化数据库；展示商品列表；
(4) onPause()：关闭数据库连接
(5) downloadGoods()：首次打开，先将准备好的商品写入数据库；小图保存在全局内存；大图保存SD卡；
(6) showGoods()：展示商品列表，移除线性布局中的子视图，4层线性布局嵌套：ll_channel -> ll_row -> ll_goods -> ll_bottom,每行显示2个商品，注意商品总数为单数的情况；点击商品缩略图可以跳转商品详情注意传递rowid行号；添加购物车按钮，参数rowid行号；
(7) addToCart(long goods_id)：将商品加入购物车，购物车中商品数量加1；查询购物车是不是已经存在该商品，如果存在，数量加1，不存在则插入一条记录；

#### 2. ShoppingDetailActivity：商品详情页

##### 构建商品详情页面

(1) 在布局文件 activity_shopping_detail.xml 中引用头部文件
(2) 创建一个滚动视图，用于显示商品信息，图片、价格、描述、加入购物车按钮

##### 构建菜单页面

(1) 商品详情页面菜单：menu_detail.xml

##### 编写业务逻辑

(1) 定义私有变量：标题（tv_title）、购物车数量显示（tv_count）、价格（tv_goods_price）、描述（tv_goods_desc）、商品图片（iv_goods_pic）、商品编号（mGoodsId）、购物车计数器（mCount）、商品数据库帮助器（mGoodsHelper）、购物车数据库帮助器（mCartHelper）
(2) onCreate（）：获取页面控件对象；为按钮添加监听，当按下按钮时，执行对应操作；
(3) onResume()：读取共享参数初始化购物车商品数量；获取商品数据库帮助器对象；获取购物车帮助器对象；打开数据库读写连接；展示商品信息；
(4) onPause()：关闭数据库连接
(5) 购物车页面菜单,从menu_detail.xml中构建菜单界面布局;去商场购物，跳转到商品列表页；打开购物车，跳转到购物车页面；返回按钮，返回上一页；
(6) showDetail()：获取前一个页面传来的商品编号，读取商品信息并展示在页面中
(7) addToCart(long goods_id)：将商品加入购物车，购物车中商品数量加1；查询购物车是不是已经存在该商品，如果存在，数量加1，不存在则插入一条记录；

#### 3. ShoppingCartActivity：购物车

##### 构建商品列表页面

**当购物车有商品时：**
(1) 创建表头
(2) 创建一个滚动视图，用于显示购物车商品列表
(3) 结算栏，显示总金额和结算按钮

**当购物车为空时：**
(1) 购物车为空的提示文字
(2) 返回商品列表页按钮

##### 构建菜单页面

(1) 购物车页面菜单：menu_cart.xml
(2) 购物车商品长按菜单：menu_goods

##### 编写业务逻辑

(1) 定义私有变量：总价显示（tv_total_price）、当购物车有商品时显示组（gp_content）、当购物车为空时显示组（gp_empty）、购物车商品列表视图（ll_cart）、购物车计数器（mCount）、商品数据库帮助器（mGoodsHelper）、购物车数据库帮助器（mCartHelper）
(2) onCreate（）：获取页面控件对象；为按钮添加监听，当按下按钮时，执行对应操作；
(3) onResume()：读取共享参数初始化购物车商品数量；获取商品数据库帮助器对象；获取购物车帮助器对象；打开数据库读写连接；展示购物车商品列表；
(4) onPause()：关闭数据库连接
(5) 购物车页面菜单,从menu_cart.xml中构建菜单界面布局;去商场购物，跳转到商品列表页；清空购物车，清空购物车数据表，更新购物车商品数量；更新共享参数中商品个数；返回按钮，返回上一页；
(6) 商品长按菜单，从menu_goods.xml中构建菜单界面布局，需要获取行视图，知道点击的是哪一行；查看商品详情，跳转到对应的商品详情页；从购物车中删除，删除购物车商品表中该记录；更新购物车商品个数；更新共享参数中商品个数；
(7) showCart() ：展示购物车商品列表，移除线性布局中的子视图；为商品行添加单击事件监听，跳转到商品详情页；为商品行添加菜单；(8) 计算商品总额
refreshTotalPrice()：计算商品总额
