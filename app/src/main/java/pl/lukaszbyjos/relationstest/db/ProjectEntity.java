package pl.lukaszbyjos.relationstest.db;

import java.util.Date;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ProjectEntity {

    @Id
    private long id;

    private String projectDescription;
    private Date projectEndDate;
    @Backlink
    private List<TaskEntity> tasks;
    private int justNumberLol;

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectEndDate=" + projectEndDate +
                ", tasks=" + tasks +
                ", justNumberLol=" + justNumberLol +
                '}';
    }

    public int getJustNumberLol() {
        return justNumberLol;
    }

    public void setJustNumberLol(int justNumberLol) {
        this.justNumberLol = justNumberLol;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
