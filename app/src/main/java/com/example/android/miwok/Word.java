package com.example.android.miwok;

/**
 * Created by mukund.jogi on 12/9/17.
 */

class Word {

    private String defaultTranslation;
    private String miwokTrasnslation;
    private int imgageIdRes=NOIMAGE;
    private static final int NOIMAGE=-1;
    private int miwokAudioId ;


    public Word(String defaultTran,String miwokTran,int miwokAudio)
    {
        defaultTranslation = defaultTran;
        miwokTrasnslation = miwokTran;
        miwokAudioId = miwokAudio;
    }

    public Word(String defaultTran, String miwokTran, int image_number, int miwokAudio)
    {
        defaultTranslation = defaultTran;
        miwokTrasnslation = miwokTran;
        imgageIdRes = image_number;
        miwokAudioId = miwokAudio;
    }


    public String getDefaultTranslation()
    {
        return defaultTranslation;
    }

    public String getMiwokTranslation()
    {
        return miwokTrasnslation;
    }

    public int getImgageIdRes() { return imgageIdRes; }

    public boolean hasImage()
    {
        return imgageIdRes!=NOIMAGE;
    }

    public int getMiwokAudioId() {return miwokAudioId; }
}
