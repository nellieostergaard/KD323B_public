package com.example.k3lara.lecture_3_examples.xmlparser;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URLDecoder;
import java.util.ArrayList;

public class Parser {
	  /**
  	 * Calls Kronox and searches for schedule for in this test version Pfi3 c
  	 * @return list of ScheduleItems.
  	 * */
	public static ArrayList<ScheduleItem> getSchedule(String addr){

		ArrayList<ScheduleItem> foundItem = new ArrayList<ScheduleItem>();
		String name;
		String x;
		String y;
		String id;
		 XMLParser parser = new XMLParser();
	     String xml = parser.getXmlFromUrl("http://schema.mah.se/setup/jsp/SchemaXML.jsp?startDatum=idag&intervallTyp=m&intervallAntal=6&sokMedAND=false&sprak=SV&resurser=k."+addr+"-%2C");
        //Log.i("Parser", "xml" + xml);
        if (xml!=null){
            ScheduleItem scheduleItem;
				Document doc = parser.getDomElement(xml); // getting DOM element
				NodeList nl = doc.getElementsByTagName("schemaPost");
             Log.i("Parser", "schemaPost" + nl.getLength());
				for (int i = 0; i < nl.getLength(); i++){
                    scheduleItem = new ScheduleItem();
					Element e = (Element) nl.item(i);
                    //Log.i("Parser", "++++++++++++++++++++++new Schema Post++++++++++++++++++++++++++");
                    //Log.i("Parser","TagName: "+e.getTagName()+parser.getValue(e,"moment"));
                    NodeList nl2 = e.getChildNodes();
                    for (int k = 0; k < nl2.getLength(); k++){
                        Element e2 = (Element) nl2.item(k);
                        //Log.i("Parser","TagName: "+e2.getTagName()+" value "+e2.getNodeValue()+" text: "+ e2.getTextContent());
                        e2.getNodeValue();
                        if (e2.getTagName().equals("moment")) {
                            //Log.i("Parser","TagName: "+e2.getTagName()+" value "+e2.getNodeValue()+" text: "+ e2.getTextContent());
                            try {
                                scheduleItem.setMoment(URLDecoder.decode(e2.getTextContent(), "UTF-8"));  //Funkar inte
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (e2.getTagName().equals("bokadeDatum")){
                            Element n2 = (Element)e2.getFirstChild();
                            n2.getAttribute("datum");
                            //Log.i("Parser", "Datum: " + n2.getAttribute("datum"));
                            scheduleItem.setDate(n2.getAttribute("datum"));
                            //Log.i("Parser","Start: "+ n2.getAttribute("startTid"));
                            scheduleItem.setStartTime(n2.getAttribute("startTid"));
                            //Log.i("Parser","Slut: "+ n2.getAttribute("slutTid"));
                            scheduleItem.setEndTime(n2.getAttribute("slutTid"));
                        }

                        if (e2.getTagName().equals("resursTrad")){
                            NodeList nl3 = e2.getChildNodes();
                            for (int m = 0; m < nl3.getLength(); m++) {
                                Element e3 = (Element) nl3.item(m);
                                String s2 = e3.getTagName();
                                String s = e3.getAttribute("resursTypId");
                                if (s.equals("UTB_PROGRAMINSTANS_KLASSER")){
                                    NodeList nl4=e3.getChildNodes();
                                    for (int n=0;n<nl4.getLength();n++){
                                        Element e4 = (Element) nl4.item(n);
                                        if (e4.getTagName().equals("resursIdURLEncoded")){
                                            //Log.i("ResursNod",e4.getTextContent());
                                        }
                                    }
                                }
                                if (s.equals("UTB_KURSINSTANS_GRUPPER")){
                                    NodeList nl4=e3.getChildNodes();
                                    for (int n=0;n<nl4.getLength();n++){
                                        Element e4 = (Element) nl4.item(n);
                                        if (e4.getTagName().equals("resursIdURLEncoded")){
                                            //Log.i("ResursNod",e4.getTextContent());
                                            scheduleItem.setCourse(e4.getTextContent().substring(0, 6));
                                        }
                                    }
                                }
                                if (s.equals("RESURSER_LOKALER")){
                                    NodeList nl4=e3.getChildNodes();
                                    for (int n=0;n<nl4.getLength();n++){
                                        Element e4 = (Element) nl4.item(n);
                                        if (e4.getTagName().equals("resursIdURLEncoded")){
                                            //Log.i("ResursNod",e4.getTextContent());
                                            scheduleItem.setRoom(e4.getTextContent()); //Only the last room
                                        }
                                    }
                                }
                                if (s.equals("RESURSER_SIGNATURER")){
                                    NodeList nl4=e3.getChildNodes();
                                    for (int n=0;n<nl4.getLength();n++){
                                        Element e4 = (Element) nl4.item(n);
                                        if (e4.getTagName().equals("resursIdURLEncoded")){
                                            //Log.i("ResursNod",e4.getTextContent());
                                            scheduleItem.setTeacher(e4.getTextContent()); //Only the last teaher
                                        }
                                    }
                                }
                            }
                        }
                    }
                    foundItem.add(scheduleItem);
				}
	     }
		return foundItem;
	}
}
