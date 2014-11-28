package com.etop.weixin.utils.weixinUtils;

import com.etop.weixin.entity.BaseWeixinEntity;
import com.etop.weixin.entity.event.EventEnum;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 微信专用XML转换工具类
 *
 * @author Jeremie
 *         Created by Jeremie on 2014/9/1.
 */
public class XMLUtil {

    /**
     * XML转换为对象
     *
     * @param xml 输入的xml
     * @return 基础类
     */
    public static BaseWeixinEntity XMLToObject(String xml) {
        BaseWeixinEntity baseWeixinEntity = null;
        try {
            Reader in = new StringReader(xml);
            SAXBuilder saxb = new SAXBuilder();
            Document doc = saxb.build(in);
            Element root = doc.getRootElement(); // 获取根元素
            List<Element> list = root.getChildren();
            String type = root.getChildText("MsgType");
            String clazzName = "";
            String clazz = "";
            EventEnum eventEnum = null;
            if ("event".equals(type)) {
                String event = root.getChildText("Event");
                switch (event) {
                    case "subscribe":
                        if (root.getChildText("Ticket") != null && !"".equals(root.getChildText("Ticket"))) {
                            clazzName = "SubscribeEvent";
                            eventEnum = EventEnum.SUBSCRIBE;
                        } else {
                            clazzName = "SubscribeByQREvent";
                            eventEnum = EventEnum.SUBSCRIBEBYQR;
                        }
                        break;
                    case "unsubscribe":
                        clazzName = "SubscribeEvent";
                        eventEnum = EventEnum.UNSUBSCRIBE;
                        break;
                    case "SCAN":
                        clazzName = "SubscribeByQREvent";
                        eventEnum = EventEnum.SUBSCRIBEDBYQR;
                        break;
                    case "LOCATION":
                        clazzName = "LocationEvent";
                        eventEnum = EventEnum.LOCATION;
                        break;
                    case "CLICK":
                        clazzName = "MenuEvent";
                        eventEnum = EventEnum.MENUCLICK;
                        break;
                    case "VIEW":
                        clazzName = "MenuEvent";
                        eventEnum = EventEnum.MENUVIEW;
                        break;
                    case "TEMPLATESENDJOBFINISH":
                        clazzName = "TemplateMsgEvent";
                        eventEnum = EventEnum.TEMPLATESENDJOBFINISH;
                        break;
                    default:
                        return null;
                }
                clazz = "com.etop.weixin.entity.event." + clazzName;
            } else {
                clazzName = type.substring(0, 1).toUpperCase() + type.substring(1) + "Msg";
                clazz = "com.etop.weixin.entity.message.request." + clazzName;
            }
            Class<?> c = Class.forName(clazz);
            baseWeixinEntity = (BaseWeixinEntity) c.newInstance();
            for (Element element : list) {
                String methodStr = "set" + element.getName();
                String parm = element.getText();
                Method method = c.getMethod(methodStr, String.class);
                method.invoke(baseWeixinEntity, parm);
            }
            if ("event".equals(type)) {
                String methodStr = "setEventType";
                Method method = c.getMethod(methodStr, EventEnum.class);
                method.invoke(baseWeixinEntity, eventEnum);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.error("输入流读取失败", e);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException
                | JDOMException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
            LogUtil.error("实体生成失败", e);
        } finally {
            return baseWeixinEntity;
        }
    }

    /*public static <T> String ObjectToXML(T object) {
        String result = "";
        try {
            Element reRoot = new Element("xml");
            Document reDoc = new Document();
            reDoc.addContent(reRoot);
            reRoot = createXML(reRoot, object);
            XMLOutputter xmlOut = new XMLOutputter();
            result = xmlOut.outputString(reDoc);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            LogUtil.error("xml生成失败",e);
        } finally {
            return result;
        }
    }

    public static Element createXML(Element root, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> rec = object.getClass();
        ArrayList<Field> fields = new ArrayList<Field>();
        fields.addAll(Arrays.asList(rec.getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(rec.getDeclaredFields()));
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(object);
            Element element = new Element(field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
            if(o == null){
                break;
            }else if(o.getClass() == String.class)
                element.addContent(new CDATA((String) o));
            else if(o.getClass() == Integer.class)
                element.setText(o + "");
            else if (o.getClass() == ArrayList.class) {
                ArrayList list = (ArrayList) o;
                for (Object sub : list) {
                    Element arrayElement = new Element(sub.getClass().getSimpleName().substring(0,1).toLowerCase()
                            + sub.getClass().getSimpleName().substring(1).toLowerCase());
                    arrayElement = createXML(arrayElement, sub);
                    element.addContent(arrayElement);
                }
            } else {
                element = createXML(element, o);
            }
            root.addContent(element);
        }
        return root;
    }*/


    private static XStream xStream = null;

    private static XStream getXstream() {
        if (xStream == null)
            xStream = new XStream(new XppDriver() {
                @Override
                public HierarchicalStreamWriter createWriter(Writer out) {
                    return new PrettyPrintWriter(out) {
                        // 对所有xml节点的转换都增加CDATA标记
                        boolean cdata = true;

                        @Override
                        public void startNode(String name, Class clazz) {
                            super.startNode(getNodeName(name), clazz);
                            if(clazz == String.class) cdata = true;
                            else  cdata = false;
                        }

                        private String getNodeName(String name){
                            String[] strs = name.split("\\.");
                            if(strs.length == 1)
                                return name.equals("xml") ? name : name.substring(0,1).toUpperCase() + name.substring(1)  ;
                            else
                                return strs[strs.length-1].substring(0,1).toLowerCase() + strs[strs.length-1].substring(1);
                        }

                        @Override
                        protected void writeText(QuickWriter writer, String text) {
                            if (cdata) {
                                writer.write("<![CDATA[");
                                writer.write(text);
                                writer.write("]]>");
                            } else {
                                writer.write(text);
                            }
                        }
                    };
                }

            });
        return xStream;
    }

    /**
     * 对象转换为XML
     *
     * @param object 对象
     * @param <T>    对象类
     * @return xml
     */
    public static <T> String ObjectToXML(T object) {
        String result = null;
        XStream xstream = getXstream();
        try {
            xstream.alias("xml", object.getClass());
            result = xstream.toXML(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
