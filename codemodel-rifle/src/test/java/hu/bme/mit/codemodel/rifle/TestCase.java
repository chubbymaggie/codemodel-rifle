package hu.bme.mit.codemodel.rifle;

import hu.bme.mit.codemodel.rifle.database.ResourceReader;

import java.io.File;

public class TestCase {

    protected static final String branchId = "dummyTest";
    protected static final String sessionId = "dummyTest";

    protected String getTestResourcesFolderPath(String testMethodName) {
        try {
            String testResourceFolderWithinResources = this.getClass().getSimpleName() + File.separator +
                testMethodName;
            String path = ResourceReader.class.getClassLoader().getResource(testResourceFolderWithinResources).getPath();
            return path;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

}
