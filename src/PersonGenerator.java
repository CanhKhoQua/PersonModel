import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args)
    {
        ArrayList<String> folks = new ArrayList<>();
        boolean done = false;
        Scanner in = new Scanner(System.in);
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");


    /*    a.	ID (a String)
        b.	FirstName
        c.	LastName
        d.	Title (a string like Mr., Mrs., Ms., Dr., etc.)
        e.	YearOfBirth (an int)
*/

        String personRec = "";
        String ID = "";
        String FirstName = "";
        String LastName = "";
        String Title = "";
        int YOB = 0;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Please enter your ID [6 digits]: ");
            FirstName = SafeInput.getNonZeroLenString(in, "Please enter your first name: ");
            LastName = SafeInput.getNonZeroLenString(in, "Please enter your last name: ");
            Title = SafeInput.getNonZeroLenString(in, "Please enter your title: ");
            YOB = SafeInput.getRangedInt(in, "Please enter your year of birth ", 1000, 2023);

            personRec = ID + ", " + FirstName + ", " + LastName + ", " + Title + ", " + YOB;

            folks.add(personRec);

            done = SafeInput.getYNConfirm(in, "Are you done ?");
        }while(!done);

        for(String p:folks)
            System.out.println(p);

        try
        {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file,CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

           for (String rec : folks)
           {
               writer.write(rec, 0, rec.length());
               writer.newLine();
           }
           writer.close();
           System.out.println("Data file is written!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
