import org.activiti.engine.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class ProcessServiceTest {

    ProcessEngine processEngine;
    @Before
    public void initProcess(){
        ProcessEngineConfiguration processEngineConfiguration =
                ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://rm-bp19up4mlc018hgwl5o.mysql.rds.aliyuncs.com:3306/test_activity?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true");
        processEngineConfiguration.setJdbcUsername("liuxingwu");
        processEngineConfiguration.setJdbcPassword("Lxw123456");
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        processEngine = processEngineConfiguration.buildProcessEngine();
    }

    @Test
    public void testService(){
        //定义文件 部署文件
        RepositoryService repositoryService = processEngine.getRepositoryService();
        assertNotNull(repositoryService);
        //启动流程，查询流程，设置流程
        RuntimeService runtimeService = processEngine.getRuntimeService();
        assertNotNull(runtimeService);
        //运行时任务的查询，领取完成删除
        TaskService taskService = processEngine.getTaskService();
        assertNotNull(taskService);
        //用户和用户组
        IdentityService identityService = processEngine.getIdentityService();
        assertNotNull(identityService);
        //acti日常维护
        ManagementService managementService = processEngine.getManagementService();
        assertNotNull(managementService);
        //赈灾允许或已完成流程实例信息
        HistoryService historyService = processEngine.getHistoryService();
        assertNotNull(historyService);

    }
}
