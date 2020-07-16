package work.koreyoshi.base.config;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.Dialect;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @author zhoujx
 */
@Setter
@Builder
public class ModelGeneratorFactory {

    private String host = "localhost";
    private String database = "zhou";
    private String jdbcUrl;
    private String user = "root";
    private String password = "123456";
    private String baseModelPackageName = "work.koreyoshi.project.common.model.base";
    private String modelPackageName = "work.koreyoshi.project.common.model";

    /**
     * configure whether to generate notes
     */
    private boolean generateRemarks;

    /**
     * set the database dialect
     */
    private Dialect dialect;

    /**
     * set whether to generate chained setter method
     */
    private boolean generateChainSetter;

    /**
     * Add table names that do not need to be generated
     */
    private String[] excludedTables;

    /**
     * set whether to generate dao object in model
     */
    private boolean generateDaoInModel;

    /**
     * set whether to generate dictionary files
     */
    private boolean generateDataDictionary;

    /**
     * Set the table name prefix that needs to be removed to generate the model name
     */
    private String[] removedTableNamePrefixes;

    /**
     * base model file save path
     */
    private String baseModelOutputDir;
    private String modelOutputDir;

    public DataSource getDataSource() {
        if (StringUtils.isEmpty(jdbcUrl)) {
            jdbcUrl = "jdbc:mysql://" + host + "/" + database + "?characterEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
        }
        DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, user, password.trim());
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public Generator getGenerate() {
        if (StringUtils.isEmpty(modelOutputDir)) {
            modelOutputDir = baseModelOutputDir + "/..";
        }
        // create a generator
        Generator generator = new Generator(
                getDataSource(),
                baseModelPackageName,
                baseModelOutputDir,
                modelPackageName,
                modelOutputDir
        );

        // 配置是否生成备注
        generator.setGenerateRemarks(generateRemarks);

        // 设置数据库方言
        generator.setDialect(dialect);

        // 设置是否生成链式 setter 方法
        generator.setGenerateChainSetter(generateChainSetter);

        // 添加不需要生成的表名
        generator.addExcludedTable(excludedTables);

        // 设置是否在 Model 中生成 dao 对象
        generator.setGenerateDaoInModel(generateDaoInModel);

        // 设置是否生成字典文件
        generator.setGenerateDataDictionary(generateDataDictionary);

        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        generator.setRemovedTableNamePrefixes(removedTableNamePrefixes);
        return generator;
    }
}
