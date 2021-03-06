package no.ntnu.ais.fmu4j.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FmuBuilder extends DefaultTask {

    @Input
    private List<String> mainClasses = new ArrayList<>();

    public List<String> getMainClasses() {
        return mainClasses;
    }

    public void setMainClasses(List<String> mainClass) {
        this.mainClasses = mainClass;
    }

    @TaskAction
    void fmu4j() {

        String archiveBaseName = getProject().getName();
        File jar = new File(getProject().getBuildDir(), "libs/" + archiveBaseName + ".jar").getAbsoluteFile();

        for (String mainClass : mainClasses) {
            no.ntnu.ais.fmu4j.FmuBuilder builder = new no.ntnu.ais.fmu4j.FmuBuilder(mainClass, jar,  null);
            builder.build(new File(getProject().getBuildDir(), "fmus"));
        }
    }

}
