import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Directory_Rename {

    public static void main(String[] str) throws IOException {

        String oldDirPath = "C:\\Users\\niprajapati\\Desktop\\Main Folder\\4th";
           /* File dir = new File(oldDirPath);
            if (!dir.isDirectory()) {
                System.err.println("There is no directory at given path");
                System.exit(0);
            }
            String newdir = "5d";

            File newDir = new File(dir.getParent() + "\\" + newdir );
            dir.renameTo(newDir);
            FileUtils.deleteQuietly(newDir);
            System.out.println("Done");*/

        Collection<File> lst=new ArrayList<File>();
        List<String> txtfile= new ArrayList<String>();
        List<String> data= new ArrayList<String>();
        String[] str1=new String[10];
        str1[0]="txt";

        str1[1]="rar";

        lst=FileUtils.listFiles(new File("C:\\Users\\niprajapati\\Desktop\\Main Folder"),str1,true);

        Iterator it=lst.iterator();
        while(((Iterator) it).hasNext()){
            System.out.println(it.next());
            txtfile.add(it.next().toString());
        }
    /*        for(int i=0;i<txtfile.size();i++){
                if(txtfile.get(i).endsWith("txt")) {
                    data=FileUtils.readLines(new File(txtfile.get(i)));
                }
                }*/
        data=FileUtils.readLines(new File("C:\\Users\\niprajapati\\Desktop\\Main Folder\\1st\\New Text Document.txt"));


        String strProjectNameAsPerTemplate=null;
        String strUpdatedProjectName=null;
        String temp="import.zaptech."+"projectNameZap";
        List<String> updatedList=new ArrayList<String>();
        for(int i=0;i<data.size();i++) {
            if (i==0){
                updatedList.add(i,temp);
            }
            else
                updatedList.add(i,data.get(i));


        }
        for(int i=0;i<updatedList.size();i++) {
            System.out.println(updatedList.get(i));
        }


        File file = new File("C:\\Users\\niprajapati\\Desktop\\Main Folder\\AndroidStructureSample - Copy\\AndroidStructure\\app\\build.gradle");
        String fileContext = FileUtils.readFileToString(file);

        fileContext = fileContext.replaceFirst( "com.builderfly.satson","com.builderfly.json");
        FileUtils.write(file, fileContext);

    }
}
