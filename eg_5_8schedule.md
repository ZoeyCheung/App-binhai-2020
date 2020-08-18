## 05.8 日程表
#### 安装support-v4
![f13e97063840c2663bb53ae1ec03ed99.png](evernotecid://98A9354A-B35C-491A-9613-EBBBF75FC449/appyinxiangcom/1559383/ENResource/p1986)
![723ea5e7935ef5a5d856867dcf1ae3ed.png](evernotecid://98A9354A-B35C-491A-9613-EBBBF75FC449/appyinxiangcom/1559383/ENResource/p1988)
![24289b39095175108fba2e34854b86d4.png](evernotecid://98A9354A-B35C-491A-9613-EBBBF75FC449/appyinxiangcom/1559383/ENResource/p1987)

#### 日历展示页 ScheduleActivity.java

1. 翻页视图(ViewPager):每页1周，1年52周，左右滑动
2. 选项卡标题栏(PagerTabStrip):周数标题对应的选择卡
3. 碎片(Fragment):52周对应52个页面，每个页面都是一个Fragment
4. 碎片适配器(FragmentStatePagerAdapter):把52个Fragment组装到ViewPager中去
5. 列表视图(ListView):每页7天，每天1行，显示星期、日期、农历日期、节日（）、日程安排
6. 基本适配器(BaseAdapter):星期、公历日期、农历日期、节日节气、日程安排
7. 广播(Broadcast):判断当周是不是有节日，如果有节日广播节日背景图，无节日则广播默认图片，Fragment发送广播，Activity接受处理

#### 日程设置页 ScheduleDetailActivity.java

1. 时间选择对话框(TimeOickerDialog):时间选择对话框，选择日程时间
2. 定时器(AlarmManager):设置日程提醒

#### 适配器
日历网格适配器:adapter/CalendarGridAdapter.java
当周列表适配器:adapter/ScheduleListAdapter.java
周配器:adapter/SchedulePagerAdapter.java

#### 结构体
公历/农历日期:bean/CalendarTransfer.java
日程:bean/ScheduleArrange.java

#### 日历值
星期、日期、节日、背景图:calendar/Constant.java
农历:calendar/LunarCalendar.java
节气:calendar/SolarTerm.java
特殊日期:calendar/SpecialCalendar.java

#### 数据库
创建数据库:database/DbHelper.java
日历数据库:database/ScheduleArrangeHelper.java

#### 碎片
fragment/ScheduleFragment.java

#### 工具类
日期转换工具类:util/DateUtil.java


