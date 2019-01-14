package fuentis;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.fuentis.etl.dto.AdapterAttributeDto;
import com.fuentis.etl.dto.AdapterObjectDto;
import org.w3c.dom.Document;

public class parser{
	AdapterObjectDto ret=new AdapterObjectDto();
	public String filename;
	public AdapterObjectDto fileopen() {
		try
		{
			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			Node rootNode=doc.getFirstChild();
			while(!(rootNode.getNodeType()==(Node.ELEMENT_NODE)))
			{
				rootNode=rootNode.getNextSibling();
			}
			AdapterObjectDto mainAdapterObject=new AdapterObjectDto();
			mainAdapterObject.setId(doc.getDocumentElement().getNodeName());
			mainAdapterObject.setType("Root Element");
			ret= parseFile(rootNode,mainAdapterObject);			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return ret;
	}
	public AdapterObjectDto parseFile(Node parent,AdapterObjectDto main) {
		try
		{
			NodeList nList=parent.getChildNodes();

			for (int count = 0; count < nList.getLength(); count++) {
				AdapterObjectDto abc=new AdapterObjectDto();
				Node tempNode = nList.item(count);
				if(checkNode(tempNode)) {
					abc.setId(tempNode.getNodeName()+count+1);
					abc.setType("Child Node"+count+1);
					NodeList childNodeList=tempNode.getChildNodes();
					for (int x = 0; x < childNodeList.getLength(); x++) {
						Node childAtr= childNodeList.item(x);
						if(checkNode(childAtr)) {
							AdapterAttributeDto efg= new AdapterAttributeDto();
							efg.setKey(childAtr.getNodeName());
							efg.setValue(childAtr.getTextContent());	
							abc.addAttribute(efg);
						}
						else {
							continue;
						}
					}
					main.addChild(abc);//main is the final adapter object
				
					
				}
				else
				{
					continue;
				}
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return main;

}
	public Boolean checkNode(Node ns) {
		if(!(ns.getNodeType()==(Node.ELEMENT_NODE))) {
			return false;
		}
		else
		{
			return true;
		}
	}

}
