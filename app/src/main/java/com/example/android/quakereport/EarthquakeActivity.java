/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("earthquake.usgs.gov")
                .appendPath("fdsnws")
                .appendPath("event")
                .appendPath("1")
                .appendPath("query")
                .appendQueryParameter("format","geojson")
                .appendQueryParameter("starttime","2016-01-01")
                .appendQueryParameter("endtime","2016-01-31")
                .appendQueryParameter("minmag","6")
                .appendQueryParameter("limit","10");

        ArrayList<Earthquake> earthquakeList = QueryUtils.fetchEarthquakeList(builder.build().toString());

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // setting up the array adapter
        EarthquakeAdapter itemsAdapter = new EarthquakeAdapter(this, earthquakeList);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(itemsAdapter);
    }
}
