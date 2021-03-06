package hu.bme.mit.codemodel.rifle.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Manages resources reading from the resources directory.
 * <p>
 * Separate resource types have their own getters, see {@link #query(String)}.
 */
public class ResourceReader {
    protected static String readFromResource(String fileName) {
        InputStream resourceAsStream = ResourceReader.class.getClassLoader().getResourceAsStream(fileName);

        try {
            return IOUtils.toString(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String query(String queryName) {
        return readFromResource("queries" + File.separator + queryName + ".cypher");
    }

    public static Collection<String> getImportExportQueries() {
        final String[] extensions = new String[]{ "cypher" };

        File importExportQueriesDirectory = new File(ResourceReader.class.getClassLoader().getResource("queries" + File.separator + "impex").getPath());
        Collection<File> importExportQueryFiles = FileUtils.listFiles(importExportQueriesDirectory, extensions, true);
        Collection<String> importExportQueries = new ArrayList<>();

        for (File file : importExportQueryFiles) {
            importExportQueries.add(readFromResource("queries" + File.separator + "impex" + File.separator + file.getName()));
        }

        return importExportQueries;
    }

    public static Collection<String> getAnalysisQueries() {
        final String[] extensions = new String[]{ "cypher" };

        File analysisQueriesDirectory = new File(ResourceReader.class.getClassLoader().getResource("queries" + File.separator + "analysis").getPath());
        Collection<File> analysisQueryFiles = FileUtils.listFiles(analysisQueriesDirectory, extensions, false);
        Collection<String> analysisQueries = new ArrayList<>();

        for (File file : analysisQueryFiles) {
            analysisQueries.add(readFromResource("queries" + File.separator + "analysis" + File.separator + file.getName()));
        }

        return analysisQueries;
    }

    public static Collection<String> getQualifierQueries() {
        final String[] extensions = new String[]{ "cypher" };

        File qualifierQueriesDirectory = new File(ResourceReader.class.getClassLoader().getResource("queries" + File.separator + "qualifier" + File.separator + "qualifiers").getPath());
        Collection<File> qualifierQueryFiles = FileUtils.listFiles(qualifierQueriesDirectory, extensions, false);
        Collection<String> qualifierQueries = new ArrayList<>();

        for (File file : qualifierQueryFiles) {
            qualifierQueries.add(readFromResource("queries" + File.separator + "qualifier" + File.separator + "qualifiers" + File.separator + file.getName()));
        }

        return qualifierQueries;
    }

    public static Collection<String> getQualifierProviderQueries() {
        final String[] extensions = new String[]{ "cypher" };

        File qualifierProviderQueriesDirectory = new File(ResourceReader.class.getClassLoader().getResource("queries" + File.separator + "qualifier" + File.separator + "qualifierProviders").getPath());
        Collection<File> qualifierProviderQueryFiles = FileUtils.listFiles(qualifierProviderQueriesDirectory, extensions, false);
        Collection<String> qualifierProviderQueries = new ArrayList<>();

        for (File file : qualifierProviderQueryFiles) {
            qualifierProviderQueries.add(readFromResource("queries" + File.separator + "qualifier" + File.separator + "qualifierProviders" + File.separator + file.getName()));
        }

        return qualifierProviderQueries;
    }
}
