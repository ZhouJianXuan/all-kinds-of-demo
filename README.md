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

### base
集成Jfinald的ActiveRecord操作数据库
示例配置：
```java
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
	}
}

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 * @author zhoujx
 */
public class _ModelGenerator {

	public static void main(String[] args) {
		ModelGeneratorFactory.builder()
				.host("localhost")
				.database("zhou")
				.user("root")
				.password("123456")
				.baseModelOutputDir(PathKit.getWebRootPath() + "/src/main/java/work/koreyoshi/project/common/model/base")
				.build().getGenerate()
				.generate();
	}
}
```
然后在Service层继承BaseService进行开发啦，语法参考[Jfinal的model使用](http://www.jfinal.com/doc/5-3)
```java
@Service
public class ProductService extends BaseService<Product> {

    public static final Product DAO = new Product().dao();

    @Override
    public Model<Product> getDao() {
        return DAO;
    }

    public List<Product> findAll() {
        return getDao().findAll();
    }
}
```