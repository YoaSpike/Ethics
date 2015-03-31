import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;
import com.avaje.ebean.TxRunnable;

import play.Play;
import play.Logger;
import play.Application;
import play.GlobalSettings;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;


public class Global extends GlobalSettings {
    public void onStart(Application app) {
        File folder = new File("app/fixtures");
        if (folder == null || !folder.canRead()) {
            System.out.println("Couldn't load fixtures");
            return;
        }

        for (File file : folder.listFiles()) {
            loadFromFile(file);
        }
    }

    public void loadFromFile(File file) {
        Yaml yaml = new Yaml(
            new CustomClassLoaderConstructor(Play.application().classloader())
        );
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException fnfe) { return; }

        Map<String, List<Object>> tableMap = (Map<String, List<Object>>) yaml.load(fis);  //yaml must be in conf folder?

        try {
            fis.close();
        } catch (IOException ioe) {}

        System.out.println("Load order: " + tableMap.entrySet());

        SqlUpdate disable = Ebean.createSqlUpdate("SET REFERENTIAL_INTEGRITY FALSE;"),
                  enable = Ebean.createSqlUpdate("SET REFERENTIAL_INTEGRITY TRUE;");

        Ebean.execute(new TxRunnable() {
            public void run() {
                Ebean.execute(disable); // disable;

                for (Map.Entry<String, List<Object>> tableEntry : tableMap.entrySet()) {
                    // delete old entries
                    Class<?> klass = tableEntry.getValue().getClass();

                    save(
                        tableEntry.getKey(),
                        tableEntry.getValue()
                    );
                }

                Ebean.execute(enable); // re-enable
            }
        });
    }

    private void save(String key, List<?> value) {
        Class<?> klass = value.get(0).getClass();

        Logger.info("Deleting {}", klass);
        Ebean.delete(Ebean.find(klass).findList());

        // add new entries
        Logger.info("Loading {}", key);
        Ebean.save(value);
    }
}
