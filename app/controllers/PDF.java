package controllers;

import play.*;
import play.mvc.*;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;

import java.io.File;
import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PDF extends Controller {
    private static CYaHPConverter converter = new CYaHPConverter();
    private static Map<String,Object> properties = new HashMap<String,Object>();
    private static List<Object> headerFooterList = new ArrayList<Object>();

    static {
        properties.put(
            IHtmlToPdfTransformer.PDF_RENDERER_CLASS,
            IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER
        );
        // headerFooterList.add(
        //     new IHtmlToPdfTransformer.CHeaderFooter(
        //         "<table width=\"100%\">" +
        //         "<tbody><tr><td align=\"left\">Generated with YaHPConverter.</td><td align=\"right\">Page <pagenumber>/<pagecount></td></tr></tbody></table>",
        //         IHtmlToPdfTransformer.CHeaderFooter.HEADER
        //     )
        // );
        // headerFooterList.add(
        //     new IHtmlToPdfTransformer.CHeaderFooter(
        //         "© 2011 Quentin Anciaux",
        //         IHtmlToPdfTransformer.CHeaderFooter.FOOTER
        //     )
        // );
    }

    public static Result pdftest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String content = "Blah blah content.";

        try {
            converter.convertToPdf(
                content,
                IHtmlToPdfTransformer.A4P,
                headerFooterList,
                "http://localhost:9000/",
                out,
                properties
            );
        } catch (IHtmlToPdfTransformer.CConvertException e) {
            e.printStackTrace();
            throw new Error(e.toString());
        }

        response().setContentType("application/PDF");
        return ok(out.toByteArray());
    }
}
