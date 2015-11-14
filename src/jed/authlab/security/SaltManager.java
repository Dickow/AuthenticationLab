package jed.authlab.security;

/**
 * Created by Jeppe Dickow on 11/14/2015.
 */
public class SaltManager {
    private static SaltManager ourInstance = new SaltManager();

    public static SaltManager getInstance() {
        return ourInstance;
    }

    private SaltManager() {
    }
    private String[] salts = {"gà?\u0001’œ‹UR`.Y&ÕY;û\u0014\"Ò","G`Í°†Jfý‘¹‹+]!Çˆö\\K-",
            "ï„\u0012ôI\u0001O\u0006ËÌ˜^/\u0010¨yRø®ˆ","úÉˆæ›ûn¿†\u000B?\u00AD@i×–…3Òƒ",
            "n€ÿx?%c\u0010\u000EMÕ\u0010÷©³‡§=¬.","â!zuAvÑ‡?Àâ·mo)¨^)Á>","¯|Ü„ë\u0015t[w\u0011•¸P,,\u001Cÿ¨x"};

    public String getSalt(int index){
        if(index > salts.length){
            throw new IndexOutOfBoundsException(String.format("max index is: %d", salts.length));
        }
        return salts[index];
    }
}
