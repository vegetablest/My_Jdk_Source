package FastJsonTest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

public class Test03 {
    public static void main(String[] args) throws DocumentException {
        Document doc = null;
        String result_gmsfhm = "";
        String frms_publicSecurityresult = "";
        String result_xm = "";
        String result = "<?xml ERR=\"1.0\" encoding=\"UTF-8\"?><ROWS><ROW no=\"1\"><INPUT><gmsfhm>500222199101020915</gmsfhm><xm>李波</xm></INPUT><OUTPUT><ITEM><gmsfhm></gmsfhm><result_gmsfhm>一致</result_gmsfhm></ITEM><ITEM><xm></xm><result_xm>一致</result_xm></ITEM></OUTPUT></ROW></ROWS>";
        if (result.contains("ERR")) {
            frms_publicSecurityresult = "err";
        } else {
            doc = DocumentHelper.parseText(result); // 将字符串转为XMLversion
            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
            Iterator iter = rootElt.elementIterator("ROW"); // 获取根节点下的子节点ROW
            // 遍历head节点
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                Iterator output = recordEle.elementIterator("OUTPUT"); // 拿到ROW节点下的子节点OUTPUT
                while (output.hasNext()) { //遍历OUTPUT
                    Element item = (Element) output.next();
                    Iterator items = item.elementIterator("ITEM"); //拿到OUTOUT下的ITEM
                    // 遍历OUTPUT节点下的ITEM节点
                    while (items.hasNext()) {
                        Element itemEle = (Element) items.next();

                        result_gmsfhm = result_gmsfhm + (itemEle.elementTextTrim("result_gmsfhm") != null ? itemEle.elementTextTrim("result_gmsfhm") : ""); // 拿到item下的子节点的值
                        result_xm = result_xm + (itemEle.elementTextTrim("result_xm") != null ? itemEle.elementTextTrim("result_xm") : "");
                        System.out.println("result_gmsfhm:" + result_gmsfhm);
                        System.out.println("result_xm:" + result_xm);
                    }
                }
                if (result_gmsfhm.equals("一致") && result_xm.equals("一致")) {
                    frms_publicSecurityresult = "一致";
                } else {
                    frms_publicSecurityresult = "不一致";
                }
            }
        }
        System.out.println("frms_publicSecurityresult:" + frms_publicSecurityresult);
    }
}
