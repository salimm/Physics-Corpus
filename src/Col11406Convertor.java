import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Col11406Convertor {
	public static void main(String[] args) throws IOException {
		OutXMLWriter out = new OutXMLWriter("data/col11406.xml");

		Collection<File> files = FileUtils.listFiles(new File(
				"data/col11406/content/"), new RegexFileFilter(".*\\.(html)"),
				DirectoryFileFilter.DIRECTORY);
		for (File file : files) {
			Document doc = Jsoup.parse(file, "UTF-8");
			Elements elementsByTag = doc.getElementsByTag("p");
			out.startPage();
			for (Element e : elementsByTag) {
				if (e.text().trim().split(" ").length > 6)
					out.writeSection(e.text(), "col11406-p");
			}
			out.endPage(); 
		}
		
		out.close();
	}

}
