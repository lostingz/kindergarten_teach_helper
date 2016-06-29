/**
 * Chsi
 * Created on 2016年3月14日
 */
package com.teach.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.rtf.RtfWriter2;
import com.teach.config.ResourceURL;

/**
 * @author zhenggm<a href="mailto:zhenggm@chsi.com.cn">zhenggm</a>
 * @version $Id$
 */
public class WordUtil {
    public static void convertToWord(String html, String title, String type, String FilePath) {
        OutputStream out = null;
        try {
            String subFolder = "";
            if (StringUtils.isNotBlank(type)) {
                subFolder = type + "/";
            }
            File dir = new File(FilePath + subFolder);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File f = new File(FilePath + subFolder + "/" + title + ".doc");
            if (!f.exists()) {
                f.createNewFile();
            }
            out = new FileOutputStream(f);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = new Document();
        document.setPageSize(PageSize.A4);
        RtfWriter2 writer = RtfWriter2.getInstance(document, out);
        document.open();
        StyleSheet style = new StyleSheet();
        style.loadStyle("<br/>", null);
        style.loadStyle("<strong>", null);
        style.loadStyle("<span style='COLOR: #000; FONT-FAMILY: 宋体'>", null);
        Paragraph context = new Paragraph();
        try {
            List<Image> imgList = new ArrayList<Image>();
            List htmlList = HTMLWorker.parseToList(new StringReader(handleImage(html, imgList)), style);
            for (int i = 0; i < htmlList.size(); i++) {
                Element e = (Element) htmlList.get(i);
                context.add(e);
            }
            for (int i = 0; i < imgList.size(); i++) {
                context.add(imgList.get(i));
            }
            try {
                document.add(context);
            } catch (Exception e) {
            }

            document.close();
            System.out.println("生成" + title + ".doc完成");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private static String handleImage(String html, List<Image> imgList) throws BadElementException, IOException {
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        Elements elems = doc.select("img");
        int i = 0;
        if (elems.size() > 0) {
            for (org.jsoup.nodes.Element elem : elems) {
                String src = elem.attr("src");
                if (src.indexOf(ResourceURL.BASE) < 0) {
                    imgList.add(Image.getInstance(new URL(ResourceURL.BASE + src)));
                }
                elem.remove();
            }
        }
        return doc.html();
    }
}
