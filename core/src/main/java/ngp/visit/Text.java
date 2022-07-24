package ngp.visit;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.XmlReader;

public class Text {
    public static final Array<LocationData> locations = new Array<>();
    public static final IntMap<String> about_button = new IntMap<>();
    public static final IntMap<String> about_title = new IntMap<>();
    public static final IntMap<String> about_text = new IntMap<>();
    public static final IntMap<String> select_button = new IntMap<>();

    public static void init() {
        XmlReader reader = new XmlReader();
        XmlReader.Element root = reader.parse(Gdx.files.getFileHandle("locations.xml", Files.FileType.Internal));
        for (int i = 0; i < root.getChildCount(); i++){
            locations.add(initLoc(root.getChild(i)));
        }

        about_button.put(Style.ENGLISH,"About");
        about_button.put(Style.KAZAKH,"(Kz) About");
        about_button.put(Style.RUSSIAN,"(Ru) About");

        select_button.put(Style.ENGLISH,"Take me to..");
        select_button.put(Style.KAZAKH,"(Kz) Select");
        select_button.put(Style.RUSSIAN,"(Ru) Select");

        about_title.put(Style.ENGLISH,"About This App");
        about_title.put(Style.KAZAKH,"(Kz) About This App");
        about_title.put(Style.RUSSIAN,"(Ru) About This App");

        about_text.put(Style.ENGLISH,"This is an Informative Tourism App developed by " +
                "schoolchildren in Bayanaul. " +
                "\n\n" +
                "Next Generation Programmers is an initiative established between alumni of " +
                "the University of Warwick, in England, Nazarbayev University, in Nursultan, " +
                "and School Number 2, in Bayanaul. " +
                "\n\n" +
                "Volunteers from UK visit the school and teach kids to program from as early as " +
                "sixth grade. This project was sponsored by the Kazakhstan Ministry of Education." +
                "\n\n" +
                "Thank you for using our app, and remember to leave a review and comments in " +
                "the App posting");
        about_text.put(Style.KAZAKH,"Табиғаты әсем Баянауыл өлкесіне жыл сайын көптеген туристтер  келіп тұрады. Алғаш рет бұл жерге аяқ басқан адамдар үшін тамашалауға болатын орындарды табу қиындау. Сол себепті біз бұл қосымшаны ойлап таптық.\n" +
                "\"Visit Bayanaul\" қосымшасында: \n" +
                "1) локациялардың фотосуреттері;\n" +
                "2) 3 тілде жазылған ақпарат;\n" +
                "3) Google map қосымшасы арқылы орынға қалай баруға болатынын білуге болады.\n" +
                "\n" +
                "Әрбір локацияның бетінде google map қосымшасына өткізетін батырма орналасқан.\n" +
                "\n" +
                "Бұл қосымшаның интерфейсі оңай болғандықтан, барлық адам қиындықсыз қолдана алады.\n" +
                "\n" +
                "Бастапқы бетте Баянауыл картасы бейнелеген. Картаның үстіндегі белгілерді басу арқылы локацияларды аша аламыз.");
        about_text.put(Style.RUSSIAN,"Баянаул отличное место для отдыха. Но для того чтобы  посетить" +
                " самые красивые и необычные места можно воспользоваться приложением \"Visit Bayanaul\"." +
                " \n\nВ приложении отобраны самые красивые места национального природного парка Баянаул. " +
                "В приложении  имеется карта Баянаула с иконками, при нажати на них вам открываютя фото этих мест и информация.\n\n" +
                "Информация открывается на трёх языках");
    }

    public static LocationData initLoc(XmlReader.Element child){
        if (child.getName().equalsIgnoreCase("location")) {
            try{String folder = child.getAttribute("images");
                try{String url = child.getAttribute("mapUrl");
                    try{String icon = child.getAttribute("icon");
                        try{float x = child.getFloatAttribute("x");
                            try{float y = child.getFloatAttribute("y");
                                IntMap<String> names = new IntMap<>();
                                IntMap<String> texts = new IntMap<>();
                                for (int j = 0; j < child.getChildCount(); j++){
                                    XmlReader.Element grandchild = child.getChild(j);
                                    try{String lang = grandchild.get("language");
                                        int langCode = Style.languages.get(lang);
                                        names.put(langCode,grandchild.getAttribute("name"));
                                        texts.put(langCode,grandchild.getText());
                                    }catch (Exception e){}
                                }
                                return new LocationData(folder, icon, new Vector2(x, y), url, names, texts);
                            }catch (Exception e){}
                        }catch (Exception e){}
                    }catch (Exception e){}
                }catch (Exception e){}
            }catch (Exception e){}
        }
        return null;
    }
}
