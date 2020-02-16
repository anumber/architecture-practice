# architecture-practice

## gradle 笔记

作用：依赖包的配置
依赖方式：在2.x版本和3.0以后的版本更换了关键字

| 2.x | 3.0 OR later | 描述 |
| -------- | ----- | :---- |
| compile | implementation，api |编译以及运行时都需要|
| provided | compileOnly、compileClasspath  |仅编译需要|
| apk | runtimeOnly |仅运行时需要|
| testCompile| testImplementation | 测试编译时使用 |