package fuentis;

import java.util.*;

//import javax.swing.text.html.HTMLDocument.Iterator;

import com.fuentis.etl.dto.*;

public class parsemain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		parser parsefile = new parser();
		parsefile.filename = "C:\\Users\\User_3\\Desktop\\Fulldata.xml";
		AdapterObjectDto ret=parsefile.fileopen();
		System.out.println(ret.getType());
		System.out.println(ret.getId());
		Collection<AdapterObjectDto> abc=ret.getChildren();
		Collection<AdapterAttributeDto> rrr =null;
		Iterator itr = abc.iterator();
		int count=1;
		while (itr.hasNext()) {
			System.out.println(count);
			AdapterObjectDto efg = (AdapterObjectDto)itr.next();
			rrr=efg.getAttributes();
			Iterator tr = rrr.iterator();
			while (tr.hasNext()) {
				
				AdapterAttributeDto gg = (AdapterAttributeDto) tr.next();
				System.out.println(gg.getKey() + ":" + gg.getValue());
			}
			count++;
		}
		/*int count=1;
		for (AdapterObjectDto dt : ret.getChildren()) {
			System.out.println("Element"+count);
			for (AdapterAttributeDto ds : dt.getAttributes())
			{
				System.out.println(ds.getKey()+" : "+ds.getValue());
			}
			count++;
			System.out.println();
		}*/

	}

}
