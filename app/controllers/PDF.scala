package controllers;

import play.api.data._
import play.api.data.Forms._
import play.api.http.Status
import play.api.libs.iteratee.Enumerator
import play.api.mvc._
import play.twirl.api.Html

import it.innove.play.pdf.PdfGenerator;

case class Thing(rand1: String, rand2: String)

object PDF {
    def Ok(thing: Html  ) : Result = { Ok(thing.body) }
    def Ok(thing: Result) : Result = {    thing       }
    def Ok(thing: String) : Result = {
        Result(
            ResponseHeader(Status.OK, Map.empty),
            Enumerator(thing.getBytes)
        )
    }

    val pdftest = Action { implicit request => {
        // in production code, we would cache the result of this, and timestamp
        // all the information we use to build it. when the user requests it again,
        // we check the timestamps of the last generated pdf, if the data hasn't
        // changed, we serve the cached one, else we generate it anew

        val data = Thing(
            "Two paths diverged in a wood, and I, I took the one less travelled by, and that has made all the difference.",
            "Come away oh human child, to the water and the wild, with a faery hand in hand, for the world's more full of weeping, than you can understand."
        )

        val userForm = Form(
            mapping("rand1" -> text, "rand2" -> text)
            (Thing.apply)
            (Thing.unapply)
        )
        val content = views.html.pdf.demo.render(userForm.fill(data));
        // val content = views.html.pdf.minimal.render();

        if (request.queryString.contains("html")) {
            Ok(content)
        } else {
            Ok(PdfGenerator.ok(content, "http://localhost:9000/").toScala)
        }
    }}
}
