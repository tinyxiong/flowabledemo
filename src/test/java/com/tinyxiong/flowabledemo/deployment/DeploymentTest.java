package com.tinyxiong.flowabledemo.deployment;

import com.tinyxiong.flowabledemo.base.BaseTest;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

public class DeploymentTest extends BaseTest {

    @Autowired
    private RepositoryService repositoryService;

    public void deployByZipFile() throws Exception {
        String barFile = "";
        ZipInputStream inputStream = new ZipInputStream(new FileInputStream(barFile));
        repositoryService.createDeployment()
                .addZipInputStream(inputStream)
                .deploy();
    }

    public void deployWithImg() throws Exception {
        repositoryService.createDeployment().name("bpmnProcess.bar")
                .category("xiong")//user-defined categories: targetNamespace
                .addClasspathResource("org/flowable/expenseProcess.bpmn20.xml")
                .addClasspathResource("org/flowable/expenseProcess.png")
                .deploy()
        ;

        getImgAfterwords();
    }

    private void getImgAfterwords() {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("expense")
                .singleResult();
        String diagramResourceName = processDefinition.getDiagramResourceName();
        InputStream imageStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
    }
}
