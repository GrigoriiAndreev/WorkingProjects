package ru.detskiostrov.webservices;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class is transforming usual web pages to turbo pages and save it into xml files to feed Yandex Turbo
 * Author: Grgigorii Andreev
 * Start date: 13.03.2021
 * Version: 1.00
 */

public class TurboPagesGenerator {
    //Be careful to publish private and secure info in OpenSource places!!!
    //Data to extract turbo pages from web sites. Filled by users
    private static final String originalWebSite = "oursite.ru";                     //No http or https
    private static final String turboPagesFile = "turboPages.txt";
//    private static final String workingFolder = "D:\\JavaStudy\\UsefullUtils\\src\\main\\resources\\WebServices";
    private static final String workingFolder = "D:/JavaStudy/UsefullUtils/src/main/resources/WebServices/";



    // Page structure
    String author;
    String category;
    String publicationDate;
    String title = "";
    String link = "";

    // Turbo content structure
    String h1 = "";
    String h2 = "";
    String h4DownloadBlock = "";
    String googleAdsBloc = "";
    String htmlTurboContent = "";

    public TurboPagesGenerator() throws IOException {
    }

//    public turboPagesGenerator() throws IOException {
//    }

    // Example lines to test the file writing
    public static void createTurboPagesHead() throws IOException {

        File file = new File(workingFolder + turboPagesFile);
        FileWriter writer = new FileWriter(file);

        //Create the RSS channel structure
        String xmlHead = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
        writer.write(xmlHead);
        String comments = "<!-- Turbo Pages generator -->\n";
        writer.write(comments);
        String rss = "<rss version=\"2.0\" xmlns:atom=\"http://www.w3.org/2005/Atom\">\n";
        writer.write(rss);
        writer.write("<channel>\n");
        String headTitle = "Контрольные и самостоятельные работы, задачи и примеры, " +
                "тесты,  уроки и репетиторы для 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 классов ";
        writer.write("<title>" + headTitle + "</title>\n");
        writer.close();
    }

    // Example lines to test the file writing
    public static void createTurboPages() throws IOException {

        File file = new File(workingFolder + turboPagesFile);
        FileWriter writer = new FileWriter(file, true);

        //Test lines to write to sitemap
        String turboPageTitle = "Сложение и вычитание числа 2. Скачать pdf или jpg.";
        writer.write("<item turbo=\"true\">\n");
        writer.write("<title>" + turboPageTitle + "</title>\n");
        String turboPageLink = "https://mathematics-tests.com/zadachi-na-slozhenie-i-vychitanie/slozhenie-vychitanie-chisla-2";
        String turboContentH1 = "Математика.  Сложение и вычитание числа 4.";
        String turboContentH2 = "Задачи по математике на тему:<br />Сложение и вычитание числа 9. Задачи с ответами. Скачать pdf или jpg.";
        String turboContentH4 = "<h4>Дополнительные материалы для свободного скачивания<br /> (Google Drive или Яндекс Диск)</h4> \n" +
                "<hr> <br /> \n" +
                "<strong>Скачать:</strong> <a href=\"https://yadi.sk/d/hW-M_0Jd8_VWUg?w=1\" title =\" Сложение и вычитание числа 9. Сложи или вычити число 9 из заданного числа.\">Сложи или вычити число 9 из заданного числа.</a> (\n" +
                "Формат PDF или JPG) \n" +
                "<br /><br /> <hr>";
        String turboContentGoogleAds = "<script async src=\"https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>\n" +
                "<!-- netboard_580x400_1_kl_All -->\n" +
                "<ins class=\"adsbygoogle\"\n" +
                "     style=\"display:block\"\n" +
                "     data-ad-client=\"ca-pub-3061518079340402\"\n" +
                "     data-ad-slot=\"4709942115\"\n" +
                "     data-ad-format=\"auto\"\n" +
                "     data-full-width-responsive=\"true\"></ins>\n" +
                "<script>\n" +
                "     (adsbygoogle = window.adsbygoogle || []).push({});\n" +
                "</script>";
        String turboContentHtml ="<br /><h3> Сложение и вычитание. Сложение и вычитание числа 9. " +
                "Вариант № 1.</h3>Дата: ______________ ФИО: _________________________________ Оценка:__________\n" +
                "\n" +
                "<br /><br />Сложи или вычити число 9 из заданного числа.\n" +
                "\n" +
                "<br /><br /> &nbsp; 1) &nbsp;73 + 9 = ____ &nbsp; &nbsp; &nbsp;  &nbsp; 2) &nbsp;80 - 9 = ____ &nbsp; &nbsp; &nbsp;  &nbsp; 3) " +
                "&nbsp;37 + 9 = ____ &nbsp; &nbsp; &nbsp;  &nbsp; 4) &nbsp;66 - 9 = ____ &nbsp; &nbsp; &nbsp;  &nbsp; 5) &nbsp;64 + 9 = ____ &nbsp; " +
                "&nbsp; &nbsp; <br /><br /><br />\n";

        writer.close();
    }



        public static void main(String[] args) throws IOException {

        //createTurboPagesHead();

        //createTurboPages();

//            Document doc = null;
/*

            doc = Jsoup.connect("https://derevenski-pogrebok.ru/domashnie-vareniya/vareniya-iz-abrikosov").get();
            System.out.println(doc.title());

            Elements links = doc.select("a[href]");
            for (Element link : links) {
                System.out.println(link.attr("href"));
                System.out.println(link.text());
            }
            System.out.println("---------------------------");
            Elements pngs = doc.select("img[src$=.jpg]");
            for (Element link : pngs) {
                System.out.println(pngs.text());
                System.out.println(pngs.attr("href"));
            }
*/

            Document doc = Jsoup.parse("https://derevenski-pogrebok.ru/domashnie-vareniya/vareniya-iz-abrikosov", "utf-8");

            Element form = doc.getElementById("banner-center");

            System.out.println("Form action = "+ form.text());

/*
            Elements inputElements = form.getElementsByTag("input");

            for (Element inputElement : inputElements) {
                String key = inputElement.attr("name");
                String value = inputElement.attr("value");

                System.out.println(key + " =  " + value);
            }
*/
        }
    }



