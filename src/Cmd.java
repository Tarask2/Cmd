import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cmd {

    public static void main(String[] args) {
        try {
            operation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cdTwoDots(Home home){
        Path path = Paths.get(home.getHome());
   `     home.setHome(path.getParent().toString());
    }

    private static void cd(Home home, String[] tab) {
        Path path = Paths.get(home.getHome());
        Path an = path.resolve(tab[1]);
        home.setHome(an.toString());
    }

    private static void dir(String text) {
        File file = new File(text);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files){
                System.out.println(f.getName());
            }
        }
    }

    private static void help(){
        System.out.println("CD " + " Displays the name of or changes the current directory.");
        System.out.println("MKDIR " + " Creates a directory.");
        System.out.println("DIR " + " Displays a list of files and subdirectories in a directory.");
        System.out.println("EXIT " + " Quits the CMD.EXE program (command interpreter).");
        System.out.println("RENAME " + " Renames a file or files.");
        System.out.println("DEL " + " Deletes one or more files.");
    }

    private static void mkdir(String t,String[] text){
        File file = new File(t + text[1]);
        if (file.mkdir()){
            System.out.println("Directory is created");
        } else if (file.mkdirs()){
            System.out.println("Directories are created");
        } else {
            System.out.println("Directory isn't created");
        }
    }

    private static void rename(String text, String[] tab){
        File file = new File(text + tab[1]);
        File renameFile = new File(text + tab[2]);
        if (file.renameTo(renameFile)){
            System.out.println("Directory is rename");
        } else {
            System.out.println("Directory isn't rename");
        }
    }

    private static void del(String text,String[] tab){
        File file = new File(text + tab[1]);
        if (file.delete()){
            System.out.println("Directory is deleted");
        } else {
            System.out.println("Directory isn't deleted");
        }
    }

    private static void operation() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        Home home = new Home();
        while (flag){
            System.out.print(home.getHome()+ " ");
            String operation = reader.readLine();
            String[] tab = operation.split(" ");
            switch (tab[0]) {
                case "cd": cd(home, tab);break;
                case "cd..": cdTwoDots(home);break;
                case "mkdir": mkdir(home.getHome(), tab);break;
                case "help": help();break;
                case "dir": dir(home.getHome());break;
                case "rename": rename(home.getHome(), tab);break;
                case "del": del(home.getHome(), tab);break;
                case "exit": flag = false;break;
            }
        }
        reader.close();
    }

}




