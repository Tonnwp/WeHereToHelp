package ku.cs.directory.services;

import ku.cs.directory.models.AdminProfileList;

import ku.cs.directory.models.AdminProfile;

public class AdminProfileHardCodeDataSource {
    private AdminProfileList adminList;

    public AdminProfileHardCodeDataSource() {
        adminList = new AdminProfileList();
        readData();
    }

    public void readData() {
        adminList.addProfile(new AdminProfile("Nawapon Leelanawalikhit", "6410450893", "Ton"));
        adminList.addProfile(new AdminProfile("Sittichai Panplod", "6410451466", "Folk"));
        adminList.addProfile(new AdminProfile("Kanchanok Nanual", "6410450699", "Kaohom"));
        adminList.addProfile(new AdminProfile("Atthakorn Kuasakul", "6410451539", "Rom"));

    }

    public AdminProfileList getProfileList() {
        return adminList;
    }
}
