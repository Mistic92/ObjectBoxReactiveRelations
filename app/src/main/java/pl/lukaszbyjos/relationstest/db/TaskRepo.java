package pl.lukaszbyjos.relationstest.db;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class TaskRepo {
    private Box<TaskEntity> box;

    public TaskRepo(BoxStore boxStore) {
        box = boxStore.boxFor(TaskEntity.class);
    }

    public void addTask(TaskEntity taskEntity) {
        updateTask(taskEntity);
    }

    public TaskEntity getTaskByBackendId(final String backendId) {
        return box.query().equal(TaskEntity_.backendId, backendId).build().findFirst();
    }

    public void updateTask(TaskEntity taskEntity) {
        TaskEntity local = getTaskByBackendId(taskEntity.getBackendId());
        if (local != null) {
            local.setCompleted(taskEntity.getCompleted());
            local.setProject(taskEntity.getProject());
            local.setTaskDescription(taskEntity.getTaskDescription());
            local.setTaskType(taskEntity.getTaskType());
        } else {
            local = taskEntity;
        }
        box.put(local);
    }

    protected void clearDb(){
        box.removeAll();
    }

}
