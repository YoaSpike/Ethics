package views.foundation

import play.twirl.api.Html;
import scala.collection.immutable.Seq;

object Helpers {

  import views.html.helper.FieldConstructor
  implicit val foundationFields = FieldConstructor(views.html.foundation.foundationFieldConstructor.f)

  def swapIf(condition: Any) (firstBit: Html) (secondBit: Html) : Html = {

    val proper_condition = condition == true;
    System.out.println(condition);
    System.out.println(proper_condition);

    return new Html(
      if (proper_condition) {
        Seq[Html](secondBit, firstBit)
      } else {
        Seq[Html](firstBit, secondBit)
      }
    );
  }
}
