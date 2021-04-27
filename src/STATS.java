public class STATS {

    public static int _NUMENEMIES;

    public static int _FIRERATE;

    public static int _EMOVEMENT;

    public static int _LEVEL;

    public static void setLevel(int level){
        switch (_LEVEL){
            case 0:
                _NUMENEMIES = 3;
                _FIRERATE = 5;
                _EMOVEMENT = 3;
                break;

            case 1:
                _NUMENEMIES = 4;
                _FIRERATE = 15;
                _EMOVEMENT = 4;
            break;

            default: break;
            }
        }





}
