package business;

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.{Constructor, CustomClassLoaderConstructor}
import java.io._

import scala.beans.BeanProperty
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

class ThingSorter extends Object with scala.math.Ordering[String] {
    val RE = "(\\d+)([a-z])?".r
    val ALPHA = "abcdefghijklmnopqrstuvwxyz"

    def compare(x: String, y: String) : Int = {
        if (x == null || y == null) {
            0
        } else {
            println(x, y, get_val(x), get_val(y))
            get_val(x) - get_val(y)
        }
    }

    def get_val(q: String) : Int = {
        val inter = (int: String) => Integer.parseInt(int) * 10

        q match {
            case RE(int, null)  => inter(int)
            case RE(int, alpha) => inter(int) + ALPHA.indexOf(alpha) + 1
            case _              => 0
        }
    }
}


case class Section(name: String, questions: Map[String,String])

object Sections {
    val SECTIONS = "app\\sections.yaml"
    var _sections : List[Section] = null
    var _section_map : Map[String,Section] = null

    def load_sections() : List[Section] = {
        if (_sections == null) {
            val yaml = new Yaml()

            val fis = new FileInputStream(new File(SECTIONS))

            var raw_yaml = yaml.load(fis).asInstanceOf[java.util.List[java.util.Map[String,Any]]]
            _sections = raw_yaml.map(map => Section(
                map.get("name").asInstanceOf[String],
                map.get("questions").asInstanceOf[java.util.Map[String,String]].toMap
            )).toList

            _section_map = (for (tup <- _sections) yield (tup.name, tup)).toMap

            fis.close
        }

        _sections
    }

    def get_questions(section_name: String) : List[String] = {
        Sections.load_sections()

        _section_map.get(section_name) match {
            case Some(value) => value.questions.entrySet.toList.sorted(new ThingSorter())
            case None        => List()
        }
    }

    def get_sections_with_numbers() : List[(Int,String)] = {
        Sections.load_sections()

        (
            for {i <- Range(0, _sections.size)}
            yield (i + 1, _sections(i).name)
        ).toList
    }
}