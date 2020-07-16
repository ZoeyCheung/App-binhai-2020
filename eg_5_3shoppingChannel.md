## 05.3 购物车商品列表改进(GridView)
#### 1. 修改布局文件 activity_shopping_channel.xml
GridView替代ScrollView
#### 2. 添加基础适配器 adapter/CartAdapter.java
MVVM是Model-View-ViewModel的简写。它本质上就是MVC 的改进版。MVVM 就是将其中的View 的状态和行为抽象化，让我们将视图 UI 和业务逻辑分开。ViewModel可以取出 Model 的数据同时帮忙处理 View 中由于需要展示内容而涉及的业务逻辑。

* View:布局文件
* ViewModel:适配器,将布局文件和数据联系在一起
* Model:数据

#### 3. 添加购物车行布局文件 item_goods.xml
构建单个商品线性布局
#### 4. 修改购物车类 bean/CartInfo.java
添加商品信息GoodsInfo goods
#### 5. 修改业务逻辑

* 声明列表视图对象、当前商品对象、当前视图对象、处理器对象
* onCreate：从布局获取列表视图
* onItemClick：商品项点击事件
* onItemLongClick：商品项长按事件
* 定义一个上下文菜单的弹出任务，注意先取消点击、长按监听和上下文菜单，设置好适配器后再次添加监听
* showGoods()：修改显示，读取商品信息，设置适配器，添加点击和长按监听