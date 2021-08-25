package com.example.sqlitelearn;

import java.util.Date;
import java.util.UUID;

public class Student {
    private UUID mUUID;
    private String mName;
    private Date mBirthDate;
    private Boolean mGender;

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Date getBirthDate() {
        return mBirthDate;
    }

    public void setBirthDate(Date birthDate) {
        mBirthDate = birthDate;
    }

    public Boolean getGender() {
        return mGender;
    }

    public void setGender(Boolean mgender) {
        this.mGender = mgender;
    }
}
