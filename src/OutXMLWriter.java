import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutXMLWriter {
	private BufferedWriter out;

	public OutXMLWriter(String address) {
		try {
			out = new BufferedWriter(new FileWriter(new File(address)));
			out.write("<corpus>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startPage() {
		try {
			out.write("\t<page>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeSection(String content, String type) {
		try {
			out.write("\t\t<section type=\"" + type + "\">\n");
			out.write(content + "\n");
			out.write("\t\t</section>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void endPage() {
		try {
			out.write("\t</page>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			out.write("</corpus>\n");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
