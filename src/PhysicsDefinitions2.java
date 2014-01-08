import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PhysicsDefinitions2 {
	public static void main(String[] args) throws IOException {
		OutXMLWriter out = new OutXMLWriter("data/def2.xml");

		Document doc = Jsoup.parse(new File("data/phdefinitions2.html"),
				"UTF-8");
		Elements elementsByTag = doc.getElementsByTag("p");

		out.startPage();
		int index = 0;
		for (Element e : elementsByTag) {
			if (e.html().contains("-------"))
				continue;
			String[] split = e.html().split("<br />");
			for (String line : split) {
				index++;
				if (index < 5 || line.contains("Return to Home Page"))
					continue;
				line = line.replaceFirst(":", " is");
				line = Jsoup.parse(line).text();

				out.writeSection(line, "pd2-p-line");
			}
			// if (e.text().trim().length() > 5)
		}
		out.endPage();
		out.close();

	}
}
