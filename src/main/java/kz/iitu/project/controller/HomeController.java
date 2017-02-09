package kz.iitu.project.controller;

import kz.iitu.project.model.Department;
import kz.iitu.project.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kasyanov Maxim on 2/9/2017.
 */
@RestController
public class HomeController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Map<Object, Object> test() {
        List<Department> departments = new ArrayList<>();
        Map<Object, Object> result = new LinkedHashMap<>();
        List<User> users = new ArrayList<>();
        try {
            File fXmlFile = new File("C:\\Users\\kasya\\Desktop\\xml-parcer\\src\\main\\resources\\podr.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Department");
            try {
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        Department department = new Department();
                        department.setId(eElement.getAttribute("UID"));
                        if (department.getId().equals("5906A0BA14BA4E9BA93C1C68A08367B4")) {
                            System.out.println("HomeController.test");
                        }
                        department.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                        department.setParentId(eElement.getElementsByTagName("Parent").item(0).getAttributes().item(1).getTextContent());
                        departments.add(department);
                    }
                }
            }catch (Exception e ){
                e.printStackTrace();
            }
            try {
            NodeList uList = doc.getElementsByTagName("User");
            for (int temp = 0; temp < uList.getLength(); temp++) {
                Node nNode = uList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    User user = new User();
                    user.setId(eElement.getAttribute("UID"));
                    user.setFio(eElement.getElementsByTagName("Name").item(0).getTextContent());
                    user.setDepartmentId(eElement.getElementsByTagName("Department").item(0).getAttributes().item(1).getTextContent());;
                    users.add(user);
                }
            }
            }catch (Exception e ){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("user", users);
        result.put("departments", departments);
        return result;
    }
}
