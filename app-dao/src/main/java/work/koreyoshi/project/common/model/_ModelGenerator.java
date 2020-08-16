package work.koreyoshi.project.common.model;

import com.jfinal.kit.PathKit;
import work.koreyoshi.base.config.activerecord.ModelGeneratorFactory;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 * @author zhoujx
 */
public class _ModelGenerator {

	public static void main(String[] args) {
		ModelGeneratorFactory.builder()
				.host("koreyoshi.work")
				.database("zhou")
				.user("root")
				.password("123456")
				.removedTableNamePrefixes(new String[]{"sys", "tb"})
				.excludedTables(new String[]{""})
				.baseModelPackageName("work.koreyoshi.project.common.model.base")
				.modelPackageName("work.koreyoshi.project.common.model")
				.baseModelOutputDir(PathKit.getWebRootPath() + "/src/main/java/work/koreyoshi/project/common/model/base")
				.build().getGenerate()
				.generate();
	}
}




