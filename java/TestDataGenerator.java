import javafx.util.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

final public class TestDataGenerator {

    private DatabaseDAO db;
    private Random rand;

    //initializing all the lists and sets etc should be done in constructor not here - will change this before hand in
    private List <String> companyPrefixes = new ArrayList<>();
    private List <String> companyAffix = new ArrayList<>();
    private List <String> companyPostfixes = new ArrayList<>();

    private String templateUrlPrefix;
    private List <String> templateUrlAffix1 = new ArrayList<>();
    private List <String> templateUrlAffix2 = new ArrayList<>();
    private List <String> templateUrlAffix3 = new ArrayList<>();
    private List <String> templateUrlPostfixes = new ArrayList<>();

    private List <String> templateLangs = new ArrayList<>();

    private List <String> unterSeitePfadPostfixes = new ArrayList<>();

    private List <String> verkaufSeiteTitelPostfixes = new ArrayList<>();

    private List <String> userFirstNames = new ArrayList<>();
    private List <String> userMiddleNames = new ArrayList<>();
    private List <String> userLastNames = new ArrayList<>();
    private List <String> userEmailProviders =  new ArrayList<>();
    private List <String> userAdressPart1 =  new ArrayList<>();
    private List <String> userAdressPart2 = new ArrayList<>();
    private List <String> userBankData = new ArrayList<>();

    private List <String> produktPrefix = new ArrayList<>();
    private List <String> produktAffix = new ArrayList<>();
    private List <String> produktPostfix =  new ArrayList<>();


    TestDataGenerator(DatabaseDAO db) {

        this.db = db;
        rand =  new Random();

        //would probably be better to read the following data from a file but for testing it is hardcoded. also could be added to the database objects for better encapsulation
        companyPrefixes.add("Boston");
        companyPrefixes.add("Micro");
        companyPrefixes.add("Macro");
        companyPrefixes.add("Typo");
        companyPrefixes.add("IT");
        companyPrefixes.add("Mega");
        companyPrefixes.add("Cyber");
        companyPrefixes.add("YBM");
        companyPrefixes.add("Vienna");
        companyPrefixes.add("Washington");
        companyPrefixes.add("Alpha");
        companyPrefixes.add("Beta");
        companyPrefixes.add("Gamma");
        companyPrefixes.add("Delta");
        companyPrefixes.add("Epsilon");
        companyPrefixes.add("Theta");
        companyPrefixes.add("Chevron");
        companyPrefixes.add("Psy");
        companyPrefixes.add("Neural");
        companyPrefixes.add("Neuro");
        companyPrefixes.add("Evil");
        companyPrefixes.add("Banana");
        companyPrefixes.add("Zeta");
        companyPrefixes.add("Eta");
        companyPrefixes.add("Iota");
        companyPrefixes.add("Kappa");
        companyPrefixes.add("Xi");
        companyPrefixes.add("Omikron");
        companyPrefixes.add("Pi");
        companyPrefixes.add("Rho");
        companyPrefixes.add("Sigma");
        companyPrefixes.add("Tau");
        companyPrefixes.add("Phi");
        companyPrefixes.add("Omega");
        companyPrefixes.add("Saturn");
        companyPrefixes.add("Mercury");
        companyPrefixes.add("Minerva");
        companyPrefixes.add("Hermes");
        companyPrefixes.add("Apollo");
        companyPrefixes.add("Athene");
        companyPrefixes.add("Zeus");
        companyPrefixes.add("Minotaur");
        companyPrefixes.add("Centurion");
        companyPrefixes.add("Cerberos");
        companyPrefixes.add("Prometheus");
        companyPrefixes.add("Bravo");
        companyPrefixes.add("Charlie");

        companyAffix.add("-Central");
        companyAffix.add("-Workx");
        companyAffix.add("-Worxx");
        companyAffix.add("-Constructions");
        companyAffix.add("-Buildings");
        companyAffix.add("-Soft");
        companyAffix.add("-Computing");
        companyAffix.add("-Dynamics");
        companyAffix.add("-Development");
        companyAffix.add("-Net");
        companyAffix.add("-Networks");
        companyAffix.add("-Works");
        companyAffix.add("-7");
        companyAffix.add("-µ");
        companyAffix.add("-9");
        companyAffix.add("-Shipyards");
        companyAffix.add("-Trading");
        companyAffix.add("-6");
        companyAffix.add("-Up");

        companyPostfixes.add(" Group");
        companyPostfixes.add(" Cooperation");
        companyPostfixes.add(" Corp.");
        companyPostfixes.add(" Incorporated");
        companyPostfixes.add(" GmbH");
        companyPostfixes.add(" AG");
        companyPostfixes.add(" Operations");
        companyPostfixes.add("");
        companyPostfixes.add(" Company");
        companyPostfixes.add(" CP");
        companyPostfixes.add(" Productions");
        companyPostfixes.add(" Solutions");
        companyPostfixes.add(" Enterprises");

        templateUrlPrefix = "http://wwww.";

        templateUrlAffix1.add("great");
        templateUrlAffix1.add("awesome");
        templateUrlAffix1.add("nice");
        templateUrlAffix1.add("good");
        templateUrlAffix1.add("hilarious");
        templateUrlAffix1.add("exhilerating");
        templateUrlAffix1.add("random-adjective");
        templateUrlAffix1.add("meaningless");
        templateUrlAffix1.add("breakable");
        templateUrlAffix1.add("homeless");
        templateUrlAffix1.add("ugly");
        templateUrlAffix1.add("odd");
        templateUrlAffix1.add("dirty");
        templateUrlAffix1.add("tasteless");
        templateUrlAffix1.add("used");
        templateUrlAffix1.add("expensive");
        templateUrlAffix1.add("finest");
        templateUrlAffix1.add("illegal");
        templateUrlAffix1.add("radioactive");
        templateUrlAffix1.add("noisy");
        templateUrlAffix1.add("beautiful");
        templateUrlAffix1.add("mercyful");
        templateUrlAffix1.add("british");
        templateUrlAffix1.add("french");
        templateUrlAffix1.add("german");
        templateUrlAffix1.add("spanish");
        templateUrlAffix1.add("austrian");
        templateUrlAffix1.add("hot");
        templateUrlAffix1.add("enerving");
        templateUrlAffix1.add("smelly");
        templateUrlAffix1.add("asian");
        templateUrlAffix1.add("chinese");
        templateUrlAffix1.add("tasty");
        templateUrlAffix1.add("spciy");
        templateUrlAffix1.add("smart");
        templateUrlAffix1.add("sweet");
        templateUrlAffix1.add("elegant");
        templateUrlAffix1.add("infected");
        templateUrlAffix1.add("defective");
        templateUrlAffix1.add("japanese");
        templateUrlAffix1.add("russian");
        templateUrlAffix1.add("flying");
        templateUrlAffix1.add("arab");
        templateUrlAffix1.add("antique");
        templateUrlAffix1.add("cheap");
        templateUrlAffix1.add("imported");
        templateUrlAffix1.add("mature");
        templateUrlAffix1.add("handcrafted");
        templateUrlAffix1.add("fancy");
        templateUrlAffix1.add("handy");
        templateUrlAffix1.add("electric");

        templateUrlAffix2.add("-fun");
        templateUrlAffix2.add("-sport");
        templateUrlAffix2.add("-weapons");
        templateUrlAffix2.add("-cheerleaders");
        templateUrlAffix2.add("-pets");
        templateUrlAffix2.add("-racing");
        templateUrlAffix2.add("-computerparts");
        templateUrlAffix2.add("-pcrepair");
        templateUrlAffix2.add("-electronics");
        templateUrlAffix2.add("-washingmachines");
        templateUrlAffix2.add("-oddities");
        templateUrlAffix2.add("-carparts");
        templateUrlAffix2.add("-toys");
        templateUrlAffix2.add("-cardgames");
        templateUrlAffix2.add("-sodamachines");
        templateUrlAffix2.add("-birthdaypresents");
        templateUrlAffix2.add("-accessoires");
        templateUrlAffix2.add("-devices");
        templateUrlAffix2.add("-machines");
        templateUrlAffix2.add("-appetizers");
        templateUrlAffix2.add("-whiskys");
        templateUrlAffix2.add("-motorcycles");
        templateUrlAffix2.add("-creditcards");
        templateUrlAffix2.add("-churchbells");
        templateUrlAffix2.add("-doorbells");
        templateUrlAffix2.add("-laptops");
        templateUrlAffix2.add("-computers");
        templateUrlAffix2.add("-smartphones");
        templateUrlAffix2.add("-smartwatches");
        templateUrlAffix2.add("-telephones");

        templateUrlAffix3.add("-trading");
        templateUrlAffix3.add("-exchanges");
        templateUrlAffix3.add("-business");
        templateUrlAffix3.add("-shop");
        templateUrlAffix3.add("-onlineboutique");
        templateUrlAffix3.add("-market");
        templateUrlAffix3.add("-outlet");
        templateUrlAffix3.add("-webstore");
        templateUrlAffix3.add("-webshop");
        templateUrlAffix3.add("-store");

        templateUrlPostfixes.add(".com");
        templateUrlPostfixes.add(".at");
        templateUrlPostfixes.add(".de");
        templateUrlPostfixes.add(".org");
        templateUrlPostfixes.add(".rus");
        templateUrlPostfixes.add(".ie");
        templateUrlPostfixes.add(".il");
        templateUrlPostfixes.add(".in");
        templateUrlPostfixes.add(".gov");

        templateLangs.add("Deutsch");
        templateLangs.add("Spanish");
        templateLangs.add("English");
        templateLangs.add("French");
        templateLangs.add("Italian");
        templateLangs.add("Russian");
        templateLangs.add("Japanese");
        templateLangs.add("Chinese");

        unterSeitePfadPostfixes.add("/shop");
        unterSeitePfadPostfixes.add("/store");
        unterSeitePfadPostfixes.add("/sales");

        verkaufSeiteTitelPostfixes.add("-Shop");
        verkaufSeiteTitelPostfixes.add("-Store");
        verkaufSeiteTitelPostfixes.add("-Sales");

        userFirstNames.add("lukas");
        userFirstNames.add("kevin");
        userFirstNames.add("lorenz");
        userFirstNames.add("natasha");
        userFirstNames.add("fabian");
        userFirstNames.add("markus");
        userFirstNames.add("maria");
        userFirstNames.add("lisa");
        userFirstNames.add("eva");
        userFirstNames.add("melanie");
        userFirstNames.add("Kirill");
        userFirstNames.add("achmed");
        userFirstNames.add("mohammed");
        userFirstNames.add("xing");
        userFirstNames.add("georg");
        userFirstNames.add("daniel");
        userFirstNames.add("dora");
        userFirstNames.add("aisha");
        userFirstNames.add("anna");
        userFirstNames.add("xaver");
        userFirstNames.add("matteuz");
        userFirstNames.add("gabi");
        userFirstNames.add("monika");
        userFirstNames.add("kaoru");
        userFirstNames.add("greogor");
        userFirstNames.add("karl");
        userFirstNames.add("tony");
        userFirstNames.add("toni");
        userFirstNames.add("johannes");
        userFirstNames.add("john");
        userFirstNames.add("erich");
        userFirstNames.add("hanna");
        userFirstNames.add("german");
        userFirstNames.add("christian");
        userFirstNames.add("karl");
        userFirstNames.add("max");
        userFirstNames.add("friedrich");
        userFirstNames.add("abdullah");
        userFirstNames.add("sarah");
        userFirstNames.add("leyla");
        userFirstNames.add("jean");
        userFirstNames.add("johann");
        userFirstNames.add("hannes");
        userFirstNames.add("hanna");
        userFirstNames.add("carol");
        userFirstNames.add("carlotta");
        userFirstNames.add("charlotte");
        userFirstNames.add("Karla");
        userFirstNames.add("gabriel");
        userFirstNames.add("gabriela");
        userFirstNames.add("anabell");
        userFirstNames.add("domi");
        userFirstNames.add("dominik");
        userFirstNames.add("dominique");
        userFirstNames.add("keanu");

        userMiddleNames.add("william");
        userMiddleNames.add("-luc");
        userMiddleNames.add("kappler");
        userMiddleNames.add("-uchida-");
        userMiddleNames.add("");
        userMiddleNames.add("kummer-");
        userMiddleNames.add("zappe-");
        userMiddleNames.add("");
        userMiddleNames.add("schmon-");
        userMiddleNames.add("");
        userMiddleNames.add("weber-");
        userMiddleNames.add("ibn-usman-");
        userMiddleNames.add("maier");
        userMiddleNames.add("");
        userMiddleNames.add("mayer");
        userMiddleNames.add("-mayar-");
        userMiddleNames.add("-urbanez-");
        userMiddleNames.add("");
        userMiddleNames.add("stiegler");
        userMiddleNames.add("strasser-");
        userMiddleNames.add("jesus-");
        userMiddleNames.add("");
        userMiddleNames.add("eichberger");
        userMiddleNames.add("nudelkocher-");
        userMiddleNames.add("wurstmann-");
        userMiddleNames.add("apfler");
        userMiddleNames.add("weinbauer-");
        userMiddleNames.add(""); //empty space thrown in multiple times to reflect the fact that most people dont have a middle name

        userLastNames.add("klenkhart");
        userLastNames.add("huber");
        userLastNames.add("pinchasov");
        userLastNames.add("streltsov");
        userLastNames.add("gül");
        userLastNames.add("babic");
        userLastNames.add("schnee");
        userLastNames.add("bush");
        userLastNames.add("al-iraqi");
        userLastNames.add("kettler");
        userLastNames.add("schneider");
        userLastNames.add("merkel");
        userLastNames.add("schröder");
        userLastNames.add("kurz");
        userLastNames.add("hofer");
        userLastNames.add("möller");
        userLastNames.add("müller");
        userLastNames.add("cenker");
        userLastNames.add("krampla");
        userLastNames.add("christus");
        userLastNames.add("putin");
        userLastNames.add("obama");
        userLastNames.add("picard");
        userLastNames.add("walla");
        userLastNames.add("kern");
        userLastNames.add("fitz");
        userLastNames.add("königshofer");
        userLastNames.add("ivanovic");
        userLastNames.add("tretiakova");
        userLastNames.add("höllisch");
        userLastNames.add("haubner");
        userLastNames.add("sidak");
        userLastNames.add("uhrmacher");

        userEmailProviders.add("@gmail.com");
        userEmailProviders.add("@gmx.at");
        userEmailProviders.add("@hotmail.at");
        userEmailProviders.add("@web.de");
        userEmailProviders.add("@spambox.de");
        userEmailProviders.add("@unvie.ac.at");
        userEmailProviders.add("@whitehouse.gov");
        userEmailProviders.add("@yahoo.com");

        userAdressPart1.add("Schweden ");
        userAdressPart1.add("Schotten ");
        userAdressPart1.add("Wiener ");
        userAdressPart1.add("Türken ");
        userAdressPart1.add("Währinger ");
        userAdressPart1.add("Andreas Hofer ");
        userAdressPart1.add("Herman Maier ");
        userAdressPart1.add("Universums ");
        userAdressPart1.add("Universitäts ");
        userAdressPart1.add("Albert Einstein ");
        userAdressPart1.add("Barack Obama ");
        userAdressPart1.add("Helge Schneider ");
        userAdressPart1.add("Lieber Augustin ");
        userAdressPart1.add("Basilisken ");
        userAdressPart1.add("Kanal ");
        userAdressPart1.add("Vogt ");
        userAdressPart1.add("Morgenstern ");
        userAdressPart1.add("Fantasie ");
        userAdressPart1.add("Armenier ");
        userAdressPart1.add("Brigitta ");
        userAdressPart1.add("Brgitten ");
        userAdressPart1.add("Lorey ");
        userAdressPart1.add("Lorenz Kummer ");
        userAdressPart1.add("Raumschiff Enterprise ");
        userAdressPart1.add("Captian Kirk ");
        userAdressPart1.add("Darth Vader ");
        userAdressPart1.add("Obi-Wan Kenobi ");
        userAdressPart1.add("Luke Skywalker ");
        userAdressPart1.add("Johnny Bravo ");
        userAdressPart1.add("Dagobert Duck ");
        userAdressPart1.add("Bitcoin ");
        userAdressPart1.add("Etherium ");
        userAdressPart1.add("Weltuntergangs ");
        userAdressPart1.add("Peter Reichl ");
        userAdressPart1.add("Wohnhaus ");
        userAdressPart1.add("Sonnen ");
        userAdressPart1.add("Sonneblumen ");
        userAdressPart1.add("Motorrad ");
        userAdressPart1.add("John Kettler ");
        userAdressPart1.add("Hofer Andreas ");
        userAdressPart1.add("Landjäger ");
        userAdressPart1.add("Schinkenfleckerl ");
        userAdressPart1.add("Schweinsbraten ");
        userAdressPart1.add("Ottarkinger ");
        userAdressPart1.add("Kaffeehaus ");
        userAdressPart1.add("Müllberg ");
        userAdressPart1.add("Erdberg ");
        userAdressPart1.add("Schneeberg ");
        userAdressPart1.add("Exelberg ");
        userAdressPart1.add("Tulbinger Kogel ");
        userAdressPart1.add("Tulpen ");
        userAdressPart1.add("Eiscreme ");
        userAdressPart1.add("Leberkässemmel ");
        userAdressPart1.add("Vegetarier ");
        userAdressPart1.add("Red-Bull ");
        userAdressPart1.add("Fussball ");
        userAdressPart1.add("Austria Wien ");
        userAdressPart1.add("Rapid ");
        userAdressPart1.add("Tiroler ");
        userAdressPart1.add("Suzuki ");
        userAdressPart1.add("Captain Adama ");
        userAdressPart1.add("Nick Seafort ");
        userAdressPart1.add("Caprica Sechs ");
        userAdressPart1.add("Dönerkebap ");
        userAdressPart1.add("Wurstsemmel ");
        userAdressPart1.add("Budweiser ");
        userAdressPart1.add("Bierbauch ");
        userAdressPart1.add("Uhrmacher ");
        userAdressPart1.add("Bierbrauer ");

        userAdressPart2.add("Straße");
        userAdressPart2.add("Promenade");
        userAdressPart2.add("Gasse");
        userAdressPart2.add("Platz");
        userAdressPart2.add("Allee");

        userBankData.add("Bank Austria, ");
        userBankData.add("Erste Bank, ");
        userBankData.add("Bank of America");
        userBankData.add("Credit Suisse");
        userBankData.add("LGT Bank");
        userBankData.add("Hypo Alpe Adria, ");
        userBankData.add("Volksbank, ");

        produktPrefix.add("Neuwertiges ");
        produktPrefix.add("Neues ");
        produktPrefix.add("Kindersicheres ");
        produktPrefix.add("Gesundes ");
        produktPrefix.add("Schmutziges ");
        produktPrefix.add("Billiges ");
        produktPrefix.add("Handgemachtes ");
        produktPrefix.add("Witziges ");
        produktPrefix.add("Nützliches ");
        produktPrefix.add("Nutzloses ");
        produktPrefix.add("Weihnachtliches ");
        produktPrefix.add("Osterliches ");
        produktPrefix.add("Herbstliches ");
        produktPrefix.add("Schönes ");
        produktPrefix.add("Antikes ");
        produktPrefix.add("Wertvolles ");
        produktPrefix.add("Musikalisches ");
        produktPrefix.add("Geruchsneutrales ");
        produktPrefix.add("Geschmacksneutrales ");
        produktPrefix.add("Gebrauchtes ");
        produktPrefix.add("Schnelles ");
        produktPrefix.add("Großes");
        produktPrefix.add("Kleines ");
        produktPrefix.add("Feines ");
        produktPrefix.add("Schweres ");
        produktPrefix.add("Leichtes ");
        produktPrefix.add("Gekochtes ");
        produktPrefix.add("Handsigniertes ");
        produktPrefix.add("Signiertes ");
        produktPrefix.add("Verschmutztes ");
        produktPrefix.add("Defektes ");
        produktPrefix.add("Kaputtes ");
        produktPrefix.add("Funktionierendes ");
        produktPrefix.add("Süßes ");

        produktAffix.add("Garten");
        produktAffix.add("Auto");
        produktAffix.add("Freundschafts");
        produktAffix.add("Geschwindigkeits");
        produktAffix.add("Wohnzimmer");
        produktAffix.add("Badezimmer");
        produktAffix.add("Rasierer");
        produktAffix.add("Motorrad");
        produktAffix.add("Reperatur");
        produktAffix.add("Küchen");
        produktAffix.add("Computer");
        produktAffix.add("Heizungs");
        produktAffix.add("DVD-Player");
        produktAffix.add("Unkraut");
        produktAffix.add("Gemüse");
        produktAffix.add("Zigaretten");
        produktAffix.add("Feuerzeug");
        produktAffix.add("Verhütungs");
        produktAffix.add("Mausklick");
        produktAffix.add("Handtuch");
        produktAffix.add("Küchen");
        produktAffix.add("Esszimmer");
        produktAffix.add("Schlafzimmer");
        produktAffix.add("Bett");
        produktAffix.add("Badewannen");
        produktAffix.add("Toiletten");
        produktAffix.add("Weihwasser");
        produktAffix.add("Pizzaofen");
        produktAffix.add("Schnitzelpfannen");
        produktAffix.add("Einkaufstaschen");
        produktAffix.add("Handtaschen");
        produktAffix.add("Hosentaschen");
        produktAffix.add("Computerbildschirm");
        produktAffix.add("Bildschirm");
        produktAffix.add("Tastatur");
        produktAffix.add("Haustier");
        produktAffix.add("Motorrad");
        produktAffix.add("Moped");
        produktAffix.add("Fahrrad");
        produktAffix.add("Tretroller");
        produktAffix.add("Teppich");
        produktAffix.add("Weinkeller");
        produktAffix.add("Smartphone");
        produktAffix.add("Handy");
        produktAffix.add("Socken");
        produktAffix.add("Brief");
        produktAffix.add("Rucksack");

        produktPostfix.add("hilfmittel");
        produktPostfix.add("ersatzteil");
        produktPostfix.add("gerät");
        produktPostfix.add("reperaturgerät");
        produktPostfix.add("messgerät");
        produktPostfix.add("geschichtenbuch");
        produktPostfix.add("handbuch");
        produktPostfix.add("trockengerät");
        produktPostfix.add("reinigungsmittel");
        produktPostfix.add("vernichtungsmittel");
        produktPostfix.add("versteck");
        produktPostfix.add("besteck");
        produktPostfix.add("bedienhandbuch");
        produktPostfix.add("abdecktuch");
        produktPostfix.add("abwischtuch");
        produktPostfix.add("reinigungstuch");
        produktPostfix.add("lesezeichen");
        produktPostfix.add("objekt");
        produktPostfix.add("schmiermittel");
        produktPostfix.add("mittel");
        produktPostfix.add("putzmittel");
        produktPostfix.add("chronometer");
        produktPostfix.add("blitzgerät");
        produktPostfix.add("alphabet");
    }

        private String makeKontoNr(int stellen) {
            String knr = "";
            for (int i = 0; i <= stellen; i++)
                knr += Integer.toString(rand.nextInt(9));
            return  knr;
        }

        final void testData(Relation type, int numberOfEntrys) throws SQLException {
        switch (type){
            case Firma : {
                Set<Firma> firmenSet = new TreeSet<>();
                while (firmenSet.size() < numberOfEntrys){ //possibly causes problems if someone tries to create more entries than can be generated, this would have to be intercepted in a real world scenario
                    firmenSet.add(new Firma(
                            "'" + companyPrefixes.get(rand.nextInt(companyPrefixes.size())) + companyAffix.get(rand.nextInt(companyAffix.size())) +companyPostfixes.get(rand.nextInt(companyPostfixes.size())) + "'",
                            "'" + "UID-" + Integer.toString(rand.nextInt(100000)) + Integer.toString(rand.nextInt(100000)) + "'",
                            "'" + "FID-" + Integer.toString(rand.nextInt(100000)) + Integer.toString(rand.nextInt(100000)) + "'"
                            )
                    );
                }
                firmenSet.forEach(f -> db.saveDatabaseEntry(Relation.Firma, f));
                db.printTableSize(Relation.Firma.toString());
            } break;
            case Template : {
                Set<Template> templateSet = new TreeSet<>();
                while (templateSet.size() < numberOfEntrys)
                    templateSet.add(new Template(
                            "'" + templateUrlPrefix + templateUrlAffix1.get(rand.nextInt(templateUrlAffix1.size())) + templateUrlAffix2.get(rand.nextInt(templateUrlAffix2.size())) + templateUrlAffix3.get(rand.nextInt(templateUrlAffix3.size())) + templateUrlPostfixes.get(rand.nextInt(templateUrlPostfixes.size())) + "'",
                            "'" + templateLangs.get(rand.nextInt(templateLangs.size())) + "'",
                            "'" + Integer.toString(rand.nextInt(255)) + "." + Integer.toString(rand.nextInt(255)) + "." + Integer.toString(rand.nextInt(255)) + "." + Integer.toString(rand.nextInt(255)) + "'"

                        )
                    );
                templateSet.forEach( t-> db.saveDatabaseEntry(Relation.Template, t));
                db.printTableSize(Relation.Template.toString());
            } break;
            case verwendet: {
                ResultSet rs = null;
                try{
                    rs = db.getStmt().executeQuery("SELECT SteuernummerUID FROM " + Relation.Firma.toString());
                    List<String> stnrList = new ArrayList<>();
                    while (rs.next())
                        stnrList.add("'" + rs.getString("SteuernummerUID") + "'");

                    List<String> urlList = new ArrayList<>();
                    rs = db.getStmt().executeQuery("SELECT URL FROM " + Relation.Template.toString());

                    //System.out.println(rs.getMetaData().getColumnName(1));
                    while (rs.next())
                        urlList.add("'" + rs.getString("URL") + "'");

                    Set<verwendet> verwendetSet =  new TreeSet<>();
                    int cnt = 0;
                    if(urlList.size() > stnrList.size())
                        for (String s : stnrList){
                            verwendetSet.add(new verwendet(urlList.get(cnt++), s));
                            if (cnt >= numberOfEntrys) break;
                        }
                    else for (String u : urlList) {
                        verwendetSet.add(new verwendet(u, stnrList.get(cnt++)));
                        if (cnt > numberOfEntrys) break;
                    }
                    verwendetSet.forEach(v -> db.saveDatabaseEntry(Relation.verwendet, v));
                    db.printTableSize(Relation.verwendet.toString());
                }
                catch (Exception e) {
                    System.err.println("Fehler beim Auslesen des Datensatzes in TestDataGenerator.testData(Relation type, int numberOfEntrys): " + e.getMessage());
                    if (!rs.equals(null)) rs.close();
                }
                finally {
                    if (!rs.equals(null)) rs.close();
                }
            } break;
            case Unterseite: {
                ResultSet rs = null;
                try {
                    List<String> urlList = new ArrayList<>();
                    rs = db.getStmt().executeQuery("SELECT URL FROM " + Relation.verwendet.toString()); //we take from url from verwendet instead of Template so each company that uses a template also gets a shop. makes testing queries more interesting.

                    while (rs.next())
                        urlList.add("'" + rs.getString("URL") + "'");

                    Set<Unterseite> unterseiteSet = new TreeSet<>();

                    int cnt = 0;
                    for (String url : urlList) {
                        unterseiteSet.add(new Unterseite(url,"'" + unterSeitePfadPostfixes.get(rand.nextInt(unterSeitePfadPostfixes.size())) + "'"));
                        if (++cnt > numberOfEntrys) break;
                    }
                    unterseiteSet.forEach(us -> db.saveDatabaseEntry(Relation.Unterseite, us));
                    db.printTableSize(Relation.Unterseite.toString());
                }
                catch (Exception e) {
                    System.err.println("Fehler beim Auslesen des Datensatzes in TestDataGenerator.testData(Relation type, int numberOfEntrys): " + e.getMessage());
                    if (!rs.equals(null)) rs.close();
                }
                finally {
                    if (!rs.equals(null)) rs.close();
                }
            } break;
            case Verkaufsseite: {
                ResultSet rs = null;
                try {
                    List<Verkaufsseite> verkaufsseiteList = new ArrayList<>();
                    //System.out.println(rs.getMetaData().getColumnName(1));
                    rs = db.getStmt().executeQuery("SELECT Firma.Name, Unterseite.URL, Unterseite.Pfad FROM ((Firma INNER JOIN verwendet ON Firma.SteuernummerUID = verwendet.SteuernummerID) INNER JOIN Unterseite ON verwendet.URL = Unterseite.URL)"); //names of companies that have Template which has a Unterseite

                    int cnt = 0;
                    while (rs.next()) {
                        verkaufsseiteList.add(new Verkaufsseite("'" + rs.getString("URL") + "'", "'" + rs.getString("Pfad") + "'", "'" + rs.getString("Name") + verkaufSeiteTitelPostfixes.get(rand.nextInt(verkaufSeiteTitelPostfixes.size())) + "'"));
                        if (++cnt > numberOfEntrys) break;
                    }
                    verkaufsseiteList.forEach( vs -> db.saveDatabaseEntry(Relation.Verkaufsseite, vs));
                    db.printTableSize(Relation.Verkaufsseite.toString());
                }
                catch (Exception e){
                    System.err.println("Fehler beim Auslesen des Datensatzes in TestDataGenerator.testData(Relation type, int numberOfEntrys): " + e.getMessage());
                    if (!rs.equals(null)) rs.close();
                }
                finally {
                    if (!rs.equals(null)) rs.close();
                }
            } break;
            case UserAccount: {
                Set<UserAccount> userAccountSet = new TreeSet<>();
                for (int i = 0; i <= numberOfEntrys; i++)
                    userAccountSet.add(new UserAccount(
                            "'" + userFirstNames.get(rand.nextInt(userFirstNames.size())) + userMiddleNames.get(rand.nextInt(userMiddleNames.size())) + userLastNames.get(rand.nextInt(userLastNames.size())) + userEmailProviders.get(rand.nextInt(userEmailProviders.size())) +"'",
                            "'" + userBankData.get(rand.nextInt(userBankData.size())) +"'",
                            "'" + makeKontoNr(15)  + "'",
                            "'" + userAdressPart1.get(rand.nextInt(userAdressPart1.size())) + userAdressPart2.get(rand.nextInt(userAdressPart2.size())) + " " + Integer.toString(rand.nextInt(150)) + "'"));
                userAccountSet.forEach(ua -> db.saveDatabaseEntry(Relation.UserAccount, ua));
                db.printTableSize(Relation.UserAccount.toString());
            } break;
            case kauftEin:{
                ResultSet rs = null;
                try{
                    rs = db.getStmt().executeQuery("SELECT URL, Pfad FROM " + Relation.Verkaufsseite.toString());
                    List<Pair<String,String>> urlPfadList = new ArrayList<>();
                    List<String> produktIdList = new ArrayList<>();
                    List<String> userIdList =  new ArrayList<>();
                    Set<kauftEin> kauftEinSet = new TreeSet<>();
                    while (rs.next())
                        urlPfadList.add(new Pair<String, String>(rs.getString("URL"), rs.getString("Pfad")));
                    rs = db.getStmt().executeQuery("SELECT ProduktID FROM " + Relation.Produkt.toString());
                    while (rs.next())
                        produktIdList.add(rs.getString("ProduktID"));
                    rs = db.getStmt().executeQuery("SELECT UserID FROM " + Relation.UserAccount.toString());
                    while (rs.next())
                        userIdList.add(rs.getString("UserID"));
                    while (kauftEinSet.size() < numberOfEntrys) {
                        int pos = rand.nextInt(urlPfadList.size());
                        kauftEinSet.add(new kauftEin(
                                "'" + urlPfadList.get(pos).getKey() + "'",
                                "'" +userIdList.get(rand.nextInt(userIdList.size())) + "'",
                                "'" + urlPfadList.get(pos).getValue() + "'",
                                "'" + produktIdList.get(rand.nextInt(produktIdList.size())) + "'",
                                "'" + Integer.toString(rand.nextInt(1000)) + "'"
                            )
                        );
                    }
                    kauftEinSet.forEach(ke -> db.saveDatabaseEntry(Relation.kauftEin, ke));
                    db.printTableSize(Relation.kauftEin.toString());
                }
                catch (Exception e) {
                    System.err.println("Fehler beim Auslesen des Datensatzes in TestDataGenerator.testData(Relation type, int numberOfEntrys): " + e.getMessage());
                    if (!rs.equals(null)) rs.close();
                }
                finally {
                    if (!rs.equals(null)) rs.close();
                }
            } break;
            case Produkt:{
                Set<Produkt> produktSet = new TreeSet<>();
                for (int i = 0; i < numberOfEntrys; i++)
                    produktSet.add(new Produkt(
                            "'" +Integer.toString(rand.nextInt(10000000)) + Integer.toString(rand.nextInt(10000000)) + "'",
                            "'" + Float.toString(rand.nextFloat()*rand.nextInt(100000)) + "'",
                            "'" + produktPrefix.get(rand.nextInt(produktPrefix.size())) +  produktAffix.get(rand.nextInt(produktAffix.size())) + produktPostfix.get(rand.nextInt(produktPostfix.size())) + "'"));
                produktSet.forEach(ps -> db.saveDatabaseEntry(Relation.Produkt, ps) );
                db.printTableSize(Relation.Produkt.toString());
            }; break;
            case befreundet:{
                ResultSet rs = null;
                try{
                    rs = db.getStmt().executeQuery("SELECT UserID FROM " + Relation.UserAccount.toString());
                    List<String> uidsList = new ArrayList<>();
                    Set<befreundet> befreundetSet = new TreeSet<>();
                    int cnt = 0;
                    while (rs.next()) {
                        uidsList.add(rs.getString("UserID"));
                        if (cnt++ > numberOfEntrys) break;
                    }
                    for ( String uid : uidsList)
                        befreundetSet.add(new befreundet(uidsList.get(rand.nextInt(uidsList.size())), uidsList.get(rand.nextInt(uidsList.size()))));
                    befreundetSet.forEach(bf -> db.saveDatabaseEntry(Relation.befreundet, bf));
                    db.printTableSize(Relation.befreundet.toString());
                }
                catch (Exception e) {
                    System.err.println("Fehler beim Auslesen des Datensatzes in TestDataGenerator.testData(Relation type, int numberOfEntrys): " + e.getMessage());
                    if (!rs.equals(null)) rs.close();
                }
                finally {
                    if (!rs.equals(null)) rs.close();
                }
            } break;
            default : throw new IllegalArgumentException("Parameter ungueltig in TestDataGenerator.testData(Relation type, int numberOfEntrys).");
        }
    }
}
