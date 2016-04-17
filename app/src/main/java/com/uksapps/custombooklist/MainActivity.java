package com.uksapps.custombooklist;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Main initialisation of various variables that are to be used in the custom items
    String[] names ={"Angels and Demons", "His Dark Materials", "Chronicles of Narnia", "Harry Potter and the Deathly Hallows"};
    String[] author ={"Dan Brown", "Philip Pullman","C.S. Lewis", "J.K. Rowling"};
    String[] publisher ={"Pocket Books","Scholastic Corp.", "Harper and Collins","Bloomsbury"};
    int[] images ={R.drawable.angels_and_demons,R.drawable.his_dark_materials,R.drawable.chronicles_of_narnia,R.drawable.harry_potter_7};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lView = (ListView)findViewById(R.id.lView);
       final EditText etSearch = (EditText) findViewById(R.id.etSearch);
        final TextView tvResult = (TextView)findViewById(R.id.tvResult);//is used to show no. of search results

        final CustomAdapter adapt = new CustomAdapter(this, getBooks(), tvResult,lView);
        lView.setAdapter(adapt);




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


            }
        });



    }



    //Combining all the above arrays into an Array List
    private ArrayList<Book> getBooks(){
        ArrayList<Book> books = new ArrayList<Book>();
        Book b;
        for (int i=0; i<names.length;i++)
        { b = new Book(names[i],author[i],publisher[i],images[i]);
            books.add(b);}
      return books;
    }
}
