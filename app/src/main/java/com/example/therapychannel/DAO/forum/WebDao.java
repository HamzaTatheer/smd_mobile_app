package com.example.therapychannel.DAO.forum;

import android.net.Uri;
import android.util.Log;

import com.example.therapychannel.DAO.forum.DTO.ProblemDTO;
import com.example.therapychannel.DAO.forum.DTO.ProblemPostDTO;
import com.example.therapychannel.service.forum.IDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class WebDao implements IDAO {

    private static final String api_url = "https://evil-days-post-39-46-76-190.loca.lt";

    private JSONArray parse(String str){
        JSONArray array = null;
        try {
            array = new JSONArray(str);
        } catch (JSONException e) {

        }

        return array;
    }

    @Override
    public ArrayList<ProblemDTO> getProblems() {

        ArrayList<ProblemDTO> problemDTOS = new ArrayList<>();



        URL url = null;
        try {
            url = new URL(api_url+"/getAllProblems");
        } catch (MalformedURLException e) {
            Log.e("TherapyChannel","url Error" + e.getMessage());
            return problemDTOS;
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            Log.e("TherapyChannel","open con Error" + e.getMessage());
            return problemDTOS;
        }

        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            Log.e("TherapyChannel","setGet Error" + e.getMessage());
            return problemDTOS;
        }


        connection.setRequestProperty("Content-type","text/plain");
        connection.setRequestProperty("Bypass-Tunnel-Reminder","true");
        connection.setDoInput(true);

        try {
            connection.connect();
        } catch (IOException e) {
            Log.e("TherapyChannel","connect Error" + e.getMessage());
            return problemDTOS;
        }

        try {
            if (connection.getResponseCode() != 200){
                Log.e("TherapyChannel","Response code is not 200 for getAllProblems");
                return problemDTOS;
            }else{
                Log.e("TherapyChannel","code is 200");
            }
        }
        catch (IOException e){
            Log.e("TherapyChannel","Could not check response code");
            return problemDTOS;
        }

        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            Log.e("TherapyChannel","reader Error" + e.getMessage());
            return problemDTOS;
        }



        try {

            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            Log.e("TherapyChannel","reader no lines Error" + e.getMessage());
            return problemDTOS;

        }




        JSONArray json = parse(content.toString());
        Log.e("TherapyChannel",json.length()+"");

        try {

            for (int i = 0; i < json.length(); i++) {

                JSONObject object = json.getJSONObject(i);

                String name = (String) object.get("name");
                Integer posts_count = (Integer) object.get("posts_count");
                problemDTOS.add(new ProblemDTO(name, posts_count));
            }
        }
        catch (JSONException e){
            Log.e("TherapyChannel","JSON object not good for getProblems");
        }


//        try {
//            ArrayList<String> arr =  (ArrayList<String>)json.get(0);
//            Log.e("TherapyChannel","size"+arr.size());
//
//        } catch (JSONException e) {
//            Log.e("TherapyChannel","Could not convert json to array");
//        }

        return problemDTOS;
    }

    @Override
    public ArrayList<ProblemPostDTO> getProblemPosts(String problem_name) {

        ArrayList<ProblemPostDTO> problemPostDTOS = new ArrayList<>();


        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority(api_url)
                .path("getProblemPosts")
                .appendQueryParameter("problem", problem_name)
                .build();
        String uri_string = "http://"+uri.toString().substring(21);
        Log.e("TherapyChannel",uri_string);



        URL url = null;
        try {
            url = new URL(uri_string);
        } catch (MalformedURLException e) {
            Log.e("TherapyChannel","url Error" + e.getMessage());
            return problemPostDTOS;
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            Log.e("TherapyChannel","open con Error" + e.getMessage());
            return problemPostDTOS;
        }

        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            Log.e("TherapyChannel","setGet Error" + e.getMessage());
            return problemPostDTOS;
        }


        connection.setRequestProperty("Content-type","text/plain");
        connection.setRequestProperty("Bypass-Tunnel-Reminder","true");
        connection.setDoInput(true);

        try {
            connection.connect();
        } catch (IOException e) {
            Log.e("TherapyChannel","connect Error" + e.getMessage());
            return problemPostDTOS;
        }

        try {
            if (connection.getResponseCode() != 200){
                Log.e("TherapyChannel","Response code is not 200 for getAllProblems");
                return problemPostDTOS;
            }else{
                Log.e("TherapyChannel","code is 200");
            }
        }
        catch (IOException e){
            Log.e("TherapyChannel","Could not check response code");
            return problemPostDTOS;
        }

        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            Log.e("TherapyChannel","reader Error" + e.getMessage());
            return problemPostDTOS;
        }



        try {

            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            Log.e("TherapyChannel","reader no lines Error" + e.getMessage());
            return problemPostDTOS;

        }




        JSONArray json = parse(content.toString());
        Log.e("TherapyChannel",json.length()+"");

        try {

            for (int i = 0; i < json.length(); i++) {

                JSONObject object = json.getJSONObject(i);

                String title = (String) object.get("question");
                String body = (String) object.get("answer");
                problemPostDTOS.add(new ProblemPostDTO(title, body));
            }
        }
        catch (JSONException e){
            Log.e("TherapyChannel","JSON object not good for getProblems");
        }



        return problemPostDTOS;
    }

    @Override
    public void createProblemPost(String problem_name, String title, String answer) {

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority(api_url)
                .path("createPostForProblem")
                .build();
        String uri_string = "http://"+uri.toString().substring(21);//  http:// encoded incorrectly so need to add back in correct form
        Log.e("TherapyChannel",uri_string);



        URL url = null;
        try {
            url = new URL(uri_string);
        } catch (MalformedURLException e) {
            Log.e("TherapyChannel","url Error" + e.getMessage());
            return;
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            Log.e("TherapyChannel","open con Error" + e.getMessage());
            return;
        }

        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        try {
            connection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            Log.e("TherapyChannel","setGet Error" + e.getMessage());
            return;
        }


        connection.setRequestProperty("Content-type","application/json");
        connection.setRequestProperty("Bypass-Tunnel-Reminder","true");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json;
        try {
            json = new JSONObject().put("problem",problem_name).put("question",title).put("answer",answer);
        } catch (JSONException e) {
            return;
        }

        Log.e("TherapyChannel",json.toString());

        try {
            writer.write(json.toString());
            writer.flush();
        }catch (IOException e){
            Log.e("TherapyChannel","Could not create body of request" + e.getMessage());
            return;
        }

        try {
            connection.connect();
        } catch (IOException e) {
            Log.e("TherapyChannel","connect Error" + e.getMessage());
            return;
        }

        try {
            if (connection.getResponseCode() != 200){
                Log.e("TherapyChannel","Response code is not 200 for getAllProblems");
                return;
            }else{
                Log.e("TherapyChannel","code is 200");
                return;
            }
        }
        catch (IOException e){
            Log.e("TherapyChannel","Could not check response code");
            return;
        }

    }
}
