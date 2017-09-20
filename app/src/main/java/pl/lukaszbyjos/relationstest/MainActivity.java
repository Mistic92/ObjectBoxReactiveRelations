package pl.lukaszbyjos.relationstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.objectbox.BoxStore;
import pl.lukaszbyjos.relationstest.db.ProjectEntity;
import pl.lukaszbyjos.relationstest.db.ProjectRepo;
import pl.lukaszbyjos.relationstest.db.TaskEntity;
import pl.lukaszbyjos.relationstest.db.TaskRepo;

public class MainActivity extends AppCompatActivity {

    public static final String BACKEND_ID_TO_EDIT = "dasok-1234-123333";
    public static final String TAG = "LOL";
    BoxStore boxStore;
    private TaskRepo mTaskRepo;
    private ProjectRepo mProjectRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boxStore = ((AppClass) getApplication()).getBoxStore();
        mTaskRepo = new TaskRepo(boxStore);
        mProjectRepo = new ProjectRepo(boxStore, mTaskRepo);
        findViewById(R.id.editEntity).setOnClickListener(view -> {
            Log.d(TAG, "updating");
            final TaskEntity taskEntity = mTaskRepo.getTaskByBackendId(BACKEND_ID_TO_EDIT);
            taskEntity.setCompleted(!taskEntity.getCompleted());
            mTaskRepo.updateTask(taskEntity);
            Log.d(TAG, "updated");
        });

        findViewById(R.id.addProjectButton).setOnClickListener(view -> {
            addTestProject();
        });
        findViewById(R.id.editProject).setOnClickListener(view -> {
            updateProjectField();
        });

        mProjectRepo.getEndedProjects()
                .subscribe(projectEntities -> {
                    projectEntities.forEach(projectEntity -> {
                        Log.d(TAG, "tostring: " + projectEntity.toString());
                    });
                });

    }


    private void updateProjectField() {
        final ProjectEntity projectEntity = mProjectRepo.getFirst();
        Random r = new Random();
        projectEntity.setJustNumberLol(r.nextInt(50));
        mProjectRepo.updateProject(projectEntity);
    }

    private void addTestProject() {

        mProjectRepo.clearDb();

        ProjectEntity project = new ProjectEntity();
        project.setProjectDescription("bla bla description");
        project.setProjectEndDate(new Date(8180353614L));


        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setProject(project);
        taskEntity1.setTaskType("BLEH");
        taskEntity1.setTaskDescription("i dont know");
        taskEntity1.setBackendId(BACKEND_ID_TO_EDIT);
        taskEntity1.setCompleted(false);

        TaskEntity taskEntity2 = new TaskEntity();
        taskEntity2.setProject(project);
        taskEntity2.setTaskType("BLEH");
        taskEntity2.setTaskDescription("i like pancakes");
        taskEntity2.setBackendId("dasok-1234-111111");
        taskEntity2.setCompleted(false);

        TaskEntity taskEntity3 = new TaskEntity();
        taskEntity3.setProject(project);
        taskEntity3.setTaskType("NOPE");
        taskEntity3.setTaskDescription("do what you want");
        taskEntity3.setBackendId("dasok-1234-842158");
        taskEntity3.setCompleted(false);

        final List<TaskEntity> taskEntities = Arrays.asList(taskEntity1, taskEntity2, taskEntity3);

        project.setTasks(taskEntities);

        mProjectRepo.addNewProject(project);

    }
}
