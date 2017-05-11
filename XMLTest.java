package com.example;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xiaosong on 2017/5/11.
 */
public class XMLTest {

    @Test
    public void createXml(){
        XStream xStream =new XStream(new XppDriver(new   XmlFriendlyReplacer("_-", "_")));
        xStream.autodetectAnnotations(true);
        String xs = xStream.toXML(new NotifyBean("SUCCESS", "OK"));
        System.out.println(xs);
   //     System.out.println(createXML());
    }

    public String createXML(){
        String xmlStr = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document.setXmlVersion("1.0");

            Element root = document.createElement("xml");
            document.appendChild(root);

            Element return_code = document.createElement("return_code");
            return_code.setTextContent("SUCCESS");
            root.appendChild(return_code);

            Element return_msg = document.createElement("return_msg");
            return_msg.setTextContent("OK");
            root.appendChild(return_msg);

            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transFormer = transFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            //export string
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            transFormer.transform(domSource, new StreamResult(bos));
            xmlStr = bos.toString();

            //-------
            //save as file
            File file = new File("TelePhone.xml");
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            StreamResult xmlResult = new StreamResult(out);
            transFormer.transform(domSource, xmlResult);
            //--------
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return xmlStr;
    }

}
