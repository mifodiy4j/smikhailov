package ru.job4j.musicCourt.domain;

public class MusicType extends Model {

    private String musicType;

    public MusicType(int id) {
        super(id);
    }

    public MusicType() {
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

}
