import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PhysicsDefinitions1 {
	public static void main(String[] args) throws IOException {
		OutXMLWriter out = new OutXMLWriter("data/def1.xml");

		Document doc = Jsoup
				.parse(new File("data/phdefinitions.html"), "UTF-8");
		Elements elementsByTag = doc.getElementsByTag("p");

		out.startPage();
		for (Element e : elementsByTag) {
			if (e.text().trim().length() > 5)
				out.writeSection(e.text(), "pd1-p");
		}
		out.endPage();
		out.close();

	}
}
