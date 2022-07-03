package proj.maxima;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Vector;

public class WMXtoTXT{
  
  public static File open() {
			FileDialog fileDialog = new FileDialog(new Frame(), "Load .wxm-file", FileDialog.LOAD);
			fileDialog.setFilenameFilter(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".wxm");
				}
				});
				fileDialog.setVisible(true);
				return new File(fileDialog.getDirectory() + fileDialog.getFile());
  }
  
  public static Vector<String> readFile(File wxmFile) {
	  Vector<String> input = null;
	  
	  try {
			FileReader fr = new FileReader(wxmFile);
			int anz;
			char buffer[] = new char[10];
			input = new Vector<String>();
			String temp = "";

			while ((anz = fr.read(buffer)) > 0) {
				for (int i = 0; i < anz; i++) {
					char z = buffer[i];

					if (z == '\n') {
						input.add(temp);
						if (temp == null)
							break;
						temp = "";
					} else
						temp += z;
				}
			}
			
			
			
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  return input;
  }
  
	public static String stripExtension(String str) {
		// Handle null case specially.

		if (str == null)
			return null;

		// Get position of last '.'.

		int pos = str.lastIndexOf(".");

		// If there wasn't any '.' just return the string as is.

		if (pos == -1)
			return str;

		// Otherwise return the string, up to the dot.

		return str.substring(0, pos);
	}
  
  public static void main(String[] args) throws IOException {
	  File wxmFile = open();
	  Vector<String> input = readFile(wxmFile);


	  File txtFile = new File(wxmFile.getParent()+"/"+stripExtension(wxmFile.getName())+".txt");
	  
	  
	  System.out.println(txtFile);
	  
	  FileWriter fr = new FileWriter(txtFile);
	  
	  for(int i = 0; i<input.size(); i++) {
		  if(input.get(i).length() > 0)
			  if(input.get(i).charAt(0) != '/')
				  fr.write(input.get(i));
	  }
	  
	  fr.close();
	  System.exit(0);
  }
}

