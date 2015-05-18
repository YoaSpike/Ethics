package business

import java.net.URL
import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream, InputStream}

import play.twirl.api.Html

import org.fit.cssbox.io.{DefaultDOMSource, DocumentSource}
import org.fit.cssbox.css.{CSSNorm, DOMAnalyzer, NormalOutput}


case class StringDocumentSource(source: String) extends DocumentSource(null) {
    // Closes the document source.
    def close() = {}
    // Obtains the MIME content type of the target document.
    def getContentType() : String = "text/html"
    // Obtains the input stream for reading the referenced document.
    def getInputStream() : InputStream = new ByteArrayInputStream(
        this.source.getBytes
    )
    // Obtains the final URL of the obtained document.
    def getURL() : URL = null
}


object Email {
    def inlineCSS(input: Html) : Html = {
        Html(inlineCSS(input.body))
    }

    def inlineCSS(input: String) : String = {
        // Open the network connection
        val docSource = StringDocumentSource(input)

        // Parse the input document
        val parser = new DefaultDOMSource(docSource)
        val doc = parser.parse()

        // Create the CSS analyzer
        val da = new DOMAnalyzer(doc, docSource.getURL())
        da.attributesToStyles() // convert the HTML presentation attributes to inline styles
        da.addStyleSheet(null, CSSNorm.stdStyleSheet(),   DOMAnalyzer.Origin.AGENT) // use the standard style sheet
        da.addStyleSheet(null, CSSNorm.userStyleSheet(),  DOMAnalyzer.Origin.AGENT) // use the additional style sheet
        da.addStyleSheet(null, CSSNorm.formsStyleSheet(), DOMAnalyzer.Origin.AGENT) // (optional) use the forms style sheet
        da.getStyleSheets() // load the author style sheets

        // Compute the styles
        da.stylesToDomInherited()

        // Save the output
        val baos = new ByteArrayOutputStream()
        new NormalOutput(doc).dumpTo(baos)

        baos.toString
    }
}
