package com.uksapps.custombooklist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Main initialisation of various variables that are to be used in the custom items
    String[] names ={"Angels and Demons", "His Dark Materials", "Chronicles of Narnia", "Harry Potter and the Deathly Hallows"};
    String[] author ={"Dan Brown", "Philip Pullman","C.S. Lewis", "J.K. Rowling"};
    String[] publisher ={"Pocket Books","Scholastic Corp.", "Harper and Collins","Bloomsbury"};
    int[] images ={R.drawable.angels_and_demons,R.drawable.his_dark_materials,R.drawable.chronicles_of_narnia,
            R.drawable.harry_potter_7};
    String[] url = {"http://www.amazon.in/Angels-Demons-Robert-Langdon-Brown/dp/0552161268",
            "http://www.amazon.in/Materials-Yearling-3-book-Boxed-Paperback/dp/0440419514",
            "http://www.amazon.in/Chronicles-Narnia-adult-C-Lewis/dp/0066238501",
            "http://www.amazon.in/Harry-Potter-Deathly-Hallows/dp/1408855712"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lView = (ListView)findViewById(R.id.lView);
       final EditText etSearch = (EditText) findViewById(R.id.etSearch);
        final TextView tvResult = (TextView)findViewById(R.id.tvResult);//is used to show no. of search results

        final CustomAdapter adapt = new CustomAdapter(this, getBooks(), tvResult,lView);
        try{lView.setAdapter(adapt);}catch (Exception e){e.fillInStackTrace();}




        //Setting up the search view
        assert etSearch != null;
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence query, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                adapt.getFilter().filter(query);


            }

            @Override
            public void afterTextChanged(Editable query) {
                //Opens up the shopping link for the books of the FILTERED list
                lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MainActivity.this,WebActivity.class);
                        i.putExtra("Address",getBooks().get(position).getUrl());
                        i.putExtra("Name",getBooks().get(position).getNames());
                        startActivity(i);

                    }
                });


            }
        });

       //Opens up the shopping link for the books of the UNFILTERED list
       lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent i = new Intent(MainActivity.this,WebActivity.class);
               i.putExtra("Address",getBooks().get(position).getUrl());
               i.putExtra("Name",getBooks().get(position).getNames());
               startActivity(i);

           }
       });

    }



    //Combining all the above arrays into an Array List
    private ArrayList<Book> getBooks(){
        ArrayList<Book> books = new ArrayList<Book>();
        Book b;
        for (int i=0; i<names.length;i++)
        { b = new Book(names[i],author[i],publisher[i],images[i],url[i]);
            books.add(b);}
      return books;
    }
}
