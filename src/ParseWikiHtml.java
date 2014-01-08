
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseWikiHtml {

	public static void main(String[] args) throws IOException {

		FileWriter outsections = new FileWriter(new File("wiki-sections.xml"));
		FileWriter outpars = new FileWriter(new File("wiki-paragraphs.xml"));

		String dir = "data/wiki-pages";
		Collection<File> files = FileUtils.listFiles(new File(dir),
				new RegexFileFilter(".*\\.(html)"),
				DirectoryFileFilter.DIRECTORY);

		outsections.write("<corpus>\n");
		outpars.write("<corpus>\n");
		int pindex = 0;
		for (File file : files) {
			pindex++;
			outsections.write("\t<page id=\"" + pindex + "\">\n");
			outpars.write("\t<page id=\"" + pindex + "\">\n");
			Document doc = Jsoup.parse(file, "UTF-8");
			Elements sections = doc.select("#mw-content-text *");
			Elements paragraphs = doc.select("#mw-content-text p");

			for (Element element : sections) {
				String tmp = Jsoup.parse(element.text()).text();
				;
				if (tmp.trim().length() > 10) {
					outsections.write("\t\t<sections type=\"wiki-"
							+ element.tagName() + "\">\n");
					outsections.write("\t\t\t" + tmp + "\n");
					outsections.write("\t\t</sections>\n");
				}
			}

			for (Element element : paragraphs) {
				String tmp = Jsoup.parse(element.text()).text();
				;
				if (tmp.trim().length() > 10) {
					outpars.write("\t\t<sections type=\"wiki-"
							+ element.tagName() + "\">\n");
					outpars.write("\t\t\t" + tmp + "\n");
					outpars.write("\t\t</sections>\n");
				}
			}

			outsections.write("\t</page>\n");
			outpars.write("\t</page>\n");
		}

		outsections.write("</corpus>\n");
		outpars.write("</corpus>\n");

		outpars.flush();
		outpars.close();
		outsections.flush();
		outsections.close();
	}
}
