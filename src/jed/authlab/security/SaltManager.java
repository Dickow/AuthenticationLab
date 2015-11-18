package jed.authlab.security;

/**
 * Created by Jeppe Dickow on 11/14/2015.
 */
public class SaltManager {
    private static SaltManager ourInstance = new SaltManager();
    private String[] salts = {"\u0015溷1a岈?翺喨=们J邐歞", "?p򣀰�洢[�%�,�/u娳�", "\u0002+Q鏶I橳焘m\u001E(�.$\u0007�",
            "婂逴孿u001C氾聾僡痋u0001DN=齒", "\u0015[\t鄪\u0001��>u0017泒謺\u001D\u001E],�", "?wn鯷yK�/?A芢u000Bv\u0004[\\g",
            "|?=b/CK⑿圽u0018划w禚c�#�", "珮梅DY\u0006\\([啡|\u000B� <炠I"};

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
