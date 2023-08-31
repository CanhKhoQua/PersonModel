import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {
    public static void main(String[] args)
    {
        JFileChooser chooser= new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();

        String ID,FirstName,LastName,Title;
        int YOB = 0;

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                //read file
                //process it into arraylist
                //line counter
                while(reader.ready())
                {
                    rec = reader.readLine();
                    lines.add(rec);
                }
                reader.close();

                //Decorate a table
                System.out.printf("\n%-8s%-25s%-25s%-6s%6s", "ID", "First Name", "Last Name", "Title", "YOB");
                System.out.println();
                for(int i =0; i<71; i++)
                {
                    System.out.printf("-");
                }

                String[] fields;
                for(String l : lines)
                {
                    fields = l.split(","); //split the record into the fields
                    if(fields.length == 5)
                    {
                        ID = fields[0].trim();
                        FirstName = fields[1].trim();
                        LastName = fields[2].trim();
                        Title = fields[3].trim();
                        YOB = Integer.parseInt(fields[4].trim());

                        System.out.printf("\n%-8s%-25s%-25s%-6s%6d", ID, FirstName, LastName, Title, YOB);
                    }
                    else
                    {
                        System.out.println("Found a record that may corrupt: ");
                        System.out.println(l);

                    }
                }
            }
            else
            {
                System.out.println("Failed to choose a file to read");
                System.out.println("Please run the program again!");
                System.exit(0);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
