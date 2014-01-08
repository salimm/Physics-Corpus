import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExtractWikiWebpages {
	public static void main(String[] args) throws IOException {
		File nutchdump = new File("data/wiki-dump");
		String outdir = "data/wiki-pages";

		File f = new File(outdir);
		if (!f.exists())
			f.mkdirs();

		String line = "";

		BufferedWriter out = null;
		int index = 0;
		boolean inpage = false;
		BufferedReader in = new BufferedReader(new FileReader(new File("")));
		while ((line = in .readLine()) != null) {
			if (line.toLowerCase().contains("<html")) {
				if (out != null) {
					out.flush();
					out.close();
				}
				out = new BufferedWriter(new FileWriter(new File(outdir + "/"
						+ (++index) + ".html")));
				inpage = true;
			}
			if (inpage)
				out.write(line + "\n");
			if (line.toLowerCase().contains("</html>")) {
				out.flush();
				out.close();
				out = null;
				inpage=false;
			}

		}

	}

}
