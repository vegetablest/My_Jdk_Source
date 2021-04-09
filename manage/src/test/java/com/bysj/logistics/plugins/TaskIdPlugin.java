package com.bysj.logistics.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.plugins.EqualsHashCodePlugin;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/4 10:30
 * @verson
 */
public class TaskIdPlugin extends EqualsHashCodePlugin {
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> columns;
        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            columns = introspectedTable.getNonBLOBColumns();
        } else {
            columns = introspectedTable.getAllColumns();
        }

        generateEquals(topLevelClass, columns, introspectedTable);
        generateHashCode(topLevelClass, columns, introspectedTable);
        generateTaskId(topLevelClass, columns, introspectedTable);
        generateSetTaskId(topLevelClass, columns, introspectedTable);
        generateGetTaskId(topLevelClass, columns, introspectedTable);

        return true;
    }

    protected void generateTaskId(TopLevelClass topLevelClass,
                                  List<IntrospectedColumn> introspectedColumns,
                                  IntrospectedTable introspectedTable) {
        Field field = new Field();
        field.setName("taskId"); //$NON-NLS-1$
        field.setType(new FullyQualifiedJavaType("String")); //$NON-NLS-1$
        field.setVisibility(JavaVisibility.PRIVATE);
        context.getCommentGenerator().addFieldComment(field, introspectedTable);

        topLevelClass.addField(field);
    }

    protected void generateSetTaskId(TopLevelClass topLevelClass,
                                     List<IntrospectedColumn> introspectedColumns,
                                     IntrospectedTable introspectedTable) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("setTaskId"); //$NON-NLS-1$
        method.addParameter(new Parameter(FullyQualifiedJavaType
                .getStringInstance(), "taskId")); //$NON-NLS-1$
        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);
        method.addBodyLine("this.taskId = taskId;");
        topLevelClass.addMethod(method);
    }

    protected void generateGetTaskId(TopLevelClass topLevelClass,
                                     List<IntrospectedColumn> introspectedColumns,
                                     IntrospectedTable introspectedTable) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("getTaskId"); //$NON-NLS-1$
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);
        method.addBodyLine("return this.taskId;");
        topLevelClass.addMethod(method);
    }
}

