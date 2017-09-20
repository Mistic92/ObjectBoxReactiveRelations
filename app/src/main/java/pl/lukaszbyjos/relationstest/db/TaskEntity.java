package pl.lukaszbyjos.relationstest.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.relation.ToOne;

@Entity
public class TaskEntity {

    protected ToOne<ProjectEntity> project;
    @Id
    private long id;
    @Index
    private String backendId;
    private String taskType;
    private String taskDescription;

    @Override
    public String toString() {
        return "TaskEntity{" +
                "project=" + project +
                ", id=" + id +
                ", backendId='" + backendId + '\'' +
                ", taskType='" + taskType + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", completed=" + completed +
                '}';
    }

    private boolean completed;

    public String getBackendId() {
        return backendId;
    }

    public void setBackendId(String backendId) {
        this.backendId = backendId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ProjectEntity getProject() {
        return project.getTarget();
    }

    public void setProject(ProjectEntity project) {
        this.project.setTarget(project);
    }
}
