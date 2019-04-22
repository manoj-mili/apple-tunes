package com.demo.musicwiki.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "MUSIC_TABLE")
public class Music {

    @PrimaryKey
    private Integer trackId;
    private String kind,artistName,collectionName,trackName,collectionCensoredName,
            artistViewUrl,trackViewUrl,previewUrl,artworkUrl100,
            releaseDate,country,currency,primaryGenreName,isStreamable;

    private Integer artistId,collectionId,trackTimeMillis,trackNumber,trackCount;


    /*private String wrapperType,collectionViewUrl,artworkUrl30,artworkUrl60,collectionExplicitness,trackExplicitness,discCount,trackCensoredName;

    private Integer discNumber;*/



    private Double collectionPrice,trackPrice;

    /*public Music(String wrapperType, String kind, String artistName, String collectionName, String trackName, String collectionCensoredName,
                 String trackCensoredName, String artistViewUrl, String collectionViewUrl, String trackViewUrl, String previewUrl, String artworkUrl30, String artworkUrl60, String artworkUrl100, String releaseDate, String collectionExplicitness, String trackExplicitness, String discCount,
                 String country, String currency, String primaryGenreName, String isStreamable,
                 Integer artistId, Integer collectionId, Integer trackId, Integer discNumber, Integer trackTimeMillis, Integer trackNumber, Integer trackCount, Double collectionPrice, Double trackPrice) {

        this.kind = kind;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackName = trackName;
        this.collectionCensoredName = collectionCensoredName;
        this.trackCensoredName = trackCensoredName;
        this.artistViewUrl = artistViewUrl;

        this.trackViewUrl = trackViewUrl;
        this.previewUrl = previewUrl;
        this.artworkUrl100 = artworkUrl100;
        this.releaseDate = releaseDate;

        this.country = country;
        this.currency = currency;
        this.primaryGenreName = primaryGenreName;
        this.isStreamable = isStreamable;
        this.artistId = artistId;
        this.collectionId = collectionId;
        this.trackId = trackId;

        this.trackTimeMillis = trackTimeMillis;
        this.trackNumber = trackNumber;
        this.trackCount = trackCount;
        this.collectionPrice = collectionPrice;
        this.trackPrice = trackPrice;

        *//*this.collectionExplicitness = collectionExplicitness;
        this.trackExplicitness = trackExplicitness;
        this.discCount = discCount;
        this.discNumber = discNumber;
        this.artworkUrl30 = artworkUrl30;
        this.artworkUrl60 = artworkUrl60;
        this.wrapperType = wrapperType;
        this.collectionViewUrl = collectionViewUrl;*//*
    }*/

    public Music() {
    }

    /*public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }*/

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    /*public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }*/

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    /*public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }*/

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /*public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }*/

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /*public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    public String getDiscCount() {
        return discCount;
    }

    public void setDiscCount(String discCount) {
        this.discCount = discCount;
    }*/

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getIsStreamable() {
        return isStreamable;
    }

    public void setIsStreamable(String isStreamable) {
        this.isStreamable = isStreamable;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    /*public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }*/

    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public Double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public Double getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(Double trackPrice) {
        this.trackPrice = trackPrice;
    }
}
