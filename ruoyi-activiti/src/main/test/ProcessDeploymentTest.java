import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProcessDeploymentTest {

    ProcessEngine processEngine;
    IdentityService identityService;
    RepositoryService repositoryService;
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
        identityService =processEngine.getIdentityService();
        repositoryService=processEngine.getRepositoryService();
    }

    @Test
    public void initUser(){
        User axianlu = identityService.newUser("axianlu");
        axianlu.setFirstName("一只闲鹿");
        identityService.saveUser(axianlu);
        User ransom = identityService.newUser("rensom");
        ransom.setFirstName("人事喵");
        identityService.saveUser(ransom);
        assertEquals(2,identityService.createUserQuery().count());
    }
    
    @Test
    public void initGroup(){
        Group deptLeader = identityService.newGroup("deptLeader");
        deptLeader.setName("deptLeader");
        deptLeader.setType("assignment");
        identityService.saveGroup(deptLeader);
        Group hr = identityService.newGroup("hr");
        hr.setName("hr");
        hr.setType("assignment");
        identityService.saveGroup(hr);
        assertEquals(2,identityService.createGroupQuery().count());
    }

    @Test
    public void testInitMembership(){
        identityService.createMembership("axianlu","deptLeader");
        identityService.createMembership("rensm","hr");
    }

    @Test
    public void deployTest(){
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("leave.bpmn").deploy();
        assertNotNull(deploy);
    }
}
