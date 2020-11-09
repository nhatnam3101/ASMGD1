package com.example.asmgd1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asmgd1.Adapter.NewsAdapter;
import com.example.asmgd1.Model.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.android.gms.internal.zzip.runOnUiThread;


public class NewsFragment extends Fragment {
    ListView lv;
    NewsAdapter newsAdapter;
    ArrayList<model> ds;
    Toolbar toolbar;
    public NewsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        toolbar=view.findViewById(R.id.tobar);
        lv = view.findViewById(R.id.lv);
        ds = new ArrayList<model>();
        toolbar.setNavigationIcon(R.drawable.ic_newspaper);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("News");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Readdata().execute("https://vnexpress.net/rss/suc-khoe.rss");
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(),xemtin.class);
                i.putExtra("link",ds.get(position).link);
                startActivity(i);
            }
        });
        return view;
    }

    class Readdata extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {

            XMLDomParser parser = new XMLDomParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListdescription = document.getElementsByTagName("description");
            String title = "";
            String link = "";
            String pubDate = "";
            String hinhanh = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeListdescription.item(i + 1).getTextContent();
                Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = pattern.matcher(cdata);
                if (matcher.find()) {
                    hinhanh = matcher.group(1);
                }
                Element element = (Element) nodeList.item(i);
                title += parser.getValue(element, "title");
                link += parser.getValue(element, "link");
                pubDate = parser.getValue(element, "pubDate");
                Log.d("pubDate", pubDate);
                newsAdapter = new NewsAdapter(getContext(), 0, ds);
                newsAdapter.add(new model(title, link, pubDate, hinhanh));
            }
            newsAdapter = new NewsAdapter(getContext(), R.layout.item_news, ds);
            lv.setAdapter(newsAdapter);
            super.onPostExecute(s);
        }

        private String docNoiDung_Tu_URL(String theUrl) {
            StringBuilder content = new StringBuilder();
            try {

                URL url = new URL(theUrl);


                URLConnection urlConnection = url.openConnection();


                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;


                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toString();
        }
    }
}