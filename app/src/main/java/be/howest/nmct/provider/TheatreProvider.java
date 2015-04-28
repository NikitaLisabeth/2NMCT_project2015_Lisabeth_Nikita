package be.howest.nmct.provider;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import be.howest.nmct.Model.Theatre;

/**
 * Created by Nikita on 16/04/2015.
 */
public class TheatreProvider {
    private static TheatreProvider instance = new TheatreProvider();
    private static List<Theatre> theatresWestEnd;

    public static TheatreProvider getInstance(){
        return instance;
    }

    private TheatreProvider(){
        loadTheatres();
    }

    public static Theatre getTheatresWestEnd(String name){
        for (Theatre t : getTheatresWestEnd()){
            if(t.getName().equals(name)){
                return t;
            }
        }
        return null;
    }
    public static List<Theatre> getTheatresWestEnd() {
        return theatresWestEnd;
    }

    private void loadTheatres(){
        /*private String name;
        private String address;
        private String currentMusical;
        private String stageDoor;
        private LatLng location;*/
        theatresWestEnd = new ArrayList<>();
        Theatre AldwychTheatre = new Theatre("Aldwych Theatre",
                " 49 Aldwych, London, WC2B 4DF","Beautiful, the Carol King Musical",
                "You go out of the theatre. Go left into Dury Lane. At your left side you find the stagedoor.",
                new LatLng(51.512946,-0.118634));
        theatresWestEnd.add(AldwychTheatre);
        Theatre PhoenixTheatre = new Theatre("Phoenix Theatre",
                "Charing Cross Road, London, WC2H 0JP",
                "Bend it like Beckham",
                "When you exit the theatre, you will come out of one of two entrances: the main one on Charing Cross Road, or the side entrance on Phoenix Street. Upon exiting on Charing Cross Road, turn left until you see Phoenix Street and the side entrance, and proceed until the end of the street.  At the end of the street, take another left into Stacey Street, the road on which the stage door itself is located, and walk until the very end of the street where you will come to a garage door. The stage door will be on your left.",
                new LatLng(51.51433,-0.129527));
        theatresWestEnd.add(PhoenixTheatre);
        Theatre VictoriaPalaceTheatre = new Theatre("Victoria Palace Theatre",
                "Victoria Street, London, SW1E 5EA",
                "Billy Elliot",
                "",
                new LatLng(51.496666,-0.142506));
        theatresWestEnd.add(VictoriaPalaceTheatre);
        Theatre PrinceOfWalesTheatre = new Theatre("Prince of Wales Theatre",
                "31 Coventry Street, London, W1D 6AS",
                "Book of Mormon",
                "Go outside the theatre. Go left in Oxendon Street. Go into the first street left (Whitcomb Street). At the end of the street at your left hand you can find the stagedoor",
                new LatLng(51.510212,-0.132024));
        theatresWestEnd.add(PrinceOfWalesTheatre);
        Theatre LondonPalladium = new Theatre("London Palladium",
                "8 Argyll Street, London, W1F 7TF",
                "Cats",
                "Turn left and walk down Argyll Street towards Liberty. When you reach Liberty, turn a left and walk down the street past the massive sign adversity the show currently running at the theatre, until you read the iron gates marked: ‘LONDON PALLADIUM’.",
                new LatLng(51.514526,-0.140576));
        theatresWestEnd.add(LondonPalladium);
        Theatre QueensTheatre = new Theatre("Queens Theatre",
                "51 Shaftesbury Avenue, London, W1D 6BA",
                "Les Misérables",
                "Go outside the theatre. Take the first street left (Wardour Street). The first street left is Winnett Street, here you can find the stagedoor",
                new LatLng(51.511817,-0.132619));
        theatresWestEnd.add(QueensTheatre);
        Theatre MajestysTheatre = new Theatre("Majesty's Theatre",
                "57 Haymarket, London, SW1Y 4QL",
                "The Phantom of the Opera",
                "Go outside the theatre, go left (Charles II Street). Take the first street left, there you'll find the stagedoor ",
                new LatLng(51.508276,-0.132321));
        theatresWestEnd.add(MajestysTheatre);
        Theatre ApolloVictoriaTheatre = new Theatre("Apollo Victoria Theatre",
                "17 Wilton Road, London, SW1V 1LG",
                "Wicked",
                "Turn left and walk about 100 yards.",
                new LatLng(51.49563,-0.142922));
        theatresWestEnd.add(ApolloVictoriaTheatre);
        Theatre PrinceEdwardTheatre = new Theatre("Prince Edward Theatre",
                "28 Old Compton Street, London, W1D 4HS",
                "Miss Saigon",
                "Leave the theatre, go to right. You take the first road left (Frith Street). At your right side you see the stagedoor.",
                new LatLng(51.513471,-0.130778));
        theatresWestEnd.add(PrinceEdwardTheatre);
        Theatre SavoyTheatre = new Theatre("Savoy Theatre",
                "Strand, London, WC2R 0ET",
                "Gypsy",
                "No matter where the theatre spits you out, always go right until you reach the black gates. ",
                new LatLng(51.510116,-0.120538));
        theatresWestEnd.add(SavoyTheatre);
        Theatre PiccadillyTheatre = new Theatre("Piccadilly Theatre",
                "16 Denman Street, London, W1D 7DY",
                "Jersey Boys",
                "Go left, take the first street left (Denman Street). Go right until you see a black fence. That's the stagedoor.",
                new LatLng(51.5108825,-0.1355742));
        theatresWestEnd.add(PiccadillyTheatre);
        Theatre ShaftesburyTheatre = new Theatre("Shaftesbury Theatre",
                "210 Shaftesbury Avenue, London, WC2H 8DP",
                "Memphis",
                "Go outside the theatre and take High Holhorn. Take the first street at your left side. Just around the corner is the stagedoor.",
                new LatLng(51.516019,-0.126005));
         theatresWestEnd.add(ShaftesburyTheatre);
        Theatre CambridgeTheatre = new Theatre("Cambridge Theatre",
                "32-34 Earlham Street, London, WC2H 9HU",
                "Matilda",
                "Go outside the theatre and take Mercer Street. Take the first street at your left side (Shelton Street). Just around the corner is the stagedoor.",
                new LatLng(51.513598,-0.126751));
        theatresWestEnd.add(CambridgeTheatre);

        Collections.sort(theatresWestEnd, new Comparator<Theatre>() {
            @Override
            public int compare(Theatre th1, Theatre th2) {
                return th1.getName().compareTo(th2.getName());
            }
        });
    }
}
