package jed.authlab.security;

/**
 * Created by Jeppe Dickow on 11/14/2015.
 */
public class SaltManager {
    private static SaltManager ourInstance = new SaltManager();
    private String[] salts = {"\u0015äã1aá¬?ÂO†È=ÃÇJßŠšd", "?p¿7ó0ÿ›¥[º%ë,ƒ/uŠÚ¦", "\u0002+QçgI™Tìâm\u001E(Ñ.$\u0007üéú",
            "ŠåßOŒ\u001CšïÃ@ƒa¯\u0001DN=ýX", "\u0015[\tàŠ\u0001ÿ¶>¦\u0017›}Ö•\u001D\u001E],‹", "?wnö[yKó/?AÆ\u000Bv\u0004[\\g¦€",
            "|?=b/CK¢Ðˆ\u0018»®wìúc÷#‡", "«˜Ã·DY\u0006\\([·È|\u000BÙ <žÙI"};

    private SaltManager() {
    }

    public static SaltManager getInstance() {
        return ourInstance;
    }

    public String getSalt(int index){
        if(index > salts.length){
            throw new IndexOutOfBoundsException(String.format("max index is: %d", salts.length));
        }
        return salts[index];
    }
}
