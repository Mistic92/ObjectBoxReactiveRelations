package pl.lukaszbyjos.relationstest;

import android.app.Application;

import io.objectbox.BoxStore;
import pl.lukaszbyjos.relationstest.db.MyObjectBox;

public class AppClass extends Application {

    private BoxStore mBoxStore;

    public BoxStore getBoxStore() {
        return mBoxStore;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBoxStore = MyObjectBox.builder().androidContext(this).build();
    }
}
