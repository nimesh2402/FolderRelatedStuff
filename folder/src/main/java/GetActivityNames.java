import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class GetActivityNames {

    private static ArrayList<String> strTotalActivity=new ArrayList<String>();
    private static String strURL ="C:\\Users\\niprajapati\\Desktop\\Main Folder\\AndroidStructure\\app\\src\\main\\java\\com\\builderfly\\satson";
    private static String folderNames(String strURL){
        //String strURL ="C:\\Users\\niprajapati\\Desktop\\Main Folder\\AndroidStructure\\app\\src\\main\\java\\com\\builderfly\\satson";

        String strFolderNames="";
        File folder=new File(strURL);
        File[] listOfFiles = folder.listFiles();
        String fileName = "";
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
            } else if (listOfFiles[i].isDirectory()) {
                strFolderNames=strFolderNames+listOfFiles[i].getName()+",";
                //      System.out.println("Directory " + strFolderNames);
            }
        }
        //System.out.println("folder-ccmma " +strFolderNames.substring(0, strFolderNames.length() - 1));


        return strFolderNames;
    }

    public static void fileNames(String folderName){

        String strPath=strURL+File.separator+folderName;

        File folder=new File(strPath);
        File[] listOfFiles = folder.listFiles();

        String strFolderNames="";
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                strFolderNames = strFolderNames + listOfFiles[i].getName() + ",";
                strTotalActivity.add(strFolderNames);
            } else if (listOfFiles[i].isDirectory()) {

                //      System.out.println("Directory " + strFolderName
            }
        }
    }
    public static void fileNamesLast(String folderName){



        File folder=new File(folderName);
        File[] listOfFiles = folder.listFiles();

        String strFolderNames="";
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                //strFolderNames = strFolderNames + listOfFiles[i].getName() + ",";
                strTotalActivity.add((strFolderNames + listOfFiles[i].getName()).replace(".java",""));
            } else if (listOfFiles[i].isDirectory()) {

                //      System.out.println("Directory " + strFolderName
            }
        }
    }
    public static void listFoldersAndRemoves(String directoryName,String folterTobeDeleted){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isDirectory()){
                System.out.println(file.getName());
                System.out.println(file.getAbsolutePath());
                if(file.getName().equalsIgnoreCase(folterTobeDeleted)){
                    FileUtils.deleteQuietly(new File(file.getAbsolutePath()));
                }

            }
        }
    }
    public static void main(String[] str){

         // TODO Deleting
        // Deleting listed folder from sub folder
        String[] arrfoldertobeDeleted={"signupscreentone - Copy (4)","signupscreentone - Copy (3)","signupscreentone - Copy (2)","loginscreentone - Copy (4)","loginscreentone - Copy (3)","loginscreentone - Copy (2)"};
        for(int t=0;t<arrfoldertobeDeleted.length;t++){
            String[] arrFolderNames1 = folderNames(strURL).split(",");
            for (int i = 0; i < arrFolderNames1.length; i++) {

                //System.out.println("Folder Name: " + arrFolderNames[i]);
                String[] arrLastFolderNames1 = folderNames(strURL + File.separator + arrFolderNames1[i]).split(",");
                for (int j = 0; j < arrLastFolderNames1.length; j++) {
                    String strurl = strURL + File.separator + arrFolderNames1[i];
                    //System.out.println(arrLastFolderNames1[j]);
                    listFoldersAndRemoves(strurl, arrfoldertobeDeleted[t]);
                }
            }
        }
        // Deleting folder ends here


        //Listing out all the activity java file names which are important
        String[] arrFolderNames=folderNames(strURL).split(",");
        for(int i=0;i<arrFolderNames.length;i++) {

            //System.out.println("Folder Name: " + arrFolderNames[i]);
            String[] arrLastFolderNames = folderNames(strURL + File.separator + arrFolderNames[i]).split(",");
            for (int j = 0; j < arrLastFolderNames.length; j++) {
                System.out.println(strURL + File.separator + arrFolderNames[i] + File.separator + arrLastFolderNames[j]);
                String arrLastToLastFolderName = (strURL + File.separator + arrFolderNames[i] + File.separator + arrLastFolderNames[j]);

                fileNamesLast(arrLastToLastFolderName);
                System.out.println("***********************");
            }
        }

        for(int p=0;p<strTotalActivity.size();p++){
            System.out.println("From List: "+strTotalActivity.get(p));
           }
         // Listing activity class files ends here



    }
}
