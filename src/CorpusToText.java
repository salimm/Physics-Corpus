import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class CorpusToText {
	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
		String dir = "data/corpus";
		Collection<File> files = FileUtils.listFiles(new File(dir),
				new RegexFileFilter(".*\\.(xml)"),
				DirectoryFileFilter.DIRECTORY);
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("data/corpus-text.txt")));
		for (File f : files) {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = dBuilder.parse(f);
			out.write(doc.getTextContent()+"\n");
			out.flush();
		}

	}
}
