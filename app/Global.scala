import java.io.File
import java.util.List
import java.util.ArrayList
import java.io.FileInputStream

import scala.collection.JavaConversions._

import play.Play
import play.Logger
import play.Application
import play.GlobalSettings
import play.db.ebean._

import com.avaje.ebean.Ebean
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor

import org.joda.time.DateTime;
import play.data.format.Formatters;
import views.formdata.JodaDateFormatter;


class Global extends GlobalSettings {
    override
    def onStart(app: Application) {

        Formatters.register(classOf[DateTime], new JodaDateFormatter())

        // val users = models.UserModel.find.all
        // if (users.size != 0) throw new Error("Restart application to load new fixtures")

        val folder = new File("app/fixtures")

        if (folder == null || !folder.canRead()) {
            play.Logger.error("Couldn't load fixtures")

        } else {
            play.Logger.info("Loading fixtures")

            disable_integrity()
            load_models(folder)
            enable_integrity()
        }
    }

    def exec(s: String) {
        Ebean.execute(Ebean.createSqlUpdate(s))
    }

    def load_models(folder: File) = {
        val all_models = (
            folder
            .listFiles
            .map(getYaml)
            .map(thing => thing.toList.map(_.toList))
        )

        for (model_file <- all_models) {
            for (models <- model_file) {
                save(new ArrayList(models))
            }
        }
    }

    def integrity(set: String) = () => exec("SET REFERENTIAL_INTEGRITY " + set + ";")

    val disable_integrity = integrity("FALSE")
    val  enable_integrity = integrity("TRUE")

    def getYaml(file: File) : List[List[Any]] = {
        val yaml = new Yaml(
            new CustomClassLoaderConstructor(Play.application().classloader())
        )

        val fis = new FileInputStream(file)

        if (fis != null) {
            val tableMap = yaml.load(fis)  //yaml must be in conf folder?

            fis.close

            return tableMap.asInstanceOf[List[List[Any]]]
        } else {
            throw new Error();
        }
    }

    def save(value: List[Any]) {
        val klass = value.toList.get(0).getClass()

        Logger.info("Deleting {}", klass)
        Ebean.delete(Ebean.find(klass).findList)

        // add new entries
        Logger.info("Loading {}", klass)
        value.foreach(Ebean.save)
    }
}
