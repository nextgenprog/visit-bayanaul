package ngp.visit;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.XmlReader;

public class Text {
    public static final Array<LocationData> locations = new Array<>();
    public static final IntMap<String> main_title = new IntMap<>();
    public static final IntMap<String> about_button = new IntMap<>();
    public static final IntMap<String> about_title = new IntMap<>();
    public static final IntMap<String> about_text = new IntMap<>();

    public static void init() {
        XmlReader reader = new XmlReader();
        XmlReader.Element root = reader.parse(Gdx.files.getFileHandle("locations.xml", Files.FileType.Internal));
        for (int i = 0; i < root.getChildCount(); i++){
            locations.add(initLoc(root.getChild(i)));
        }

        main_title.put(Style.ENGLISH,"Visit Bayanaul!");
        main_title.put(Style.KAZAKH,"(Kz) Visit Bayanaul!");
        main_title.put(Style.RUSSIAN,"(Ru) Visit Bayanaul!");

        about_button.put(Style.ENGLISH,"About");
        about_button.put(Style.KAZAKH,"(Kz) About");
        about_button.put(Style.RUSSIAN,"(Ru) About");

        about_title.put(Style.ENGLISH,"About This App");
        about_title.put(Style.KAZAKH,"(Kz) About This App");
        about_title.put(Style.RUSSIAN,"(Ru) About This App");

        about_text.put(Style.ENGLISH,"This is an Informative Tourism App developed by " +
                "schoolchildren in Bayanaul \n\n" +
                "Next Generation Programmers is an initiative established between alumni of " +
                "the University of Warwick, in England, Nazarbayev University, in Nursultan, " +
                "and School Number 2, in Bayanaul. \n\n" +
                "Volunteers from UK visit the school and teach kids to program from as early as " +
                "sixth grade. This project was sponsored by the Kazakhstan Ministry of Education, " +
                "etc. \n\n" +
                "Thank you for using our app, and remember to leave a review and comments in " +
                "the App posting");
        about_text.put(Style.KAZAKH,"(Kz) Cool App developed by schoolchildren in Bayanaul");
        about_text.put(Style.RUSSIAN,"(Ru) Cool App developed by schoolchildren in Bayanaul");
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
