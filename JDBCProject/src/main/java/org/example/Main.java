package org.example;
public class Main {
    public static void main(String[] args) {
            DBOperations obj = new DBOperations();
            obj.doConnectDB();
            //obj.fetchData();
            obj.fetchDataForMatch();
        }
    }