import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExtractUrls {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"data/dump-url-2")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"data/links.txt")));
		String line = "";
		while ((line = br.readLine()) != null) {
			if (line.startsWith("URL::")) {
				bw.write(line.substring(5) + "\n");
			}
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
