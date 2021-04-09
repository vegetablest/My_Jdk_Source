package com.bysj.logistics.utils;

import com.bysj.logistics.plugins.PaginationCondPlugin;
import com.bysj.logistics.plugins.TaskIdPlugin;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.NullProgressCallback;
import org.mybatis.generator.internal.PluginAggregator;
import org.mybatis.generator.plugins.SerializablePlugin;
import org.mybatis.generator.plugins.ToStringPlugin;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 *
 *  mybatis 生成类
 *  运行Test方法即可
 *  !!需要配置好对应的数据库连接、目录、包信息及对应的表信息,目前只支持单表的生成
 *  只支持Oracle和Mysql数据库
 * @author bangsun
 * @data 2021/4/4 10:18
 * @verson
 */
public class MybatisCodeGenerator {

    /**
     * 数据库连接信息
     */
    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/easy?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
    private static final String username = "root";
    private static final String password = "root";

//    private static final String driverClassName = "oracle.jdbc.OracleDriver";
//    private static final String url = "jdbc:oracle:thin:@10.100.1.20:1521:db3";
//    private static final String username = "ccbank_ds_330";
//    private static final String password = "bangsun";

    /**
     * 对应的表名
     */
    private static final String tableName = "t_user";
    /**
     * 对应的pojo文件名称
     */
    private static final String domainObjectName = "User2";
    /**
     * 数据库的schema
     */
    private static final String schema = DatabaseUtil.getSchemaName(url,username);

    private static final String databaseType = DatabaseUtil.getDatabaseType(url,username).toString();

    /**
     * mapper文件的package名称
     */
    private static final String mapperTargetPackage = "com.bysj.logistics.manage.mapper";
    /**
     * pojo的package名称
     */
    private static final String pojoTargetPackage = "com.bysj.logistics.manage.pojo";
    /**
     * sqlmap的package名称
     */
    private static final String sqlmapTargetPackage =databaseType.toLowerCase()+ ".cn.com.bsfit.frms.ds.sqlmap";


    /**
     * 生成的文件目录
     */
    private static final String generateFilePath = "./src/test/resources/file/generate/";


    @Test
    public void Test(){
        get();
    }

    public List<IntrospectedTable> get() {


        Context context = new Context(ModelType.FLAT);
        context.setTargetRuntime("MyBatis3");

        CommentGeneratorConfiguration cgc = new CommentGeneratorConfiguration();
        cgc.addProperty("suppressDate", "true");
        cgc.addProperty("suppressAllComments", "true");
        context.setCommentGeneratorConfiguration(cgc);

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(url);
        jdbcConnectionConfiguration.setDriverClass(driverClassName);
        jdbcConnectionConfiguration.setPassword(password);
        jdbcConnectionConfiguration.setUserId(username);
        /**
         * 对于MySQL来说数据库有多张同名的表会生成多次
         * */
        jdbcConnectionConfiguration.addProperty("nullCatalogMeansCurrent","true");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        JavaClientGeneratorConfiguration jcgc = new JavaClientGeneratorConfiguration();
        jcgc.setConfigurationType("XMLMAPPER");//ANNOTATEDMAPPER
        jcgc.setTargetPackage(mapperTargetPackage);
        jcgc.addProperty("enableSubPackages", "true");
        context.setJavaClientGeneratorConfiguration(jcgc);

        JavaModelGeneratorConfiguration jmgc = new JavaModelGeneratorConfiguration();
        jmgc.setTargetPackage(pojoTargetPackage);
        jmgc.addProperty("enableSubPackages", "true");
        jmgc.addProperty("trimStrings", "true");
        context.setJavaModelGeneratorConfiguration(jmgc);

        SqlMapGeneratorConfiguration smgc = new SqlMapGeneratorConfiguration();
        smgc.setTargetPackage(sqlmapTargetPackage);
        smgc.addProperty("enableSubPackages", "true");
        context.setSqlMapGeneratorConfiguration(smgc);

        TableConfiguration tc = new TableConfiguration(context);
        context.addTableConfiguration(tc);
        tc.setSchema(schema);
        tc.setTableName(tableName);
        tc.setDomainObjectName(domainObjectName);
        setTableConfig(tableName,tc);
        tc.addProperty("useActualColumnNames", "false");
        tc.addProperty("constructorBased", "false");
        tc.addProperty("ignoreQualifiersAtRuntime", "true");
        tc.addProperty("nullCatalogMeansCurrent", "true");

        try {
            Field field = Context.class.getDeclaredField("pluginAggregator");
            field.setAccessible(true);
            PluginAggregator pluginAggregator = new PluginAggregator();

            SerializablePlugin serializablePlugin = new SerializablePlugin();
            serializablePlugin.setContext(context);
            pluginAggregator.addPlugin(serializablePlugin);

            TaskIdPlugin taskIdPlugin = new TaskIdPlugin();
            taskIdPlugin.setContext(context);
            pluginAggregator.addPlugin(taskIdPlugin);


            ToStringPlugin toStringPlugin = new ToStringPlugin();
            toStringPlugin.setContext(context);
            pluginAggregator.addPlugin(toStringPlugin);

            //分页插件
            PaginationCondPlugin paginationPlugin = new PaginationCondPlugin();
            paginationPlugin.setContext(context);
            pluginAggregator.addPlugin(paginationPlugin);


            field.set(context, pluginAggregator);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        ProgressCallback callback = new NullProgressCallback();
        List<IntrospectedTable> introspectedTables = new ArrayList<IntrospectedTable>();
        try {
            context.introspectTables(callback, new ArrayList<String>(), null);

            Field field = Context.class.getDeclaredField("introspectedTables");
            field.setAccessible(true);
            introspectedTables = (List<IntrospectedTable>) field.get(context);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        for(IntrospectedTable it : introspectedTables){
            it.initialize();
            it.calculateGenerators(new ArrayList<String>(), callback);
            List<GeneratedJavaFile> generatedJavaFiles = it.getGeneratedJavaFiles();
            List<GeneratedXmlFile> generatedXmlFiles = it.getGeneratedXmlFiles();
            for(GeneratedJavaFile gjf : generatedJavaFiles){
                System.out.println(gjf.getFileName());
                System.out.println(gjf.getFormattedContent());
                File file = new File(generateFilePath,gjf.getFileName());
                try {
                    writeFile(file,gjf.getFormattedContent(),"utf-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            for(GeneratedXmlFile gxf : generatedXmlFiles){
                System.out.println(gxf.getFormattedContent());

                try {
                    File file = new File(generateFilePath,gxf.getFileName());
                    writeFile(file,gxf.getFormattedContent(),"utf-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Writes, or overwrites, the contents of the specified file
     *
     * @param file
     * @param content
     */
    private static void writeFile(File file, String content, String fileEncoding) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if (fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }

        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(content);
        bw.close();
    }

    /**
     * 需要自定义的一些表信息,可以自定义主键方式及字段类型
     * @param tableName
     * @param tc
     */
    private void setTableConfig(String tableName,TableConfiguration tc){

        if("BANK_OPER".equalsIgnoreCase(tableName)) {
            GeneratedKey generatedKey = new GeneratedKey("id",
                    "SELECT SEQ_BK_OPER_ID.nextval from dual", false, null);
//            ColumnOverride co1 = new ColumnOverride("id");
//            co1.setJavaType("java.lang.Long");
//            ColumnOverride co2 = new ColumnOverride("txn_amount");
//            co2.setJavaType("java.lang.Long");
//            tc.addColumnOverride(co1);
//            tc.addColumnOverride(co2);
//            ColumnOverride co1 = new ColumnOverride("suaf_test");
//            co1.setJavaType("java.lang.Double");
//            tc.addColumnOverride(co1);
            tc.setGeneratedKey(generatedKey);
        }else if("BANK_USER".equalsIgnoreCase(tableName)) {
            ColumnOverride co1 = new ColumnOverride("single_trans_limit");
            co1.setJavaType("java.lang.Long");
            ColumnOverride co2 = new ColumnOverride("daily_trans_limit");
            co2.setJavaType("java.lang.Long");
            tc.addColumnOverride(co1);
            tc.addColumnOverride(co2);
        }else if("BANK_CUST".equalsIgnoreCase(tableName)) {
            ColumnOverride co1 = new ColumnOverride("age");
            co1.setJavaType("java.lang.Integer");
            tc.addColumnOverride(co1);
        }else if("BANK_ACCT".equalsIgnoreCase(tableName)) {
            ColumnOverride co1 = new ColumnOverride("daily_balance");
            co1.setJavaType("java.lang.Long");
            tc.addColumnOverride(co1);
        }
    }

}
