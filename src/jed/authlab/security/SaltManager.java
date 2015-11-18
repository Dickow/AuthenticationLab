package jed.authlab.security;

/**
 * Created by Jeppe Dickow on 11/14/2015.
 */
public class SaltManager {
    private static SaltManager ourInstance = new SaltManager();
    private String[] salts = {"\u0015��1a�?�O��=��Jߊ�d", "?p�7�0���[�%�,�/u�ڦ", "\u0002+Q�gI�T��m\u001E(�.$\u0007���",
            "���O�\u001C���@�a�\u0001DN=�X", "\u0015[\t��\u0001��>�\u0017�}֕\u001D\u001E],�", "?wn�[yK�/?A�\u000Bv\u0004[\\g��",
            "|?=b/CK�Ј\u0018��w��c�#�", "��÷DY\u0006\\([��|\u000B� <��I"};

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
