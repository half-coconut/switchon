## GC分析

### 一. 打印GC日志

```java
appledeMacBook-Air-2:Desktop apple$ cd jvm
appledeMacBook-Air-2:jvm apple$ pwd
/Users/apple/Desktop/jvm
appledeMacBook-Air-2:jvm apple$ ls
GCLogAnalysis.class	GCLogAnalysis.java
appledeMacBook-Air-2:jvm apple$ java -Xmx2g -Xms2g -XX:+UseG1GC -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:gc.log  GCLogAnalysis
正在执行...
执行结束!共生成对象次数:3888
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 16380K->2542K(18944K)] 16380K->5644K(62976K), 0.0035146 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 18924K->2554K(35328K)] 22026K->11932K(79360K), 0.0059702 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 35032K->2553K(35328K)] 44410K->21149K(79360K), 0.0057599 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 35146K->2545K(68096K)] 53742K->33105K(112128K), 0.0092502 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
[Full GC (Ergonomics) [PSYoungGen: 2545K->0K(68096K)] [ParOldGen: 30559K->32505K(66560K)] 33105K->32505K(134656K), [Metaspace: 2705K->2705K(1056768K)], 0.0097521 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 65526K->2553K(68096K)] 98031K->55720K(134656K), 0.0118846 secs] [Times: user=0.02 sys=0.03, real=0.01 secs] 
[Full GC (Ergonomics) [PSYoungGen: 2553K->0K(68096K)] [ParOldGen: 53167K->53981K(102400K)] 55720K->53981K(170496K), [Metaspace: 2705K->2705K(1056768K)], 0.0102022 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 65393K->24555K(135680K)] 119375K->80221K(238080K), 0.0130524 secs] [Times: user=0.02 sys=0.02, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 135659K->30207K(141312K)] 191325K->117419K(243712K), 0.0464057 secs] [Times: user=0.04 sys=0.07, real=0.05 secs] 
[Full GC (Ergonomics) [PSYoungGen: 30207K->8005K(141312K)] [ParOldGen: 87211K->102186K(165376K)] 117419K->110192K(306688K), [Metaspace: 2705K->2705K(1056768K)], 0.0274445 secs] [Times: user=0.05 sys=0.01, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 119109K->43325K(238080K)] 221296K->145511K(403456K), 0.0941771 secs] [Times: user=0.02 sys=0.05, real=0.10 secs] 
[GC (Allocation Failure) [PSYoungGen: 229693K->57851K(244224K)] 331879K->201817K(409600K), 0.1756140 secs] [Times: user=0.08 sys=0.15, real=0.18 secs] 
[Full GC (Ergonomics) [PSYoungGen: 57851K->16243K(244224K)] [ParOldGen: 143965K->165260K(243200K)] 201817K->181504K(487424K), [Metaspace: 2705K->2705K(1056768K)], 0.2118920 secs] [Times: user=0.15 sys=0.19, real=0.21 secs] 
执行结束!共生成对象次数:3108
[GC (Allocation Failure) [PSYoungGen: 202611K->66406K(250368K)] 367872K->237693K(493568K), 0.0627120 secs] [Times: user=0.05 sys=0.06, real=0.06 secs] 
Heap
 PSYoungGen      total 250368K, used 69725K [0x00000007aab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 165888K, 2% used [0x00000007aab00000,0x00000007aae3d898,0x00000007b4d00000)
  from space 84480K, 78% used [0x00000007bad80000,0x00000007bee59be8,0x00000007c0000000)
  to   space 91648K, 0% used [0x00000007b4d00000,0x00000007b4d00000,0x00000007ba680000)
 ParOldGen       total 243200K, used 171286K [0x0000000780000000, 0x000000078ed80000, 0x00000007aab00000)
  object space 243200K, 70% used [0x0000000780000000,0x000000078a7459a0,0x000000078ed80000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

#### 设置1G内存

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -Xmx1g -Xms1g GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 262124K->43517K(305664K)] 262124K->79767K(1005056K), 0.2055356 secs] [Times: user=0.08 sys=0.15, real=0.20 secs] 
[GC (Allocation Failure) [PSYoungGen: 305661K->43514K(305664K)] 341911K->149174K(1005056K), 0.2160851 secs] [Times: user=0.07 sys=0.14, real=0.22 secs] 
执行结束!共生成对象次数:1944
Heap
 PSYoungGen      total 305664K, used 53941K [0x00000007aab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 262144K, 3% used [0x00000007aab00000,0x00000007ab52ee00,0x00000007bab00000)
  from space 43520K, 99% used [0x00000007bd580000,0x00000007bfffe870,0x00000007c0000000)
  to   space 43520K, 0% used [0x00000007bab00000,0x00000007bab00000,0x00000007bd580000)
 ParOldGen       total 699392K, used 105660K [0x0000000780000000, 0x00000007aab00000, 0x00000007aab00000)
  object space 699392K, 15% used [0x0000000780000000,0x000000078672f080,0x00000007aab00000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

#### 修改堆内存初始大小和最大堆内存(Xms-Xmx)为 512m 

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -Xmx512m -Xms512m GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 131584K->21483K(153088K)] 131584K->46156K(502784K), 0.0201829 secs] [Times: user=0.03 sys=0.05, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 153067K->21492K(153088K)] 177740K->94987K(502784K), 0.0323138 secs] [Times: user=0.04 sys=0.07, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 153076K->21493K(153088K)] 226571K->139780K(502784K), 0.0311914 secs] [Times: user=0.04 sys=0.05, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 153077K->21501K(153088K)] 271364K->180248K(502784K), 0.0243352 secs] [Times: user=0.04 sys=0.04, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 153085K->21495K(153088K)] 311832K->219483K(502784K), 0.0588971 secs] [Times: user=0.04 sys=0.04, real=0.06 secs] 
[GC (Allocation Failure) [PSYoungGen: 153079K->21495K(80384K)] 351067K->261464K(430080K), 0.0835509 secs] [Times: user=0.04 sys=0.04, real=0.08 secs] 
[GC (Allocation Failure) [PSYoungGen: 80375K->32676K(116736K)] 320344K->279686K(466432K), 0.0212696 secs] [Times: user=0.02 sys=0.02, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 91168K->43947K(116736K)] 338178K->295155K(466432K), 0.0178547 secs] [Times: user=0.03 sys=0.02, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 102827K->53129K(116736K)] 354035K->312768K(466432K), 0.0217301 secs] [Times: user=0.04 sys=0.02, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 112000K->38608K(116736K)] 371640K->329261K(466432K), 0.0374013 secs] [Times: user=0.05 sys=0.04, real=0.04 secs] 
[GC (Allocation Failure) [PSYoungGen: 96984K->20196K(116736K)] 387637K->347141K(466432K), 0.0372687 secs] [Times: user=0.04 sys=0.05, real=0.04 secs] 
[Full GC (Ergonomics) [PSYoungGen: 20196K->0K(116736K)] [ParOldGen: 326945K->232791K(349696K)] 347141K->232791K(466432K), [Metaspace: 2705K->2705K(1056768K)], 0.3035538 secs] [Times: user=0.20 sys=0.38, real=0.31 secs] 
执行结束!共生成对象次数:4107
Heap
 PSYoungGen      total 116736K, used 2942K [0x00000007b5580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 58880K, 4% used [0x00000007b5580000,0x00000007b585f978,0x00000007b8f00000)
  from space 57856K, 0% used [0x00000007b8f00000,0x00000007b8f00000,0x00000007bc780000)
  to   space 57856K, 0% used [0x00000007bc780000,0x00000007bc780000,0x00000007c0000000)
 ParOldGen       total 349696K, used 232791K [0x00000007a0000000, 0x00000007b5580000, 0x00000007b5580000)
  object space 349696K, 66% used [0x00000007a0000000,0x00000007ae355ff8,0x00000007b5580000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

#### 加时间戳

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx512m -Xms512m GCLogAnalysis
正在执行...
2022-03-13T16:06:08.133-0800: [GC (Allocation Failure) [PSYoungGen: 131584K->21494K(153088K)] 131584K->47534K(502784K), 0.0996446 secs] [Times: user=0.04 sys=0.06, real=0.10 secs] 
2022-03-13T16:06:08.303-0800: [GC (Allocation Failure) [PSYoungGen: 153078K->21481K(153088K)] 179118K->94399K(502784K), 0.0797099 secs] [Times: user=0.05 sys=0.08, real=0.08 secs] 
2022-03-13T16:06:08.430-0800: [GC (Allocation Failure) [PSYoungGen: 152555K->21495K(153088K)] 225472K->134529K(502784K), 0.0812871 secs] [Times: user=0.03 sys=0.08, real=0.08 secs] 
2022-03-13T16:06:08.548-0800: [GC (Allocation Failure) [PSYoungGen: 153079K->21500K(153088K)] 266113K->177582K(502784K), 0.0445565 secs] [Times: user=0.04 sys=0.05, real=0.05 secs] 
2022-03-13T16:06:08.627-0800: [GC (Allocation Failure) [PSYoungGen: 153084K->21501K(153088K)] 309166K->219425K(502784K), 0.0334478 secs] [Times: user=0.04 sys=0.04, real=0.04 secs] 
2022-03-13T16:06:08.694-0800: [GC (Allocation Failure) [PSYoungGen: 153085K->21498K(80384K)] 351009K->260843K(430080K), 0.0382530 secs] [Times: user=0.03 sys=0.04, real=0.04 secs] 
2022-03-13T16:06:08.753-0800: [GC (Allocation Failure) [PSYoungGen: 80378K->34497K(116736K)] 319723K->280416K(466432K), 0.0114790 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T16:06:08.782-0800: [GC (Allocation Failure) [PSYoungGen: 93298K->51348K(116736K)] 339217K->302985K(466432K), 0.0135815 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2022-03-13T16:06:08.810-0800: [GC (Allocation Failure) [PSYoungGen: 110228K->57842K(116736K)] 361865K->321299K(466432K), 0.0190807 secs] [Times: user=0.03 sys=0.02, real=0.01 secs] 
执行结束!共生成对象次数:3683
Heap
 PSYoungGen      total 116736K, used 60477K [0x00000007b5580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 58880K, 4% used [0x00000007b5580000,0x00000007b5812a40,0x00000007b8f00000)
  from space 57856K, 99% used [0x00000007b8f00000,0x00000007bc77ca00,0x00000007bc780000)
  to   space 57856K, 0% used [0x00000007bc780000,0x00000007bc780000,0x00000007c0000000)
 ParOldGen       total 349696K, used 263457K [0x00000007a0000000, 0x00000007b5580000, 0x00000007b5580000)
  object space 349696K, 75% used [0x00000007a0000000,0x00000007b01484c0,0x00000007b5580000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

#### GC日志的含义

```java
// GC使用情况：时间、GC发生的原因（分配内存失败导致的），GC暂停时间是 0.0996446s，young区的大小从
// 131m到21m，整个young区的容量是 153088K；整个堆内存的情况：502784K，从131m压缩到47m
// 同时，young区减少了131-21=110m，整个堆内存 131-47=84m，110-84=26m，其余这26m去了old区。
2022-03-13T16:06:08.133-0800: [GC (Allocation Failure) [PSYoungGen: 131584K->21494K(153088K)] 131584K->47534K(502784K), 0.0996446 secs] 
// CPU使用情况
[Times: user=0.04 sys=0.06, real=0.10 secs] 

// FullGC young区从20m直接到0k，old区从326m到232m
// 整个full GC的时间是 0.3035538s
// meta区不变
[Full GC (Ergonomics) [PSYoungGen: 20196K->0K(116736K)] [ParOldGen: 326945K->232791K(349696K)] 347141K->232791K(466432K), [Metaspace: 2705K->2705K(1056768K)], 0.3035538 secs] [Times: user=0.20 sys=0.38, real=0.31 secs] 
```

#### 设置为256m时，发生多次full GC

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx256m -Xms256m GCLogAnalysis
正在执行...
2022-03-13T16:41:54.615-0800: [GC (Allocation Failure) [PSYoungGen: 65536K->10750K(76288K)] 65536K->21453K(251392K), 0.0461903 secs] [Times: user=0.02 sys=0.02, real=0.05 secs] 
2022-03-13T16:41:54.689-0800: [GC (Allocation Failure) [PSYoungGen: 76237K->10741K(76288K)] 86940K->41240K(251392K), 0.0406281 secs] [Times: user=0.02 sys=0.03, real=0.04 secs] 
2022-03-13T16:41:54.747-0800: [GC (Allocation Failure) [PSYoungGen: 76119K->10736K(76288K)] 106617K->60175K(251392K), 0.0298216 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2022-03-13T16:41:54.794-0800: [GC (Allocation Failure) [PSYoungGen: 76272K->10750K(76288K)] 125711K->81000K(251392K), 0.0296439 secs] [Times: user=0.02 sys=0.01, real=0.03 secs] 
2022-03-13T16:41:54.841-0800: [GC (Allocation Failure) [PSYoungGen: 76286K->10749K(76288K)] 146536K->105269K(251392K), 0.0460682 secs] [Times: user=0.02 sys=0.03, real=0.04 secs] 
2022-03-13T16:41:54.904-0800: [GC (Allocation Failure) [PSYoungGen: 76171K->10743K(40448K)] 170690K->122605K(215552K), 0.0128114 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T16:41:54.924-0800: [GC (Allocation Failure) [PSYoungGen: 40364K->20698K(58368K)] 152226K->135425K(233472K), 0.0124544 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T16:41:54.945-0800: [GC (Allocation Failure) [PSYoungGen: 50176K->26759K(58368K)] 164904K->143241K(233472K), 0.0059559 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T16:41:54.960-0800: [GC (Allocation Failure) [PSYoungGen: 56402K->28530K(58368K)] 172883K->150199K(233472K), 0.0080139 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2022-03-13T16:41:54.977-0800: [GC (Allocation Failure) [PSYoungGen: 58019K->20689K(58368K)] 179687K->160239K(233472K), 0.0180356 secs] [Times: user=0.02 sys=0.02, real=0.02 secs] 
2022-03-13T16:41:55.002-0800: [GC (Allocation Failure) [PSYoungGen: 50286K->10198K(58368K)] 189836K->168786K(233472K), 0.0393767 secs] [Times: user=0.02 sys=0.03, real=0.04 secs] 
2022-03-13T16:41:55.042-0800: [Full GC (Ergonomics) [PSYoungGen: 10198K->0K(58368K)] [ParOldGen: 158587K->140157K(175104K)] 168786K->140157K(233472K), [Metaspace: 2705K->2705K(1056768K)], 0.1402045 secs] [Times: user=0.10 sys=0.11, real=0.14 secs] 
2022-03-13T16:41:55.190-0800: [GC (Allocation Failure) [PSYoungGen: 29696K->10295K(58368K)] 169853K->150452K(233472K), 0.0031519 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T16:41:55.199-0800: [GC (Allocation Failure) [PSYoungGen: 39780K->10232K(58368K)] 179937K->160463K(233472K), 0.0042404 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T16:41:55.204-0800: [Full GC (Ergonomics) [PSYoungGen: 10232K->0K(58368K)] [ParOldGen: 150230K->153707K(175104K)] 160463K->153707K(233472K), [Metaspace: 2705K->2705K(1056768K)], 0.0264370 secs] [Times: user=0.07 sys=0.01, real=0.03 secs] 
2022-03-13T16:41:55.238-0800: [Full GC (Ergonomics) [PSYoungGen: 29643K->0K(58368K)] [ParOldGen: 153707K->159944K(175104K)] 183351K->159944K(233472K), [Metaspace: 2705K->2705K(1056768K)], 0.0436179 secs] [Times: user=0.10 sys=0.01, real=0.05 secs] 
2022-03-13T16:41:55.288-0800: [Full GC (Ergonomics) [PSYoungGen: 29696K->0K(58368K)] [ParOldGen: 159944K->166181K(175104K)] 189640K->166181K(233472K), [Metaspace: 2705K->2705K(1056768K)], 0.0309380 secs] [Times: user=0.06 sys=0.01, real=0.03 secs] 
2022-03-13T16:41:55.328-0800: [Full GC (Ergonomics) [PSYoungGen: 29634K->0K(58368K)] [ParOldGen: 166181K->168843K(175104K)] 195815K->168843K(233472K), [Metaspace: 2705K->2705K(1056768K)], 0.0308929 secs] [Times: user=0.10 sys=0.00, real=0.03 secs] 
2022-03-13T16:41:55.367-0800: [Full GC (Ergonomics) [PSYoungGen: 29696K->710K(58368K)] [ParOldGen: 168843K->174813K(175104K)] 198539K->175523K(233472K), [Metaspace: 2705K->2705K(1056768K)], 0.0369162 secs] [Times: user=0.07 sys=0.01, real=0.04 secs] 
2022-03-13T16:41:55.410-0800: [Full GC (Ergonomics) [PSYoungGen: 29576K->9683K(58368K)] [ParOldGen: 174813K->174252K(175104K)] 204390K->183936K(233472K), [Metaspace: 2705K->2705K(1056768K)], 0.0331977 secs] [Times: user=0.08 sys=0.00, real=0.03 secs] 
2022-03-13T16:41:55.448-0800: [Full GC (Ergonomics) [PSYoungGen: 29633K->11448K(58368K)] [ParOldGen: 174252K->174393K(175104K)] 203885K->185841K(233472K), [Metaspace: 2705K->2705K(1056768K)], 0.0304334 secs] [Times: user=0.10 sys=0.00, real=0.03 secs] 
执行结束!共生成对象次数:2968
Heap
 PSYoungGen      total 58368K, used 26014K [0x00000007bab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 29696K, 87% used [0x00000007bab00000,0x00000007bc467a10,0x00000007bc800000)
  from space 28672K, 0% used [0x00000007bc800000,0x00000007bc800000,0x00000007be400000)
  to   space 28672K, 0% used [0x00000007be400000,0x00000007be400000,0x00000007c0000000)
 ParOldGen       total 175104K, used 174393K [0x00000007b0000000, 0x00000007bab00000, 0x00000007bab00000)
  object space 175104K, 99% used [0x00000007b0000000,0x00000007baa4e400,0x00000007bab00000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```



#### 设置128m时，发生多次full GC后，报堆内存溢出

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx128m -Xms128m GCLogAnalysis
正在执行...
2022-03-13T16:44:09.885-0800: [GC (Allocation Failure) [PSYoungGen: 33280K->5117K(38400K)] 33280K->11735K(125952K), 0.0076451 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2022-03-13T16:44:09.907-0800: [GC (Allocation Failure) [PSYoungGen: 38256K->5101K(38400K)] 44874K->22404K(125952K), 0.0115196 secs] [Times: user=0.01 sys=0.02, real=0.01 secs] 
2022-03-13T16:44:09.933-0800: [GC (Allocation Failure) [PSYoungGen: 38381K->5109K(38400K)] 55684K->34969K(125952K), 0.0135516 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2022-03-13T16:44:09.957-0800: [GC (Allocation Failure) [PSYoungGen: 38389K->5103K(38400K)] 68249K->47765K(125952K), 0.0075027 secs] [Times: user=0.01 sys=0.02, real=0.01 secs] 
2022-03-13T16:44:09.971-0800: [GC (Allocation Failure) [PSYoungGen: 38383K->5119K(38400K)] 81045K->57828K(125952K), 0.0069922 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T16:44:09.985-0800: [GC (Allocation Failure) [PSYoungGen: 38318K->5105K(19968K)] 91027K->71197K(107520K), 0.0078493 secs] [Times: user=0.01 sys=0.02, real=0.01 secs] 
2022-03-13T16:44:09.996-0800: [GC (Allocation Failure) [PSYoungGen: 19720K->7766K(29184K)] 85813K->75586K(116736K), 0.0028499 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T16:44:10.004-0800: [GC (Allocation Failure) [PSYoungGen: 22598K->12165K(29184K)] 90418K->81477K(116736K), 0.0037891 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T16:44:10.011-0800: [GC (Allocation Failure) [PSYoungGen: 26966K->14320K(29184K)] 96279K->86656K(116736K), 0.0043599 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2022-03-13T16:44:10.015-0800: [Full GC (Ergonomics) [PSYoungGen: 14320K->0K(29184K)] [ParOldGen: 72336K->78327K(87552K)] 86656K->78327K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0185156 secs] [Times: user=0.04 sys=0.01, real=0.02 secs] 
2022-03-13T16:44:10.036-0800: [Full GC (Ergonomics) [PSYoungGen: 14801K->0K(29184K)] [ParOldGen: 78327K->82287K(87552K)] 93129K->82287K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0137114 secs] [Times: user=0.04 sys=0.01, real=0.02 secs] 
2022-03-13T16:44:10.054-0800: [Full GC (Ergonomics) [PSYoungGen: 14654K->738K(29184K)] [ParOldGen: 82287K->87512K(87552K)] 96941K->88251K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0179913 secs] [Times: user=0.04 sys=0.01, real=0.02 secs] 
2022-03-13T16:44:10.076-0800: [Full GC (Ergonomics) [PSYoungGen: 14513K->3647K(29184K)] [ParOldGen: 87512K->87162K(87552K)] 102026K->90810K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0183417 secs] [Times: user=0.06 sys=0.00, real=0.02 secs] 
2022-03-13T16:44:10.097-0800: [Full GC (Ergonomics) [PSYoungGen: 14714K->6231K(29184K)] [ParOldGen: 87162K->86832K(87552K)] 101877K->93063K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0176565 secs] [Times: user=0.06 sys=0.00, real=0.02 secs] 
2022-03-13T16:44:10.117-0800: [Full GC (Ergonomics) [PSYoungGen: 14658K->6898K(29184K)] [ParOldGen: 86832K->87519K(87552K)] 101490K->94417K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0131898 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2022-03-13T16:44:10.132-0800: [Full GC (Ergonomics) [PSYoungGen: 14848K->9975K(29184K)] [ParOldGen: 87519K->87434K(87552K)] 102367K->97409K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0168821 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2022-03-13T16:44:10.151-0800: [Full GC (Ergonomics) [PSYoungGen: 14516K->10890K(29184K)] [ParOldGen: 87434K->87418K(87552K)] 101950K->98308K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0166995 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2022-03-13T16:44:10.169-0800: [Full GC (Ergonomics) [PSYoungGen: 14800K->12865K(29184K)] [ParOldGen: 87418K->87084K(87552K)] 102218K->99949K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0091435 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T16:44:10.178-0800: [Full GC (Ergonomics) [PSYoungGen: 14807K->12713K(29184K)] [ParOldGen: 87084K->87482K(87552K)] 101891K->100196K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0161508 secs] [Times: user=0.05 sys=0.01, real=0.02 secs] 
2022-03-13T16:44:10.195-0800: [Full GC (Ergonomics) [PSYoungGen: 14768K->13806K(29184K)] [ParOldGen: 87482K->87315K(87552K)] 102251K->101121K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0114895 secs] [Times: user=0.04 sys=0.00, real=0.01 secs] 
2022-03-13T16:44:10.207-0800: [Full GC (Ergonomics) [PSYoungGen: 14832K->13985K(29184K)] [ParOldGen: 87315K->87315K(87552K)] 102148K->101301K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0040132 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T16:44:10.211-0800: [Full GC (Ergonomics) [PSYoungGen: 14564K->13985K(29184K)] [ParOldGen: 87315K->87315K(87552K)] 101879K->101301K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0037128 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T16:44:10.216-0800: [Full GC (Ergonomics) [PSYoungGen: 14748K->14748K(29184K)] [ParOldGen: 87315K->87315K(87552K)] 102063K->102063K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0033973 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T16:44:10.219-0800: [Full GC (Ergonomics) [PSYoungGen: 14748K->14748K(29184K)] [ParOldGen: 87476K->87315K(87552K)] 102224K->102063K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0026610 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2022-03-13T16:44:10.222-0800: [Full GC (Allocation Failure) [PSYoungGen: 14748K->14748K(29184K)] [ParOldGen: 87315K->87296K(87552K)] 102063K->102044K(116736K), [Metaspace: 2705K->2705K(1056768K)], 0.0144593 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:48)
	at GCLogAnalysis.main(GCLogAnalysis.java:25)
Heap
 PSYoungGen      total 29184K, used 14848K [0x00000007bd580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 14848K, 100% used [0x00000007bd580000,0x00000007be400000,0x00000007be400000)
  from space 14336K, 0% used [0x00000007be400000,0x00000007be400000,0x00000007bf200000)
  to   space 14336K, 0% used [0x00000007bf200000,0x00000007bf200000,0x00000007c0000000)
 ParOldGen       total 87552K, used 87296K [0x00000007b8000000, 0x00000007bd580000, 0x00000007bd580000)
  object space 87552K, 99% used [0x00000007b8000000,0x00000007bd540130,0x00000007bd580000)
 Metaspace       used 2735K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

### 二. GC日志解读

young GC

Full GC = young GC+old GC

minor GC

major GC

### 三. 串行 / 并行 /CMS/G1 GC

#### 3.1 串行GC

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx512m -Xms512m -XX:+UseSerialGC GCLogAnalysis
正在执行...
2022-03-13T16:53:03.653-0800: [GC (Allocation Failure) 2022-03-13T16:53:03.654-0800: [DefNew: 139776K->17471K(157248K), 0.1485857 secs] 139776K->47011K(506816K), 0.1571632 secs] [Times: user=0.03 sys=0.05, real=0.16 secs] 
2022-03-13T16:53:03.974-0800: [GC (Allocation Failure) 2022-03-13T16:53:03.974-0800: [DefNew: 157247K->17470K(157248K), 0.1048787 secs] 186787K->90087K(506816K), 0.1049510 secs] [Times: user=0.03 sys=0.04, real=0.10 secs] 
2022-03-13T16:53:04.115-0800: [GC (Allocation Failure) 2022-03-13T16:53:04.115-0800: [DefNew: 157246K->17471K(157248K), 0.0574879 secs] 229863K->131896K(506816K), 0.0575463 secs] [Times: user=0.03 sys=0.03, real=0.06 secs] 
2022-03-13T16:53:04.214-0800: [GC (Allocation Failure) 2022-03-13T16:53:04.215-0800: [DefNew: 157247K->17471K(157248K), 0.0493401 secs] 271672K->174598K(506816K), 0.0496707 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
2022-03-13T16:53:04.299-0800: [GC (Allocation Failure) 2022-03-13T16:53:04.299-0800: [DefNew: 157247K->17469K(157248K), 0.0525009 secs] 314374K->222303K(506816K), 0.0526184 secs] [Times: user=0.02 sys=0.02, real=0.06 secs] 
执行结束!共生成对象次数:2699
Heap
 def new generation   total 157248K, used 40784K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,  16% used [0x00000007a0000000, 0x00000007a16c4b30, 0x00000007a8880000)
  from space 17472K,  99% used [0x00000007a9990000, 0x00000007aaa9f6d8, 0x00000007aaaa0000)
  to   space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
 tenured generation   total 349568K, used 204833K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 349568K,  58% used [0x00000007aaaa0000, 0x00000007b72a8558, 0x00000007b72a8600, 0x00000007c0000000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

#### 3.2 并行GC

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx512m -Xms512m -XX:+UseParallelGC GCLogAnalysis
正在执行...
2022-03-13T16:59:01.764-0800: [GC (Allocation Failure) [PSYoungGen: 131584K->21492K(153088K)] 131584K->48989K(502784K), 0.0213613 secs] [Times: user=0.03 sys=0.05, real=0.02 secs] 
2022-03-13T16:59:01.814-0800: [GC (Allocation Failure) [PSYoungGen: 153076K->21496K(153088K)] 180573K->89205K(502784K), 0.0797267 secs] [Times: user=0.04 sys=0.06, real=0.08 secs] 
2022-03-13T16:59:01.923-0800: [GC (Allocation Failure) [PSYoungGen: 153080K->21491K(153088K)] 220789K->129162K(502784K), 0.0979438 secs] [Times: user=0.04 sys=0.04, real=0.10 secs] 
2022-03-13T16:59:02.052-0800: [GC (Allocation Failure) [PSYoungGen: 153075K->21498K(153088K)] 260746K->168098K(502784K), 0.0659490 secs] [Times: user=0.03 sys=0.04, real=0.06 secs] 
2022-03-13T16:59:02.151-0800: [GC (Allocation Failure) [PSYoungGen: 153082K->21489K(153088K)] 299682K->206905K(502784K), 0.0406624 secs] [Times: user=0.05 sys=0.04, real=0.04 secs] 
2022-03-13T16:59:02.230-0800: [GC (Allocation Failure) [PSYoungGen: 153073K->21492K(80384K)] 338489K->251560K(430080K), 0.0477355 secs] [Times: user=0.06 sys=0.05, real=0.04 secs] 
2022-03-13T16:59:02.298-0800: [GC (Allocation Failure) [PSYoungGen: 80239K->36106K(116736K)] 310307K->272784K(466432K), 0.0117031 secs] [Times: user=0.02 sys=0.01, real=0.02 secs] 
2022-03-13T16:59:02.323-0800: [GC (Allocation Failure) [PSYoungGen: 94986K->47681K(116736K)] 331664K->288716K(466432K), 0.0139316 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2022-03-13T16:59:02.350-0800: [GC (Allocation Failure) [PSYoungGen: 106334K->57727K(116736K)] 347368K->307250K(466432K), 0.0172837 secs] [Times: user=0.04 sys=0.01, real=0.01 secs] 
2022-03-13T16:59:02.382-0800: [GC (Allocation Failure) [PSYoungGen: 116295K->39292K(116736K)] 365817K->322751K(466432K), 0.0359071 secs] [Times: user=0.04 sys=0.04, real=0.03 secs] 
2022-03-13T16:59:02.437-0800: [GC (Allocation Failure) [PSYoungGen: 98172K->20714K(116736K)] 381631K->339324K(466432K), 0.0351751 secs] [Times: user=0.03 sys=0.04, real=0.04 secs] 
2022-03-13T16:59:02.472-0800: [Full GC (Ergonomics) [PSYoungGen: 20714K->0K(116736K)] [ParOldGen: 318610K->239402K(349696K)] 339324K->239402K(466432K), [Metaspace: 2705K->2705K(1056768K)], 0.5397990 secs] [Times: user=0.22 sys=0.46, real=0.54 secs] 
执行结束!共生成对象次数:4103
Heap
 PSYoungGen      total 116736K, used 2496K [0x00000007b5580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 58880K, 4% used [0x00000007b5580000,0x00000007b57f02b8,0x00000007b8f00000)
  from space 57856K, 0% used [0x00000007b8f00000,0x00000007b8f00000,0x00000007bc780000)
  to   space 57856K, 0% used [0x00000007bc780000,0x00000007bc780000,0x00000007c0000000)
 ParOldGen       total 349696K, used 239402K [0x00000007a0000000, 0x00000007b5580000, 0x00000007b5580000)
  object space 349696K, 68% used [0x00000007a0000000,0x00000007ae9ca8f0,0x00000007b5580000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

##### 3.2.1 分别增加内存4g，2g

```java
// 4g内存下，未发生GC
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx4g -Xms4g -XX:+UseParallelGC GCLogAnalysis
正在执行...
执行结束!共生成对象次数:2634
Heap
 PSYoungGen      total 1223168K, used 766042K [0x000000076ab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 1048576K, 73% used [0x000000076ab00000,0x0000000799716988,0x00000007aab00000)
  from space 174592K, 0% used [0x00000007b5580000,0x00000007b5580000,0x00000007c0000000)
  to   space 174592K, 0% used [0x00000007aab00000,0x00000007aab00000,0x00000007b5580000)
 ParOldGen       total 2796544K, used 0K [0x00000006c0000000, 0x000000076ab00000, 0x000000076ab00000)
  object space 2796544K, 0% used [0x00000006c0000000,0x00000006c0000000,0x000000076ab00000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K

// 2g内存下 发生一次 young GC
// 0.35s，young GC发生的时间 非常长，因为一次回收的量比较大 从524m到 87m，比堆内存小的时候，时间长了2-3倍
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx2g -Xms2g -XX:+UseParallelGC GCLogAnalysis
正在执行...
2022-03-13T17:02:30.221-0800: [GC (Allocation Failure) [PSYoungGen: 524800K->87025K(611840K)] 524800K->141651K(2010112K), 0.3484898 secs] [Times: user=0.14 sys=0.33, real=0.35 secs] 
执行结束!共生成对象次数:1974
Heap
 PSYoungGen      total 611840K, used 102772K [0x0000000795580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 524800K, 3% used [0x0000000795580000,0x00000007964e0a70,0x00000007b5600000)
  from space 87040K, 99% used [0x00000007b5600000,0x00000007baafc6c8,0x00000007bab00000)
  to   space 87040K, 0% used [0x00000007bab00000,0x00000007bab00000,0x00000007c0000000)
 ParOldGen       total 1398272K, used 54625K [0x0000000740000000, 0x0000000795580000, 0x0000000795580000)
  object space 1398272K, 3% used [0x0000000740000000,0x0000000743558738,0x0000000795580000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

##### 3.2.2 不配置 Xms，明显GC次数 和 full GC次数 多了

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx2g -XX:+UseParallelGC GCLogAnalysis
正在执行...
2022-03-13T17:09:03.479-0800: [GC (Allocation Failure) [PSYoungGen: 16256K->2534K(18944K)] 16256K->6048K(62976K), 0.0209540 secs] [Times: user=0.02 sys=0.00, real=0.03 secs] 
2022-03-13T17:09:03.515-0800: [GC (Allocation Failure) [PSYoungGen: 18876K->2558K(35328K)] 22390K->11030K(79360K), 0.0065605 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:09:03.554-0800: [GC (Allocation Failure) [PSYoungGen: 35326K->2539K(35328K)] 43798K->23731K(79360K), 0.0129203 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2022-03-13T17:09:03.580-0800: [GC (Allocation Failure) [PSYoungGen: 35304K->2559K(68096K)] 56496K->34389K(112128K), 0.0143635 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:09:03.594-0800: [Full GC (Ergonomics) [PSYoungGen: 2559K->0K(68096K)] [ParOldGen: 31830K->34325K(67584K)] 34389K->34325K(135680K), [Metaspace: 2705K->2705K(1056768K)], 0.0147370 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:09:03.650-0800: [GC (Allocation Failure) [PSYoungGen: 65375K->2554K(68096K)] 99700K->54936K(135680K), 0.0151617 secs] [Times: user=0.01 sys=0.02, real=0.01 secs] 
2022-03-13T17:09:03.665-0800: [Full GC (Ergonomics) [PSYoungGen: 2554K->0K(68096K)] [ParOldGen: 52382K->53306K(97792K)] 54936K->53306K(165888K), [Metaspace: 2705K->2705K(1056768K)], 0.0136782 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2022-03-13T17:09:03.698-0800: [GC (Allocation Failure) [PSYoungGen: 65536K->21922K(136704K)] 118842K->75229K(234496K), 0.0338724 secs] [Times: user=0.01 sys=0.02, real=0.04 secs] 
2022-03-13T17:09:03.837-0800: [GC (Allocation Failure) [PSYoungGen: 136610K->26616K(143360K)] 189917K->115368K(241152K), 0.1052290 secs] [Times: user=0.04 sys=0.08, real=0.11 secs] 
2022-03-13T17:09:03.943-0800: [Full GC (Ergonomics) [PSYoungGen: 26616K->8660K(143360K)] [ParOldGen: 88752K->97519K(157696K)] 115368K->106179K(301056K), [Metaspace: 2705K->2705K(1056768K)], 0.1212521 secs] [Times: user=0.09 sys=0.10, real=0.12 secs] 
2022-03-13T17:09:04.192-0800: [GC (Allocation Failure) [PSYoungGen: 125396K->47101K(242176K)] 222915K->144620K(399872K), 0.0801654 secs] [Times: user=0.03 sys=0.04, real=0.08 secs] 
2022-03-13T17:09:04.355-0800: [GC (Allocation Failure) [PSYoungGen: 240125K->57336K(250368K)] 337644K->198286K(408064K), 0.1395153 secs] [Times: user=0.06 sys=0.08, real=0.14 secs] 
2022-03-13T17:09:04.495-0800: [Full GC (Ergonomics) [PSYoungGen: 57336K->16189K(250368K)] [ParOldGen: 140949K->157209K(232960K)] 198286K->173399K(483328K), [Metaspace: 2705K->2705K(1056768K)], 0.0905740 secs] [Times: user=0.08 sys=0.05, real=0.09 secs] 
执行结束!共生成对象次数:2451
Heap
 PSYoungGen      total 250368K, used 23936K [0x0000000795580000, 0x00000007b0680000, 0x00000007c0000000)
  eden space 193024K, 4% used [0x0000000795580000,0x0000000795d10db8,0x00000007a1200000)
  from space 57344K, 28% used [0x00000007a1200000,0x00000007a21cf4a0,0x00000007a4a00000)
  to   space 84480K, 0% used [0x00000007ab400000,0x00000007ab400000,0x00000007b0680000)
 ParOldGen       total 232960K, used 157209K [0x0000000740000000, 0x000000074e380000, 0x0000000795580000)
  object space 232960K, 67% used [0x0000000740000000,0x00000007499867d0,0x000000074e380000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

#### 3.3 CMS GC

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx2g -XX:+UseConcMarkSweepGC GCLogAnalysis
正在执行...
2022-03-13T17:12:43.613-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.613-0800: [ParNew: 17472K->2173K(19648K), 0.0045421 secs] 17472K->6004K(63360K), 0.0046383 secs] [Times: user=0.02 sys=0.01, real=0.00 secs] 
2022-03-13T17:12:43.632-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.632-0800: [ParNew: 19478K->2166K(19648K), 0.0054626 secs] 23309K->11441K(63360K), 0.0055403 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
2022-03-13T17:12:43.642-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.642-0800: [ParNew: 19502K->2156K(19648K), 0.0068646 secs] 28777K->19156K(63360K), 0.0069194 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:43.658-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.658-0800: [ParNew: 19363K->2174K(19648K), 0.0047966 secs] 36364K->24380K(63360K), 0.0048531 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:43.664-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 22205K(43712K)] 25014K(63360K), 0.0002597 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:43.664-0800: [CMS-concurrent-mark-start]
2022-03-13T17:12:43.694-0800: [CMS-concurrent-mark: 0.030/0.030 secs] [Times: user=0.04 sys=0.01, real=0.03 secs] 
2022-03-13T17:12:43.694-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.694-0800: [ParNew: 19592K->2168K(19648K), 0.0055191 secs] 41797K->30074K(63360K), 0.0055725 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.700-0800: [CMS-concurrent-preclean-start]
2022-03-13T17:12:43.700-0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:43.701-0800: [CMS-concurrent-abortable-preclean-start]
2022-03-13T17:12:43.704-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.704-0800: [ParNew: 19640K->2173K(19648K), 0.0075371 secs] 47546K->38676K(63360K), 0.0076022 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.716-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.716-0800: [ParNew: 19532K->2168K(19648K), 0.0048441 secs] 56036K->44222K(63360K), 0.0049080 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:43.725-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.725-0800: [ParNew: 19630K->2170K(19648K), 0.0058911 secs] 61684K->50334K(68236K), 0.0059501 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.735-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.735-0800: [ParNew: 19168K->2174K(19648K), 0.0054272 secs] 67332K->55262K(73196K), 0.0054775 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.744-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.744-0800: [ParNew: 19408K->2166K(19648K), 0.0045790 secs] 72496K->59774K(77816K), 0.0046467 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
2022-03-13T17:12:43.754-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.754-0800: [ParNew: 19638K->2175K(19648K), 0.0065078 secs] 77246K->65972K(84028K), 0.0065719 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.764-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.764-0800: [ParNew: 19647K->2153K(19648K), 0.0060656 secs] 83444K->71533K(89604K), 0.0061237 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.774-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.774-0800: [ParNew: 19625K->2166K(19648K), 0.0067262 secs] 89005K->77861K(95868K), 0.0067891 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:43.786-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.786-0800: [ParNew: 19438K->2146K(19648K), 0.0078753 secs] 95133K->84520K(102608K), 0.0079331 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.798-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.798-0800: [ParNew: 19563K->2174K(19648K), 0.0064005 secs] 101937K->91236K(109212K), 0.0064651 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:43.808-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.808-0800: [ParNew: 19538K->2172K(19648K), 0.0079165 secs] 108600K->99191K(117140K), 0.0079902 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.820-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.820-0800: [ParNew: 19332K->2173K(19648K), 0.0076811 secs] 116350K->105707K(123632K), 0.0077575 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:43.831-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.831-0800: [ParNew: 19287K->2172K(19648K), 0.0091649 secs] 122821K->114143K(132144K), 0.0092321 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:43.844-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.844-0800: [ParNew: 19369K->2172K(19648K), 0.0055775 secs] 131340K->120047K(138132K), 0.0056448 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:43.853-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.853-0800: [ParNew: 19404K->2151K(19648K), 0.0059127 secs] 137278K->125548K(143684K), 0.0059940 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
2022-03-13T17:12:43.865-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.865-0800: [ParNew: 19280K->2161K(19648K), 0.0051063 secs] 142677K->129762K(147792K), 0.0051690 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.874-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.874-0800: [ParNew: 19633K->2167K(19648K), 0.0046845 secs] 147234K->134040K(152028K), 0.0047464 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:43.883-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.883-0800: [ParNew: 19555K->2174K(19648K), 0.0085492 secs] 151429K->141947K(160124K), 0.0086094 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:43.896-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.896-0800: [ParNew: 19549K->2174K(19648K), 0.0060414 secs] 159321K->148264K(166332K), 0.0061008 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.906-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.906-0800: [ParNew: 18996K->2173K(19648K), 0.0051169 secs] 165086K->152869K(171024K), 0.0051830 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:43.916-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.916-0800: [ParNew: 19466K->2164K(19648K), 0.0069637 secs] 170163K->158876K(177000K), 0.0070253 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.927-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.927-0800: [ParNew: 19488K->2158K(19648K), 0.0075700 secs] 176201K->166736K(184876K), 0.0076357 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:43.938-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.938-0800: [ParNew: 19612K->2174K(19648K), 0.0050874 secs] 184190K->171466K(189492K), 0.0051507 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.947-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.948-0800: [ParNew: 19584K->2175K(19648K), 0.0085339 secs] 188875K->179441K(197480K), 0.0086042 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:43.961-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.961-0800: [ParNew: 19647K->2151K(19648K), 0.0065608 secs] 196913K->185513K(203632K), 0.0066450 secs] [Times: user=0.02 sys=0.01, real=0.00 secs] 
2022-03-13T17:12:43.972-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.972-0800: [ParNew: 19554K->2169K(19648K), 0.0067361 secs] 202916K->191842K(210032K), 0.0067986 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:43.983-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.983-0800: [ParNew: 19559K->2157K(19648K), 0.0070908 secs] 209232K->198215K(216356K), 0.0071498 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:43.995-0800: [GC (Allocation Failure) 2022-03-13T17:12:43.995-0800: [ParNew: 19106K->2162K(19648K), 0.0054577 secs] 215164K->202899K(221024K), 0.0055146 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.004-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.004-0800: [ParNew: 19296K->2158K(19648K), 0.0076992 secs] 220033K->209868K(228000K), 0.0077579 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.016-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.016-0800: [ParNew: 19188K->2173K(19648K), 0.0068614 secs] 226897K->213682K(231816K), 0.0069305 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:44.029-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.029-0800: [ParNew: 19554K->2175K(19648K), 0.0056539 secs] 231063K->218883K(237116K), 0.0057149 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.039-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.039-0800: [ParNew: 19624K->2162K(19648K), 0.0072349 secs] 236332K->223678K(241852K), 0.0073016 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.054-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.054-0800: [ParNew: 19502K->2167K(19648K), 0.0086871 secs] 241019K->230311K(248544K), 0.0087499 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:44.067-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.067-0800: [ParNew: 19593K->2152K(19648K), 0.0092529 secs] 247737K->236953K(255232K), 0.0093343 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.082-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.082-0800: [ParNew: 19624K->2163K(19648K), 0.0061703 secs] 254425K->241654K(259900K), 0.0062404 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
2022-03-13T17:12:44.095-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.095-0800: [ParNew: 19594K->2174K(19648K), 0.0090124 secs] 259085K->246108K(264320K), 0.0092400 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.109-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.109-0800: [ParNew: 19644K->2142K(19648K), 0.0178685 secs] 263578K->251871K(270100K), 0.0179358 secs] [Times: user=0.04 sys=0.01, real=0.02 secs] 
2022-03-13T17:12:44.132-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.132-0800: [ParNew: 18947K->2175K(19648K), 0.0175809 secs] 268676K->259567K(277820K), 0.0180126 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2022-03-13T17:12:44.157-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.157-0800: [ParNew: 19440K->2175K(19648K), 0.0080782 secs] 276833K->264038K(282296K), 0.0081421 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:44.169-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.169-0800: [ParNew: 19336K->2152K(19648K), 0.0097908 secs] 281199K->269580K(287900K), 0.0098530 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.184-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.185-0800: [ParNew: 19479K->2175K(19648K), 0.0212523 secs] 286907K->276635K(294768K), 0.0213182 secs] [Times: user=0.04 sys=0.01, real=0.02 secs] 
2022-03-13T17:12:44.210-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.210-0800: [ParNew: 19640K->2173K(19648K), 0.0199228 secs] 294100K->283224K(301408K), 0.0199879 secs] [Times: user=0.05 sys=0.00, real=0.02 secs] 
2022-03-13T17:12:44.237-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.237-0800: [ParNew: 19495K->2175K(19648K), 0.0092626 secs] 300546K->287474K(305600K), 0.0093217 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.250-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.250-0800: [ParNew: 19540K->2168K(19648K), 0.0208541 secs] 304839K->295887K(314116K), 0.0209609 secs] [Times: user=0.03 sys=0.01, real=0.02 secs] 
2022-03-13T17:12:44.277-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.278-0800: [ParNew: 19600K->2173K(19648K), 0.0118575 secs] 313319K->302737K(320940K), 0.0119231 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:44.289-0800: [CMS-concurrent-abortable-preclean: 0.037/0.589 secs] [Times: user=1.08 sys=0.19, real=0.59 secs] 
2022-03-13T17:12:44.290-0800: [GC (CMS Final Remark) [YG occupancy: 2777 K (19648 K)]2022-03-13T17:12:44.290-0800: [Rescan (parallel) , 0.0010800 secs]2022-03-13T17:12:44.291-0800: [weak refs processing, 0.0000241 secs]2022-03-13T17:12:44.291-0800: [class unloading, 0.0004291 secs]2022-03-13T17:12:44.291-0800: [scrub symbol table, 0.0005377 secs]2022-03-13T17:12:44.292-0800: [scrub string table, 0.0002577 secs][1 CMS-remark: 300563K(301292K)] 303341K(320940K), 0.0024786 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.292-0800: [CMS-concurrent-sweep-start]
2022-03-13T17:12:44.293-0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.294-0800: [CMS-concurrent-reset-start]
2022-03-13T17:12:44.298-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.298-0800: [ParNew: 19645K->2167K(19648K), 0.0070585 secs] 320142K->307603K(520476K), 0.0071149 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.305-0800: [CMS-concurrent-reset: 0.004/0.012 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.309-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.309-0800: [ParNew: 19470K->2171K(19648K), 0.0096861 secs] 324905K->315343K(520476K), 0.0097544 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:44.319-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 313171K(500828K)] 315674K(520476K), 0.0003530 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.328-0800: [CMS-concurrent-mark-start]
2022-03-13T17:12:44.329-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.329-0800: [ParNew: 19459K->2175K(19648K), 0.0101026 secs] 332630K->320584K(520476K), 0.0101972 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.354-0800: [CMS-concurrent-mark: 0.016/0.026 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2022-03-13T17:12:44.354-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.354-0800: [ParNew: 19565K->2166K(19648K), 0.0071547 secs] 337974K->325820K(520476K), 0.0072104 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.361-0800: [CMS-concurrent-preclean-start]
2022-03-13T17:12:44.363-0800: [CMS-concurrent-preclean: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.366-0800: [CMS-concurrent-abortable-preclean-start]
2022-03-13T17:12:44.368-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.368-0800: [ParNew: 19638K->2169K(19648K), 0.0087887 secs] 343292K->331955K(520476K), 0.0088481 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:44.377-0800: [CMS-concurrent-abortable-preclean: 0.001/0.010 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:44.377-0800: [GC (CMS Final Remark) [YG occupancy: 2544 K (19648 K)]2022-03-13T17:12:44.377-0800: [Rescan (parallel) , 0.0012198 secs]2022-03-13T17:12:44.378-0800: [weak refs processing, 0.0000265 secs]2022-03-13T17:12:44.378-0800: [class unloading, 0.0003531 secs]2022-03-13T17:12:44.379-0800: [scrub symbol table, 0.0006701 secs]2022-03-13T17:12:44.379-0800: [scrub string table, 0.0002462 secs][1 CMS-remark: 329785K(500828K)] 332330K(520476K), 0.0026414 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.380-0800: [CMS-concurrent-sweep-start]
2022-03-13T17:12:44.381-0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.381-0800: [CMS-concurrent-reset-start]
2022-03-13T17:12:44.383-0800: [CMS-concurrent-reset: 0.002/0.002 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.385-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.385-0800: [ParNew: 19628K->2175K(19648K), 0.0052155 secs] 240479K->228975K(520476K), 0.0052764 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.394-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.394-0800: [ParNew: 19646K->2156K(19648K), 0.0042703 secs] 246445K->237643K(520476K), 0.0043298 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.402-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.402-0800: [ParNew: 19578K->2164K(19648K), 0.0031412 secs] 255065K->242427K(520476K), 0.0032031 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.410-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.410-0800: [ParNew: 19632K->2173K(19648K), 0.0050614 secs] 259894K->248726K(520476K), 0.0051381 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.415-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 246553K(500828K)] 248798K(520476K), 0.0001791 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.415-0800: [CMS-concurrent-mark-start]
2022-03-13T17:12:44.420-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.420-0800: [ParNew: 18924K->2173K(19648K), 0.0057691 secs] 265477K->256815K(520476K), 0.0058493 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.435-0800: [CMS-concurrent-mark: 0.013/0.019 secs] [Times: user=0.04 sys=0.00, real=0.02 secs] 
2022-03-13T17:12:44.435-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.435-0800: [ParNew: 19631K->2164K(19648K), 0.0035900 secs] 274272K->264067K(520476K), 0.0036331 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.439-0800: [CMS-concurrent-preclean-start]
2022-03-13T17:12:44.440-0800: [CMS-concurrent-preclean: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.440-0800: [CMS-concurrent-abortable-preclean-start]
2022-03-13T17:12:44.443-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.443-0800: [ParNew: 19568K->2174K(19648K), 0.0036258 secs] 281472K->270274K(520476K), 0.0036846 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.450-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.450-0800: [ParNew: 19510K->2149K(19648K), 0.0040943 secs] 287610K->277420K(520476K), 0.0041637 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.458-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.458-0800: [ParNew: 19589K->2157K(19648K), 0.0044138 secs] 294860K->283426K(520476K), 0.0044851 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.468-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.468-0800: [ParNew: 19618K->2172K(19648K), 0.0049177 secs] 300887K->289348K(520476K), 0.0049729 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.477-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.477-0800: [ParNew: 19538K->2167K(19648K), 0.0043881 secs] 306714K->297985K(520476K), 0.0044510 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:44.485-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.485-0800: [ParNew: 19394K->2152K(19648K), 0.0038590 secs] 315211K->306051K(520476K), 0.0039243 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.493-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.493-0800: [ParNew: 19624K->2172K(19648K), 0.0038807 secs] 323523K->313962K(520476K), 0.0039435 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.501-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.501-0800: [ParNew: 19594K->2123K(19648K), 0.0036084 secs] 331384K->319702K(520476K), 0.0036734 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.512-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.512-0800: [ParNew: 19570K->2174K(19648K), 0.0032685 secs] 337148K->324966K(520476K), 0.0033264 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.520-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.520-0800: [ParNew: 19646K->2127K(19648K), 0.0049683 secs] 342438K->330807K(520476K), 0.0050871 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.529-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.529-0800: [ParNew: 19379K->2156K(19648K), 0.0064612 secs] 348059K->335912K(520476K), 0.0065152 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.541-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.541-0800: [ParNew: 19581K->2166K(19648K), 0.0059059 secs] 353337K->339836K(520476K), 0.0059637 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2022-03-13T17:12:44.552-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.552-0800: [ParNew: 19425K->2160K(19648K), 0.0074260 secs] 357095K->344887K(520476K), 0.0074964 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2022-03-13T17:12:44.564-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.564-0800: [ParNew: 19230K->2169K(19648K), 0.0105249 secs] 361957K->351508K(520476K), 0.0106585 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2022-03-13T17:12:44.582-0800: [GC (Allocation Failure) 2022-03-13T17:12:44.583-0800: [ParNew: 19385K->2169K(19648K), 0.0092768 secs] 368724K->356900K(520476K), 0.0093323 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
执行结束!共生成对象次数:5074
Heap
 par new generation   total 19648K, used 3235K [0x0000000740000000, 0x0000000741550000, 0x0000000754cc0000)
  eden space 17472K,   6% used [0x0000000740000000, 0x000000074010a7e0, 0x0000000741110000)
  from space 2176K,  99% used [0x0000000741110000, 0x000000074132e7e0, 0x0000000741330000)
  to   space 2176K,   0% used [0x0000000741330000, 0x0000000741330000, 0x0000000741550000)
 concurrent mark-sweep generation total 500828K, used 354730K [0x0000000754cc0000, 0x00000007735d7000, 0x00000007c0000000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

##### 3.3.1 CMS 6个阶段

CMS 初始化标记、和最终标记时，会产生GC暂停。

- inital Mark 初始化标记
- Concurrent Mark 并发标记
- Concurrent Preclean 并发预清理
- Final Remark 最终标记
- Concurrent Sweep 并发清除
- Concurrent Reset 并发重置

##### 3.3.2 设置 4g CMS堆内存

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx4g -Xms4g -XX:+UseConcMarkSweepGC GCLogAnalysis
正在执行...
2022-03-13T17:20:49.441-0800: [GC (Allocation Failure) 2022-03-13T17:20:49.441-0800: [ParNew: 272640K->34047K(306688K), 0.0546979 secs] 272640K->80433K(4160256K), 0.0547933 secs] [Times: user=0.10 sys=0.08, real=0.05 secs] 
2022-03-13T17:20:49.574-0800: [GC (Allocation Failure) 2022-03-13T17:20:49.574-0800: [ParNew: 306687K->34048K(306688K), 0.0675504 secs] 353073K->162360K(4160256K), 0.0676156 secs] [Times: user=0.12 sys=0.10, real=0.07 secs] 
2022-03-13T17:20:49.689-0800: [GC (Allocation Failure) 2022-03-13T17:20:49.689-0800: [ParNew: 306688K->34048K(306688K), 0.0923720 secs] 435000K->237803K(4160256K), 0.0924315 secs] [Times: user=0.27 sys=0.04, real=0.10 secs] 
2022-03-13T17:20:49.826-0800: [GC (Allocation Failure) 2022-03-13T17:20:49.826-0800: [ParNew: 306688K->34048K(306688K), 0.1586219 secs] 510443K->315440K(4160256K), 0.1586892 secs] [Times: user=0.34 sys=0.05, real=0.16 secs] 
2022-03-13T17:20:50.042-0800: [GC (Allocation Failure) 2022-03-13T17:20:50.042-0800: [ParNew: 306688K->34048K(306688K), 0.1210828 secs] 588080K->389302K(4160256K), 0.1211431 secs] [Times: user=0.27 sys=0.08, real=0.12 secs] 
2022-03-13T17:20:50.215-0800: [GC (Allocation Failure) 2022-03-13T17:20:50.215-0800: [ParNew: 306688K->34048K(306688K), 0.1207320 secs] 661942K->467636K(4160256K), 0.1207943 secs] [Times: user=0.28 sys=0.08, real=0.12 secs] 
执行结束!共生成对象次数:6098
Heap
 par new generation   total 306688K, used 44969K [0x00000006c0000000, 0x00000006d4cc0000, 0x00000006d4cc0000)
  eden space 272640K,   4% used [0x00000006c0000000, 0x00000006c0aaa7d8, 0x00000006d0a40000)
  from space 34048K, 100% used [0x00000006d0a40000, 0x00000006d2b80000, 0x00000006d2b80000)
  to   space 34048K,   0% used [0x00000006d2b80000, 0x00000006d2b80000, 0x00000006d4cc0000)
 concurrent mark-sweep generation total 3853568K, used 433588K [0x00000006d4cc0000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

#### 3.4 G1 GC

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx4g -Xms4g -XX:+UseG1GC GCLogAnalysis
正在执行...
2022-03-13T17:25:12.917-0800: [GC pause (G1 Evacuation Pause) (young), 0.0543729 secs]
   [Parallel Time: 45.9 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 591.9, Avg: 591.9, Max: 592.1, Diff: 0.2]
      [Ext Root Scanning (ms): Min: 0.1, Avg: 0.3, Max: 0.4, Diff: 0.3, Sum: 1.1]
      [Update RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
         [Processed Buffers: Min: 0, Avg: 0.0, Max: 0, Diff: 0, Sum: 0]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 44.2, Avg: 44.6, Max: 45.4, Diff: 1.1, Sum: 178.3]
      [Termination (ms): Min: 0.0, Avg: 0.8, Max: 1.3, Diff: 1.3, Sum: 3.2]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 45.6, Avg: 45.7, Max: 45.8, Diff: 0.3, Sum: 182.9]
      [GC Worker End (ms): Min: 637.7, Avg: 637.7, Max: 637.7, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.1 ms]
   [Other: 8.3 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.2 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.1 ms]
      [Humongous Register: 0.1 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.1 ms]
   [Eden: 204.0M(204.0M)->0.0B(178.0M) Survivors: 0.0B->26.0M Heap: 204.0M(4096.0M)->67.3M(4096.0M)]
 [Times: user=0.05 sys=0.07, real=0.06 secs] 
2022-03-13T17:25:13.015-0800: [GC pause (G1 Evacuation Pause) (young), 0.0350802 secs]
   [Parallel Time: 34.4 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 690.7, Avg: 690.7, Max: 690.7, Diff: 0.0]
      [Ext Root Scanning (ms): Min: 0.2, Avg: 0.3, Max: 0.4, Diff: 0.2, Sum: 1.1]
      [Update RS (ms): Min: 0.2, Avg: 0.3, Max: 0.4, Diff: 0.2, Sum: 1.3]
         [Processed Buffers: Min: 1, Avg: 1.2, Max: 2, Diff: 1, Sum: 5]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Object Copy (ms): Min: 32.7, Avg: 33.2, Max: 33.6, Diff: 1.0, Sum: 132.7]
      [Termination (ms): Min: 0.0, Avg: 0.5, Max: 1.1, Diff: 1.1, Sum: 1.8]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 34.3, Avg: 34.3, Max: 34.3, Diff: 0.0, Sum: 137.1]
      [GC Worker End (ms): Min: 725.0, Avg: 725.0, Max: 725.0, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.1 ms]
   [Other: 0.6 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.2 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.1 ms]
      [Humongous Register: 0.1 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.0 ms]
   [Eden: 178.0M(178.0M)->0.0B(178.0M) Survivors: 26.0M->26.0M Heap: 245.3M(4096.0M)->126.5M(4096.0M)]
 [Times: user=0.04 sys=0.06, real=0.04 secs] 
2022-03-13T17:25:13.090-0800: [GC pause (G1 Evacuation Pause) (young), 0.0562804 secs]
   [Parallel Time: 55.4 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 765.0, Avg: 765.1, Max: 765.2, Diff: 0.2]
      [Ext Root Scanning (ms): Min: 0.1, Avg: 0.2, Max: 0.2, Diff: 0.2, Sum: 0.7]
      [Update RS (ms): Min: 0.6, Avg: 0.9, Max: 1.4, Diff: 0.8, Sum: 3.6]
         [Processed Buffers: Min: 1, Avg: 1.5, Max: 2, Diff: 1, Sum: 6]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 53.4, Avg: 53.7, Max: 54.0, Diff: 0.6, Sum: 214.9]
      [Termination (ms): Min: 0.0, Avg: 0.4, Max: 0.8, Diff: 0.8, Sum: 1.7]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 55.1, Avg: 55.3, Max: 55.3, Diff: 0.2, Sum: 221.0]
      [GC Worker End (ms): Min: 820.3, Avg: 820.3, Max: 820.4, Diff: 0.1]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.1 ms]
   [Other: 0.8 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.2 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.1 ms]
      [Humongous Register: 0.1 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.1 ms]
   [Eden: 178.0M(178.0M)->0.0B(178.0M) Survivors: 26.0M->26.0M Heap: 304.5M(4096.0M)->190.2M(4096.0M)]
 [Times: user=0.04 sys=0.05, real=0.05 secs] 
2022-03-13T17:25:13.184-0800: [GC pause (G1 Evacuation Pause) (young), 0.0750505 secs]
   [Parallel Time: 74.3 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 858.9, Avg: 859.0, Max: 859.3, Diff: 0.4]
      [Ext Root Scanning (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.6]
      [Update RS (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.3]
         [Processed Buffers: Min: 0, Avg: 1.5, Max: 3, Diff: 3, Sum: 6]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 73.6, Avg: 73.7, Max: 73.9, Diff: 0.3, Sum: 294.9]
      [Termination (ms): Min: 0.0, Avg: 0.2, Max: 0.3, Diff: 0.3, Sum: 0.6]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 73.9, Avg: 74.1, Max: 74.3, Diff: 0.4, Sum: 296.6]
      [GC Worker End (ms): Min: 933.1, Avg: 933.1, Max: 933.2, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.1 ms]
   [Other: 0.7 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.2 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.1 ms]
      [Humongous Register: 0.1 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.1 ms]
   [Eden: 178.0M(178.0M)->0.0B(178.0M) Survivors: 26.0M->26.0M Heap: 368.2M(4096.0M)->248.2M(4096.0M)]
 [Times: user=0.04 sys=0.05, real=0.07 secs] 
2022-03-13T17:25:13.302-0800: [GC pause (G1 Evacuation Pause) (young), 0.0437276 secs]
   [Parallel Time: 42.7 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 977.4, Avg: 977.4, Max: 977.5, Diff: 0.0]
      [Ext Root Scanning (ms): Min: 0.2, Avg: 0.2, Max: 0.2, Diff: 0.0, Sum: 0.8]
      [Update RS (ms): Min: 0.1, Avg: 0.1, Max: 0.1, Diff: 0.1, Sum: 0.4]
         [Processed Buffers: Min: 1, Avg: 1.5, Max: 2, Diff: 1, Sum: 6]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 42.0, Avg: 42.2, Max: 42.3, Diff: 0.3, Sum: 168.8]
      [Termination (ms): Min: 0.0, Avg: 0.1, Max: 0.3, Diff: 0.3, Sum: 0.5]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 42.6, Avg: 42.7, Max: 42.7, Diff: 0.1, Sum: 170.6]
      [GC Worker End (ms): Min: 1020.1, Avg: 1020.1, Max: 1020.1, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.1 ms]
   [Other: 0.9 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.2 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.1 ms]
      [Humongous Register: 0.2 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.1 ms]
   [Eden: 178.0M(178.0M)->0.0B(178.0M) Survivors: 26.0M->26.0M Heap: 426.2M(4096.0M)->299.9M(4096.0M)]
 [Times: user=0.04 sys=0.04, real=0.04 secs] 
2022-03-13T17:25:13.382-0800: [GC pause (G1 Evacuation Pause) (young), 0.0768944 secs]
   [Parallel Time: 75.1 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 1057.2, Avg: 1057.5, Max: 1058.1, Diff: 0.9]
      [Ext Root Scanning (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.6]
      [Update RS (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.4]
         [Processed Buffers: Min: 0, Avg: 1.2, Max: 2, Diff: 2, Sum: 5]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 73.6, Avg: 74.2, Max: 74.6, Diff: 1.0, Sum: 296.7]
      [Termination (ms): Min: 0.0, Avg: 0.3, Max: 0.5, Diff: 0.5, Sum: 1.1]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 74.1, Avg: 74.8, Max: 75.0, Diff: 0.9, Sum: 299.0]
      [GC Worker End (ms): Min: 1132.2, Avg: 1132.2, Max: 1132.2, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.1 ms]
   [Other: 1.7 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.9 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.1 ms]
      [Humongous Register: 0.1 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.1 ms]
   [Eden: 178.0M(178.0M)->0.0B(178.0M) Survivors: 26.0M->26.0M Heap: 477.9M(4096.0M)->356.9M(4096.0M)]
 [Times: user=0.05 sys=0.06, real=0.07 secs] 
2022-03-13T17:25:13.500-0800: [GC pause (G1 Evacuation Pause) (young), 0.0461113 secs]
   [Parallel Time: 44.8 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 1175.2, Avg: 1175.3, Max: 1175.5, Diff: 0.3]
      [Ext Root Scanning (ms): Min: 0.0, Avg: 0.2, Max: 0.2, Diff: 0.2, Sum: 0.6]
      [Update RS (ms): Min: 0.1, Avg: 0.1, Max: 0.2, Diff: 0.1, Sum: 0.5]
         [Processed Buffers: Min: 1, Avg: 1.2, Max: 2, Diff: 1, Sum: 5]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 44.1, Avg: 44.2, Max: 44.4, Diff: 0.2, Sum: 176.9]
      [Termination (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.5]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 44.5, Avg: 44.7, Max: 44.8, Diff: 0.3, Sum: 178.7]
      [GC Worker End (ms): Min: 1219.9, Avg: 1220.0, Max: 1220.0, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.1 ms]
   [Other: 1.2 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.7 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.1 ms]
      [Humongous Register: 0.1 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.1 ms]
   [Eden: 178.0M(178.0M)->0.0B(178.0M) Survivors: 26.0M->26.0M Heap: 534.9M(4096.0M)->402.5M(4096.0M)]
 [Times: user=0.05 sys=0.06, real=0.04 secs] 
2022-03-13T17:25:13.587-0800: [GC pause (G1 Evacuation Pause) (young), 0.0517391 secs]
   [Parallel Time: 50.8 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 1262.7, Avg: 1262.7, Max: 1262.9, Diff: 0.3]
      [Ext Root Scanning (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.6]
      [Update RS (ms): Min: 0.2, Avg: 0.3, Max: 0.6, Diff: 0.4, Sum: 1.2]
         [Processed Buffers: Min: 1, Avg: 1.5, Max: 3, Diff: 2, Sum: 6]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 49.0, Avg: 49.8, Max: 50.2, Diff: 1.2, Sum: 199.3]
      [Termination (ms): Min: 0.0, Avg: 0.3, Max: 0.9, Diff: 0.9, Sum: 1.3]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 50.5, Avg: 50.6, Max: 50.7, Diff: 0.3, Sum: 202.6]
      [GC Worker End (ms): Min: 1313.4, Avg: 1313.4, Max: 1313.4, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.1 ms]
   [Other: 0.9 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.2 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.1 ms]
      [Humongous Register: 0.1 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.0 ms]
   [Eden: 178.0M(178.0M)->0.0B(178.0M) Survivors: 26.0M->26.0M Heap: 580.5M(4096.0M)->456.5M(4096.0M)]
 [Times: user=0.05 sys=0.07, real=0.06 secs] 
2022-03-13T17:25:13.680-0800: [GC pause (G1 Evacuation Pause) (young), 0.0638074 secs]
   [Parallel Time: 62.6 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 1354.9, Avg: 1354.9, Max: 1355.0, Diff: 0.1]
      [Ext Root Scanning (ms): Min: 0.1, Avg: 0.2, Max: 0.2, Diff: 0.1, Sum: 0.7]
      [Update RS (ms): Min: 0.1, Avg: 0.1, Max: 0.2, Diff: 0.1, Sum: 0.4]
         [Processed Buffers: Min: 1, Avg: 1.2, Max: 2, Diff: 1, Sum: 5]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 61.1, Avg: 61.7, Max: 62.2, Diff: 1.1, Sum: 246.7]
      [Termination (ms): Min: 0.0, Avg: 0.5, Max: 1.0, Diff: 1.0, Sum: 2.1]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 62.5, Avg: 62.5, Max: 62.6, Diff: 0.1, Sum: 250.1]
      [GC Worker End (ms): Min: 1417.5, Avg: 1417.5, Max: 1417.5, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.1 ms]
   [Other: 1.1 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.2 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.1 ms]
      [Humongous Register: 0.1 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.3 ms]
   [Eden: 178.0M(178.0M)->0.0B(178.0M) Survivors: 26.0M->26.0M Heap: 634.5M(4096.0M)->509.8M(4096.0M)]
 [Times: user=0.05 sys=0.07, real=0.06 secs] 
执行结束!共生成对象次数:6192
Heap
 garbage-first heap   total 4194304K, used 536355K [0x00000006c0000000, 0x00000006c0204000, 0x00000007c0000000)
  region size 2048K, 21 young (43008K), 13 survivors (26624K)
 Metaspace       used 2711K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K
appledeMacBook-Air-2:jvm apple$ 
```

使用PrintGC 代替PrintGCDetails

```java
appledeMacBook-Air-2:jvm apple$ java -XX:+PrintGC -XX:+PrintGCDateStamps -Xmx4g -Xms4g -XX:+UseG1GC GCLogAnalysis
正在执行...
2022-03-13T17:28:58.151-0800: [GC pause (G1 Evacuation Pause) (young) 204M->70M(4096M), 0.0298522 secs]
2022-03-13T17:28:58.226-0800: [GC pause (G1 Evacuation Pause) (young) 248M->126M(4096M), 0.0565132 secs]
2022-03-13T17:28:58.335-0800: [GC pause (G1 Evacuation Pause) (young) 304M->177M(4096M), 0.1225887 secs]
2022-03-13T17:28:58.511-0800: [GC pause (G1 Evacuation Pause) (young) 355M->234M(4096M), 0.1395577 secs]
2022-03-13T17:28:58.699-0800: [GC pause (G1 Evacuation Pause) (young) 412M->294M(4096M), 0.0981548 secs]
2022-03-13T17:28:58.896-0800: [GC pause (G1 Evacuation Pause) (young) 472M->353M(4096M), 0.0777775 secs]
执行结束!共生成对象次数:4015
appledeMacBook-Air-2:jvm apple$ 
```



