package ua.edu.sumdu.ta.shestak.maksym.pr7;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ua.edu.sumdu.ta.shestak.maksym.pr6_additional.AbstractTaskList;
import ua.edu.sumdu.ta.shestak.maksym.pr6_additional.ArrayTaskList;
import ua.edu.sumdu.ta.shestak.maksym.pr6_additional.Task;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Maksym Shestak
 * @since 10.07.2016
 */
public class TaskXMLSerializer {

    public static void main(String[] args) {

    }
    public void save(AbstractTaskList object, String file) {


        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("tasks");
            doc.appendChild(rootElement);


            Iterator<Task> iterator = object.iterator();

            Task task = null;

            while(iterator.hasNext()) {
                task = iterator.next();

                Element taskXml = doc.createElement("task");
                taskXml.setAttribute("active", String.valueOf(task.isActive()));
                taskXml.setAttribute("time", String.valueOf(task.getTime()));
                taskXml.setAttribute("start", String.valueOf(task.getStartTime()));
                taskXml.setAttribute("end", String.valueOf(task.getEndTime()));
                taskXml.setAttribute("repeat", String.valueOf(task.getRepeatInterval()));
                taskXml.setAttribute("repeated", String.valueOf(task.isRepeated()));
                taskXml.appendChild(doc.createTextNode(task.getTitle()));

                rootElement.appendChild(taskXml);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }

    public AbstractTaskList load(String file) {
        AbstractTaskList list = new ArrayTaskList();
        try {
            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("task");


            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    list.add(new Task(
                            eElement.getTextContent(),
                            Integer.parseInt(eElement.getAttribute("start")),
                            Integer.parseInt(eElement.getAttribute("end")),
                            Integer.parseInt(eElement.getAttribute("repeat")))
                            .setActive(Boolean.parseBoolean(eElement.getAttribute("active"))));
                }
            }


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
