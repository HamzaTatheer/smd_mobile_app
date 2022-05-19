package com.example.therapychannel.DAO.forum;

import com.example.therapychannel.DAO.forum.MockDao;
import com.example.therapychannel.DAO.forum.WebDao;
import com.example.therapychannel.service.forum.IDAO;

public class DAO {

    static IDAO dao;

    public static void setType(String name){
        if(name.equals("web")){
            dao = new WebDao();
        }

        if(name.equals("mock")){
            dao = new MockDao();
        }
    }

    public static IDAO getDao(){
        return  dao;
    }


}
