import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MergeXmlFiles {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc1 = dBuilder.parse(new File("License1.xml"));
            Document doc2 = dBuilder.parse(new File("License2.xml"));

            NodeList nodeList1 = doc1.getElementsByTagName("License");
            NodeList nodeList2 = doc2.getElementsByTagName("License");

            BufferedWriter validLicenseWriter = new BufferedWriter(new FileWriter("ValidLicenses.txt"));
            BufferedWriter invalidLicenseWriter = new BufferedWriter(new FileWriter("InvalidLicenses.txt"));
            BufferedWriter MergedFileWriter = new BufferedWriter(new FileWriter("MergedFile.txt"));

            validLicenseWriter.write(LICENSE_HEADER_ROW);
            invalidLicenseWriter.write(LICENSE_HEADER_ROW);
            MergedFileWriter.write(LICENSE_HEADER_ROW);

            for (int i = 0; i < nodeList1.getLength(); i++) {
                Element license1 = (Element) nodeList1.item(i);
                String key1 = getKey(license1);
                for (int j = 0; j < nodeList2.getLength(); j++) {
                    Element license2 = (Element) nodeList2.item(j);
                    String key2 = getKey(license2);
                    if (key1.equals(key2)) {
                        if (isValid(license1)) {
                            MergedFileWriter.write("\n \n Valid License");
                            writeLicenseToFile(license1, validLicenseWriter);
                            writeLicenseToFile(license1, MergedFileWriter);

                        } else {
                            MergedFileWriter.write("\n \n InValid License");
                            writeLicenseToFile(license1, invalidLicenseWriter);
                            writeLicenseToFile(license1, MergedFileWriter);

                        }
                        break;
                    }
                }
            }
            validLicenseWriter.close();
            invalidLicenseWriter.close();
            MergedFileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getKey(Element license) {
        return license.getAttribute("NIPR_Number") + ","
                + license.getAttribute("State_Code") + ","
                + license.getAttribute("License_Number") + ","
                + license.getAttribute("License_Expiration_Date");
    }

    private static boolean isValid(Element license) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String expirationDateString = license.getAttribute("License_Expiration_Date");
        LocalDate expirationDate = LocalDate.parse(expirationDateString, formatter);
        return expirationDate.isAfter(currentDate) || expirationDate.isEqual(currentDate);
    }

    private static void writeLicenseToFile(Element license, BufferedWriter writer) throws IOException {
        String line = license.getAttribute("NIPR_Number") + ","
                + license.getAttribute("License_Number") + ","
                + license.getAttribute("State_Code") + ","
                + license.getAttribute("Resident_Indicator") + ","
                + license.getAttribute("License_Class") + ","
                + license.getAttribute("License_Issue_Date") + ","
                + license.getAttribute("License_Expiration_Date") + ","
                + license.getAttribute("License_Status");
        NodeList loaList = license.getElementsByTagName("LOA");
        for (int k = 0; k < loaList.getLength(); k++) {
            Element loa = (Element) loaList.item(k);
            String loaLine = line + ","
                    + loa.getAttribute("LOA_Name") + ","
                    + loa.getAttribute("LOA_Issue_Date") + ","
                    + loa.getAttribute("LOA_Expiration_Date") + ","
                    + loa.getAttribute("LOA_Status");
            writer.newLine();
            writer.write(loaLine);
        }
    }

    private static final String LICENSE_HEADER_ROW = "nipr,License ID,Jurisdiction,Resident,License Class,License Effective Date,License Expiry Date,License Status,License Line,License Line Effective Date,License Line Expiry Date,License Line Status";
}