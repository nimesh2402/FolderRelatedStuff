import org.apache.commons.io.FileUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListOfFolders {
    public static void main(String[] str) throws Exception {
        String strMainFolder="C:\\Users\\niprajapati\\Desktop\\Main Folder\\AndroidStructureSample - Copy\\AndroidStructure\\app\\src\\main\\java\\com\\builderfly\\satson\\signupscreens";
        //listFoldersAndRemoves(strMainFolder,"signupscreenttwo");
        //removingIntentActionFilter("C:\\Users\\niprajapati\\Desktop\\Main Folder\\AndroidStructureSample - Copy\\AndroidStructure\\app\\src\\main\\AndroidManifest - Copy.xml");
        //addingIntentActionFilter();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new FileInputStream(new File("C:\\Users\\niprajapati\\Desktop\\DemoProject\\DemoProject\\Modules\\Login\\Login - Copy - Copy.storyboard")));
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        XPathExpression expression = xpath.compile("//scenes//viewController[@storyboardIdentifier='LoginVC3']//..//..");


        try {
            Node b13Node = (Node) expression.evaluate(doc, XPathConstants.NODE);
            b13Node.getParentNode().removeChild(b13Node);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.transform(new DOMSource(doc), new StreamResult(System.out));
            doc.normalize();
            DOMSource dm=new DOMSource(doc);
            StreamResult result=new StreamResult(new File("C:\\Users\\niprajapati\\Desktop\\DemoProject\\DemoProject\\Modules\\Login\\Login.storyboard"));
            t.transform(dm,result);
        } catch (DOMException e) {
            System.out.println("Node already deleted");
        }catch(Exception ee){
            System.out.println("Null pointer"+ee);
        }



        //element.getParentNode().removeChild(element);
        //
        //System.out.println(element);
        //prettyPrint(doc);

        //LoginTtwoActivity
    }
    public static final void prettyPrint(Document xml) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();

        tf.transform(new DOMSource(xml), new StreamResult(out));
        System.out.println(out.toString());

    }
        public static void addingIntentActionFilter() throws IOException {
         List<String> data= new ArrayList<String>();
         String strFileName="C:\\Users\\niprajapati\\Desktop\\Main Folder\\AndroidStructureSample - Copy\\AndroidStructure\\app\\src\\main\\AndroidManifest - Copy.xml";
         data=FileUtils.readLines(new File("C:\\Users\\niprajapati\\Desktop\\Main Folder\\AndroidStructureSample - Copy\\AndroidStructure\\app\\src\\main\\AndroidManifest - Copy.xml"));


         String strProjectIntentActionFilterTemplate=" <intent-filter>\n" +
                 "                <action android:name=\"android.intent.action.MAIN\" />\n" +
                 "                <category android:name=\"android.intent.category.LAUNCHER\" />\n" +
                 "            </intent-filter>";
         String strUpdatedProjectName=null;
         String temp="com.builderfly.satson.signupscreens.signupscreentone.SingUpToneActivity";
         List<String> updatedList=new ArrayList<String>();
         boolean flag=false;
         for(int i=0;i<data.size();i++) {
             if (data.get(i).contains(temp)){

                 updatedList.add(data.get(i));
                 updatedList.add((i+1),strProjectIntentActionFilterTemplate);
                 flag=true;
                 continue;
             }
              if(flag){
                 updatedList.add(i+1,data.get(i));

             }
             else {
                 if (!data.get(i).contains(temp)) {
                     updatedList.add(i, data.get(i));
                 }
             }
         }
         for(int i=0;i<updatedList.size();i++) {
             System.out.println(updatedList.get(i));
         }
         FileUtils.writeLines(new File(strFileName),updatedList);
     }
    public static void removingIntentActionFilter(String strFilePath) throws IOException {
        File file = new File(strFilePath);
        String fileContext = FileUtils.readFileToString(file);

        fileContext = fileContext.replaceFirst( " <intent-filter>\n" +
                "                <action android:name=\"android.intent.action.MAIN\" />\n" +
                "                <category android:name=\"android.intent.category.LAUNCHER\" />\n" +
                "            </intent-filter>","");
        FileUtils.write(file, fileContext);
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
}
