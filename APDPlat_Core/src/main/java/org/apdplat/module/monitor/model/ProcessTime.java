/**
 * 
 * APDPlat - Application Product Development Platform
 * Copyright (c) 2013, 杨尚川, yang-shangchuan@qq.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.apdplat.module.monitor.model;

import org.apdplat.platform.generator.ActionGenerator;
import org.apdplat.platform.annotation.IgnoreBusinessLog;
import org.apdplat.platform.annotation.IgnoreUser;
import org.apdplat.platform.annotation.ModelAttr;
import org.apdplat.platform.util.ConvertUtils;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apdplat.platform.annotation.Database;
import org.apdplat.platform.model.Model;
import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 *不需要保存该模型的增删改日志
 * 不需要自动设置模型的添加用户
 * @author 杨尚川
 */
@Entity
@Table(name="ProcessTimeTable")
@Scope("prototype")
@Component
@Searchable
@IgnoreBusinessLog
@IgnoreUser
@Database("log")
public class ProcessTime extends Model {
    public String getProcessTimeStr(){
        return ConvertUtils.getTimeDes(processTime);
    }
    @ModelAttr("用户IP地址")
    protected String userIP;
    @ModelAttr("服务器IP地址")
    protected String serverIP;

    @ModelAttr("应用系统名称")
    protected String appName;

    @ModelAttr("资源路径")
    @Column(name="resourceField")
    protected String resource;

    @Temporal(TemporalType.TIMESTAMP)
    @SearchableProperty(format="yyyy-MM-dd")
    @ModelAttr("开始处理时间")
    protected Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @SearchableProperty(format="yyyy-MM-dd")
    @ModelAttr("处理完成时间")
    protected Date endTime;

    //单位为毫秒
    @ModelAttr("操作耗时")
    @Column(name="processTimeField")
    protected Long processTime;

    
    //用户名不分词
    @SearchableProperty(index=Index.NOT_ANALYZED)
    @ModelAttr("用户名")
    protected String username;    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    public Long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Long processTime) {
        this.processTime = processTime;
    }
    

    @Override
    public String getMetaData() {
        return "请求处理时间日志";
    }
    public static void main(String[] args){
        ProcessTime obj=new ProcessTime();
        //生成Action
        ActionGenerator.generate(obj.getClass());
    }
}