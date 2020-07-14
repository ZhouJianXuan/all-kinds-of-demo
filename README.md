# all-kinds-of-demo
将各种工具封装起来，方便以后使用
```xml
<parent>
    <groupId>org.example</groupId>
    <version>1.0</version>
    <artifactId>all-kinds-of-demo</artifactId>
    <relativePath>../all-kinds-of-demo/pom.xml</relativePath>
</parent>

<dependencies>
    <dependency>
        <groupId>org.example</groupId>
        <artifactId>base</artifactId>
        <version>1.0</version>
    </dependency>
    <dependency>
        <groupId>org.example</groupId>
        <artifactId>mail</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```
```java
@ComponentScan(basePackages = {
    "work.koreyoshi.*"
})
```

### mail
封装一下发送Mail的工具或注解
