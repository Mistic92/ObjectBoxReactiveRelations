package pl.lukaszbyjos.relationstest.db;

import java.util.Date;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.objectbox.rx.RxQuery;
import io.reactivex.Observable;

public class ProjectRepo {
    private Box<ProjectEntity> box;
    private TaskRepo mTaskRepo;

    public ProjectRepo(BoxStore boxStore, TaskRepo taskRepo) {
        box = boxStore.boxFor(ProjectEntity.class);
        mTaskRepo = taskRepo;
    }

    public void clearDb() {
        box.removeAll();
        mTaskRepo.clearDb();
    }

    public void updateProject(ProjectEntity projectEntity) {
        box.put(projectEntity);
    }

    public ProjectEntity getFirst() {
        return box.getAll().get(0);
    }

    public void addNewProject(ProjectEntity projectEntity) {
        for (TaskEntity taskEntity : projectEntity.getTasks()) {
            mTaskRepo.addTask(taskEntity);
        }
        box.put(projectEntity);
    }

    public Observable<List<ProjectEntity>> getEndedProjects() {
        Query<ProjectEntity> query = box.query()
                .less(ProjectEntity_.projectEndDate, new Date())
                .orderDesc(ProjectEntity_.projectEndDate)
                .build();
        return RxQuery.observable(query);
    }
}
