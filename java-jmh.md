---
title: JAVA拾遗 — 使用JMH进行测试的8个理由
date: 2018-08-13 19:47:28
tags:
- JAVA
categories:
- JAVA
---



```haskell
Benchmark                                Mode  Cnt   Score    Error  Units
JMHSample_16_CompilerControl.baseline    avgt    3   0.300 ±  0.090  ns/op
JMHSample_16_CompilerControl.blank       avgt    3   0.323 ±  0.544  ns/op
JMHSample_16_CompilerControl.dontinline  avgt    3   2.099 ±  7.515  ns/op
JMHSample_16_CompilerControl.exclude     avgt    3  56.809 ± 20.612  ns/op
JMHSample_16_CompilerControl.inline      avgt    3   0.308 ±  0.264  ns/op
```



#### 使用 fork 避免 JIT 优化干扰

```haskell
Benchmark                                 Mode  Cnt   Score   Error  Units
JMHSample_12_Forking.measure_1_c1         avgt    5   2.518 ± 0.622  ns/op
JMHSample_12_Forking.measure_2_c2         avgt    5  14.080 ± 0.283  ns/op
JMHSample_12_Forking.measure_3_c1_again   avgt    5  13.462 ± 0.164  ns/op
JMHSample_12_Forking.measure_4_forked_c1  avgt    5   3.861 ± 0.712  ns/op
JMHSample_12_Forking.measure_5_forked_c2  avgt    5   3.574 ± 0.220  ns/op
```



#### 不要使用循环来测试

```haskell
Benchmark                               Mode  Cnt  Score   Error  Units
JMHSample_11_Loops.measureRight         avgt    5  2.343 ± 0.199  ns/op
JMHSample_11_Loops.measureWrong_1       avgt    5  2.358 ± 0.166  ns/op
JMHSample_11_Loops.measureWrong_10      avgt    5  0.326 ± 0.354  ns/op
JMHSample_11_Loops.measureWrong_100     avgt    5  0.032 ± 0.011  ns/op
JMHSample_11_Loops.measureWrong_1000    avgt    5  0.025 ± 0.002  ns/op
JMHSample_11_Loops.measureWrong_10000   avgt    5  0.022 ± 0.005  ns/op
JMHSample_11_Loops.measureWrong_100000  avgt    5  0.019 ± 0.001  ns/op
```





**欢迎关注我的微信公众号：「Kirito的技术分享」，关于文章的任何疑问都会得到回复，带来更多 Java 相关的技术分享。**

![关注微信公众号](http://ov0zuistv.bkt.clouddn.com/qrcode_for_gh_c06057be7960_258%20%281%29.jpg)

